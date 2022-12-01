package fr.iutfbleau.projetIHM2022FI2.VIEW.Admin;

import fr.iutfbleau.projetIHM2022FI2.API.*;
import fr.iutfbleau.projetIHM2022FI2.CONTROLLER.*;
import fr.iutfbleau.projetIHM2022FI2.MNP.GroupeNP;
import fr.iutfbleau.projetIHM2022FI2.UTILS.*;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.tree.*;
import java.sql.*;



public class DashboardGroupe extends JFrame {
    private static DefaultMutableTreeNode root;
    private static JPanel pan_groupe ;

    public static  JFrame menu_fen;
    public static  JButton modification_groupe = new JButton("modifier");
    public static JButton suppression_groupe = new JButton("supprimer");
    public static JPanel menu_etudiant = new JPanel();  
    public static Map<String,Integer> group_map = new HashMap<String,Integer>();
    public static CardLayout gestionnaire = new CardLayout();
    public static JTree arbre ;
    public static MyAbstractGroupeFactory bd = new MyAbstractGroupeFactory();
    

  
 

    public DashboardGroupe(){

         menu_fen = this;
        JPanel north_pan = new JPanel();
        JPanel south_pan = new JPanel();
        pan_groupe = new JPanel();
        JButton creation_groupe = new JButton("créer un groupe");
        JButton ajout_etudiant = new JButton("ajouter etudiant");

        pan_groupe.setLayout(gestionnaire);
        setLocation(10,10);
        setSize(Utils.fen_dimension);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        north_pan.setPreferredSize(new Dimension(300,300));
        north_pan.setLayout(new GridLayout(1,2));
        
        menu_etudiant.setLayout(gestionnaire);
             

        
      
        arbre =  display_group(new DefaultMutableTreeNode(bd.brain.get(1).getName()),1);
          
       
  
        
        pan_groupe.add(arbre);
        north_pan.add(pan_groupe);
        north_pan.add(new JScrollPane(pan_groupe));
        north_pan.add(menu_etudiant);
        

        modification_groupe.setEnabled(false);
        suppression_groupe.setEnabled(false);
        creation_groupe.addActionListener(new Observateur_MEG(this));
        modification_groupe.addActionListener(new Observateur_MEG(this));
        suppression_groupe.addActionListener(new Observateur_MEG());
        ajout_etudiant.addActionListener(new Observateur_MEG(this));
          
       
        south_pan.setBackground(new Color(116, 208, 241));
        south_pan.add(creation_groupe,BorderLayout.CENTER);
        south_pan.add(modification_groupe,BorderLayout.CENTER);
        south_pan.add(suppression_groupe,BorderLayout.CENTER);
        south_pan.add(ajout_etudiant,BorderLayout.CENTER);

        add(north_pan);
        add(south_pan,BorderLayout.SOUTH);
             
        
        setVisible(true);

    }

    

    
    public static void display_etudiant (Groupe groupe_selected,String titre_diapo){

         JPanel diapositive_etudiant = new JPanel(); 
        int nbr_etudiant = 0;

        Set<Etudiant> list_etu =  groupe_selected.getEtudiants();
        Iterator<Etudiant> iterator = list_etu.iterator();

        while(iterator.hasNext()){

            JPanel info_etudiant = new JPanel();
            info_etudiant.setBorder(BorderFactory.createLineBorder(Color.black));
            Etudiant a = iterator.next();

            nbr_etudiant++;
                info_etudiant.add(new JLabel(a.getNom()+ " "+  a.getPrenom() ));
                JButton info = new JButton("infos");
           info_etudiant.add(info);
            if(groupe_selected.getId() != 1){
                info_etudiant.add(new JButton("Supprimer"));
            }

            diapositive_etudiant.add(info_etudiant);
        }



              
            diapositive_etudiant.setLayout(new GridLayout(nbr_etudiant,3));
            

        menu_etudiant.add(diapositive_etudiant,groupe_selected.getId()+"");
        
    }

    
    
    
    
    public static JTree  display_group( DefaultMutableTreeNode node,int id_groupe){
  
        DefaultMutableTreeNode parent = node;

        String path = Utils.TreeNode_to_String(parent.getPath());

        MyGroupe g = bd.brain.get(id_groupe);

      g.setPath(path);

        group_map.put(path,id_groupe);
        display_etudiant(g,g.getPath());
     
      if(id_groupe == 1 ){
         root = parent;
      }

    
     
        Set<Groupe> list_groupe = g.getSousGroupes();
        Iterator<Groupe> iterator = list_groupe.iterator();


                   
            while(iterator.hasNext()){

                Groupe sous_groupe = iterator.next();
                DefaultMutableTreeNode fils = new DefaultMutableTreeNode(sous_groupe.getName());
               
                parent.add(fils);
                
                
               
                 display_group(fils,sous_groupe.getId());
                
                
            }   
  

            JTree arborescence_groupe = new JTree(root);
            arborescence_groupe.addTreeSelectionListener(new Observateur_arborescence());

      return arborescence_groupe;

    }

    public static void loadPanGroup(){

        group_map.clear();
       

        arbre = display_group(new DefaultMutableTreeNode(bd.brain.get(1).getName()),1);

        
        pan_groupe.add(arbre);
        gestionnaire.next(pan_groupe);

        menu_fen.setVisible(true);
       
       
        
    }

    



    public static void main(String[] args) {
        
        new DashboardGroupe();
    }
    
}
