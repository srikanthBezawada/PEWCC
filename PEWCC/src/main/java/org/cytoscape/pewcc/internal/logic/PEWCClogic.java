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
        gui.startComputation();
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
        
        Result res = new Result();
        for(CyNode cprotein : nodeList) {
            neighbourNodeList = network.getNeighborList(cprotein, CyEdge.Type.ANY);
            neighbourNodeList.add(cprotein);
            if(neighbourNodeList.size() > 3) {
                neightbourEdgeList =  findNeighbourEdges(edgeList, neighbourNodeList);
                subNet = root.addSubNetwork(neighbourNodeList, neightbourEdgeList);
                subNetTemp = root.addSubNetwork(neighbourNodeList, neightbourEdgeList);
                
                while(subNetTemp.getNodeCount() > 3) {
                    wcc = findwcc(subNetTemp, cprotein);
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
                Complex C = new Complex(cprotein, subNet.getNodeList(), subNet.getEdgeList(), wcc);
                res.add(C);
                neighbourNodeList.clear();
                neightbourEdgeList.clear();
                rejoinList.clear();
                remnodeList.clear();
                subNetTemp.dispose();
            } 
            

        }
        /*
        System.out.println(" Printing complexes Start after removing duplicates------");
        for(Complex c : res.getComplexes()) {
            System.out.println("------");
            for(CyNode n : c.subnodeList) {
                System.out.println(network.getRow(n).get(CyNetwork.NAME, String.class));
            }
            System.out.println("wcc : "+c.getwcc());
            System.out.println("------");
        }
        System.out.println(" End -------");
        */
        
        long endTime = System.currentTimeMillis();
        long difference = endTime - startTime;
        System.out.println("Execution time for PE-measure algo: " + difference +" milli seconds");
        gui.endComputation();
        
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
        int cliques=0;
        for(CyEdge e:teList) {
            for(CyNode n:tnList) {
                if(tempNet.containsEdge(n, e.getSource()) || tempNet.containsEdge(n, e.getSource())) {
                    if(tempNet.containsEdge(n, e.getTarget()) || tempNet.containsEdge(n, e.getTarget())) {
                        if(n.equals(centerNode) || n.equals(e.getSource()) || n.equals(e.getTarget()))
                        cliques++;
                    }
                }
            }
        }
        
        double ni = tempNet.getNeighborList(centerNode, CyEdge.Type.ANY).size();
        ni = (ni*ni)*(ni - 1);
        
        return (2*cliques)/(ni);
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
    
    
}
