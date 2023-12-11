package com.example.adminorderfood;

public class DataFood {
    private String nom;
    private String description;
    private String image;
    private long prix; // Change the type to long
String key ;

    public DataFood(String nom, String description, String image, long prix) {
        this.nom = nom;
        this.description = description;
        this.image = image;
        this.prix = prix;
    }

    public DataFood() {

    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public long getPrix() {
        return prix;
    }

    public void setPrix(long prix) {
        this.prix = prix;
    }
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
}

