package btsciel;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;

public class GestionDesCoureurs {
    private ArrayList<Coureur> coureurs = new ArrayList();
    private ConnectionDeBDD connectionDeBDD = new ConnectionDeBDD("lacourse", "root", "");
    private Connection con = connectionDeBDD.getConnection();
    private PreparedStatement ps;

    public ArrayList<Coureur> getCoureurs() {
        return coureurs;
    }

    public void setCoureurs(ArrayList<Coureur> coureurs) {
        this.coureurs = coureurs;
    }

    public ArrayList<Genre> lireLesGenres() throws SQLException {
        ArrayList<Genre> genres = new ArrayList<>();
        ps = con.prepareStatement("SELECT * FROM genre");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Genre genre = new Genre(rs.getInt("id"), rs.getString("type"), rs.getString("nom"));
            genres.add(genre);
        }
        return genres;
    }

    public ArrayList<Categorie> lireLesCategories() throws SQLException {
        ArrayList<Categorie> categories = new ArrayList<>();
        ps = con.prepareStatement("SELECT * FROM categorie");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Categorie categorie = new Categorie(rs.getInt("id"), rs.getString("nom"), rs.getString("ages"));
            categories.add(categorie);
        }
        return categories;
    }

    public GestionDesCoureurs() throws IOException, SQLException, ClassNotFoundException {
        ps = con.prepareStatement("SELECT c.id id, c.nom nom, c.prenom prenom, c.temps temps, g.id id_genre, cat.id id_categorie, g.nom nom_genre, g.type type_genre, cat.nom nom_categorie, cat.ages ages FROM coureur c JOIN genre g ON c.id_genre = g.id JOIN categorie cat ON c.id_categorie = cat.id ORDER BY c.id ASC");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Genre genre = new Genre(rs.getInt("id_genre"), rs.getString("type_genre"), rs.getString("nom_genre"));
            Categorie categorie = new Categorie(rs.getInt("id_categorie"), rs.getString("nom_categorie"), rs.getString("ages"));
            Coureur coureur = new Coureur(genre, rs.getString("nom"), rs.getString("prenom"), rs.getInt("id"), categorie, rs.getTime("temps").toLocalTime());
            coureurs.add(coureur);
        }
    }
    public void trierParNom() {
        coureurs.sort(Comparator.comparing(Coureur::getNom ));
    }

    public void trierParNomDecroissant() {
        coureurs.sort(Comparator.comparing(Coureur::getNom ).reversed());
    }

    public void trierParPrenom() {
        coureurs.sort(Comparator.comparing(Coureur::getPrenom ));
    }

    public void trierParPrenomDecroissant() {
        coureurs.sort(Comparator.comparing(Coureur::getPrenom ).reversed());
    }

    public void trierParClassement() {
        coureurs.sort(Comparator.comparing(Coureur::getDuree));
    }

    public void trierParClassementDecroissant() {
        coureurs.sort(Comparator.comparing(Coureur::getDuree).reversed());
    }

    public int ajouterUnCoureur(Coureur coureur) throws SQLException {
        ps = con.prepareStatement("INSERT INTO coureur (id, nom, prenom, temps, id_genre, id_categorie) VALUES (NULL, ?, ?, ?, ?, ?)");
        ps.setString(1, coureur.getNom());
        ps.setString(2, coureur.getPrenom());
        ps.setInt(3, coureur.getDuree());
        ps.setInt(4, coureur.getGenre().getId());
        ps.setInt(5, coureur.getCategorie().getId());
        return ps.executeUpdate();
    }
    public int supprimerUnCoureur(Coureur c) throws SQLException {
        ps = con.prepareStatement("DELETE FROM coureur WHERE id = ?");
        ps.setInt(1, c.getId());
        return ps.executeUpdate();
    }

    public void modifierUnCoureur(Coureur c) throws SQLException {
        ps = con.prepareStatement("UPDATE coureur SET nom = ?, prenom = ?, temps = ? WHERE id = ?");
        ps.setString(1, c.getNom());
        ps.setString(2, c.getPrenom());
        ps.setInt(3, c.getDuree());
        ps.setInt(4, c.getId());
        ps.executeUpdate();
    }

    public ResultSet lireLesCoureurs() throws SQLException {
        ps = con.prepareStatement("SELECT c.id id, c.nom nom, c.prenom prenom, c.temps temps, g.id id_genre, cat.id id_categorie, g.nom nom_genre, g.type type_genre, cat.nom nom_categorie, cat.ages ages FROM coureur c JOIN genre g ON c.id_genre = g.id JOIN categorie cat ON c.id_categorie = cat.id ORDER BY c.id ASC");
        return ps.executeQuery();
    }
}