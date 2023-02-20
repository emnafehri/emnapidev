/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Patient;
import Entities.Pharmacien;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Tools.MyConnection;

/**
 *
 * @author DELL
 */
public class CRUDPharmacien {
   public void addPharmacien(Pharmacien P) {
        try {  
                String requete = "INSERT INTO pharmacien (Nom,Phone,Email,Adresse,Role,Mdp)" + "VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = new MyConnection().cn.prepareStatement(requete);
            pst.setString(1, P.getNomPharmacien());
            pst.setInt(2, P.getPhonePharmacien());
            pst.setString(3, P.getEmailPharmacien());
            pst.setString(4, P.getAdrPharmacien());
            pst.setString(5,"Pharmacien");
             pst.setString(6,P.getMdp());
            pst.executeUpdate();

            System.out.println("Pharmacien ajouté!");
        } catch (SQLException ex) {
            Logger.getLogger(CRUDPharmacien.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ObservableList<Pharmacien> showPharmacien() {
        ObservableList<Pharmacien> list = FXCollections.observableArrayList();
        try {
            String requete = "SELECT * FROM pharmacien";
            PreparedStatement pst = new MyConnection().cn.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                
                
                list.add(new Pharmacien(rs.getString("nomPharmacien"),rs.getInt("phonePharmacien"),rs.getString("emailPharmacien"),rs.getString("AdrPharmacien"),rs.getString("RoleUser"),rs.getString("Mdp"))); 
            }

        } catch (SQLException ex) {
            Logger.getLogger(CRUDPharmacien.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void editPharmacien(Pharmacien P) {
        try {

            String requete = "UPDATE pharmacien  Nom= ? ,Phone= ? ,Email = ? ,Adresse= ? ,Role= ? , Mdp= ? " ; 
            PreparedStatement pst = new MyConnection().cn.prepareStatement(requete);
            pst.setString(1, P.getNomPharmacien());
            pst.setInt(2, P.getPhonePharmacien());
            pst.setString(3, P.getEmailPharmacien());
            pst.setString(4, P.getAdrPharmacien());
            pst.setString(5, "Pharmacien");
            pst.setString(6, P.getMdp());
            
        
            pst.executeUpdate();

            System.out.println("Pharmacien modifié!");

        } catch (SQLException ex) {
            Logger.getLogger(CRUDPharmacien.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void deletePharmacien(int Phone) {

        try {
            String requete = "DELETE FROM pharmacien WHERE Phone = ?";
            PreparedStatement pst   = new MyConnection().cn.prepareStatement(requete);
            pst.setInt(1,Phone);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CRUDPharmacien.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
 
}
