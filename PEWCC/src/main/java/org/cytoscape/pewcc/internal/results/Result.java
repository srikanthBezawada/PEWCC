package org.cytoscape.pewcc.internal.results;


import java.util.HashSet;
import java.util.Set;

public class Result {
    private Set<Complex> complexes = new HashSet<Complex>();
    
    public void add(Complex c) {
                    complexes.add(c);
                }
    
    public void remove(Complex c) {
        complexes.remove(c);
    }
    
    public Set<Complex> getComplexes() {
        return complexes;
    }
}
