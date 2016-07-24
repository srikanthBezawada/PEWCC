package org.cytoscape.pewcc.internal.logic;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.model.subnetwork.CyRootNetwork;
import org.cytoscape.model.subnetwork.CySubNetwork;
import org.cytoscape.pewcc.internal.PEWCCapp;
import org.cytoscape.view.model.CyNetworkView;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

public class PEWCClogic extends Thread {
    CyNetwork network;
    CyNetworkView networkView;
    PEWCCapp pewccapp;
    double joinPValue, overlapValue;
    int cliqueNumber;
    boolean stop;
    
    public PEWCClogic(PEWCCapp pewccapp, CyNetwork currentnetwork, CyNetworkView currentnetworkview, int cliqueNumber, double joinPValue, double overlapValue) {
        this.pewccapp = pewccapp;
        this.network = currentnetwork;
        this.networkView = currentnetworkview;
        this.joinPValue = joinPValue;
        this.cliqueNumber = cliqueNumber;
        this.overlapValue = overlapValue;
    }
    
    public void run(){
        stop = false;
        pewccapp.getGui().startComputation();
        long startTime = System.currentTimeMillis();
        CyRootNetwork root = ((CySubNetwork)network).getRootNetwork();
        CyNetwork subNet, subNetTemp;
        List<CyNode> nodeList = network.getNodeList();
        List<CyNode> remnodeList = new ArrayList<CyNode>();
        List<CyNode> rejoinList = new ArrayList<CyNode>();
        List<CyEdge> edgeList = network.getEdgeList();
        List<CyNode> neighbourNodeList;
        List<CyEdge> neightbourEdgeList;
        int counter = 0;
        double wcc = 0.0;
       
        Set<PEWCCCluster> clusters = new HashSet<PEWCCCluster>();
        for(CyNode cprotein : nodeList) {
            if(stop) {
                return;
            }
            neighbourNodeList = network.getNeighborList(cprotein, CyEdge.Type.ANY);
            neighbourNodeList.add(cprotein);
            if(neighbourNodeList.size() > 3) {
                neightbourEdgeList =  findNeighbourEdges(edgeList, neighbourNodeList);
                subNet = root.addSubNetwork(neighbourNodeList, neightbourEdgeList);
                subNetTemp = root.addSubNetwork(neighbourNodeList, neightbourEdgeList);
                
                while(subNetTemp.getNodeCount() > 3) {
                    //wcc = findwcc(subNetTemp, cprotein);
                    remnodeList.add(findNodeToRemove(subNetTemp, cprotein));
                    subNetTemp.removeNodes(remnodeList);
                    remnodeList.clear();
                }
                
                for(CyNode n : neighbourNodeList) {
                    counter = 0;
                    if(subNetTemp.containsNode(n)) {
                        continue;
                    }
                    for(CyNode x : subNetTemp.getNodeList()) {
                        if(subNet.containsEdge(x, n) || subNet.containsEdge(x, n)) {
                            counter++;
                        }
                    }

                    if(counter/(double)(subNetTemp.getNodeCount()) > joinPValue){
                        rejoinList.add(n);
                    }
                    
                }
                
                for(CyNode n: neighbourNodeList) {
                    if(subNetTemp.containsNode(n)) {
                        continue;
                    }
                    if(rejoinList.contains(n)){
                        continue;
                    }
                    Set<CyNode> coll = new HashSet<CyNode>();
                    coll.add(n);
                    subNet.removeNodes(coll); 
                    coll.clear();
                }
                /*
                System.out.println("Complex");
                for(CyNode n:subNet.getNodeList()) {
                    printNode(subNet, n);
                }
                System.out.println(" : "+ wcc);
                System.out.println("Complex");
                */
                PEWCCCluster C = new PEWCCCluster(subNet);
                if(subNet.getEdgeList().size() >= 3)
                clusters.add(C);
                
                neighbourNodeList.clear();
                neightbourEdgeList.clear();
                rejoinList.clear();
                remnodeList.clear();
                subNetTemp.dispose();
            } 
            

        }
        /*
        System.out.println(" Printing complexes Start after removing duplicates------");
        for(PEWCCCluster c : result.getComplexes()) {
            System.out.println("------");
            for(CyNode n : c.getNodes()) {
                System.out.println(network.getRow(n).get(CyNetwork.NAME, String.class));
            }
            //System.out.println("wcc : "+c.getwcc());
            //System.out.println("------");
        }
        System.out.println(" End -------");
        */
        
        if(stop) {
            return;
        }
        Set<PEWCCCluster> newClusters = merge(clusters);
        if(newClusters == null) {
            return;
        }
        if(stop) {
            return;
        }
        pewccapp.resultsCalculated(newClusters, network);
        
        long endTime = System.currentTimeMillis();
        long difference = endTime - startTime;
        System.out.println("Execution time for PEWCC algo: " + difference +" milli seconds");
        pewccapp.getGui().endComputation();
        
    }
    
    
    public List<CyEdge> findNeighbourEdges(List<CyEdge> edgeList, List<CyNode> neightbourNodes) {
        List<CyEdge> neighbourEges = new ArrayList<CyEdge>();
        for(CyEdge e : edgeList) {
            if(neightbourNodes.contains(e.getSource()) && neightbourNodes.contains(e.getTarget())) 
                neighbourEges.add(e);
        }
        
        return neighbourEges;
    }


