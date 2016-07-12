package org.cytoscape.pewcc.internal;

import java.awt.Component;
import java.awt.event.ItemListener;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.JOptionPane;
import org.cytoscape.application.swing.CytoPanel;
import org.cytoscape.application.swing.CytoPanelComponent;
import org.cytoscape.application.swing.CytoPanelName;
import org.cytoscape.application.swing.CytoPanelState;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.events.NetworkAddedEvent;
import org.cytoscape.model.events.NetworkAddedListener;
import org.cytoscape.model.events.NetworkDestroyedEvent;
import org.cytoscape.model.events.NetworkDestroyedListener;

import org.cytoscape.view.model.CyNetworkView;

/**
 * @author SrikanthB
 * GUI of the app, Control goes to logic from here
 */
public class PEWCCgui extends javax.swing.JPanel implements CytoPanelComponent, NetworkAddedListener, NetworkDestroyedListener {

    /**
     * Creates new form CliqueUI
     */
    private PEWCCapp pewccapp;
    public PEWCCgui(PEWCCapp pewccapp) {
        initComponents();
        this.pewccapp = pewccapp;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @Override
    public Icon getIcon() {
        return null;
    }
    
    @Override
    public String getTitle() {
        return pewccapp.APPNAME;
    }
    
    @Override
    public CytoPanelName getCytoPanelName() {
        return CytoPanelName.WEST;
    }
    
    public Component getComponent() {
        return this;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        mainPanel = new javax.swing.JPanel();
        headingLabel = new javax.swing.JLabel();
        networkPanel = new javax.swing.JPanel();
        networkLabel = new javax.swing.JLabel();
        networkComboBox = new javax.swing.JComboBox();
        joinPLabel = new javax.swing.JLabel();
        joinPValue = new javax.swing.JTextField();
        startB = new javax.swing.JButton();
        statusPanel = new javax.swing.JPanel();
        statusBar = new javax.swing.JProgressBar();
        statusLabel = new javax.swing.JLabel();
        stopButton = new javax.swing.JButton();
        helpB = new javax.swing.JButton();
        exitB = new javax.swing.JButton();

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        mainPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        headingLabel.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        headingLabel.setForeground(new java.awt.Color(255, 0, 51));
        headingLabel.setText("PEWCC");

        networkPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Select the network"));

        networkLabel.setText("Network");

        networkComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                networkComboBoxActionPerformed(evt);
            }
        });

        joinPLabel.setText("Join parameter");

        joinPValue.setText("0.5");

        startB.setText("Run PEWCC on Selected Network");
        startB.setToolTipText("");
        startB.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        startB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startBActionPerformed(evt);
            }
        });

        statusPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Status bar"));

        statusLabel.setText("status");

        stopButton.setBackground(new java.awt.Color(255, 102, 102));
        stopButton.setText("Stop");
        stopButton.setEnabled(false);
        stopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(statusBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, statusPanelLayout.createSequentialGroup()
                        .addComponent(statusLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stopButton)))
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(statusBar, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusLabel)
                    .addComponent(stopButton))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        helpB.setForeground(new java.awt.Color(0, 200, 0));
        helpB.setText("Help");
        helpB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpBActionPerformed(evt);
            }
        });

        exitB.setForeground(new java.awt.Color(200, 0, 0));
        exitB.setText("Exit");
        exitB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout networkPanelLayout = new javax.swing.GroupLayout(networkPanel);
        networkPanel.setLayout(networkPanelLayout);
        networkPanelLayout.setHorizontalGroup(
            networkPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(networkPanelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(networkPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(networkPanelLayout.createSequentialGroup()
                        .addComponent(helpB, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(exitB, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(statusPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(startB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, networkPanelLayout.createSequentialGroup()
                        .addGroup(networkPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(networkLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(joinPLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                        .addGroup(networkPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(networkComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(joinPValue, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(28, 28, 28))
        );
        networkPanelLayout.setVerticalGroup(
            networkPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(networkPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(networkPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(networkLabel)
                    .addComponent(networkComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(networkPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(joinPLabel)
                    .addComponent(joinPValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(startB, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(statusPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addGroup(networkPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(helpB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(exitB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(26, 26, 26))
        );

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(headingLabel)
                    .addComponent(networkPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(headingLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(networkPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(143, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(mainPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
                .addGap(11, 11, 11))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 569, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );
    }// </editor-fold>//GEN-END:initComponents
   
    private void startBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startBActionPerformed
        CyNetwork network = getSelectedNetwork();
        CyNetworkView networkview;
        
        if(network != null){
            networkview = pewccapp.getApplicationManager().getCurrentNetworkView();
            pewccapp.runAlgorithm(network, networkview, 3, joinPValueValidate(joinPValue));
            
        } else{
            startB.setEnabled(false);
            JOptionPane.showMessageDialog(null, "IMPORT a network first! ", "No Network found ", JOptionPane.WARNING_MESSAGE);
        }  
    }//GEN-LAST:event_startBActionPerformed

    private void helpBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpBActionPerformed
        PEWCChelp help = new PEWCChelp();
        help.setText(1);
        help.setVisible(true);
    }//GEN-LAST:event_helpBActionPerformed

    private void exitBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBActionPerformed
        statusBar.setIndeterminate(false);
        statusLabel.setText("Closing this menu");
        if(pewccapp.getPEWCClogic().isAlive()) {
            pewccapp.getPEWCClogic().end();
            startB.setEnabled(true);
        }
        deactivate();
    }//GEN-LAST:event_exitBActionPerformed

    private void stopButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopButtonActionPerformed
        if(pewccapp.getPEWCClogic().isAlive()) {
            pewccapp.getPEWCClogic().end();
            stopcalculus(null);
            startB.setEnabled(true);
        }
    }//GEN-LAST:event_stopButtonActionPerformed

    private void networkComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_networkComboBoxActionPerformed
        statusLabel.setText("status");
    }//GEN-LAST:event_networkComboBoxActionPerformed
    
    public void startComputation(){
        startB.setEnabled(false);
        stopButton.setEnabled(true);
        statusBar.setIndeterminate(true);
        statusBar.setVisible(true);
        statusLabel.setText("PEWCC algorithm is running ......");
    }
    
    public void endComputation(){
        statusBar.setIndeterminate(false);
        statusLabel.setText("<html>Completed! Check Results Panel <html>");
        startB.setEnabled(true);
        stopButton.setEnabled(false);
    }
    
    public void calculatingresult(String msg){
        statusLabel.setText(msg);
    }
    
    public void stopcalculus(String message) {
        statusBar.setIndeterminate(false);
        if(message == null) {
            statusLabel.setText("Interrupted by the user, click run to restart");
            stopButton.setEnabled(false);
        }
        else {
            statusLabel.setText(message);
        }    
    }
    
    
    public double joinPValueValidate(javax.swing.JTextField jtf) {
        double joinPValue = 0.0;
        try{
            joinPValue = Double.parseDouble(jtf.getText());
        } catch(NumberFormatException e){
            System.out.println("Number format exception");
        } catch(NullPointerException e){
            System.out.println("String is null");
        }
        
        return joinPValue;
    }
    
        protected void updateNetworkList() {
        final Set<CyNetwork> networks = pewccapp.getNetworkManager().getNetworkSet();
        final SortedSet<String> networkNames = new TreeSet<String>();

        for (CyNetwork net : networks)
                networkNames.add(net.getRow(net).get("name", String.class));

        // Clear the comboBox
        networkComboBox.setModel(new DefaultComboBoxModel());

        for (String name : networkNames)
                networkComboBox.addItem(name);

        CyNetwork currNetwork = pewccapp.getApplicationManager().getCurrentNetwork();
        if (currNetwork != null) {
                String networkTitle = currNetwork.getRow(currNetwork).get("name", String.class);
                networkComboBox.setSelectedItem(networkTitle);			
        }
    }
    
    
    public void addItemListener(final ItemListener newListener) {
        networkComboBox.addItemListener(newListener);
    }
    
    public CyNetwork getSelectedNetwork() {
        for (CyNetwork net : pewccapp.getNetworkManager().getNetworkSet()) {
                String networkTitle = net.getRow(net).get("name", String.class);
                if (networkTitle.equals(networkComboBox.getSelectedItem()))
                        return net;
        }

        return null;
    }
   
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton exitB;
    private javax.swing.JLabel headingLabel;
    private javax.swing.JButton helpB;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel joinPLabel;
    private javax.swing.JTextField joinPValue;
    private javax.swing.JPanel mainPanel;
    protected javax.swing.JComboBox networkComboBox;
    private javax.swing.JLabel networkLabel;
    private javax.swing.JPanel networkPanel;
    private javax.swing.JButton startB;
    private javax.swing.JProgressBar statusBar;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JButton stopButton;
    // End of variables declaration//GEN-END:variables
    @Override
    public void handleEvent(NetworkAddedEvent e){
        CyNetwork net = e.getNetwork();
        String title = net.getRow(net).get(CyNetwork.NAME, String.class);
        ((DefaultComboBoxModel)this.networkComboBox.getModel()).addElement(title);
    }

    @Override
    public void handleEvent(NetworkDestroyedEvent e){
        updateNetworkList();
    }
    
    
    public void activate() {
        pewccapp.registerService(this, CytoPanelComponent.class);
        pewccapp.registerService(this, NetworkAddedListener.class);
        pewccapp.registerService(this, NetworkDestroyedListener.class);
        CytoPanel cytoPanel = pewccapp.getCySwingApplication().getCytoPanel(getCytoPanelName());
        /* Ensure that the panel is visible */
        if (cytoPanel.getState() == CytoPanelState.HIDE) {
                cytoPanel.setState(CytoPanelState.DOCK);
        }
        setVisible(true);

        /* Activate the panel */
        cytoPanel.setSelectedIndex(cytoPanel.indexOfComponent(getComponent()));
        updateNetworkList();
    }
    
    /**
     * Deactivates and hides the control panel.
     */
    public void deactivate() {
        //pewccapp.getCySwingApplication().removeAction(pewccapp.getscpAction());
        
        pewccapp.unregisterService(this, CytoPanelComponent.class);
        pewccapp.unregisterService(this, NetworkAddedListener.class);
        pewccapp.unregisterService(this, NetworkDestroyedListener.class);
    }
         
}
