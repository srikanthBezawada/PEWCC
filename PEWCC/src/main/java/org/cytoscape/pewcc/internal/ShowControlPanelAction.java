package org.cytoscape.pewcc.internal;

import java.awt.event.ActionEvent;
import org.cytoscape.application.swing.AbstractCyAction;
import static org.cytoscape.pewcc.internal.PEWCCapp.APPNAME;

public class ShowControlPanelAction extends AbstractCyAction{
    private final PEWCCgui gui; // he GUI that is activated by this action
    
    public ShowControlPanelAction(PEWCCgui gui){
        super(APPNAME);
        setPreferredMenu("Apps");
        this.gui = gui;
    }
    
    /**
	 * Adds the "PEWCC gui" to the Cytoscape control panel
	 * 
	 * If the ClusterONE control panel is already open, no new control panel
	 * will be added, the existing one will be selected instead.
    */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(gui != null) {
            gui.activate();
        }
    }
    
}
