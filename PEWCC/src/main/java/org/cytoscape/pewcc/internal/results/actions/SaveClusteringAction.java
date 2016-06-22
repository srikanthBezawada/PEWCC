package org.cytoscape.pewcc.internal.results.actions;

import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import org.cytoscape.pewcc.internal.PEWCCapp;
import org.cytoscape.pewcc.internal.logic.PEWCCCluster;
import org.cytoscape.pewcc.internal.results.CytoscapeResultViewerPanel;

//import uk.ac.rhul.cs.cl1.NodeSet;

/**I
 * Action that saves the names of the members of all clusters
 * in the result viewer to a file on the disk.
 * 
 * @author ntamas
 */
public class SaveClusteringAction extends SaveClusterAction {
	public SaveClusteringAction(CytoscapeResultViewerPanel panel) {
		super(panel);
		this.putValue(AbstractAction.NAME, "Save clustering...");
		this.putValue(AbstractAction.MNEMONIC_KEY, KeyEvent.VK_V);
		this.putValue(AbstractAction.SHORT_DESCRIPTION,
				"Save the clustering to a file");
                this.putValue(AbstractAction.SMALL_ICON, UIManager.getIcon("OptionPane.informationIcon"));
	}

	/**
	 * Returns the title of the dialog box where the destination file will be selected
	 */
	protected String getFileDialogTitle() {
		return "Select the file to save the clustering to";
	}
	
	/**
	 * Returns the list of nodes that should be saved
	 */
	protected List<PEWCCCluster> getNodeListsToBeSaved() {
		return this.resultViewer.getAllNodeSets();
	}
	
}
