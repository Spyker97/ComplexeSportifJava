/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package complexesportifJava.entities;

/**
 *
 * @author wided
 */
public class Avis {
    private int id;
    private String object,description;

    public Avis() {
    }

    public Avis(int id, String object, String description) {
        this.id = id;
        this.object = object;
        this.description = description;
    }

    public Avis(String object, String description) {
        this.object = object;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Avis{" + "id=" + id + ", object=" + object + ", description=" + description + '}';
    }
    
    
}
