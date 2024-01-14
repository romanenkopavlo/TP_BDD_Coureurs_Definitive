package btsciel;

public class Categorie {
    private int id;
    private String nom;
    private String ages;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAges() {
        return ages;
    }

    public void setAges(String ages) {
        this.ages = ages;
    }

    public Categorie(int id,String nom, String ages) {
        this.setId(id);
        this.setNom(nom);
        this.setAges(ages);
    }

    public Categorie() {
    }
}
