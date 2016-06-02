package org.cytoscape.pewcc.internal;

import java.awt.Component;
import java.awt.event.ItemListener;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.JOptionPane;
import org.cytoscape.application.swing.CytoPanelComponent;
import org.cytoscape.application.swing.CytoPanelName;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.pewcc.logic.PEWCClogic;
import org.cytoscape.view.model.CyNetworkView;

/**
 * @author SrikanthB
 * GUI of the app, Control goes to logic from here
 */
public class PEWCCgui extends javax.swing.JPanel implements CytoPanelComponent {

    /**
     * Creates new form CliqueUI
     */
    private PEWCCcore pewcccore; 
    public PEWCCgui(PEWCCcore pewcccore) {
        initComponents();
        this.pewcccore = pewcccore;
        updateNetworkList();
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
        return CyActivator.APPNAME;
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
        cliqueLabel = new javax.swing.JLabel();
        cliqueValue = new javax.swing.JTextField();
        joinPLabel = new javax.swing.JLabel();
        joinPValue = new javax.swing.JTextField();
        startB = new javax.swing.JButton();
        helpExitPanel = new javax.swing.JPanel();
        helpB = new javax.swing.JButton();
        exitB = new javax.swing.JButton();
        statusPanel = new javax.swing.JPanel();
        statusBar = new javax.swing.JProgressBar();
        statusLabel = new javax.swing.JLabel();

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        mainPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        headingLabel.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        headingLabel.setForeground(new java.awt.Color(255, 0, 51));
        headingLabel.setText("PEWCC");

        networkPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Select the network"));

        networkLabel.setText("Network");

        cliqueLabel.setText("Clique size");

        cliqueValue.setText("3");
        cliqueValue.setToolTipText("Interactions below the threshold will be removed");

        joinPLabel.setText("Join parameter");

        joinPValue.setText("0.3");

        javax.swing.GroupLayout networkPanelLayout = new javax.swing.GroupLayout(networkPanel);
        networkPanel.setLayout(networkPanelLayout);
        networkPanelLayout.setHorizontalGroup(
            networkPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(networkPanelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(networkPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(networkLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cliqueLabel)
                    .addComponent(joinPLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(networkPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(networkComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(networkPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(joinPValue, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(cliqueValue, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)))
                .addGap(28, 28, 28))
        );
        networkPanelLayout.setVerticalGroup(
            networkPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(networkPanelLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(networkPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(networkLabel)
                    .addComponent(networkComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(networkPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cliqueValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cliqueLabel))
                .addGap(18, 18, 18)
                .addGroup(networkPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(joinPLabel)
                    .addComponent(joinPValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        startB.setText("Run PEWCC on Selected Network");
        startB.setToolTipText("");
        startB.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        startB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startBActionPerformed(evt);
            }
        });

        helpExitPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

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

        javax.swing.GroupLayout helpExitPanelLayout = new javax.swing.GroupLayout(helpExitPanel);
        helpExitPanel.setLayout(helpExitPanelLayout);
        helpExitPanelLayout.setHorizontalGroup(
            helpExitPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(helpExitPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(helpB, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(exitB, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        helpExitPanelLayout.setVerticalGroup(
            helpExitPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, helpExitPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(helpExitPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(exitB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(helpB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        statusPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Status bar"));

        statusLabel.setFont(new java.awt.Font("Tahoma", 2, 10)); // NOI18N
        statusLabel.setText("status");

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(statusBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(statusLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(statusBar, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(statusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(helpExitPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(statusPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(networkPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(startB, javax.swing.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(124, 124, 124)
                        .addComponent(headingLabel)))
                .addContainerGap(59, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(headingLabel)
                .addGap(32, 32, 32)
                .addComponent(networkPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(startB, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(statusPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75)
                .addComponent(helpExitPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(mainPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jScrollPane1)
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
        CyNetwork currentnetwork = getSelectedNetwork();
        CyNetworkView currentnetworkview;
        PEWCClogic logicThread;
        if(currentnetwork != null){
            currentnetworkview = CyActivator.getCyApplicationManager().getCurrentNetworkView();
            logicThread = new PEWCClogic(this, currentnetwork, currentnetworkview, cliqueValueValidate(cliqueValue), joinPValueValidate(joinPValue));
            logicThread.start();
        } else{
            startB.setEnabled(false);
            JOptionPane.showMessageDialog(null, "IMPORT a network first! ", "No Network found ", JOptionPane.WARNING_MESSAGE);
            return;
        }  
    }//GEN-LAST:event_startBActionPerformed

    private void helpBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpBActionPerformed
        //PEhelp help = new PEhelp();
        //help.setText(1);
        //help.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_helpBActionPerformed

    private void exitBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBActionPerformed
        // TODO add your handling code here:
        pewcccore.closeStartMenu();
    }//GEN-LAST:event_exitBActionPerformed
    
    public void startComputation(){
        startB.setEnabled(false);
        statusBar.setIndeterminate(true);
        statusBar.setVisible(true);
        statusLabel.setText("PEWCC algorithm is running ......");
    }
    
    public void endComputation(){
        statusBar.setIndeterminate(false);
        statusLabel.setText("<html> Completed finding complexes in the current network ! <br> You might want to recompute with different inputs <html>");
        startB.setEnabled(true);
    }
    
    public void calculatingresult(String msg){
        statusLabel.setText(msg);
    }
    
    public int cliqueValueValidate(javax.swing.JTextField jtf){
        int cliqueValue = 3;
        try{
            cliqueValue = Integer.parseInt(jtf.getText());
        } catch(NumberFormatException e){
            System.out.println("Number format exception");
        } catch(NullPointerException e){
            System.out.println("String is null");
        }
        return cliqueValue;
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
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel cliqueLabel;
    private javax.swing.JTextField cliqueValue;
    private javax.swing.JButton exitB;
    private javax.swing.JLabel headingLabel;
    private javax.swing.JButton helpB;
    private javax.swing.JPanel helpExitPanel;
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
    // End of variables declaration//GEN-END:variables
    protected void updateNetworkList() {
        final Set<CyNetwork> networks = CyActivator.getCyNetworkManager().getNetworkSet();
        final SortedSet<String> networkNames = new TreeSet<String>();

        for (CyNetwork net : networks)
                networkNames.add(net.getRow(net).get("name", String.class));

        // Clear the comboBox
        networkComboBox.setModel(new DefaultComboBoxModel());

        for (String name : networkNames)
                networkComboBox.addItem(name);

        CyNetwork currNetwork = CyActivator.getCyApplicationManager().getCurrentNetwork();
        if (currNetwork != null) {
                String networkTitle = currNetwork.getRow(currNetwork).get("name", String.class);
                networkComboBox.setSelectedItem(networkTitle);			
        }
    }
    
    
    public void addItemListener(final ItemListener newListener) {
        networkComboBox.addItemListener(newListener);
    }
    
    public CyNetwork getSelectedNetwork() {
        for (CyNetwork net : CyActivator.getCyNetworkManager().getNetworkSet()) {
                String networkTitle = net.getRow(net).get("name", String.class);
                if (networkTitle.equals(networkComboBox.getSelectedItem()))
                        return net;
        }

        return null;
    }
         
}
