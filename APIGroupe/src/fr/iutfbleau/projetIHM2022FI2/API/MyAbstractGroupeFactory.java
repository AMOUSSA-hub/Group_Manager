package fr.iutfbleau.projetIHM2022FI2.API;

import fr.iutfbleau.projetIHM2022FI2.UTILS.Utils;

public class MyAbstractGroupeFactory implements AbstractGroupeFactory {

    

    
    public Groupe getPromotion(){
        
        return new MyGroupe(1);


    };



    public Boolean knows(Groupe g){

        
    };


    public void deleteGroupe(Groupe g);


    public void createGroupe(Groupe pere, String name, int min, int max){};

   
    public void createPartition(Groupe pere, String name, int n);

    
    public void addToGroupe(Groupe g, Etudiant e);

    
    public void dropFromGroupe(Groupe g, Etudiant e);

     
    public Set<Etudiant> getEtudiants(String nomEtu);

    
    public Set<Groupe> getGroupesOfEtudiant(Etudiant etu);

    
    
}
