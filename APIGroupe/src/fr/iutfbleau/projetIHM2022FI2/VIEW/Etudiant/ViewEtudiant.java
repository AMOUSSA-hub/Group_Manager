package fr.iutfbleau.projetIHM2022FI2.VIEW.Etudiant;

import fr.iutfbleau.projetIHM2022FI2.API.*;
import fr.iutfbleau.projetIHM2022FI2.MNP.*;
import fr.iutfbleau.projetIHM2022FI2.CONTROLLER.*;
import fr.iutfbleau.projetIHM2022FI2.MODEL.*;
import fr.iutfbleau.projetIHM2022FI2.UTILS.*;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.tree.*;
import java.sql.*;


public class ViewEtudiant extends JFrame {
	
    private static DefaultMutableTreeNode root;
    private static JPanel pan_groupe ;
    public static  JButton demande_de_changement;
    public static JFrame fenetre;
    public static JPanel menu_etudiant = new JPanel();  
    public static Map<String,Integer> group_map = new HashMap<String,Integer>();
    public static CardLayout gestionnaire = new CardLayout();
    public static JTree arbre ;
    public static MyAbstractGroupeFactoryEtudiant bd;
    public static MyAbstractChangementFactoryEtudiant demandes;
    

	public ViewEtudiant(String nom, int id) {
        fenetre = new JFrame(nom);
        JPanel north_pan = new JPanel();
        JPanel south_pan = new JPanel();
        pan_groupe = new JPanel();
        demande_de_changement = new JButton("Changer de groupe");
        JButton voir_les_demandes = new JButton("Voir les demandes");
        bd = new MyAbstractGroupeFactoryEtudiant();
        demandes = new MyAbstractChangementFactoryEtudiant(bd);

        pan_groupe.setLayout(gestionnaire);
		fenetre.setLocation(50, 50);
		fenetre.setSize(500,500);
		fenetre.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        north_pan.setPreferredSize(new Dimension(300,300));
        north_pan.setLayout(new GridLayout(1,2));
        
        menu_etudiant.setLayout(gestionnaire);
      
        arbre =  display_group(new DefaultMutableTreeNode(bd.getPromotion().getName()),1);

        pan_groupe.add(arbre);
        north_pan.add(pan_groupe);
        north_pan.add(new JScrollPane(pan_groupe));
        north_pan.add(new JScrollPane(menu_etudiant));
        
        demande_de_changement.addActionListener(new Observateur_MEG(this, id));
        voir_les_demandes.addActionListener(new Observateur_MEG(this, id));

        south_pan.setBackground(new Color(116, 208, 241));
        south_pan.add(demande_de_changement,BorderLayout.CENTER);
        south_pan.add(voir_les_demandes,BorderLayout.CENTER);

        fenetre.add(north_pan);
        fenetre.add(south_pan,BorderLayout.SOUTH);

		fenetre.setVisible(true);
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
            info_etudiant.add(new JLabel(a.getNom()+ " "+  a.getPrenom() ));
            diapositive_etudiant.add(info_etudiant);
        }



            
            diapositive_etudiant.setLayout(new GridLayout(list_etu.size(),3));
            

        menu_etudiant.add(diapositive_etudiant,groupe_selected.getId()+"");
        
    }

    
    
    
    
    public static JTree  display_group( DefaultMutableTreeNode node,int id_groupe){
  
        DefaultMutableTreeNode parent = node;

        String path = Utils.TreeNode_to_String(parent.getPath());

        MyGroupeEtudiant g = bd.brain.get(id_groupe);

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
            arborescence_groupe.addTreeSelectionListener(new Observateur_arborescenceEtudiant());

      return arborescence_groupe;

    }

    public static void loadPanGroup(){

        group_map.clear();
       

        arbre = display_group(new DefaultMutableTreeNode(bd.getPromotion().getName()),1);

        
        pan_groupe.add(arbre);
        gestionnaire.next(pan_groupe);
        gestionnaire.show(menu_etudiant,Observateur_arborescence.group_selected.getId()+"");

        fenetre.setVisible(true);
    }
}
