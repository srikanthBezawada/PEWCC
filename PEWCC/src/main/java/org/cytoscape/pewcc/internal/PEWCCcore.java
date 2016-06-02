package org.cytoscape.pewcc.internal;

import java.util.Properties;
import org.cytoscape.application.swing.CytoPanel;
import org.cytoscape.application.swing.CytoPanelComponent;
import org.cytoscape.application.swing.CytoPanelName;

/**
 * @author SrikanthB
 *
 */

public class PEWCCcore {
    
    private static PEWCCgui gui;
    public PEWCCcore(){
        gui = createPEWCCgui();
    }

    public PEWCCgui createPEWCCgui(){
        gui = new PEWCCgui(this);
        CyActivator.getCyServiceRegistrar().registerService(gui, CytoPanelComponent.class, new Properties());
        CytoPanel cytopanelwest = CyActivator.getCyDesktopService().getCytoPanel(CytoPanelName.WEST);
        int index = cytopanelwest.indexOfComponent(gui);
        cytopanelwest.setSelectedIndex(index);
        return gui;
    }
    
    public void closeStartMenu() {
        CyActivator.getCyServiceRegistrar().unregisterService(gui, CytoPanelComponent.class);
    }
    
    public static PEWCCgui getPEWCCgui(){
        return gui;
    }
    
}