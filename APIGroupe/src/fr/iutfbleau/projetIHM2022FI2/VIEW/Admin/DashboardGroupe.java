package fr.iutfbleau.projetIHM2022FI2.VIEW.Admin;

import fr.iutfbleau.projetIHM2022FI2.API.*;
import fr.iutfbleau.projetIHM2022FI2.CONTROLLER.*;
import fr.iutfbleau.projetIHM2022FI2.MODEL.*;
import fr.iutfbleau.projetIHM2022FI2.UTILS.*;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.tree.*;




public class DashboardGroupe extends JFrame {
    private static DefaultMutableTreeNode root;
    private static JPanel pan_tree ;

    public static  JFrame menu_fen;
    public static JPanel info_group;
    public static  JButton modification_groupe ;
    public static JButton suppression_groupe ;
    public  static JButton creation_groupe ;
    public static JPanel menu_etudiant;  
    public static Map<String,Integer> group_map;
    public static CardLayout gestionnaire;
    public static JTree arbre ;
    public static MyAbstractGroupeFactory bd;
    

  
 

    public DashboardGroupe(){

         menu_fen = this;
         bd = new MyAbstractGroupeFactory();
         group_map = new HashMap<String,Integer>();

        JPanel north_pan = new JPanel();
        JPanel south_pan = new JPanel();
        JPanel pan_group = new JPanel();
        info_group = new JPanel();
        pan_tree = new JPanel();
        menu_etudiant = new JPanel();

        gestionnaire = new CardLayout();
        
       
        JButton ajout_etudiant = new JButton("ajouter etudiant");
        modification_groupe = new JButton("modifier");
        suppression_groupe = new JButton("supprimer");
        creation_groupe = new JButton("créer un sous-groupe libre");


        info_group.setLayout(gestionnaire);
        pan_tree.setLayout(gestionnaire);
        pan_group.setLayout(new GridLayout(2,1));
        pan_group.setBackground(Color.GRAY);
        setLocation(10,10);
        setSize(Utils.fen_dimension);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        north_pan.setPreferredSize(new Dimension(300,300));
        north_pan.setLayout(new GridLayout(1,2));
        
        menu_etudiant.setLayout(gestionnaire);
             

        
      
        arbre =  display_group(new DefaultMutableTreeNode(bd.getPromotion().getName()),1);
          
       
  
        
        pan_tree.add(arbre);
        pan_group.add(new JScrollPane(pan_tree));
        pan_group.add(info_group);
        north_pan.add(pan_group);
        north_pan.add(new JScrollPane(menu_etudiant));
        

        modification_groupe.setEnabled(false);
        suppression_groupe.setEnabled(false);
        creation_groupe.setEnabled(false);
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


        Set<Etudiant> list_etu =  groupe_selected.getEtudiants();
        Iterator<Etudiant> iterator = list_etu.iterator();

        while(iterator.hasNext()){

            JPanel info_etudiant = new JPanel();
            info_etudiant.setBorder(BorderFactory.createLineBorder(Color.black));
            Etudiant a = iterator.next();

           
                info_etudiant.add(new JLabel(a.getNom()+ " "+  a.getPrenom() ));
                if(groupe_selected.getId() != 1){
                JButton move = new JButton("Déplacer");
                move.setActionCommand(a.getId()+"");
                move.addActionListener(new Observateur_menu_etudiant(a));
           info_etudiant.add(move);
                JButton supp = new JButton("Supprimer");
                supp.setActionCommand(a.getId()+"");
                info_etudiant.add(supp);
                supp.addActionListener(new Observateur_menu_etudiant(a));

                
            }

            
            

            diapositive_etudiant.add(info_etudiant);
        }



              
            diapositive_etudiant.setLayout(new GridLayout(list_etu.size(),3));
            

        menu_etudiant.add(diapositive_etudiant,groupe_selected.getId()+"");
        //info_group.add(new pan_info_groupe(groupe_selected),groupe_selected.getId()+"");
        
      
        
        
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
       

        arbre = display_group(new DefaultMutableTreeNode(bd.getPromotion().getName()),1);

        
        pan_tree.add(arbre);
        gestionnaire.next(pan_tree);
        gestionnaire.show(menu_etudiant,Observateur_arborescence.group_selected.getId()+"");

        menu_fen.setVisible(true);
       
       
        
    }

    
}
