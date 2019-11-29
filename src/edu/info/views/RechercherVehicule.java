package edu.info.views;

import com.toedter.calendar.JDateChooser;
import edu.info.connexionBD.Connexion;
import edu.info.model.Voiture;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class RechercherVehicule extends javax.swing.JFrame {

    public RechercherVehicule() {
        initComponents();
    }
    /*============== variables globales ==================*/
    Connexion c = new Connexion();
    Connection con = c.connect();

    /*======================= affichage ===========================*/
    public ArrayList<Voiture> lister(String requete) {
        ArrayList<Voiture> lv = new ArrayList();
        Voiture voit = null;
        PreparedStatement prepSt = null;
        ResultSet resultat = null;

        try {
            prepSt = con.prepareStatement(requete);
            resultat = prepSt.executeQuery();
            //DefaultTableModel tm;
            while (resultat.next()) {
                voit = new Voiture(resultat.getInt("matricule"),
                        resultat.getString("marque"),
                        resultat.getString("couleur"),
                        resultat.getInt("kilometrage"),
                        resultat.getString("carburant"),
                        resultat.getDouble("prix"),
                        resultat.getString("etat"));
                lv.add(voit);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return lv;
    }

    public void showlistVoit(ArrayList<Voiture> lv) {
        DefaultTableModel tm = (DefaultTableModel) tableRecherche.getModel();
        Object[] row = new Object[7];
        for (int i = 0; i < lv.size(); i++) {
            row[0] = lv.get(i).getMatricule();
            row[1] = lv.get(i).getMarque();
            row[2] = lv.get(i).getCouleur();
            row[3] = lv.get(i).getKilometrage();
            row[4] = lv.get(i).getCarburant();
            row[5] = lv.get(i).getPrix();
            row[6] = lv.get(i).getEtat();

            tm.addRow(row);
        }
    }

    /*============== recherche par marque =========================*/

    /* public void rechercherParMarque() {
     String etatD = "disponible";
     String inputMarque = marqueRecherchee.getText();
     String requete = "select * from voiture where etat='" + etatD + "' and marque='" + inputMarque + "'";
     ArrayList<Voiture> lv = lister(requete);
     showlistVoit(lv);}*/
    public void rechercherParMarque() {
        PreparedStatement prepSt = null;
        ResultSet resultat = null;
        String etatD = "disponible";

        DefaultTableModel tm = (DefaultTableModel) tableRecherche.getModel();

        try {
            String inputMarque = marqueRecherchee.getText();
            String requete = "select * from voiture where etat='" + etatD + "' and marque='" + inputMarque + "'";
            prepSt = con.prepareStatement(requete);
            resultat = prepSt.executeQuery();
            System.out.println(resultat);
            while (resultat.next()) {
                Object obj[] = new Object[]{resultat.getInt("matricule"),
                    resultat.getString("marque"),
                    resultat.getString("couleur"),
                    resultat.getInt("kilometrage"),
                    resultat.getString("carburant"),
                    resultat.getDouble("prix"),
                    resultat.getString("etat")};
                tm.addRow(obj);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "marque not found");
        }

    }

    /*============================recherche par date===============*/
    public void rechercherParDate() {
        PreparedStatement prepSt = null;
        ResultSet resultat = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateRecherchee.getDate();
        String dateR = dateFormat.format(date);

        System.out.println("date recherchée: " + dateR);

        String requete = "select location.idVoiture ,voiture.marque,voiture.couleur,voiture.carburant,voiture.kilometrage,voiture.prix,voiture.etat from location ,voiture  where location.idVoiture=voiture.matricule and location.dateRetour=" + "'" + dateR + "'";
        DefaultTableModel tm = (DefaultTableModel) tableRecherche.getModel();
        try {
            prepSt = con.prepareStatement(requete);
            resultat = prepSt.executeQuery();
            while (resultat.next()) {
                Object obj[] = new Object[]{
                    resultat.getInt("idVoiture"),
                    resultat.getString("marque"),
                    resultat.getString("couleur"),
                    resultat.getString("carburant"),
                    resultat.getInt("kilometrage"),
                    resultat.getDouble("prix"),
                    resultat.getString("etat")};

                tm.addRow(obj);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            // JOptionPane.showMessageDialog(null, "pas de voiture disponible pour cete date");
        }

    }
    /*=====================recherche par date et marque=========================*/

    public void rechercherParDateEtMarque() {
        String etatD = "disponible";
        String inputMarque = marqueRecherchee.getText();
        PreparedStatement prepSt = null;
        ResultSet resultat = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateRecherchee.getDate();
        String dateR = dateFormat.format(date);
        System.out.println("date recherchée: " + dateR);

        String requete = "select location.idVoiture ,voiture.marque,voiture.couleur,voiture.carburant,voiture.kilometrage,voiture.prix,voiture.etat from location ,voiture "
                + " where location.idVoiture=voiture.matricule and "
                + "((location.dateRetour=" + "'" + dateR + "'" +"and voiture.marque='" + inputMarque + "'"+")or(etat='" + etatD + "' and marque='" + inputMarque + "'"+")) group by matricule";
        System.out.println(requete);
        DefaultTableModel tm = (DefaultTableModel) tableRecherche.getModel();
        try {
            prepSt = con.prepareStatement(requete);
            resultat = prepSt.executeQuery();

            while (resultat.next()) {
                Object obj[] = new Object[]{
                    resultat.getInt("idVoiture"),
                    resultat.getString("marque"),
                    resultat.getString("couleur"),
                    resultat.getString("carburant"),
                    resultat.getInt("kilometrage"),
                    resultat.getDouble("prix"),
                    resultat.getString("etat")};

                tm.addRow(obj);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            // JOptionPane.showMessageDialog(null, "pas de voiture disponible pour cete date");
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableRecherche = new javax.swing.JTable();
        dateRecherchee = new com.toedter.calendar.JDateChooser();
        rechDate = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        marqueRecherchee = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        rechMarque = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        rechPar2 = new javax.swing.JLabel();
        nettoyer = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        tableRecherche.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "matricule", "marque", "couleur", "kilometrage", "carburant", "prix", "etat"
            }
        ));
        jScrollPane1.setViewportView(tableRecherche);

        rechDate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/info/views/assets/recherche.png"))); // NOI18N
        rechDate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rechDateMouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 153, 153));
        jLabel3.setText("taper une date");

        marqueRecherchee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                marqueRechercheeActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 153, 153));
        jLabel4.setText("Rechercher par marque et par date");

        rechMarque.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/info/views/assets/recherche.png"))); // NOI18N
        rechMarque.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rechMarqueMouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 153, 153));
        jLabel5.setText("taper une marque");

        rechPar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/info/views/assets/recherche.png"))); // NOI18N
        rechPar2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rechPar2MouseClicked(evt);
            }
        });

        nettoyer.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        nettoyer.setText("nettoyer");
        nettoyer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nettoyerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addGap(35, 35, 35))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(marqueRecherchee)
                    .addComponent(dateRecherchee, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(rechMarque)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(rechDate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rechPar2)
                        .addGap(46, 46, 46))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 583, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addComponent(nettoyer)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(rechPar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(jLabel3)
                                .addGap(18, 18, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rechDate)
                                    .addComponent(dateRecherchee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rechMarque)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(marqueRecherchee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5)))
                        .addGap(31, 31, 31)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(nettoyer)
                .addGap(79, 79, 79))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void marqueRechercheeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_marqueRechercheeActionPerformed

    }//GEN-LAST:event_marqueRechercheeActionPerformed

    private void rechMarqueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rechMarqueMouseClicked
        rechercherParMarque();
    }//GEN-LAST:event_rechMarqueMouseClicked

    private void rechDateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rechDateMouseClicked
        rechercherParDate();
    }//GEN-LAST:event_rechDateMouseClicked

    private void rechPar2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rechPar2MouseClicked
       rechercherParDateEtMarque(); // TODO add your handling code here:
    }//GEN-LAST:event_rechPar2MouseClicked

    private void nettoyerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nettoyerActionPerformed
        tableRecherche.setModel(new DefaultTableModel(null, new String[]{"matricule", "marque", "couleur", "kilometrage", "carburant", "prix", "etat"}));
    }//GEN-LAST:event_nettoyerActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RechercherVehicule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RechercherVehicule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RechercherVehicule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RechercherVehicule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RechercherVehicule().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser dateRecherchee;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField marqueRecherchee;
    private javax.swing.JButton nettoyer;
    private javax.swing.JLabel rechDate;
    private javax.swing.JLabel rechMarque;
    private javax.swing.JLabel rechPar2;
    public javax.swing.JTable tableRecherche;
    // End of variables declaration//GEN-END:variables
}
