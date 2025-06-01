package com.pillcheck.medicalapp.Model.TraitementModels;

import com.pillcheck.medicalapp.Model.Session;
import com.pillcheck.medicalapp.Model.User;
import com.pillcheck.medicalapp.Model.BaseDonneeConnexion;
import java.sql.*;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class TraitementDao {
    
  // private Connection getConnection() throws SQLException {
    //    return DriverManager.getConnection("jdbc:mysql://localhost:3306/pillcheck", "username", "password");
    //}
    
    public boolean ajouterTraitement(Traitement traitement) {
        String sql = "INSERT INTO traitement (NOM_TRAITEMENT, TYPE_Traitement, MALADIE, DESCRIPTION, DATE_DEBUT, DATE_FIN, DUREE_ESTIMEE, POSOLOGIE,  EFFETS_SECONDAIRES,ETAT, user_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)";
                      
                   
                     
        try (Connection conn = BaseDonneeConnexion.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            User currentUser = Session.getInstance().getCurrentUser();
            if (currentUser == null) {
                System.out.println("No user in session.");
                return false;
            }
            stmt.setString(1, traitement.getNOM_TRAITEMENT());
            stmt.setString(2, traitement.getTYPE_TRAITEMENT());
            stmt.setString(3, traitement.getMALADIE());
            stmt.setString(4, traitement.getDESCRIPTION());
            stmt.setDate(5, java.sql.Date.valueOf(traitement.getDATE_DEBUT()));
            stmt.setDate(6, java.sql.Date.valueOf(traitement.getDATE_FIN()));
            stmt.setString(7, traitement.getDUREE_ESTIMEE());
            stmt.setString(8, traitement.getPOSOLOGIE());
            stmt.setString(9, traitement.getEFFETS_SECONDAIRES());
            stmt.setString(10, traitement.getETAT());
            stmt.setInt(11, currentUser.getId());
            
            stmt.executeUpdate();
            return true;
            
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static List<Traitement> getAllTraitements(){
        List<Traitement> liste = new ArrayList<>();
        String sql = "SELECT * FROM traitement WHERE user_id = ?";
        
        try (Connection conn = BaseDonneeConnexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)){
                User currentUser = Session.getInstance().getCurrentUser();
                if (currentUser == null) {
                    System.out.println("No user in session.");
                    return liste;
                }

                stmt.setInt(1, currentUser.getId());
                ResultSet rs = stmt.executeQuery() ;

                while (rs.next()) {
                     Traitement traitement = new Traitement();
                        traitement.setNOM_TRAITEMENT(rs.getString("NOM_TRAITEMENT"));
                        traitement.setTYPE_TRAITEMENT(rs.getString("TYPE_Traitement"));
                        traitement.setMALADIE(rs.getString("MALADIE"));
                        traitement.setDESCRIPTION(rs.getString("DESCRIPTION"));
                        traitement.setDATE_DEBUT(rs.getDate("DATE_DEBUT").toLocalDate());
                        traitement.setDATE_FIN(rs.getDate("DATE_FIN").toLocalDate());
                        traitement.setDUREE_ESTIMEE(rs.getString("DUREE_ESTIMEE"));
                        traitement.setPOSOLOGIE(rs.getString("POSOLOGIE"));
                        traitement.setEFFETS_SECONDAIRES(rs.getString("EFFETS_SECONDAIRES"));
                        traitement.setETAT(rs.getString("ETAT"));
                        traitement.setId(rs.getInt("id")); 
                        
                    liste.add(traitement);
                }

        } catch (SQLException e) {
           // System.err.println("Erreur lors de la récupération des traitements: " + e.getMessage());
            e.printStackTrace();
        }
        
        return liste;
    }
    
    public static boolean supprimerTraitement(int id ) {
        String sql = "DELETE FROM traitement WHERE id = ? AND user_id = ?";
        
        try (Connection conn = BaseDonneeConnexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            User currentUser = Session.getInstance().getCurrentUser();
            if (currentUser == null) {
                System.out.println("No user in session.");
                return false;
            }

            stmt.setInt(1, id);
            stmt.setInt(2, currentUser.getId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
public boolean modifierTraitement(Traitement traitement) {
    // Calculer la durée estimée
    if (traitement.getDATE_DEBUT() != null && traitement.getDATE_FIN() != null) {
        long jours = ChronoUnit.DAYS.between(traitement.getDATE_DEBUT(), traitement.getDATE_FIN()) + 1;
        traitement.setDUREE_ESTIMEE(jours + " jour(s)");
    }

    String sql = "UPDATE traitement SET NOM_TRAITEMENT = ?, TYPE_Traitement = ?, MALADIE = ?, "
               + "DESCRIPTION = ?, DATE_DEBUT = ?, DATE_FIN = ?, DUREE_ESTIMEE = ?, "
               + "POSOLOGIE = ?, EFFETS_SECONDAIRES = ?, ETAT = ? "
               + " WHERE id = ?  AND user_id = ? ";

    try (Connection conn = BaseDonneeConnexion.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        User currentUser = Session.getInstance().getCurrentUser();
        if (currentUser == null) {
            System.out.println("No user in session.");
            return false;
        }

        stmt.setString(1, traitement.getNOM_TRAITEMENT());
        stmt.setString(2, traitement.getTYPE_TRAITEMENT());
        stmt.setString(3, traitement.getMALADIE());
        stmt.setString(4, traitement.getDESCRIPTION());
        stmt.setDate(5, java.sql.Date.valueOf(traitement.getDATE_DEBUT()));
        stmt.setDate(6, java.sql.Date.valueOf(traitement.getDATE_FIN()));
        stmt.setString(7, traitement.getDUREE_ESTIMEE());
        stmt.setString(8, traitement.getPOSOLOGIE());
        stmt.setString(9, traitement.getEFFETS_SECONDAIRES());
        stmt.setString(10, traitement.getETAT());
        stmt.setInt(11, traitement.getId());         
        stmt.setInt(12, currentUser.getId());     // ID utilisateur
     // ID traitement ciblé

        return stmt.executeUpdate() > 0;

    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Erreur lors de la mise à jour du traitement: " + e.getMessage());
        return false;
    }
}


}