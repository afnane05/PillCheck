package com.pillcheck.medicalapp.Model.RdvModels;

import java.time.LocalDateTime;

public class Rdv {
    private int idRdv;
    public LocalDateTime dateRdv;
    public String motifRdv;
    public String statut;


    public Rdv(int idRdv,LocalDateTime dateRdv , String motifRdv, String statut) {
        this.idRdv = idRdv;
        this.dateRdv = dateRdv;
        this.motifRdv = motifRdv;
        this.statut = statut;

    }
    public Rdv(String motif, String statut, LocalDateTime dateRdv) {
        this.motifRdv = motif;
        this.statut = statut;
        this.dateRdv = dateRdv;
    }
    public Rdv(){
        
    }

    public int getIdRdv() { return idRdv; }
    public LocalDateTime getDateRdv() { return dateRdv; }
    public String getMotifRdv() { return motifRdv; }
    public String getStatut() { return statut; }

    public void setIdRdv(int idRdv) {
        this.idRdv = idRdv;
    }

    public void setDateRdv(LocalDateTime dateRdv) {
        this.dateRdv = dateRdv;
    }
        
    public void setMotifRdv(String motifRdv) {
        this.motifRdv = motifRdv;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
    

}
