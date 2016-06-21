package org.cytoscape.pewcc.internal.logic;


import java.util.HashSet;
import java.util.List;
import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.pewcc.internal.logic.utils.CyNodeUtil;

public class PEWCCCluster {
    private CyNetwork subnetwork;
    private List<CyNode> subnodeList;
    private List<CyEdge> subedgeList;
    private double wcc;
    private CyNode centernode;
    
    public PEWCCCluster(CyNetwork subnetwork, CyNode centernode, double wcc) {
        this.subnetwork = subnetwork;
        this.centernode = centernode;
        this.subnodeList = subnetwork.getNodeList();
        this.subedgeList = subnetwork.getEdgeList();
        this.wcc = wcc;
    }
    
    public double getwcc() {
        return this.wcc;
    }
    
    public List<CyNode> getNodes() {
        return this.subnodeList;
    }
    
    public List<CyEdge> getEdges() {
        return this.subedgeList;
    }
    
    public void setwcc(double wcc) {
        this.wcc = wcc;
    }
    
    public CyNetwork getSubnetwork(){
        return subnetwork;
    }
    
    @Override
    public boolean equals(Object otherComplex) {
        if (!(otherComplex instanceof PEWCCCluster)) {
            return false;
        }    
        PEWCCCluster otherComplexRef = (PEWCCCluster)otherComplex;
        if(otherComplexRef.subnodeList.size() != this.subnodeList.size() || otherComplexRef.subedgeList.size() != otherComplexRef.subedgeList.size()) {
            return false;
        }
        
        if(new HashSet<CyNode>(otherComplexRef.subnodeList).equals(new HashSet<CyNode>(this.subnodeList))) {
            if(new HashSet<CyEdge>(otherComplexRef.subedgeList).equals(new HashSet<CyEdge>(this.subedgeList))) {
                return true;
            }
        } else{
            return false;
        }
        
        
        return true;
    }
    
    @Override
    public int hashCode() {
        int hashCode = 1;
        for(CyNode n: this.subnodeList) {
            hashCode = hashCode+n.hashCode();
        }
        for(CyEdge e: this.subedgeList) {
            hashCode = hashCode+e.hashCode();
        }
        return hashCode;
    }
    
    public String[] getMemberNames() {
        String[] result = new String[this.subnodeList.size()];
        int i = 0;
        
        for(CyNode n : subnodeList){
            result[i] = CyNodeUtil.getName(subnetwork, n);
            i++;
        }
        
        return result;  
    }
    
}