package com.pillcheck.medicalapp.Model.TraitementModels;
import java.time.LocalDate;
public class Traitement {
    private String NOM_TRAITEMENT;
    private String TYPE_Traitement;
    private String MALADIE;
    private String DESCRIPTION;
    private LocalDate  DATE_DEBUT;
    private LocalDate DATE_FIN;
    private String DUREE_ESTIMEE;
    private String POSOLOGIE;
    private String EFFETS_SECONDAIRES;
    private String ETAT;
    private int id ; 
    
    public Traitement(String NOM_TRAITEMENT,String TYPE_Traitement, String MALADIE,String DESCRIPTION,
                      LocalDate DATE_DEBUT,LocalDate DATE_FIN,String DUREE_ESTIMEE,String POSOLOGIE,
                      String EFFETS_SECONDAIRES,String ETAT ) {
        this.NOM_TRAITEMENT= NOM_TRAITEMENT;
        this.TYPE_Traitement=TYPE_Traitement;
        this.MALADIE=MALADIE;
        this.DESCRIPTION=DESCRIPTION;
        this.DATE_DEBUT=DATE_DEBUT;
        this.DATE_FIN=DATE_FIN;
        this.DUREE_ESTIMEE=DUREE_ESTIMEE;
        this.POSOLOGIE=POSOLOGIE;
        this.EFFETS_SECONDAIRES=EFFETS_SECONDAIRES;
         this.ETAT=ETAT;
    }
    public Traitement(){}
    public int getId() { return id; } 
<<<<<<< HEAD
        public void setId(int id) {
        this.id = id;
    }
=======
    public void setid(int id) {this.id=id;}
>>>>>>> 7fec08144c9572542af357eae6250e70760e4f44
    public String  getNOM_TRAITEMENT() { return NOM_TRAITEMENT; }
    public void setNOM_TRAITEMENT(String NOM_TRAITEMENT) { this.NOM_TRAITEMENT = NOM_TRAITEMENT; }

    public String getTYPE_TRAITEMENT() { return TYPE_Traitement; }
    public void setTYPE_TRAITEMENT(String TYPE_TRAITEMENT) { this.TYPE_Traitement=TYPE_TRAITEMENT; }
    
    public String getMALADIE() { return MALADIE; }
    public void setMALADIE(String MALADIE) { this.MALADIE = MALADIE; }
    

    public String getDESCRIPTION() { return DESCRIPTION; }
    public void setDESCRIPTION(String DESCRIPTION) { this.DESCRIPTION = DESCRIPTION; }

    public LocalDate getDATE_DEBUT() { return DATE_DEBUT; }
    public void setDATE_DEBUT(LocalDate DATE_DEBUT) { this.DATE_DEBUT = DATE_DEBUT; }

    public LocalDate getDATE_FIN() { return DATE_FIN; }
    public void setDATE_FIN(LocalDate DATE_FIN) { this.DATE_FIN = DATE_FIN; }
    
     public String getDUREE_ESTIMEE() { return DUREE_ESTIMEE; }
    public void setDUREE_ESTIMEE(String DUREE_ESTIMEE) { this.DUREE_ESTIMEE = DUREE_ESTIMEE; }

     public String getPOSOLOGIE() { return POSOLOGIE; }
    public void setPOSOLOGIE(String POSOLOGIE) { this.POSOLOGIE = POSOLOGIE; }
    
     public String getEFFETS_SECONDAIRES() { return EFFETS_SECONDAIRES; }
    public void setEFFETS_SECONDAIRES(String EFFETS_SECONDAIRES) { this.EFFETS_SECONDAIRES =EFFETS_SECONDAIRES;}
    
    public String getETAT() { return ETAT; }
    public void setETAT( String ETAT) {this.ETAT = ETAT;}
    
    
  
}
