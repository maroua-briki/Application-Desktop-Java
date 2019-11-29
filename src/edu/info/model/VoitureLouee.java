package edu.info.model;

import java.sql.Date;

public class VoitureLouee {

    private int matriculeV;
    // private String marque;
    private int client;
    private int employer;
    private Date dateSortie;
    private Date dateRetour;

    public VoitureLouee(int matriculeV, int client, int employer, Date dateSortie, Date dateRetour) {
        this.matriculeV = matriculeV;
        // this.marque = marque;
        this.employer = employer;
        this.client = client;
        this.dateSortie = dateSortie;
        this.dateRetour = dateRetour;

    }

    /**
     * @return the matriculeV
     */
    public int getMatriculeV() {
        return matriculeV;
    }

    /**
     * @param matriculeV the matriculeV to set
     */
    public void setMatriculeV(int matriculeV) {
        this.matriculeV = matriculeV;
    }

    /**
     * @return the client
     */
    public int getClient() {
        return client;
    }

    /**
     * @param client the client to set
     */
    public void setClient(int client) {
        this.client = client;
    }

    /**
     * @return the employer
     */
    public int getEmployer() {
        return employer;
    }

    /**
     * @param employer the employer to set
     */
    public void setEmployer(int employer) {
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