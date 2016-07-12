package org.cytoscape.pewcc.internal;

/**
 * @author SrikanthB
 *
 */

public class PEWCChelp extends javax.swing.JFrame {

    private String helpString;

    /**
     * Creates new form TieDieHelp
     */
    public PEWCChelp() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setForeground(new java.awt.Color(255, 255, 51));
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);
        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(layout.createSequentialGroup().addContainerGap().add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 518, Short.MAX_VALUE).addContainerGap()));
        layout.setVerticalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(layout.createSequentialGroup().addContainerGap().add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE).addContainerGap()));
        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new PEWCChelp().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables

    public void setText(int buttonNumber) {
      
        helpString =
                "PEWCC : \n\n"
                + "----\n"
                + "About : \n\n"
                + " PEWCC predicts protein complexes from PPI networks  \n"
                + "\n"
                + "http://www.ncbi.nlm.nih.gov/pubmed/23688127"
                + "\n\n"
                + "Tutorial : \n"
                + " - Click on Apps menu \n"
                + " - Click on PEWCC which opens \n"
                + "   a tabbed panel in control panel \n"
                + " - Select the network to be used \n"
                + "   and input join parameter \n"
                + " - Click on the Run button \n\n"
               
                + "Results : \n"
                + " - Find the results of algorithm in the Results Panel \n"
                + " - Single click on each cluster selects \n" 
                + " the corresponding nodes and edges in the view panel.  \n"
                + " - Double click on a cluster extracts the correponding "
                + " selected cluster/s as subnetwork."
                + " - User can save selected cluster/s using extract selected cluster option" 
                + " available upon right click on any cluster (or) \n" 
                + " save all clusters using the right top button."
                + "----\n";
        
                

        this.setTitle("PEWCC Help : ");
        
        jTextArea1.setText(helpString);
        jTextArea1.setCaretPosition(0);
    }
}