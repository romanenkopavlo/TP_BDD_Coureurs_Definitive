package btsciel;

public class Genre {
    private int id;
    private String type;

    public Genre(int id, String type, String nom) {
        this.setId(id);
        this.setType(type);
        this.setNom(nom);
    }

    public Genre() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    private String nom;
}
