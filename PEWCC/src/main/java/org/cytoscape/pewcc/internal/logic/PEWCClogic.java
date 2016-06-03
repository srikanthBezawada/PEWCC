package org.cytoscape.pewcc.logic;

import org.cytoscape.model.CyNetwork;
import org.cytoscape.pewcc.internal.PEWCCgui;
import org.cytoscape.view.model.CyNetworkView;

public class PEWCClogic extends Thread {
    CyNetwork currentnetwork;
    CyNetworkView currentnetworkview;
    PEWCCgui gui;
    double joinPValue;
    int cliqueNumber;
    
    public PEWCClogic(PEWCCgui gui, CyNetwork currentnetwork, CyNetworkView currentnetworkview, int cliqueNumber, double joinPValue) {
        this.gui = gui;
        this.currentnetwork = currentnetwork;
        this.currentnetworkview = currentnetworkview;
        this.joinPValue = joinPValue;
        this.cliqueNumber = cliqueNumber;
    }
    
    public void run(){
    
    }
}
