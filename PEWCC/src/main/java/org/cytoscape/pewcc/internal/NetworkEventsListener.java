package org.cytoscape.pewcc.internal;

import javax.swing.DefaultComboBoxModel;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.events.NetworkAddedEvent;
import org.cytoscape.model.events.NetworkAddedListener;
import org.cytoscape.model.events.NetworkDestroyedEvent;
import org.cytoscape.model.events.NetworkDestroyedListener;

/**
 * @author SrikanthB
 *  This class takes care of updating network combo box of GUI
 */

public class NetworkEventsListener implements NetworkAddedListener, NetworkDestroyedListener {

    public void handleEvent(NetworkAddedEvent e){
        CyNetwork net = e.getNetwork();
        String title = net.getRow(net).get(CyNetwork.NAME, String.class);
        PEWCCgui gui = PEWCCcore.getPEWCCgui();
        ((DefaultComboBoxModel)gui.networkComboBox.getModel()).addElement(title);
    }

    public void handleEvent(NetworkDestroyedEvent e){
        PEWCCgui gui = PEWCCcore.getPEWCCgui();
        gui.updateNetworkList();
    }
}