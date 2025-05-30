package com.pillcheck.medicalapp.Model.PatientModels;

import java.time.LocalDate;

public class Patient {
    private String cin;
    private String nom;
    private String prenom;
    private LocalDate dateNaiss;
    private String telephone;
    private String etat;
    private String sexe;
    private String nomTraitement;
    private int id ; 

    public Patient(String cin, String nom, String prenom, LocalDate dateNaiss, String telephone, String etat, String sexe, String nomTraitement) {
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaiss = dateNaiss;
        this.telephone = telephone;
        this.etat = etat;
        this.sexe = sexe;
        this.nomTraitement = nomTraitement;
    }
    public Patient(){
        
    }

    // Getters
    public String getCin() { return cin; }
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public LocalDate getDateNaissance() { return dateNaiss; }
    public String getTelephone() { return telephone; }
    public String getEtat() { return etat; }
    public String getSexe() { return sexe; }
    public String getNomTraitement() { return nomTraitement; }
    public int getId() { return id; }
        // Setters
    public void setCin(String cin) {
        this.cin = cin;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaiss = dateNaissance;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNomTraitement(String nomTraitement) {
        this.nomTraitement = nomTraitement;    }
}

