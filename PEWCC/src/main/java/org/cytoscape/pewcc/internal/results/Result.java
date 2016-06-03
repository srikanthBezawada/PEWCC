package org.cytoscape.pewcc.internal.results;


import java.util.ArrayList;
import java.util.List;

public class Result {
    private List<Complex> complexes = new ArrayList<Complex>();
    
    public void add(Complex c) {
        complexes.add(c);
    }
    
    public void remove(Complex c) {
        complexes.remove(c);
    }
    
    public List<Complex> getComplexes() {
        return complexes;
    }
}
