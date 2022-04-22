package entities;

public class terrain {
     
    private int id_terrain;
    private String type;
    private String chef;
    private String equipement;

    

    
    public int getId_terrain() {
        return id_terrain;
    }

    public void setId_terrain(int id_terrain) {
        this.id_terrain = id_terrain;
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

    public terrain( int id_terrain, String type, String chef, String equipement) {
        
        this.id_terrain = id_terrain;
        this.type = type;
        this.chef = chef;
        this.equipement = equipement;
    }

    public terrain() {
    }

    public terrain(int id_terrain) {
        this.id_terrain = id_terrain;
    }
    
    
}
