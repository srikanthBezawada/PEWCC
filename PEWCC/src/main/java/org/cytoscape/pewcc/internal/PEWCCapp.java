package org.cytoscape.pewcc.internal;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import javax.swing.JOptionPane;
import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.application.swing.CySwingApplication;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNetworkManager;
import org.cytoscape.pewcc.internal.logic.PEWCClogic;
import org.cytoscape.view.model.CyNetworkViewManager;


public class PEWCCapp {
    private CytoscapeAppActivator activator;
    private CySwingApplication app;
    private PEWCCgui gui;
    private ShowControlPanelAction scpa;
    public static final String APPNAME = "PEWCC - 1.0";
    private static Executor threadPool = null;
    /**
    * Variable storing the name of the resource path wthin the bundle.
    */
    
    public PEWCCapp(CytoscapeAppActivator activator) {
        this.activator = activator;
        initialize();
    }
    
    private void initialize() {
        this.app = activator.getService(CySwingApplication.class);
        this.gui = new PEWCCgui(this);
        scpa = new ShowControlPanelAction(gui);
        // Add the actions on the app
        app.addAction(scpa);
    }
    
    /*
    public void resultsCalculated(Result result, CyNetwork currentnetwork) {
        if(result == null)
            return;
        
        CytoscapeResultViewerPanel resultsPanel;
        resultsPanel = new CytoscapeResultViewerPanel(this, currentnetwork);
        List<PEWCCCluster> res = new ArrayList<PEWCCCluster>();
        res.addAll(result.getComplexes());
        resultsPanel.setResult(res);
        resultsPanel.addToCytoscapeResultPanel();
        
    }
    */
    
    
    
    public void runAlgorithm(CyNetwork network, int cliqueValueValidate, double joinPValueValidate) {
        PEWCClogic logicThread;
        logicThread = new PEWCClogic(this, network, cliqueValueValidate, joinPValueValidate);
        logicThread.start();           
    }
    
    
    /**
    * Returns a thread pool used by PEWCCapp for asynchronous operations
    */
    public static Executor getThreadPool() {
        if (threadPool == null)
                threadPool = Executors.newSingleThreadExecutor();
        return threadPool;
    }
    
    /**
    * Shows a message dialog box that informs the user about a possible bug in ClusterONE.
    * 
    * @param  message   the message to be shown
    */
   public void showBugMessage(String message) {
           StringBuilder sb = new StringBuilder(message);
           sb.append("\n\n");
           sb.append("This is possibly a bug in ");
           sb.append(APPNAME);
           sb.append(".\nPlease inform the developers about what you were doing and\n");
           sb.append("what the expected result would have been.");

           JOptionPane.showMessageDialog(app.getJFrame(),
                           sb.toString(), "Possible bug in "+APPNAME,
                           JOptionPane.ERROR_MESSAGE);
   }

   /**
    * Shows an error message in a dialog box
    * 
    * @param  message  the error message to be shown
    */
   public void showErrorMessage(String message) {
           JOptionPane.showMessageDialog(app.getJFrame(), message,
                           APPNAME, JOptionPane.ERROR_MESSAGE);
   }

   /**
    * Shows a message dialog box that informs the user about something
    * 
    * @param  message  the message to be shown
    */
   public void showInformationMessage(String message) {
           JOptionPane.showMessageDialog(app.getJFrame(), message,
                           APPNAME, JOptionPane.INFORMATION_MESSAGE);
   }
    
    
    
    public ShowControlPanelAction getscpAction(){
        return scpa;
    }
    
    
    public PEWCCgui getGui(){
        return gui;
    }
    
    
    public CyApplicationManager getApplicationManager() {
        return activator.getService(CyApplicationManager.class);
    }
    
    /**
     * Returns the CySwingApplication in which the PEWCC app lives.
     */
    public CySwingApplication getCySwingApplication() {
        return app;
    }
    
    /**
     * Returns the app-wide network view manager from Cytoscape.
     */
    public CyNetworkViewManager getNetworkViewManager() {
        return activator.getService(CyNetworkViewManager.class);
    }
    
    
    /**
     * Returns the app-wide network manager from Cytoscape.
     */
    public CyNetworkManager getNetworkManager() {
        return activator.getService(CyNetworkManager.class);
    }
    
    /**
     * Registers an object as a service in the Cytoscape Swing application.
     * 
     * @param  object      the object to register
     * @param  cls         the class of the object
     * @param  properties  additional properties to use for registering
     */
    public <S> void registerService(S object, Class<S> cls) {
        activator.registerService(object, cls);
    }

    /**
     * Unregisters an object as a service in the Cytoscape Swing application.
     * 
     * @param  object      the object to register
     * @param  cls         the class of the object
     */
    public <S> void unregisterService(S object, Class<S> cls) {
        activator.unregisterService(object, cls);
    }
    
    /**
     * Returns the Cytoscape service with the given interface.
     */
    public <S> S getService(Class<S> cls) {
        return activator.getService(cls);
    }

    /**
     * Returns the Cytoscape service with the given interface.
     */
    public <S> S getService(Class<S> cls, String properties) {
        return activator.getService(cls, properties);
    }
    
    
    
}
