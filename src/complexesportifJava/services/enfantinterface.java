/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package complexesportifJava.services;

import complexesportifJava.entities.Enfant;
import java.util.List;

/**
 *
 * @author Salma
 */
public interface enfantinterface {
    
    public void ajouterEnfant(Enfant u);
    public List<Enfant> afficherEnfant();
    public boolean modifierEnfant(Enfant a);
    public boolean supprimerEnfant(int id);
}

