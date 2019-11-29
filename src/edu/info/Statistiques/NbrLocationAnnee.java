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
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class NbrLocationAnnee extends Application {

    /*============== variables globales ==================*/
    Connexion c = new Connexion();
    Connection con = c.connect();

    /*=================	Nombre  de voitures louée au fil d’une année==============*/
    public LineChart<String, Number> locationDansUneAnnee() {
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("mois");
        final LineChart<String, Number> lineChart
                = new LineChart<String, Number>(xAxis, yAxis);
        lineChart.setTitle("Nombre  de voitures louées dans 2019");
        XYChart.Series series = new XYChart.Series();
        series.setName("nombre de locations");

        PreparedStatement prepSt = null;
        ResultSet resultat = null;
        String requete = "select idVoiture,count(*) as nbrLocation , MONTH(dateSortie) as mois "
                + "from location "
                + "where (dateSortie between '2019-01-01' and '2019-12-31')"
                + "group by mois";
        try {
            prepSt = con.prepareStatement(requete);
            resultat = prepSt.executeQuery();
            while (resultat.next()) {
                series.getData().add(new XYChart.Data(resultat.getString("mois"), resultat.getInt("nbrLocation")));
                lineChart.getData().add(series);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);

        }
        return lineChart;
    }

    @Override
    public void start(Stage primaryStage) {

        Scene scene = new Scene(locationDansUneAnnee(), 800, 600);
        primaryStage.setTitle("nombre de location au cours d'une année");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
