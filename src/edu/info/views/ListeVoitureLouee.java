package edu.info.views;

import edu.info.connexionBD.Connexion;
import edu.info.model.Voiture;
import edu.info.model.VoitureLouee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ListeVoitureLouee extends javax.swing.JFrame {

    public ListeVoitureLouee() {
        initComponents();
    }
    Connexion c = new Connexion();
    Connection con = c.connect();

    public void afficherLocationv1() {
        PreparedStatement prepSt = null;
        ResultSet resultat = null;
        String requete = "select location.idVoiture, idLocation,"
                + "location.dateRetour,location.dateSortie,"
                + "location.idClient,location.idEmployer,client.nom ,employer.prenom from location ,"
                + "client ,employer where location.idEmployer=employer.cin and location.idClient=client.cin ";
        DefaultTableModel tm = (DefaultTableModel) tableLouee.getModel();
        try {
            prepSt = con.prepareStatement(requete);
            resultat = prepSt.executeQuery();
            System.out.println(resultat);
            while (resultat.next()) {
                Object obj[] = new Object[]{resultat.getInt("idLocation"),
                    resultat.getString("idVoiture"),
                    resultat.getString("dateSortie"),
                    resultat.getString("dateRetour"),
                    resultat.getString("idClient"),
                    resultat.getString("idEmployer"),
                    resultat.getString("nom"),
                    resultat.getString("prenom")};
                tm.addRow(obj);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);

        }
        tableLouee.setModel(tm);

    }

    /*================afficher voitures louées ================*/
    /*    public void afficherLocationv1() {
     PreparedStatement prepSt = null;
     ResultSet resultat = null;
     String requete = "select * from location";
     DefaultTableModel tm = (DefaultTableModel)tableLouee.getModel();
     try {
     prepSt=con.prepareStatement(requete);
     resultat = prepSt.executeQuery();
     System.out.println(resultat);
     while (resultat.next()) {
     Object obj[] = new Object[]{resultat.getInt("idLocation"),
     resultat.getString("idVoiture"),
     resultat.getString("dateSortie"),
     resultat.getString("dateRetour"),
     resultat.getString("idClient"),
     resultat.getString("idEmployer")};
     tm.addRow(obj);
     }
     } catch (SQLException ex) {
     JOptionPane.showMessageDialog(null, ex);

     }
     tableLouee.setModel(tm);

     }*/
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tableLouee = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        afficher = new javax.swing.JLabel();
        ajouterLocation = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tableLouee.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "idLocation", "matricule", "dateSortie", "dateRetour", "cinClient", "cinEmployer", "nomCl", "prenomE"
            }
        ));
        jScrollPane1.setViewportView(tableLouee);

        jLabel1.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 0, 51));
        jLabel1.setText("Voitures louées");

        afficher.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/info/views/assets/display.png"))); // NOI18N
        afficher.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                afficherMouseClicked(evt);
            }
        });

        ajouterLocation.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/info/views/assets/ajouter.png"))); // NOI18N
        ajouterLocation.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ajouterLocationMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(afficher)
                        .addGap(36, 36, 36)
                        .addComponent(ajouterLocation))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(216, 216, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(afficher))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(ajouterLocation)))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void afficherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_afficherMouseClicked
        afficherLocationv1();        // TODO add your handling code here:
    }//GEN-LAST:event_afficherMouseClicked

    private void ajouterLocationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ajouterLocationMouseClicked
        LouerVehicule aj = new LouerVehicule();
        aj.setVisible(true);
        this.hide();
    }//GEN-LAST:event_ajouterLocationMouseClicked

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListeVoitureLouee().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel afficher;
    private javax.swing.JLabel ajouterLocation;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableLouee;
    // End of variables declaration//GEN-END:variables
}
