/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package database;

import java.util.List;
import model.Animal;

/**
 *
 * @author 2417011
 */
public interface IZooDataAccess {
    boolean ajouterAnimal(Animal animal);
    Animal obtenirAnimalParId(int id);
    List<Animal> obtenirTousLesAnimaux();
    boolean mettreAJourAnimal(Animal animal);
    boolean supprimerAnimal(int id);
}
