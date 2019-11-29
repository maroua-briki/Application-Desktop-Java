package edu.info.Statistiques;

import edu.info.connexionBD.Connexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class TauxLocationParRapportAuStock extends Application {

    Connexion c = new Connexion();
    Connection con = c.connect();
    final CategoryAxis xAxis = new CategoryAxis();
    final NumberAxis yAxis = new NumberAxis();
    BarChart<String, Number> bc = new BarChart<>(xAxis, yAxis);

    /*======= Le taux de voitures louées par rapport au  stock dans une date bien déterminée =========*/

    public BarChart<String, Number> tauxLocation() {
        // "where voiture.etat='disponible' and
        PreparedStatement prepSt = null;
        ResultSet resultat = null;
        String requete = "select count(matricule) as stock,count(distinct idVoiture) as louees from voiture,location "
                + " where voiture.matricule=location.idVoiture "
                + "and (location.dateSortie between '2019-01-01' and '2019-01-31') ";
        System.out.println(requete);

        bc.setTitle("taux de locations de voitures par rapport au stock pour le mois de janvier");
        xAxis.setLabel("Jan 2019");
        yAxis.setLabel("taux location");

        try {
            prepSt = con.prepareStatement(requete);
            resultat = prepSt.executeQuery();
            while (resultat.next()) {
                int stock = resultat.getInt("stock");
                int location = resultat.getInt("louees");

                System.out.println("stock " + resultat.getInt("stock"));
                System.out.println("nbLocations " + resultat.getInt("louees"));
                Double taux = location * 1.0 / stock;
                System.out.println("taux" + taux);
                XYChart.Series series1 = new XYChart.Series();
                //series1.setName(info);
                series1.getData().add(new XYChart.Data("1", taux));
                bc.getData().addAll(series1);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);

        }
        return bc;
    }
    /*=============controler la taille du bar    ===========*/
     private void setMaxBarWidth(double maxBarWidth, double minCategoryGap) {
        double barWidth = 0;
        do {
            double catSpace = xAxis.getCategorySpacing();
            double avilableBarSpace = catSpace - (bc.getCategoryGap() + bc.getBarGap());
            barWidth = (avilableBarSpace / bc.getData().size()) - bc.getBarGap();
            if (barWidth > maxBarWidth) {
                avilableBarSpace = (maxBarWidth + bc.getBarGap()) * bc.getData().size();
                bc.setCategoryGap(catSpace - avilableBarSpace - bc.getBarGap());
            }
        } while (barWidth > maxBarWidth);

        do {
            double catSpace = xAxis.getCategorySpacing();
            double avilableBarSpace = catSpace - (minCategoryGap + bc.getBarGap());
            barWidth = Math.min(maxBarWidth, (avilableBarSpace / bc.getData().size()) - bc.getBarGap());
            avilableBarSpace = (barWidth + bc.getBarGap()) * bc.getData().size();
            bc.setCategoryGap(catSpace - avilableBarSpace - bc.getBarGap());
        } while (barWidth < maxBarWidth && bc.getCategoryGap() > minCategoryGap);
    }

    @Override
    public void start(Stage primaryStage) {
        bc = tauxLocation();
        Scene scene = new Scene(bc, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
        setMaxBarWidth(40, 10);
        bc.widthProperty().addListener((obs, b, b1) -> {
            Platform.runLater(() -> setMaxBarWidth(40, 10));
        });
    }

    public static void main(String[] args) {
        launch(args);
    }

}
