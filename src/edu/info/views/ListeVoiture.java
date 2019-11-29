package edu.info.views;

import edu.info.connexionBD.Connexion;
import edu.info.model.Client;
import edu.info.model.Voiture;
import edu.info.model.VoitureLouee;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class ListeVoiture extends javax.swing.JFrame {

    public ListeVoiture() {
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
            e.printStackTrace();
        }
        return lv;
    }
 
    public void showlistVoit(ArrayList<Voiture> lv) {
        DefaultTableModel tm = (DefaultTableModel) tableVoiture.getModel();
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
     /*============= refresh the table ===================*/

    public void refreshJtable() {
        DefaultTableModel model = (DefaultTableModel) tableVoiture.getModel();
        model.setRowCount(0);
        String query = "select * from voiture";
        ArrayList<Voiture> lv = lister(query);
        showlistVoit(lv);
    }
  
    /*==================sected row will be showed in another frame======================*/
    /*to do that :show data in other jframe : u have 2go each field: right click customize code --> public */
        AjouterVehicule av = new AjouterVehicule();

          //  public String showSelectedRow() {

  public void showSelectedRow() {

        int i = tableVoiture.getSelectedRow();
        TableModel model = tableVoiture.getModel();
        String id = model.getValueAt(i, 0).toString();
        String marq = model.getValueAt(i, 1).toString();
        String coul = model.getValueAt(i, 2).toString();
        String kilomet = model.getValueAt(i, 3).toString();
        String carbu = model.getValueAt(i, 4).toString();
        String prix = model.getValueAt(i, 5).toString();
        String etat = model.getValueAt(i, 6).toString();
        System.out.println(etat);
        av.setVisible(true);
        av.pack();
        av.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        av.matricule.setText(id);
        av.marque.setText(marq);
        av.couleur.setText(coul);
        av.kilometrage.setText(kilomet);
        av.carburant.setSelectedItem(carbu);
        av.prix.setText(prix);
        /* on selectionne le radio button dont la valeur = valeur de row selectionnée*/
        if (etat.equals("disponible"))
            av.disponible.setSelected(true);
        else
            av.louee.setSelected(true);
       // return id;
        }

    
  

    /*============== recherche par marque =========================*/
    public ArrayList<Voiture> rechercherParMarque(String selectedMarque) {
        String etatD = "disponible";
        String requete = "select * from voiture where etat='" + etatD + "' and marque='" + selectedMarque + "'";
        ArrayList<Voiture> lv = lister(requete);
        return lv;
    }
    /*==================liste des items de combobox=============================*/
    /*  String[] ComboboxItems = {"Peugeot", "BMW", "Audi"};

     public void showVoitParMarque() {
     for (String st : ComboboxItems) {
     System.out.println(st);
     if (st == marques.getSelectedItem()) {
     System.out.println(marques.getSelectedItem());
     ArrayList<Voiture> lv = rechercherParMarque(st);
     showlistVoit(lv);
     }
     }
     }*/

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        nettoyer = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableVoiture = new javax.swing.JTable();
        afficher = new javax.swing.JLabel();
        passerLocation = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        edit = new javax.swing.JLabel();
        ajouter = new javax.swing.JLabel();

        jFormattedTextField1.setText("jFormattedTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(0, 51, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(0, 51, 102));
        jLabel1.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 102));
        jLabel1.setText("vehicules en stock");

        nettoyer.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        nettoyer.setText("nettoyer");
        nettoyer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nettoyerActionPerformed(evt);
            }
        });

        tableVoiture.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "matricule", "marque", "couleur", "kilometrage", "carburant", "prix", "etat"
            }
        ));
        jScrollPane1.setViewportView(tableVoiture);

        afficher.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/info/views/assets/afficher.png"))); // NOI18N
        afficher.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                afficherMouseClicked(evt);
            }
        });

        passerLocation.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/info/views/assets/passer.png"))); // NOI18N
        passerLocation.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                passerLocationMouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 204));
        jLabel3.setText("consultez details des locations");

        edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/info/views/assets/modifier.png"))); // NOI18N
        edit.setText("jLabel2");
        edit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editMouseClicked(evt);
            }
        });

        ajouter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/info/views/assets/ajouter.png"))); // NOI18N
        ajouter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ajouterMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(edit, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(ajouter)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(passerLocation)
                        .addGap(117, 117, 117))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 509, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(nettoyer)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(66, 66, 66)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(afficher)))))
                        .addContainerGap(193, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(nettoyer))
                            .addComponent(afficher))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(ajouter)
                            .addComponent(edit))
                        .addGap(32, 32, 32))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(passerLocation)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(141, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nettoyerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nettoyerActionPerformed
        tableVoiture.setModel(new DefaultTableModel(null, new String[]{"matricule", "marque", "couleur", "kilometrage", "carburant", "prix", "etat"}));
    }//GEN-LAST:event_nettoyerActionPerformed

    private void ajouterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ajouterMouseClicked
        AjouterVehicule aj = new AjouterVehicule();
        aj.setVisible(true);
        this.hide();
    }//GEN-LAST:event_ajouterMouseClicked

    private void afficherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_afficherMouseClicked
        String requete = "select * from voiture";
        ArrayList<Voiture> lv = lister(requete);
        showlistVoit(lv);        // TODO add your handling code here:
    }//GEN-LAST:event_afficherMouseClicked

    private void editMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editMouseClicked
     /* String value=  showSelectedRow();
       AjouterVehicule av = new AjouterVehicule(value);*/
        showSelectedRow();
        
      
    }//GEN-LAST:event_editMouseClicked

    private void passerLocationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_passerLocationMouseClicked
        ListeVoitureLouee louee =new ListeVoitureLouee();
        louee.setVisible(true);
        
    }//GEN-LAST:event_passerLocationMouseClicked

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListeVoiture().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel afficher;
    private javax.swing.JLabel ajouter;
    private javax.swing.JLabel edit;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton nettoyer;
    private javax.swing.JLabel passerLocation;
    public javax.swing.JTable tableVoiture;
    // End of variables declaration//GEN-END:variables

}
