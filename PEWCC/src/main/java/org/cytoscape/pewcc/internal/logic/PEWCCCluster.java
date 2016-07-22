package org.cytoscape.pewcc.internal.logic;


import java.util.HashSet;
import java.util.List;
import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.pewcc.internal.logic.utils.CyNodeUtil;
import org.cytoscape.pewcc.internal.logic.utils.StringUtils;

public class PEWCCCluster {
    private CyNetwork subnetwork;
    private List<CyNode> subnodeList;
    private List<CyEdge> subedgeList;
    private double wcc;
    
    public PEWCCCluster(CyNetwork subnetwork) {
        this.subnetwork = subnetwork;
        this.subnodeList = subnetwork.getNodeList();
        this.subedgeList = subnetwork.getEdgeList();
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
        if(otherComplexRef.subnodeList.size() != this.subnodeList.size()) {
            return false;
        }
        if(otherComplexRef.subedgeList.size() != otherComplexRef.subedgeList.size()) {
            return false;
        }
        /*
        if(new HashSet<CyNode>(otherComplexRef.subnodeList).equals(new HashSet<CyNode>(this.subnodeList))) {
            if(new HashSet<CyEdge>(otherComplexRef.subedgeList).equals(new HashSet<CyEdge>(this.subedgeList))) {
                return true;
            }
        } else{
            return false;
        }
        */
        return true;
    }
    
    @Override
    public int hashCode() {
        int hashCode = 1;
        
        return hashCode + subnodeList.size() + subedgeList.size();

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
    
    /**
    * Prints the nodes in this set to a string using a given separator
    */
    public String toString(String separator) {
            return StringUtils.join(getMemberNames(), separator);
    }
    
}