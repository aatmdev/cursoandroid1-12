package org.cursoandroid.applicationbmi.styleapplication.models;

import java.io.Serializable;

/**
 * Created by aatovarma on 2/02/2017.
 */

public class PointDTO implements Serializable{

    private int id;
    private String direccion;
    private String nombre;

    public PointDTO() {
        super();
    }

    public PointDTO(int id, String direccion, String nombre) {
        this.id = id;
        this.direccion = direccion;
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
