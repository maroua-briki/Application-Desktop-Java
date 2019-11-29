package edu.info.views;

import edu.info.connexionBD.Connexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class AjouterVehicule extends javax.swing.JFrame {

    public AjouterVehicule() {
        initComponents();
    }
    /*===to get data from the previous jframe u have to overload the second constructor==*/
    /* AjouterVehicule(String value) {
     initComponents();
     }*/
    Connexion c = new Connexion();
    Connection con = c.connect();
    /*===========================ajouter une vehicule=================*/

    public void ajouterVehicule() throws SQLException {
        String etat = "", carbu = "";
        PreparedStatement prepSt = null;
        ResultSet resultat = null;
        String requete = "insert into Voiture values(?,?,?,?,?,?,?)";
        try {
            prepSt = con.prepareStatement(requete);
            prepSt.setInt(1, Integer.parseInt(matricule.getText()));
            prepSt.setString(2, marque.getText());
            prepSt.setString(3, couleur.getText());
            carbu = carburant.getSelectedItem().toString();
            prepSt.setString(4, carbu);

            if (disponible.isSelected()) {
                etat = disponible.getText();//etat="disponible"
            }
            if (louee.isSelected()) {
                etat = louee.getText();//etat = "gazoil";
            }

            prepSt.setString(5, etat);
            prepSt.setDouble(6, Double.parseDouble(prix.getText()));

            prepSt.setInt(7, Integer.parseInt(kilometrage.getText()));

            prepSt.executeUpdate();
            JOptionPane.showMessageDialog(null, "inserted successfully");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
    /*======================supprimer vehicule=======================*/

    public void deleteVehicule() {
        String requete = "delete from voiture where matricule=?";
        PreparedStatement prepSt = null;
        ResultSet resultat = null;
        try {
            prepSt = con.prepareStatement(requete);
            prepSt.setString(1, matricule.getText());
            prepSt.execute();
            /*============= refresh the table ===================*/
            // refreshJtable();
            JOptionPane.showMessageDialog(null, "deleted successfully");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    /*=====================update vehicule=====================*/

    public void updateVehicule() {
        String etat = "", carbu = "";
        PreparedStatement prepSt = null;
        ResultSet resultat = null;
        /* u have to update also the primary key*/
        String requete = "insert into Voiture values(?,?,?,?,?,?,?)";
        /*int row = lv.tableVoiture.getSelectedRow();
         String value = (tableVoiture.getModel().getValueAt(row, 0).toString());*/
        // prepSt.setString(1,matricule.getText());
        /* String requete="UPDATE voiture "
         + "SET matricule=? ,marque=?,couleur=?,carburant=?,etat=?,prix=?,kilometrage=?"
         + " WHERE matricule=" + value;*/
        try {
            prepSt = con.prepareStatement(requete);
            prepSt.setInt(1, Integer.parseInt(matricule.getText()));
            prepSt.setString(2, marque.getText());
            prepSt.setString(3, couleur.getText());
            carbu = carburant.getSelectedItem().toString();
            prepSt.setString(4, carbu);

            if (disponible.isSelected()) {
                etat = disponible.getText();//etat="disponible"
            }
            if (louee.isSelected()) {
                etat = louee.getText();//etat = "gazoil";
            }

            prepSt.setString(5, etat);
            prepSt.setDouble(6, Double.parseDouble(prix.getText()));

            prepSt.setInt(7, Integer.parseInt(kilometrage.getText()));

            prepSt.executeUpdate();
            JOptionPane.showMessageDialog(null, "updated successfully");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        annuler = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        matricule = new javax.swing.JTextField();
        matriculeLabel = new javax.swing.JLabel();
        marque = new javax.swing.JTextField();
        marqueLabel = new javax.swing.JLabel();
        couleur = new javax.swing.JTextField();
        couleurLabel = new javax.swing.JLabel();
        carburant = new javax.swing.JComboBox();
        carburantLabel = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        etatLabel = new javax.swing.JLabel();
        disponible = new javax.swing.JRadioButton();
        louee = new javax.swing.JRadioButton();
        prix = new javax.swing.JTextField();
        kilometrage = new javax.swing.JTextField();
        prixLabel = new javax.swing.JLabel();
        kiloLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        ajouter1 = new javax.swing.JButton();
        supprimer = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        annuler.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        annuler.setForeground(new java.awt.Color(204, 0, 0));
        annuler.setText("annuler");
        annuler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                annulerActionPerformed(evt);
            }
        });
        jPanel1.add(annuler, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 300, -1, -1));

        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        matricule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                matriculeActionPerformed(evt);
            }
        });

        matriculeLabel.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        matriculeLabel.setForeground(new java.awt.Color(0, 0, 102));
        matriculeLabel.setText("matricule");

        marque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                marqueActionPerformed(evt);
            }
        });

        marqueLabel.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        marqueLabel.setForeground(new java.awt.Color(0, 0, 102));
        marqueLabel.setText("marque");

        couleur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                couleurActionPerformed(evt);
            }
        });

        couleurLabel.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        couleurLabel.setForeground(new java.awt.Color(0, 0, 102));
        couleurLabel.setText("couleur");

        carburant.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "essence", "gazoil" }));
        carburant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                carburantActionPerformed(evt);
            }
        });

        carburantLabel.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        carburantLabel.setForeground(new java.awt.Color(0, 0, 102));
        carburantLabel.setText("carburant");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(carburantLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(carburant, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(marqueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(marque, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(matriculeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(matricule, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(couleurLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(couleur, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(23, 23, 23))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(matriculeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(matricule, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(marque, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(marqueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(couleur, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(couleurLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(carburantLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(carburant, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 240, 230));

        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.setForeground(new java.awt.Color(0, 102, 204));

        etatLabel.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        etatLabel.setForeground(new java.awt.Color(0, 0, 102));
        etatLabel.setText("etat");

        buttonGroup1.add(disponible);
        disponible.setText("disponible");

        buttonGroup1.add(louee);
        louee.setText("louee");
        louee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loueeActionPerformed(evt);
            }
        });

        prix.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prixActionPerformed(evt);
            }
        });

        kilometrage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kilometrageActionPerformed(evt);
            }
        });

        prixLabel.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        prixLabel.setForeground(new java.awt.Color(0, 0, 102));
        prixLabel.setText("prix");

        kiloLabel.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        kiloLabel.setForeground(new java.awt.Color(0, 0, 102));
        kiloLabel.setText("kilometrage");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                            .addComponent(etatLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(disponible)
                            .addGap(18, 18, 18)
                            .addComponent(louee)
                            .addGap(16, 16, 16))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                            .addComponent(prixLabel)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(prix, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap()))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(kiloLabel)
                        .addGap(18, 18, 18)
                        .addComponent(kilometrage, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(46, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kilometrage, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(kiloLabel))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(prix, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(prixLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etatLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(disponible)
                    .addComponent(louee))
                .addGap(45, 45, 45))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 40, 260, 230));

        jLabel1.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 0, 0));
        jLabel1.setText("Gérer les véhicules dans le stock");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 270, 20));

        ajouter1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        ajouter1.setForeground(new java.awt.Color(0, 102, 0));
        ajouter1.setText("ajouter");
        ajouter1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ajouter1ActionPerformed(evt);
            }
        });
        jPanel1.add(ajouter1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 300, -1, -1));

        supprimer.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        supprimer.setForeground(new java.awt.Color(255, 0, 0));
        supprimer.setText("supprimer");
        supprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supprimerActionPerformed(evt);
            }
        });
        jPanel1.add(supprimer, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 300, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void annulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_annulerActionPerformed
        this.dispose();
    }//GEN-LAST:event_annulerActionPerformed

    private void matriculeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_matriculeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_matriculeActionPerformed

    private void couleurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_couleurActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_couleurActionPerformed

    private void marqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_marqueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_marqueActionPerformed

    private void carburantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_carburantActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_carburantActionPerformed

    private void loueeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loueeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_loueeActionPerformed

    private void prixActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prixActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_prixActionPerformed

    private void kilometrageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kilometrageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kilometrageActionPerformed

    private void ajouter1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ajouter1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ajouter1ActionPerformed

    private void supprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supprimerActionPerformed
        deleteVehicule();
        ListeVoiture lv = new ListeVoiture();
        lv.setVisible(true);
        lv.refreshJtable();
    }//GEN-LAST:event_supprimerActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AjouterVehicule().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ajouter1;
    private javax.swing.JButton annuler;
    private javax.swing.ButtonGroup buttonGroup1;
    public javax.swing.JComboBox carburant;
    private javax.swing.JLabel carburantLabel;
    public javax.swing.JTextField couleur;
    private javax.swing.JLabel couleurLabel;
    public javax.swing.JRadioButton disponible;
    private javax.swing.JLabel etatLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel kiloLabel;
    public javax.swing.JTextField kilometrage;
    public javax.swing.JRadioButton louee;
    public javax.swing.JTextField marque;
    private javax.swing.JLabel marqueLabel;
    public javax.swing.JTextField matricule;
    private javax.swing.JLabel matriculeLabel;
    public javax.swing.JTextField prix;
    private javax.swing.JLabel prixLabel;
    private javax.swing.JButton supprimer;
    // End of variables declaration//GEN-END:variables
}
