package com.pillcheck.medicalapp.Model;

public class User {
    private int id;             // User ID from the database (needed for session tracking)
    private String nom;
    private String email;
    private String motDePasse;

    // Full constructor
    public User(int id, String nom, String email, String motDePasse) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.motDePasse = motDePasse;
    }

    // Constructor without ID (e.g., before insertion)
    public User(String nom, String email, String motDePasse) {
        this.nom = nom;
        this.email = email;
        this.motDePasse = motDePasse;
    }

    // Constructor with email and password only (e.g., login)
    public User(String email, String motDePasse) {
        this.email = email;
        this.motDePasse = motDePasse;
    }

    // Empty constructor
    public User() {}

    // Getters
    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getEmail() {
        return email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
}
