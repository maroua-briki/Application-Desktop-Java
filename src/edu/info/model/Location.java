
package edu.info.model;

import java.sql.Date;

public class Location {
    private int idLocation;
    private Voiture voiture;
    private Client client;
    private Employer employer;
    private Date dateSortie;
    private Date dateRetour;
    Location(int idLocation,Voiture v,Client c,Employer e,Date dateSortie,Date dateRetour){
        this.idLocation=idLocation;
        this.client=c;
        this.voiture=v;
        this.employer= e;
        this.dateSortie=dateRetour;
        this.dateSortie=dateSortie;
                
    }

    /**
     * @return the idLocation
     */
    public int getIdLocation() {
        return idLocation;
    }

    /**
     * @param idLocation the idLocation to set
     */
    public void setIdLocation(int idLocation) {
        this.idLocation = idLocation;
    }

    /**
     * @return the voiture
     */
    public Voiture getVoiture() {
        return voiture;
    }

    /**
     * @param voiture the voiture to set
     */
    public void setVoiture(Voiture voiture) {
        this.voiture = voiture;
    }

    /**
     * @return the client
     */
    public Client getClient() {
        return client;
    }

    /**
     * @param client the client to set
     */
    public void setClient(Client client) {
        this.client = client;
    }

    /**
     * @return the employer
     */
    public Employer getEmployer() {
        return employer;
    }

    /**
     * @param employer the employer to set
     */
    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    /**
     * @return the dateSortie
     */
    public Date getDateSortie() {
        return dateSortie;
    }

    /**
     * @param dateSortie the dateSortie to set
     */
    public void setDateSortie(Date dateSortie) {
        this.dateSortie = dateSortie;
    }

    /**
     * @return the dateRetour
     */
    public Date getDateRetour() {
        return dateRetour;
    }

    /**
     * @param dateRetour the dateRetour to set
     */
    public void setDateRetour(Date dateRetour) {
        this.dateRetour = dateRetour;
    }
    
    
    
    
}
