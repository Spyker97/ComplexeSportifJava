/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package complexesportifJava.entities;

/**
 *
 * @author LENOVO
 */
public class reservation {
    
     private int id;
    private int idterrain_id;
    private int cin;
        private String discipline;

    public reservation() {
    }

    public reservation(int id, int idterrain_id, int cin, String discipline) {
        this.id = id;
        this.idterrain_id = idterrain_id;
        this.cin = cin;
        this.discipline = discipline;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getidterrain_id() {
        return idterrain_id;
    }

    public void setidterrain_id(int idterrain_id) {
        this.idterrain_id = idterrain_id;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public reservation(int id) {
        this.id = id;
    }

   
    
}
