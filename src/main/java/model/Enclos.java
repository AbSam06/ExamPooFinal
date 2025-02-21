/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author 2417011
 */


public class Enclos {
    private int id;
    private String nom;
    private int capacite;
    private String typeHabitat;

    // Constructeur
    public Enclos(int id, String nom, int capacite, String typeHabitat) {
        this.id = id;
        this.nom = nom;
        this.capacite = capacite;
        this.typeHabitat = typeHabitat;
    }

    // Getters et Setters
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

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public String getTypeHabitat() {
        return typeHabitat;
    }

    public void setTypeHabitat(String typeHabitat) {
        this.typeHabitat = typeHabitat;
    }

    @Override
    public String toString() {
        return "Enclos{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", capacite=" + capacite +
                ", typeHabitat='" + typeHabitat + '\'' +
                '}';
    }
}

