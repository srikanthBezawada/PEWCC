package org.cytoscape.pewcc.internal.results;


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
    
    
}