    public double findwcc(CyNetwork tempNet, CyNode centerNode) { // finds weighted clustering coefficient
        List<CyNode> tnList = tempNet.getNodeList();
        List<CyEdge> teList = tempNet.getEdgeList();
        int cliques = 0;
        double result;
        Set<ThreeClique> cliqueSet = new HashSet<ThreeClique>();
        for(CyEdge e:teList) {
            for(CyNode n:tnList) {
                if(n.equals(e.getSource()) || n.equals(e.getTarget())) {
                    continue;
                }
                
                if(tempNet.containsEdge(n, e.getSource()) || tempNet.containsEdge(e.getSource(), n)) {
                    if(tempNet.containsEdge(n, e.getTarget()) || tempNet.containsEdge(e.getTarget(), n)) {
                        if(n.equals(centerNode) || e.getSource().equals(centerNode) || e.getTarget().equals(centerNode)){
                            cliqueSet.add(new ThreeClique(n, e.getSource(), e.getTarget()));
                        }
                    }
                }
            }
        }
        cliques = cliqueSet.size();
        //System.out.println(cliques);
        double ni = tempNet.getNeighborList(centerNode, CyEdge.Type.ANY).size();
        ni = (ni*ni)*(ni - 1);
        result = (2*cliques)/(ni);
        DecimalFormat df = new DecimalFormat("####0.00");
        return Double.parseDouble(df.format(result));
    }

    public CyNode findNodeToRemove(CyNetwork tempNet, CyNode cp){
        CyNode toRemove = tempNet.getNodeList().get(1);
        for(CyNode n:tempNet.getNodeList()) {
            if(n.equals(cp))
                continue; // verify this
            if(tempNet.getNeighborList(n, CyEdge.Type.ANY).size() < tempNet.getNeighborList(toRemove, CyEdge.Type.ANY).size()) {
                toRemove = n;
            }
        }
        return toRemove;
    }
    /*
    public void printNode(CyNetwork network, CyNode n) {
        System.out.println("node" +network.getRow(n).get(CyNetwork.NAME, String.class));
    }
    */
    
    
    public Set<PEWCCCluster> merge(Set<PEWCCCluster> clusters) {
        Set<PEWCCCluster> newClusters = new HashSet<PEWCCCluster>();
        List<PEWCCCluster> unmergedLists = new ArrayList<PEWCCCluster>();
        unmergedLists.addAll(clusters);
        
        UndirectedGraph<PEWCCCluster, DefaultEdge> g = new SimpleGraph<PEWCCCluster, DefaultEdge>(DefaultEdge.class);
        for(PEWCCCluster cluster : clusters) {
            g.addVertex(cluster);
        }
        
        Iterator<PEWCCCluster> outer = unmergedLists.iterator();
        if(stop) {
            return null;
        }
        while(outer.hasNext()) {
            if(stop) {
                return null;
            }
            PEWCCCluster C1 = outer.next();
            Iterator<PEWCCCluster> inner = unmergedLists.iterator();
            
            while(inner.hasNext()) {
                if(stop) {
                    return null;
                }
                PEWCCCluster C2 = inner.next();
                
                if(C1.equals(C2)){
                    continue;
                }
                
                if(matchCoefficient(C1, C2) > overlapValue) {
                    g.addEdge(C1, C2);
                }
            }
        }
        
        
        
        ConnectivityInspector<PEWCCCluster, DefaultEdge> inspector = new ConnectivityInspector<PEWCCCluster, DefaultEdge>(g);
        List<Set<PEWCCCluster>> connComponents = inspector.connectedSets();
        for(Set<PEWCCCluster> component : connComponents) {
            if(component.isEmpty() == false)
            newClusters.add(mergeComponent(component));
        }
        
        return newClusters;
    }
        
    
    public double matchCoefficient(PEWCCCluster C1, PEWCCCluster C2) {
        double inter = intersection(C1.getNodes(), C2.getNodes()).size();
        double matchCoeff = (inter * inter)/(C1.getNodes().size() * C2.getNodes().size());
    
        return matchCoeff;
    }
    
