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

public class PEWCClogic extends Thread {
    CyNetwork network;
    CyNetworkView networkView;
    PEWCCapp pewccapp;
    double joinPValue;
    int cliqueNumber;
    
    public PEWCClogic(PEWCCapp pewccapp, CyNetwork currentnetwork, CyNetworkView currentnetworkview, int cliqueNumber, double joinPValue) {
        this.pewccapp = pewccapp;
        this.network = currentnetwork;
        this.networkView = currentnetworkview;
        this.joinPValue = joinPValue;
        this.cliqueNumber = cliqueNumber;
    }
    
    public void run(){
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
                PEWCCCluster C = new PEWCCCluster(subNet, wcc);
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
        //merge(result);
        
        
        pewccapp.resultsCalculated(clusters, network);
        
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
    
    /*
    public void merge(Result result){
        double merge_threshold = 0.75;
        double filter_threshold = 0.25;
        CyRootNetwork root = ((CySubNetwork)network).getRootNetwork();
        
        Set<PEWCCCluster> unmergedSets = result.getComplexes();
        //
        List<PEWCCCluster> unmergedLists = new ArrayList<PEWCCCluster>();
        unmergedLists.addAll(unmergedSets);
        //
        Set<CyNode> mergedNodeSets;
        Set<CyEdge> mergedEdgeSets;
        List<CyNode> nodesToRemove = new ArrayList<CyNode>();
        CyNetwork mergedsubNet;
        PEWCCCluster mergedCluster, smallerComplex;
        
        Iterator<PEWCCCluster> outer = unmergedLists.iterator();
        //
        int temp=0;
        
        while(outer.hasNext()) {
            PEWCCCluster C1 = outer.next();
            Iterator<PEWCCCluster> inner = unmergedLists.iterator();
            
            while(inner.hasNext()) {
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
                    mergedCluster = new PEWCCCluster(mergedsubNet, 0);
                    
                    temp = result.getComplexes().size();
                    result.remove(C1);
                    temp = result.getComplexes().size();
                    result.remove(C2);
                    temp = result.getComplexes().size();
                    
                    if(mergedCluster.getNodes().size() <3) {
                        //System.out.println("YES");
                    }
                    
                    result.add(mergedCluster);
                    temp = result.getComplexes().size();
                    temp = temp;
                    nodesToRemove.clear();
                } 
            }
        }
        
        //return unmerged;
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
    */
    
    
}
