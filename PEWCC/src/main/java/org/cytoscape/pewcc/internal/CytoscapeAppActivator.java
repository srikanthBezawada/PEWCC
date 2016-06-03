package org.cytoscape.pewcc.internal;

import java.util.Properties;
import org.cytoscape.service.util.AbstractCyActivator;
import org.cytoscape.service.util.CyServiceRegistrar;
import org.osgi.framework.BundleContext;

/**
 * @author SrikanthB
 *  This class is the entry point to Cytoscape app
 */


public class CytoscapeAppActivator extends AbstractCyActivator {
    private BundleContext context;
    
    
    public CytoscapeAppActivator() {
        super();
    }
    
    public void start(BundleContext context) throws Exception {
        this.context = context;
        PEWCCapp pewccapp = new PEWCCapp(this);
        
        registerAllServices(context, pewccapp, new Properties());
        
    }
    
    /**
     * Returns the service registered with the given class.
     */
    public <S> S getService(Class<S> cls) {
            return this.getService(context, cls);
    }
	
    /**
     * Returns the service registered with the given class.
     */
    public <S> S getService(Class<S> cls, String properties) {
            return this.getService(context, cls, properties);
    }
	
	

    /**
     * Registers an object as a service in the Cytoscape Swing application.
     * 
     * @param  object      the object to register
     * @param  cls         the class of the object
     * @param  properties  additional properties to use for registering
     */
    public <S> void registerService(S object, Class<S> cls) {
            registerService(object, cls, new Properties());
    }
	
    /**
     * Registers an object as a service in the Cytoscape Swing application.
     * 
     * @param  object      the object to register
     * @param  cls         the class of the object
     * @param  properties  additional properties to use for registering
     */
    public void registerService(Object object, Class<?> cls, Properties properties) {
            CyServiceRegistrar registrar = this.getService(CyServiceRegistrar.class);
            registrar.registerService(object, cls, properties);
    }
        
        
    /**
     * Unregisters an object as a service in the Cytoscape Swing application.
     * 
     * @param  object      the object to register
     * @param  cls         the class of the object
     */
    public <S> void unregisterService(S object, Class<S> cls) {
            CyServiceRegistrar registrar = this.getService(CyServiceRegistrar.class);
            registrar.unregisterService(object, cls);
    }
        
        
        
}

