/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enfantacademie.tests;


import enfantacademie.entities.Academie;

import enfantacademie.services.AcademieService;


import java.text.ParseException;


/**
 *
 * @author My Pc
 */
public class EnfantAcademieJava {


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
     
         Academie a = new  Academie("cvppp",22);
       AcademieService rs = new AcademieService();
        rs.ajouterAcademie(a);
        System.out.println(rs.afficherAcademie());
       Academie r = new Academie("cvppp",32);
        rs.modifierAcademie(r);
        System.out.println(rs.afficherAcademie());
        rs.supprimerAcademie(12);
        System.out.println(rs.afficherAcademie());}
   
}
    

