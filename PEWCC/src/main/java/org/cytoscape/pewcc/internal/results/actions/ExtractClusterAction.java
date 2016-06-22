package org.cytoscape.pewcc.internal.results.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.AbstractAction;
import org.cytoscape.app.swing.CySwingAppAdapter;

import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNetworkManager;
import org.cytoscape.model.CyNode;
import org.cytoscape.pewcc.internal.PEWCCapp;
import org.cytoscape.pewcc.internal.results.CytoscapeResultViewerPanel;
import org.cytoscape.task.create.NewNetworkSelectedNodesAndEdgesTaskFactory;
import org.cytoscape.work.swing.DialogTaskManager;

/**
 * Action called when a cluster must be extracted as a separate network
 * 
 * @author tamas
 */
public class ExtractClusterAction extends AbstractAction {
	/**
	 * Result viewer panel associated to the action
	 */
	protected CytoscapeResultViewerPanel resultViewer;

	/**
	 * Constructs the action
	 */
	public ExtractClusterAction(CytoscapeResultViewerPanel panel) {
		super("Extract selected cluster(s)");
		this.resultViewer = panel;
		this.putValue(AbstractAction.MNEMONIC_KEY, KeyEvent.VK_E);
	}
	
	public void actionPerformed(ActionEvent event) {
		List<CyNode> selectedNodes = this.resultViewer.getSelectedCytoscapeNodeSet();
		CyNetwork network = this.resultViewer.getNetwork();
		PEWCCapp app = this.resultViewer.getCytoscapeApp();
		
		if (network == null) {
			app.showErrorMessage("Cannot create network representation for the cluster:\n" +
					"The parent network has already been destroyed.");
			return;
		}
		
		resultViewer.selectNodes(selectedNodes);
		
		CySwingAppAdapter appAdapter = this.resultViewer.getCytoscapeApp().getService(CySwingAppAdapter.class);
		NewNetworkSelectedNodesAndEdgesTaskFactory taskFactory = appAdapter.get_NewNetworkSelectedNodesAndEdgesTaskFactory();
				
		if (taskFactory == null) {
			app.showBugMessage("Cannot create network representation for the cluster:\n" +
					"New network creation factory is not registered.");
			return;
		}
		
		DialogTaskManager taskManager =
				this.resultViewer.getCytoscapeApp().getService(DialogTaskManager.class);
		if (taskManager == null) {
			app.showBugMessage("Cannot create network representation for the cluster:\n" +
					"Dialog task manager is not registered.");
			return;
		}
		
		taskManager.execute(taskFactory.createTaskIterator(network));
                this.resultViewer.incrementClusterCount();
                
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ExtractClusterAction.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                String currentNetworkName = network.getRow(network).get(CyNetwork.NAME, String.class);
                Set<CyNetwork> allnetworks = this.resultViewer.getCytoscapeApp().getService(CyNetworkManager.class).getNetworkSet();
                        
                long maxSUID = Integer.MIN_VALUE;
                for(CyNetwork net : allnetworks){
                    if(net.getSUID() > maxSUID)
                        maxSUID = net.getSUID();
                }
                CyNetwork newnet = this.resultViewer.getCytoscapeApp().getService(CyNetworkManager.class).getNetwork(maxSUID);
                newnet.getRow(newnet).set(CyNetwork.NAME, currentNetworkName + " Cluster extracted " + this.resultViewer.getclustersExtracted());

        }
}
