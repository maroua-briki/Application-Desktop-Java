package edu.info.model;

public class Voiture {

    private int matricule;
    private String marque;
    private String couleur;
    private int kilometrage;
    private double prix;
    private String carburant, etat;

    public Voiture(int matricule, String marque, String couleur, int kilometrage,String carburant,double prix, String etat) {
        this.matricule = matricule;
        this.marque = marque;
        this.couleur = couleur;
        this.kilometrage=kilometrage;
        this.carburant = carburant;
        this.prix=prix;
        this.etat = etat;
    }

    /**
     * @return the matricule
     */
    public int getMatricule() {
        return matricule;
    }

    /**
     * @param matricule the matricule to set
     */
    public void setMatricule(int matricule) {
        this.matricule = matricule;
    }

    /**
     * @return the marque
     */
    public String getMarque() {
        return marque;
    }

    /**
     * @param marque the marque to set
     */
    public void setMarque(String marque) {
        this.marque = marque;
    }

    /**
     * @return the couleur
     */
    public String getCouleur() {
        return couleur;
    }

    /**
     * @param couleur the couleur to set
     */
    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    /**
     * @return the kilometrage
     */
    public int getKilometrage() {
        return kilometrage;
    }

    /**
     * @param kilometrage the kilometrage to set
     */
    public void setKilometrage(int kilometrage) {
        this.kilometrage = kilometrage;
    }

    /**
     * @return the prix
     */
    public double getPrix() {
        return prix;
    }

    /**
     * @param prix the prix to set
     */
    public void setPrix(double prix) {
        this.prix = prix;
    }

    /**
     * @return the carburant
     */
    public String getCarburant() {
        return carburant;
    }

    /**
     * @param carburant the carburant to set
     */
    public void setCarburant(String carburant) {
        this.carburant = carburant;
    }

    /**
     * @return the etat
     */
    public String getEtat() {
        return etat;
    }

    /**
     * @param etat the etat to set
     */
    public void setEtat(String etat) {
        this.etat = etat;
    }
    

}