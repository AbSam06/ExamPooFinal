/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package controller;

import java.util.List;
import model.Animal;
import model.Enclos;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author 2417011
 */


public class ZooServiceTest {
    private ZooService zooService;

    @BeforeEach
    void setUp() {
        zooService = new ZooService(); // Initialisation du service
    }

    @Test
    void testAjouterAnimal() {
        // Ajouter un animal de test
        boolean result = zooService.ajouterAnimal("Lion", "Mammifère", 5, "Carnivore");

        // Vérifier que l'insertion a réussi
        assertTrue(result, "L'ajout de l'animal devrait réussir.");

        // Vérifier que l'animal a bien été ajouté
        List<Animal> animaux = zooService.obtenirTousLesAnimaux();
        assertFalse(animaux.isEmpty(), "La liste des animaux ne devrait pas être vide.");
        assertEquals("Lion", animaux.get(animaux.size() - 1).getNom(), "Le dernier animal ajouté devrait être un Lion.");
    }

    @Test
    void testSupprimerAnimal() {
        // Ajouter un animal de test pour s'assurer qu'on peut le supprimer
        zooService.ajouterAnimal("Tigre", "Mammifère", 7, "Carnivore");

        // Récupérer l'ID du dernier animal ajouté
        List<Animal> animaux = zooService.obtenirTousLesAnimaux();
        int idAnimal = animaux.get(animaux.size() - 1).getId();

        // Supprimer l'animal
        boolean suppressionReussie = zooService.supprimerAnimal(idAnimal);

        // Vérifier que la suppression a réussi
        assertTrue(suppressionReussie, "La suppression de l'animal devrait réussir.");

        // Vérifier que l'animal n'est plus présent
        animaux = zooService.obtenirTousLesAnimaux();
        boolean existeEncore = animaux.stream().anyMatch(animal -> animal.getId() == idAnimal);
        assertFalse(existeEncore, "L'animal ne devrait plus exister après suppression.");
    }
}
