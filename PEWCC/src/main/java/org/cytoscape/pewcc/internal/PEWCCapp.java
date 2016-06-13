package org.cytoscape.pewcc.internal;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.application.swing.CySwingApplication;
import org.cytoscape.model.CyNetworkManager;
import org.cytoscape.view.model.CyNetworkViewManager;


public class PEWCCapp {
    private CytoscapeAppActivator activator;
    private CySwingApplication app;
    private PEWCCgui gui;
    private ShowControlPanelAction scpa;
    public static final String APPNAME = "PEWCC - 1.0";
    
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
