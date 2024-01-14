package btsciel;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

public class Ihm {
    static GestionDesCoureurs gestion = null;

    static void listerParClassemenet () {
        System.out.print("   Nom" );
        for (int i = 0; i < 20 - "Nom".length(); i++) {
            System.out.print(" ");
        }
        System.out.print("Prénom" );
        for (int i = 0; i < 20 - "Prénom".length(); i++) {
            System.out.print(" ");
        }
        System.out.println("Temps\t   Ecart");

        for (int i = 0; i < gestion.getCoureurs().size(); i++) {
            System.out.print((i + 1) + " " );
            if(i < 9) {
                System.out.print(" ");
            }
            System.out.print(  gestion.getCoureurs().get(i).getNom() );

            for (int j = 0; j <  20 - gestion.getCoureurs().get(i).getNom().length();j++) {
                System.out.print(" ");
            }

            if(i > 0) {
                System.out.print( gestion.getCoureurs().get(i).getPrenom());
                for (int j = 0; j <  20 - gestion.getCoureurs().get(i).getPrenom().length();j++) {
                    System.out.print(" ");
                }
                System.out.println(gestion.getCoureurs().get(i).getClassemenet().format(DateTimeFormatter.ofPattern("hh:mm:ss")) + "\t\t" + gestion.getCoureurs().get(i).getEcartString(gestion.getCoureurs().get(0).getDuree()));
            } else {
                System.out.print( gestion.getCoureurs().get(i).getPrenom());
                for (int j = 0; j <  20 - gestion.getCoureurs().get(i).getPrenom().length();j++) {
                    System.out.print(" ");
                }
                System.out.println( gestion.getCoureurs().get(i).getClassemenet().format(DateTimeFormatter.ofPattern("hh:mm:ss")) + " " );
            }
        }
    }
    private static void lister () {
        int position = 1;

        System.out.print("   Nom" );
        for (int i = 0; i < 20 - "Nom".length(); i++) {
            System.out.print(" ");
        }
        System.out.print("Prénom" );
        for (int i = 0; i < 20 - "Prénom".length(); i++) {
            System.out.print(" ");
        }
        System.out.println("Temps");
        for (Coureur coureur : gestion.getCoureurs()) {
            System.out.print(position++ + " ");
            if(position <= 10) {
                System.out.print(" ");
            }
            System.out.print(coureur.getNom());
            for (int i = 0; i < 20 - coureur.getNom().length(); i++) {
                System.out.print(" ");
            }
            System.out.print(coureur.getPrenom());
            for (int i = 0; i < 20 - coureur.getPrenom().length(); i++) {
                System.out.print(" ");
            }
            System.out.println( coureur.getClassemenet().format(DateTimeFormatter.ofPattern("hh:mm:ss")));
        }
    }
    public static void main(String[] args) {
        try {
            int choix;
            do {
                gestion = new GestionDesCoureurs();
                System.out.println("Nombre de coureurs: " + gestion.getCoureurs().size());
                System.out.println("Entrer votre choix");
                System.out.println("1 pour Afficher par ordre croissant du nom");
                System.out.println("2 pour Afficher par ordre décroissant du nom");
                System.out.println("3 pour Afficher par ordre croissant du prénom");
                System.out.println("4 pour Afficher par ordre décroissant du prénom");
                System.out.println("5 pour Afficher par ordre croissant du classement");
                System.out.println("6 pour Afficher par ordre décroissant du classement");
                System.out.println("7 pour ajouter un coureur");
                System.out.println("8 pour supprimer un coureur");
                System.out.println("9 pour modifier un coureur");
                System.out.println("10 pour sauvegarder la liste en mode texte");
                System.out.println("11 pour sauvegarder la liste en mode binaire");
                choix = In.readInteger();
                switch (choix) {
                    case 1:
                        gestion.trierParNom();
                        lister();
                        break;
                    case 2:
                        gestion.trierParNomDecroissant();
                        lister();
                        break;
                    case 3:
                        gestion.trierParPrenom();
                        lister();
                        break;
                    case 4:
                        gestion.trierParPrenomDecroissant();
                        lister();
                        break;
                    case 5:
                        gestion.trierParClassement();
                        listerParClassemenet();
                        break;
                    case 6:
                        gestion.trierParClassementDecroissant();
                        listerParClassemenet();
                        break;
                    case 7:
                        String nom;
                        String prenom;
                        int temps;
                        Genre genre;
                        Categorie categorie;
                        System.out.println("Entrer le nom");
                        nom = In.readString();
                        System.out.println("Entrer le prénom");
                        prenom = In.readString();
                        System.out.println("Entrer le temps");
                        temps = In.readInteger();
                        System.out.println("Entrer le genre");
                        for(int i = 0; i < gestion.lireLesGenres().size(); i++) {
                            System.out.println((i + 1) + " " + gestion.lireLesGenres().get(i).getType() + " " + gestion.lireLesGenres().get(i).getNom());
                        }
                        genre = gestion.lireLesGenres().get(In.readInteger() - 1 );

                        System.out.println("Entrer votre catégorie");
                        for(int i = 0; i < gestion.lireLesCategories().size(); i++) {
                            System.out.println((i + 1) + " " + gestion.lireLesCategories().get(i).getNom() + " " + gestion.lireLesCategories().get(i).getAges());
                        }
                        categorie = gestion.lireLesCategories().get(In.readInteger() - 1 );
                        if (gestion.ajouterUnCoureur(new Coureur(genre, nom, prenom, categorie, temps)) == 1) {
                            System.out.println("Le coureur a été ajouté");
                        } else {
                            System.err.println("Le coureur n'a pas été ajouté");
                        }
                        break;
                    case 8:
                        lister();
                        System.out.println("Entrer le numéro du coureur à supprimer");
                        int pos = In.readInteger() - 1;
                        if((pos >= 0) && (pos < gestion.getCoureurs().size()) ) {
                            Coureur c = gestion.getCoureurs().get(pos);
                            if(gestion.supprimerUnCoureur(c) == 1) {
                                System.out.println("Le coureur " + c.getNom() + " " + c.getPrenom() +   " a été supprimé");
                            } else {
                                System.out.println("Le coureur n'a pas été supprimé");
                            }
                        } else {
                            System.out.println("Le numéro saisi ne correspond pas à un coureur");
                        }
                        break;
                    case 9:
                        lister();
                        System.out.println("Entrer le numéro du coureur à modifier");
                        pos = In.readInteger() - 1;
                        if((pos >= 0) && (pos < gestion.getCoureurs().size()) ) {
                            Coureur c = gestion.getCoureurs().get(pos);
                            System.out.println("Voulez vous changer le nom du coureur " +  gestion.getCoureurs().get(pos).getNom() + " " + gestion.getCoureurs().get(pos).getPrenom() + " (1 pour oui 2 pour non)");
                            int mod = In.readInteger();
                            if(mod == 1) {
                                System.out.println("Entrer le nom");
                                c.setNom(In.readString());
                            }
                            System.out.println("Voulez vous changer le prénom du coureur " +  gestion.getCoureurs().get(pos).getNom() + " " + gestion.getCoureurs().get(pos).getPrenom() + " (1 pour oui 2 pour non)");
                            mod = In.readInteger();
                            if (mod == 1) {
                                System.out.println("Entrer le prénom");
                                c.setPrenom(In.readString());
                            }
                            System.out.println("Voulez vous changer le temps du coureur "  + gestion.getCoureurs().get(pos).getNom() + " " + gestion.getCoureurs().get(pos).getPrenom() + " " + gestion.getCoureurs().get(pos).getClassemenet() + " (1 pour oui 2 pour non)");
                            mod = In.readInteger();
                            if (mod == 1) {
                                System.out.println("Entrer le temps");
                                c.setDuree(In.readInteger());
                            }
                            gestion.modifierUnCoureur(c);
                            System.out.println("Le coureur " + gestion.getCoureurs().get(pos).getNom() + " " + gestion.getCoureurs().get(pos).getPrenom() + " " +  gestion.getCoureurs().get(pos).getClassemenet() + " a été modifié");
                        } else {
                            System.out.println("Le coureur n'a pas été modifié, il n'est pas dans la liste");
                        }
                        break;
                    case 10:
                        Path fileSource = Paths.get("coureurs.txt");
                        ResultSet rs = gestion.lireLesCoureurs();
                        BufferedWriter bw;
                        bw = Files.newBufferedWriter(fileSource, Charset.defaultCharset());
                        while (rs.next()) {
                            bw.write(rs.getInt("id") + ", " + rs.getString("nom") + ", " + rs.getString("prenom") + ", " + rs.getTime("temps") + ", " + rs.getString("type_genre") + ", " + rs.getString("nom_categorie") + ", " + rs.getString("ages"));
                            bw.newLine();
                        }
                        bw.close();
                        System.out.print("Le fichier " + fileSource.getFileName() + " a ete sauvegarde");
                        break;
                    case 11:
                        Path fileSourceBinary = Paths.get("binary_coureurs.bin");
                        ResultSet rs_for_binary = gestion.lireLesCoureurs();
                        OutputStream in = Files.newOutputStream(fileSourceBinary);
                        BufferedOutputStream bis = new BufferedOutputStream(in);
                        DataOutputStream dis = new DataOutputStream(bis);
                        while (rs_for_binary.next()) {
                            dis.writeChars(rs_for_binary.getInt("id") + ", " + rs_for_binary.getString("nom") + ", " + rs_for_binary.getString("prenom") + ", " + rs_for_binary.getTime("temps") + ", " + rs_for_binary.getString("type_genre") + ", " + rs_for_binary.getString("nom_categorie") + ", " + rs_for_binary.getString("ages"));
                            dis.writeChar('\n');
                        }
                        dis.close();
                        System.out.print("Le fichier " + fileSourceBinary.getFileName() + " a ete sauvegarde");
                        break;
                }
                System.out.println("\n________________________________________________________________");
            } while (choix != 0);
        } catch (IOException e) {
            System.err.println("Errreur Fichier -> " + e.getMessage() );
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Erreur " + e.getMessage());
        }
    }
}