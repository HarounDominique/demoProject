package com.haroun.server.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name="provincias")
public class Provincia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    public String nombre;

    @OneToMany(mappedBy = "provincia", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Localidad> localidades;

    public Provincia(int id, List<Localidad> localidades, String nombre) {
        this.id = id;
        this.localidades = localidades;
        this.nombre = nombre;
    }

    public Provincia(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Provincia() {
    }
}
