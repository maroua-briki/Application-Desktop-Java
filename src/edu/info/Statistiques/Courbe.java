package edu.info.Statistiques;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Courbe extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
       VBox box=new VBox(tracer()) ;
Scene scene=new Scene(box);
primaryStage.setScene(scene);
primaryStage.show();
    }
    public PieChart  tracer(){
         PieChart pch = new PieChart();
        PieChart.Data slice1 = new PieChart.Data("BMW", 50);
        PieChart.Data slice2 = new PieChart.Data("Peugeot", 70);
        PieChart.Data slice3 = new PieChart.Data("Audi", 18);
        pch.getData().add(slice1);
        pch.getData().add(slice2);
        pch.getData().add(slice3);
        return pch;

    }
    public static void main(String [] args ){
        launch(args);
    }
    

}
