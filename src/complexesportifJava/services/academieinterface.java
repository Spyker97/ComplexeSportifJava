/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package complexesportifJava.services;

import complexesportifJava.entities.Academie;
import java.util.List;

/**
 *
 * @author Salma
 */
public interface academieinterface {
     public void ajouterAcademie(Academie u);
    public List<Academie> afficherAcademie();
    public boolean modifierAcademie(Academie a);
    public boolean supprimerAcademie(int id);
}
