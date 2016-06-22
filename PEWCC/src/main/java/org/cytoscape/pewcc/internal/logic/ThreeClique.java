package org.cytoscape.pewcc.internal.logic;

import java.util.HashSet;
import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNode;

public class ThreeClique {
    private CyNode A;
    private CyNode B;
    private CyNode C;
    
    public ThreeClique(CyNode A, CyNode B, CyNode C){
        this.A = A;
        this.B = B;
        this.C = C;
    }
    
    public boolean containsNode(CyNode other) {
        if(this.A.equals(other) || this.B.equals(other) || this.C.equals(other)) {
            return true;
        }
        return false;
    }
    
    @Override
    public boolean equals(Object OtherThreeClique) {
        if (!(OtherThreeClique instanceof ThreeClique)) {
            return false;
        }    
        ThreeClique OtherThreeCliqueRef = (ThreeClique)OtherThreeClique;
        if(OtherThreeCliqueRef.containsNode(this.A)&& OtherThreeCliqueRef.containsNode(this.B) && OtherThreeCliqueRef.containsNode(this.C)) {
            return true;
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return A.hashCode() + B.hashCode() + C.hashCode();
    }
    
    
}
