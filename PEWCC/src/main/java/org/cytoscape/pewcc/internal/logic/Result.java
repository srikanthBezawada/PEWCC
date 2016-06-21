package org.cytoscape.pewcc.internal.logic;


import java.util.HashSet;
import java.util.Set;

public class Result {
    private Set<PEWCCCluster> clusters = new HashSet<PEWCCCluster>();
    
    public void add(PEWCCCluster c) {
        clusters.add(c);
    }
    
    public void remove(PEWCCCluster c) {
        clusters.remove(c);
    }
    
    public Set<PEWCCCluster> getComplexes() {
        return clusters;
    }
    
}
