package edu.info.Statistiques;

import edu.info.connexionBD.Connexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class Score extends Application {

    /*============== variables globales ==================*/
    Connexion c = new Connexion();
    Connection con = c.connect();


    /*============formule de calcul de score= (nbr location *0.7)+(nbr annee experience*0.2)+(note*0.1)/
     (nbr location+nbr annee experience+note) ==============================*/
    public BarChart<String, Number> calculScore() {
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String, Number> bc = new BarChart<>(xAxis, yAxis);

        bc.setTitle("scores des employers");
        xAxis.setLabel("employers");
        yAxis.setLabel("scores");

        PreparedStatement prepSt = null;
        ResultSet resultat = null;
        String requete = "select location.idEmployer,count(idLocation) as nbrLocationParEmployer,employer.note,"
                + "employer.experience,employer.nom,employer.prenom "
                + "from location,employer "
                + "where location.idEmployer=employer.cin "
                + "group by idEmployer";
        try {
            prepSt = con.prepareStatement(requete);
            resultat = prepSt.executeQuery();
            System.out.println(resultat);
            while (resultat.next()) {
                /*====recuperation des champs de la req===============*/
                String nom = resultat.getString("nom");
                String prenom = resultat.getString("prenom");
                int id = resultat.getInt("idEmployer");
                String info = nom + " " + prenom + " " + id;
                int nbLoc = resultat.getInt("nbrLocationParEmployer");
                int note = resultat.getInt("note");
                int nbrAnneeExp = resultat.getInt("experience");

                /*============calcul de score================*/
                Double score = (nbLoc * 0.7 + note * 0.1 + nbrAnneeExp * 0.2) / (nbLoc + note + nbrAnneeExp);
                System.out.println("score= " + score);
                XYChart.Series series1 = new XYChart.Series();
                series1.setName(info);
                series1.getData().add(new XYChart.Data(info, score));
                bc.getData().addAll(series1);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);

        }
        return bc;
    }
  
    @Override
    public void start(Stage primaryStage) {
       Scene scene  = new Scene(calculScore(),800,600);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
