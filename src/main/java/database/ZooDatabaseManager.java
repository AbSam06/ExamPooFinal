/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

/**
 *
 * @author 2417011
 */


import model.Animal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Enclos;


public class ZooDatabaseManager implements IZooDataAccess {
    private static final String URL = "jdbc:sqlite:zoo.db";
    private static ZooDatabaseManager instance;

    // Singleton pour éviter plusieurs connexions
    private ZooDatabaseManager() {
    try (Connection conn = DriverManager.getConnection(URL)) {
        Statement stmt = conn.createStatement();

        // Création de la table Animal
        String sqlAnimal = "CREATE TABLE IF NOT EXISTS Animal (" +
                           "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                           "nom TEXT NOT NULL, " +
                           "espece TEXT NOT NULL, " +
                           "age INTEGER NOT NULL, " +
                           "regimeAlimentaire TEXT NOT NULL)";
        stmt.executeUpdate(sqlAnimal);

        // Création de la table Enclos
        String sqlEnclos = "CREATE TABLE IF NOT EXISTS Enclos (" +
                           "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                           "nom TEXT NOT NULL, " +
                           "capacite INTEGER NOT NULL, " +
                           "typeHabitat TEXT NOT NULL)";
        stmt.executeUpdate(sqlEnclos);

    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    
    
}

  

    public static ZooDatabaseManager getInstance() {
        if (instance == null) {
            instance = new ZooDatabaseManager();
        }
        return instance;
    }

    @Override
    public boolean ajouterAnimal(Animal animal) {
        String sql = "INSERT INTO Animal (nom, espece, age, regimeAlimentaire) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, animal.getNom());
            pstmt.setString(2, animal.getEspece());
            pstmt.setInt(3, animal.getAge());
            pstmt.setString(4, animal.getRegimeAlimentaire());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Animal obtenirAnimalParId(int id) {
        String sql = "SELECT * FROM Animal WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Animal(
                    rs.getInt("id"),
                    rs.getString("nom"),
                    rs.getString("espece"),
                    rs.getInt("age"),
                    rs.getString("regimeAlimentaire")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Animal> obtenirTousLesAnimaux() {
        List<Animal> animaux = new ArrayList<>();
        String sql = "SELECT * FROM Animal";
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                animaux.add(new Animal(
                    rs.getInt("id"),
                    rs.getString("nom"),
                    rs.getString("espece"),
                    rs.getInt("age"),
                    rs.getString("regimeAlimentaire")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return animaux;
    }

    @Override
    public boolean mettreAJourAnimal(Animal animal) {
        String sql = "UPDATE Animal SET nom = ?, espece = ?, age = ?, regimeAlimentaire = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, animal.getNom());
            pstmt.setString(2, animal.getEspece());
            pstmt.setInt(3, animal.getAge());
            pstmt.setString(4, animal.getRegimeAlimentaire());
            pstmt.setInt(5, animal.getId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean supprimerAnimal(int id) {
        String sql = "DELETE FROM Animal WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<Animal> rechercherAnimal(String nom) {
    List<Animal> animaux = new ArrayList<>();
    String sql = "SELECT * FROM Animal WHERE nom LIKE ?";
    
    try (Connection conn = DriverManager.getConnection(URL);
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, "%" + nom + "%"); // Recherche partielle
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            animaux.add(new Animal(
                rs.getInt("id"),
                rs.getString("nom"),
                rs.getString("espece"),
                rs.getInt("age"),
                rs.getString("regimeAlimentaire")
            ));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return animaux;
}

    
    public List<Enclos> obtenirTousLesEnclos() {
    List<Enclos> enclosList = new ArrayList<>();
    String sql = "SELECT * FROM Enclos";
    try (Connection conn = DriverManager.getConnection(URL);
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {
        while (rs.next()) {
            enclosList.add(new Enclos(
                rs.getInt("id"),
                rs.getString("nom"),
                rs.getInt("capacite"),
                rs.getString("typeHabitat")
            ));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return enclosList;
}

}

