package org.cytoscape.pewcc.internal.results.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import org.cytoscape.pewcc.internal.logic.PEWCCCluster;
import org.cytoscape.pewcc.internal.results.NodeSetTableModel;
import org.cytoscape.pewcc.internal.results.ResultViewerPanel;



/**
 * Action that removes the selected clusters from the result list
 * 
 * @author ntamas
 */
public class RemoveClusterFromResultAction extends AbstractAction {
	/**
	 * Result viewer panel associated to the action
	 */
	protected ResultViewerPanel resultViewer;
	
	/**
	 * Constructor
	 */
	public RemoveClusterFromResultAction(ResultViewerPanel panel) {
		super("Remove");
		this.resultViewer = panel;
		this.putValue(AbstractAction.MNEMONIC_KEY, KeyEvent.VK_R);
		this.setEnabled(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		NodeSetTableModel model = this.resultViewer.getTableModel();
		
		for (PEWCCCluster nodeSet: this.resultViewer.getSelectedNodeSets())
			model.remove(nodeSet);
	}
}