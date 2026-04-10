package com.Proyecto.PPOOII.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "persona")
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private int id;

    @Column(name="PNombre")
    private String pnombre;

    @Column(name="Edad")
    private int edad;

    public Persona() {}

    public Persona(String pnombre, int edad) {
        this.pnombre = pnombre;
        this.edad = edad;
    }

    public Persona(int id, String pnombre, int edad) {
        this.id = id;
        this.pnombre = pnombre;
        this.edad = edad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getPnombre() {
        return pnombre;
    }

    public void setPnombre(String pnombre) {
        this.pnombre = pnombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}