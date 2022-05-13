package complexesportifJava.entities;

public class terrain {
     
    private int id;
    private String type;
    private String chef;
    private String equipement;

     public terrain(int id) {
        this.id = id;
    }

    
    public int getid() {
        return id;
    }

    public void setid(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getChef() {
        return chef;
    }

    public void setChef(String chef) {
        this.chef = chef;
    }

    public String getEquipement() {
        return equipement;
    }

    public void setEquipement(String equipement) {
        this.equipement = equipement;
    }

    public terrain( int id, String type, String chef, String equipement) {
        
        this.id = id;
        this.type = type;
        this.chef = chef;
        this.equipement = equipement;
    }
    
    public terrain(  String type, String chef, String equipement) {
        
        
        this.type = type;
        this.chef = chef;
        this.equipement = equipement;
    }

    public terrain() {
    }

   
    
    
}
