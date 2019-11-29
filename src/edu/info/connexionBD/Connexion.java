package edu.info.connexionBD;

import com.mysql.jdbc.Driver;
import java.sql.*;

public class Connexion {

   /* public static void main(String[] args) {
        Connexion c = new Connexion();
        Connection con = c.connect();
        System.out.println(con);
    }*/

    public static Connection connect() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("driver chargé avec succés");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/locationVehicule?zeroDateTimeBehavior=convertToNull",
                    "root", "");
            System.out.println("connexion etablie") ;
            

        } catch (ClassNotFoundException c) {
            System.out.println("echec de chargement de JDBC");
        } catch (SQLException e) {
            System.out.println("echec de connexion");
        }
        return con;

    }

}
