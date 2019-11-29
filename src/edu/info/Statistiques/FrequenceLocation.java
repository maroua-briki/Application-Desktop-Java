package edu.info.Statistiques;

import edu.info.connexionBD.Connexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class FrequenceLocation extends Application {
    /*============== variables globales ==================*/

    Connexion c = new Connexion();
    Connection con = c.connect();
    /*	Pour une voiture bien déterminée,
     la courbe représentant  la fréquence de sa location 
     dans un intervalle de temps bien déterminé===================*/

    public ScatterChart<Number, Number> calculerFrequence() {
        final NumberAxis xAxis = new NumberAxis(0, 10, 1);
        final NumberAxis yAxis = new NumberAxis();
        final ScatterChart<Number, Number> sc = new ScatterChart<>(xAxis, yAxis);
        xAxis.setLabel("jours");
        yAxis.setLabel("frequence(location)");
        sc.setTitle("frequence de location de la voiture 71 de 01 Jan 2019 à 05 Feb 2019 ");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("N");
        PreparedStatement prepSt = null;
        ResultSet resultat = null;
        String requete = "select count(idLocation)/DATEDIFF( '2019-02-05','2019-01-01') as N71,Day(dateSortie) as jour "
                + "from location where idVoiture=71 "
                + "and (dateSortie between '2019-01-01' and '2019-02-05' )"
                + "group by jour";
        System.out.println(requete);
        try {
            prepSt = con.prepareStatement(requete);
            resultat = prepSt.executeQuery();
            while (resultat.next()) {
                System.out.println("freq" + resultat.getDouble("N71"));
                System.out.println("jour" + resultat.getInt("jour"));
                series1.getData().add(new XYChart.Data(resultat.getInt("jour"), resultat.getDouble("N71")));
                sc.getData().addAll(series1);

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);

        }
        return sc ;
    }

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(calculerFrequence(), 600, 400);
        primaryStage.setTitle("frequence");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
