package fr.iutfbleau.projetIHM2022FI2.MODEL;

import fr.iutfbleau.projetIHM2022FI2.API.*;
import fr.iutfbleau.projetIHM2022FI2.UTILS.Utils;
import java.util.*;
import java.sql.*;

public class MyAbstractGroupeFactory implements AbstractGroupeFactory {

    public HashMap<Integer,MyGroupe> brain = new HashMap<Integer,MyGroupe>();

    public MyAbstractGroupeFactory(){

        Utils.open_connection();

        try{

        PreparedStatement req = Utils.con.prepareStatement("Select id from Groupe ");
            ResultSet res = req.executeQuery();

            while(res.next()){
                brain.put(res.getInt(1), new MyGroupe(res.getInt(1)));
            }
        
        Utils.close_connection();

            } catch (SQLException  se) {
                System.err.println("errreur Sql at MyAbstractGroupeFactory()"+se);

            }



    }

    
    public Groupe getPromotion(){
        
        return brain.get(1);


    };



    public Boolean knows(Groupe g){

        return this.brain.containsKey(g.getId());
    
    
    };


    public void deleteGroupe(Groupe g){
        Objects.requireNonNull(g,"On ne peut pas enlever un groupe null car null n'est pas un groupe autorisé");
        if (!this.knows(g)){
            throw new IllegalArgumentException("Impossible d'enlever un groupe inconnu");
        }
        if (this.getPromotion().equals(g)){
            throw new IllegalArgumentException("Impossible de détruire le groupe de toute la promotion");
        }
        if (g.getSize()>0){
            throw new IllegalStateException("Impossible de détruire un groupe contenant un groupe");
        }
        g.getPointPoint().removeSousGroupe(g);
        this.brain.remove(Integer.valueOf(g.getId()));
    };


    public void createGroupe(Groupe pere, String name, int min, int max){
        Objects.requireNonNull(pere,"Le groupe pere ne peut pas être null");
        Objects.requireNonNull(name,"Le nouveau groupe ne peut pas avoir null comme nom");
        if (!this.knows(pere)){
            throw new IllegalArgumentException("Interdit d'ajouter un fils à un groupe inconnu");
        }
        if (pere.getType().equals(TypeGroupe.PARTITION)){
            throw new IllegalArgumentException("Impossible d'ajouter un groupe à une parition. Il faut utiliser createPartition pour créer une partition");
        }
        if ( min <= 0 || max < min){
            throw new IllegalArgumentException("Il faut que 0 < min <= max");
        }
        MyGroupe g = new MyGroupe(pere,name,min,max);
        pere.addSousGroupe(g);
        this.brain.put(Integer.valueOf(g.getId()),g);
    };

   
    public void createPartition(Groupe pere, String name, int n){
        Objects.requireNonNull(pere,"Le groupe pere ne peut pas être null");
        Objects.requireNonNull(name,"Le nouveau groupe ne peut pas avoir null comme nom");
        if (!this.knows(pere)){
            throw new IllegalArgumentException("Impossible de partitionner ce groupe inconnu");
        }
        if (pere.getType().equals(TypeGroupe.PARTITION)){
            throw new IllegalArgumentException("Impossible de créer une partition à ce niveau. Il faut soit repartitionner le groupe au dessus, soit partitionner une partition en dessous.");
        }
        if ( n <= 0){
            throw new IllegalArgumentException("Le nombre de partitions doit être strictement positif");
        }
        //Création de la racine de la partition.
        MyGroupe copiePereRacinePartition = brain.get(pere.getId());
        pere.addSousGroupe(copiePereRacinePartition);
        this.brain.put(Integer.valueOf(copiePereRacinePartition.getId()),copiePereRacinePartition);
        // création des sous-groupes
        int min = 0;
        int max = ((int) Math.floor(pere.getSize()/n))+1;
        List<Groupe> groupes = new ArrayList<Groupe>(n);
        for(int i = 0; i<n; i++){
            MyGroupe g = new MyGroupe(copiePereRacinePartition,name+"_"+i,min,max);
            groupes.add(i,g);// ajout dans le tableau des groupes
            copiePereRacinePartition.addSousGroupe(g);
            this.brain.put(Integer.valueOf(g.getId()),g);
        }
        // Partage des étudiants (on ne prête pas attention aux min et max)
        int i=0;
        for (Etudiant s: pere.getEtudiants()){
            copiePereRacinePartition.addEtudiant(s);
            groupes.get(i).addEtudiant(s);
            i = (i+1) %n;
        }

    };

    
    public void addToGroupe(Groupe g, Etudiant e){

        Objects.requireNonNull(g,"Le groupe ne peut pas être null");
        Objects.requireNonNull(e,"L'étudiant ne peut pas être null");
        if (!this.knows(g)){
            throw new IllegalArgumentException("Impossible d'ajouter l'étudiant car le est groupe inconnu");
        }
        g.addEtudiant(e);
    };

    
    public void dropFromGroupe(Groupe g, Etudiant e){
        Objects.requireNonNull(g,"Le groupe ne peut pas être null");
        Objects.requireNonNull(e,"L'étudiant ne peut pas être null");
        if (!this.knows(g)){
            throw new IllegalArgumentException("Impossible d'ajouter l'étudiant car le est groupe inconnu");
        }
        g.removeEtudiant(e);
    };

     
    public Set<Etudiant> getEtudiants(String nomEtu){

     Set<Etudiant> student_found = new HashSet<Etudiant>();

     return student_found;

    };

    
    public Set<Groupe> getGroupesOfEtudiant(Etudiant etu){


        return new HashSet<Groupe>();
    };

    
    
}
