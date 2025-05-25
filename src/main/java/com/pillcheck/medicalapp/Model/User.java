package com.pillcheck.medicalapp.Model;

public class User {
    private String nom ; 
    private String email ; 
    private String motDePasse ; 
    
    public User(String nom , String email , String motDePasse){
        this.nom = nom ;
        this.email = email ;
        this.motDePasse = motDePasse ;
        
    }
    public User(String email, String motDePasse){
        this.email = email ;
        this.motDePasse = motDePasse ;
    }
    public User(){
        
    } 
    public String getNom(){return nom ;}
    public String getEmail(){return email ;}
    public String getMotDePasse(){return motDePasse ;}
    
    public void setNom(String nom){
        this.nom = nom ;
    }
    public void setEmail(String email){
        this.email = email ;
    }
    public void setMotDePasse(String motDePasse){
        this.motDePasse = motDePasse ;
    }
    
    
}


