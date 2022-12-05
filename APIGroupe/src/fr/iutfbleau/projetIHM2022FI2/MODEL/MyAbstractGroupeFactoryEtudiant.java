package fr.iutfbleau.projetIHM2022FI2.MODEL;

import fr.iutfbleau.projetIHM2022FI2.API.*;
import fr.iutfbleau.projetIHM2022FI2.UTILS.Utils;
import java.util.*;
import javax.swing.*;
import java.sql.*;

public class MyAbstractGroupeFactoryEtudiant implements AbstractGroupeFactory {

    public HashMap<Integer,MyGroupeEtudiant> brain = new HashMap<Integer,MyGroupeEtudiant>();

    public MyAbstractGroupeFactoryEtudiant(){
        try{

        PreparedStatement req = Utils.con.prepareStatement("Select id from Groupe");
            ResultSet res = req.executeQuery();

            while(res.next()){
                brain.put(res.getInt(1), new MyGroupeEtudiant(res.getInt(1)));
            }
        } catch (SQLException  se) {
            System.err.println("errreur Sql at MyAbstractGroupeFactoryEtudiant()"+se);
        }
    }
    
    public Groupe getPromotion(){
        return brain.get(1);
    };

    public Boolean knows(Groupe g) {
        return this.brain.containsKey(g.getId());
    };
     
    public Set<Etudiant> getEtudiants(String nomEtu){
     Set<Etudiant> student_found = new HashSet<Etudiant>();
     return student_found;
    };

    public Set<Groupe> getGroupesOfEtudiant(Etudiant etu){
        return new HashSet<Groupe>();
    };

    @Override
    public void dropFromGroupe(Groupe g, Etudiant e) {
    }

    @Override
    public void addToGroupe(Groupe g, Etudiant e) {
    }

    @Override
    public void createPartition(Groupe pere, String name, int n) {
    }

    @Override
    public void createGroupe(Groupe pere, String name, int max, int min) {
    }

    @Override
    public void deleteGroupe(Groupe g) {
    }

}
