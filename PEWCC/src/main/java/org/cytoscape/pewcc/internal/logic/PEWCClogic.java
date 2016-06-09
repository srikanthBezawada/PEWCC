package org.cytoscape.pewcc.internal.logic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.model.subnetwork.CyRootNetwork;
import org.cytoscape.model.subnetwork.CySubNetwork;
import org.cytoscape.pewcc.internal.PEWCCgui;
import org.cytoscape.pewcc.internal.results.Complex;
import org.cytoscape.pewcc.internal.results.Result;
import org.cytoscape.view.model.CyNetworkView;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.BronKerboschCliqueFinder;
import org.jgrapht.graph.SimpleGraph;

public class PEWCClogic extends Thread {
    CyNetwork network;
    CyNetworkView networkView;
    PEWCCgui gui;
    double joinPValue;
    int cliqueNumber;
    
    public PEWCClogic(PEWCCgui gui, CyNetwork currentnetwork, CyNetworkView currentnetworkview, int cliqueNumber, double joinPValue) {
        this.gui = gui;
        this.network = currentnetwork;
        this.networkView = currentnetworkview;
        this.joinPValue = joinPValue;
        this.cliqueNumber = cliqueNumber;
    }
    
    public void run(){
        CyRootNetwork root = ((CySubNetwork)network).getRootNetwork();
        CySubNetwork subNet, subNetTemp;
        List<CyNode> nodeList = network.getNodeList();
        List<CyNode> remnodeList = new ArrayList<CyNode>();
        List<CyNode> rejoinList = new ArrayList<CyNode>();
        List<CyEdge> edgeList = network.getEdgeList();
        List<CyNode> neighbourNodeList;
        List<CyEdge> neightbourEdgeList;
        int counter = 0;
        double wcc = 0.0;
        
        Result res = new Result();
        for(CyNode cprotein : nodeList) {
            System.out.println(network.getRow(cprotein).get(CyNetwork.NAME, String.class));
            neighbourNodeList = network.getNeighborList(cprotein, CyEdge.Type.ANY);
            neighbourNodeList.add(cprotein);
            if(neighbourNodeList.size() > 4) {
                neightbourEdgeList =  findNeighbourEdges(edgeList, neighbourNodeList);
                subNet = root.addSubNetwork();
                subNetTemp = root.addSubNetwork();
                for(CyEdge e : neightbourEdgeList) {
                    subNet.addEdge(e);
                    subNetTemp.addEdge(e);
                }
                

                while(subNetTemp.getNodeCount() > 4) {
                    wcc = findwcc(subNetTemp, cprotein);
                    remnodeList.add(findNodeToRemove(subNetTemp, cprotein));
                    subNetTemp.removeNodes(remnodeList);
                }

                for(CyNode n : neighbourNodeList) {
                    if(subNetTemp.containsNode(n)) {
                        continue;
                    }
                    for(CyNode x : subNetTemp.getNodeList()) {
                        if(subNetTemp.containsEdge(x, n) || subNetTemp.containsEdge(x, n)) {
                            counter++;
                        }
                    }

                    if(counter/(double)(subNetTemp.getNodeCount()) > joinPValue)
                    rejoinList.add(n);
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
                
                Complex C = new Complex(cprotein, subNet.getNodeList(), subNet.getEdgeList(), wcc);
                res.add(C);
                neighbourNodeList.clear();
                neightbourEdgeList.clear();
                rejoinList.clear();
                remnodeList.clear();
                subNetTemp.dispose();
            } 
            else {
            }

        }
        
        for(Complex c : res.getComplexes()) {
            System.out.println("------");
            for(CyNode n : c.subnodeList) {
                System.out.println(network.getRow(n).get(CyNetwork.NAME, String.class));
            }
            System.out.println("-------");
        }
        
        
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
        UndirectedGraph<CyNode, CyEdge> g = new SimpleGraph<CyNode, CyEdge>(CyEdge.class);
        List<CyNode> tnList = tempNet.getNodeList();
        List<CyEdge> teList = tempNet.getEdgeList();
        for(CyNode n : tnList){
            g.addVertex(n);
        }
        for(CyEdge e : teList){
            if(e.getSource().equals(e.getTarget())){
                continue; // removing self-loops
            }
            g.addEdge(e.getSource(), e.getTarget(),e);
        }
        BronKerboschCliqueFinder bcfinder = new BronKerboschCliqueFinder(g);
        List<Set<CyNode>> allCliques = (List<Set<CyNode>>)bcfinder.getAllMaximalCliques();
        int cliques = 0;
        for (Set<CyNode> set : allCliques) {
            if(set.size() == cliqueNumber && set.contains(centerNode)) {
                cliques++;
            }
        }
        double ni = tempNet.getNeighborList(centerNode, CyEdge.Type.ANY).size();
        ni = (ni*ni)*(ni - 1);
        
        return (2*cliques)/(ni);
    }

    public CyNode findNodeToRemove(CyNetwork tempNet, CyNode cp){
        CyNode toRemove = tempNet.getNodeList().get(1);
        if(cp.equals(toRemove)) {
            toRemove = tempNet.getNodeList().get(0);
        }
        for(CyNode n:tempNet.getNodeList()) {
            if(n.equals(cp))
                continue; // verify this
            if(tempNet.getNeighborList(n, CyEdge.Type.ANY).size() < tempNet.getNeighborList(toRemove, CyEdge.Type.ANY).size()) {
                toRemove = n;
            }
        }
        return toRemove;
    }
    
    
    
}