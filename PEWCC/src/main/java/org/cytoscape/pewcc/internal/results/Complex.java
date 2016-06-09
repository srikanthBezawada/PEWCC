package org.cytoscape.pewcc.internal.results;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNode;

public class Complex {
    public List<CyNode> subnodeList;
    public List<CyEdge> subedgeList;
    private double wcc;
    private CyNode centernode;
    
    public Complex(CyNode centernode, List<CyNode> subnodeList, List<CyEdge> subedgeList, double wcc) {
        this.centernode = centernode;
        this.subnodeList = subnodeList;
        this.subedgeList = subedgeList;
        this.wcc = wcc;
    }
    
    public double getwcc() {
        return this.wcc;
    }
    
    public void setwcc(double wcc) {
        this.wcc = wcc;
    }
    
    @Override
    public boolean equals(Object otherComplex) {
        if (!(otherComplex instanceof Complex)) {
            return false;
        }    
        Complex otherComplexRef = (Complex)otherComplex;
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
    
}