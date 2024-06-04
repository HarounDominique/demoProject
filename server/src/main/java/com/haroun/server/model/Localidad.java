package com.haroun.server.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name="localidades")
public class Localidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    public String nombre;

    @ManyToOne
    @JoinColumn(name = "provincia_id")
    @JsonBackReference
    private Provincia provincia;

    public Localidad(int id, String nombre, Provincia provincia) {
        this.id = id;
        this.nombre = nombre;
        this.provincia = provincia;
    }

    public Localidad() {
    }
}
