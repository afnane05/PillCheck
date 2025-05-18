package com.pillcheck.medicalapp.Model;

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

    // Getters
    public String getCin() { return cin; }
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public LocalDate getDateNaissance() { return dateNaiss; }
    public String getTelephone() { return telephone; }
    public String getEtat() { return etat; }
    public String getSexe() { return sexe; }
    public String getNomTraitement() { return nomTraitement; }
}
