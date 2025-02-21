/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author 2417011
 */


import database.ZooDatabaseManager;
import model.Animal;
import java.util.List;
import model.Enclos;

public class ZooService {
    private final ZooDatabaseManager databaseManager;

    public ZooService() {
        this.databaseManager = ZooDatabaseManager.getInstance();
    }

    public boolean ajouterAnimal(String nom, String espece, int age, String regime) {
        Animal animal = new Animal(0, nom, espece, age, regime);
        return databaseManager.ajouterAnimal(animal);
    }

    public boolean mettreAJourAnimal(int id, String nom, String espece, int age, String regime) {
    Animal animal = new Animal(id, nom, espece, age, regime);
    return databaseManager.mettreAJourAnimal(animal);
}

    
    public List<Animal> obtenirTousLesAnimaux() {
        return databaseManager.obtenirTousLesAnimaux();
    }

    public boolean supprimerAnimal(int id) {
        return databaseManager.supprimerAnimal(id);
    }
    
    public List<Enclos> obtenirTousLesEnclos() {
    return databaseManager.obtenirTousLesEnclos();
}
    
    public List<Animal> rechercherAnimal(String nom) {
    return databaseManager.rechercherAnimal(nom);
}


}

