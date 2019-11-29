package edu.info.Statistiques;

import edu.info.connexionBD.Connexion;
import edu.info.model.Voiture;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class PlusLouee extends Application {

    /*============== variables globales ==================*/
    Connexion c = new Connexion();
    Connection con = c.connect();

    /*======================= calculer le nbr de location pour chaque voiture ===========================*/
    public PieChart nbrLocation() {
        // String requete = "select matricule,marque from Voiture where etat='louee' group by matricule ";
        String requete = "select count(idLocation) as nbrocation,idVoiture,voiture.marque"
                + " from location,voiture "
                + "where voiture.matricule=location.idVoiture"
                + " group by idVoiture ";
        PreparedStatement prepSt = null;
        ResultSet resultat = null;
        PieChart pch = new PieChart();
        pch.setTitle("Pie Chart taux de location pour chaque voiture");

        try {
            prepSt = con.prepareStatement(requete);
            resultat = prepSt.executeQuery();
            //DefaultTableModel tm;
            while (resultat.next()) {
                PieChart.Data slice = new PieChart.Data("matricule: "+resultat.getInt("idVoiture") + " |marque: "+
                        resultat.getString("marque")+" |nbr location: "+resultat.getInt("nbrocation"),resultat.getInt("nbrocation"));
                pch.getData().add(slice);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return pch;
    }

    @Override
    public void start(Stage primaryStage) {
        VBox box = new VBox(nbrLocation());
        Scene scene = new Scene(box);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