    /*
    public Set<PEWCCCluster> merge(Set<PEWCCCluster> clusters){
        double merge_threshold = 0.75;
        double filter_threshold = 0.20;
        CyRootNetwork root = ((CySubNetwork)network).getRootNetwork();
        
        List<PEWCCCluster> unmergedLists = new ArrayList<PEWCCCluster>();
        unmergedLists.addAll(clusters);
        
        Set<PEWCCCluster> newClusters = new HashSet<PEWCCCluster>();
        Set<PEWCCCluster> toRemove = new HashSet<PEWCCCluster>();
        Set<CyNode> mergedNodeSets;
        Set<CyEdge> mergedEdgeSets;
        List<CyNode> nodesToRemove = new ArrayList<CyNode>();
        CyNetwork mergedsubNet;
        PEWCCCluster mergedCluster, smallerComplex;
        newClusters.addAll(clusters);
        Iterator<PEWCCCluster> outer = unmergedLists.iterator();
        if(stop) {
            return null;
        }
        while(outer.hasNext()) {
            if(stop) {
                return null;
            }
            PEWCCCluster C1 = outer.next();
            Iterator<PEWCCCluster> inner = unmergedLists.iterator();
            
            while(inner.hasNext()) {
                if(stop) {
                    return null;
                }
                PEWCCCluster C2 = inner.next();
                
                if(C1.equals(C2)){
                    continue;
                }
                
                if(C1.getNodes().size() <= C2.getNodes().size()) {
                    smallerComplex = C1;
                } else {
                    smallerComplex = C2;
                }
                
                mergedNodeSets = intersection(C1.getNodes(), C2.getNodes());
                
                if(mergedNodeSets.size() >= smallerComplex.getNodes().size() * merge_threshold) {
                    mergedNodeSets = nodesUnion(C1.getNodes(), C2.getNodes());
                    mergedEdgeSets = edgesUnion(C1.getEdges(), C2.getEdges());
                    mergedsubNet = root.addSubNetwork(mergedNodeSets, mergedEdgeSets);
                
                    double cutoff = mergedNodeSets.size() * filter_threshold;
                    for(CyNode n : mergedNodeSets) {
                        if(mergedsubNet.getNeighborList(n, CyEdge.Type.ANY).size() < cutoff) {
                            nodesToRemove.add(n);
                        }
                    }
                    
                    mergedsubNet.removeNodes(nodesToRemove);
                    mergedCluster = new PEWCCCluster(mergedsubNet);
                    newClusters.add(mergedCluster);
                    toRemove.add(C1);
                    toRemove.add(C2);
                    nodesToRemove.clear();
                } else{
                    
                }
            }
        }
        
        newClusters.removeAll(toRemove);
        return newClusters;
    }
    */
    
    public PEWCCCluster mergeComponent(Set<PEWCCCluster> component) {
        Set<CyNode> nodesUnion = new HashSet<CyNode>();
        Set<CyEdge> edgesUnion = new HashSet<CyEdge>();
        CyRootNetwork root = ((CySubNetwork)network).getRootNetwork();
        if(component.isEmpty()) {
            return null;
        }
        for(PEWCCCluster C : component) {
            if(component.size() == 1) {
                return C;
            } else{
                nodesUnion.addAll(C.getNodes());
                edgesUnion.addAll(C.getEdges());
                CyNetwork mergedNetwork = root.addSubNetwork(nodesUnion, edgesUnion);
                PEWCCCluster mergedCluster = new PEWCCCluster(mergedNetwork);
                return mergedCluster;
            }
        }
        return null;
    }
    
    public Set<CyNode> intersection(List<CyNode> setA, List<CyNode> setB) {
        Set<CyNode> tmp = new HashSet<CyNode>();
        for (CyNode x : setA) {
            if (setB.contains(x)) {
                tmp.add(x);
            }
        }
        return tmp;    
    }
    
    public Set<CyNode> nodesUnion(List<CyNode> setA, List<CyNode> setB) {
        Set<CyNode> tmp = new HashSet<CyNode>();
        for (CyNode x : setA) {
            tmp.add(x);
        }
        for (CyNode x : setB) {
            tmp.add(x);
        }
        return tmp;    
    }
    
    public <CyEdge> Set<CyEdge> edgesUnion(List<CyEdge> setA, List<CyEdge> setB) {
        Set<CyEdge> tmp = new HashSet<CyEdge>();
        for (CyEdge x : setA) {
            tmp.add(x);
        }
        for (CyEdge x : setB) {
            tmp.add(x);
        }
        return tmp;    
    }
    
    public void end(){
        stop = true;
    }
    
    
}
