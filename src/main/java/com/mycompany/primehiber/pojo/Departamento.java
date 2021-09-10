package com.mycompany.primehiber.pojo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author gaddiel
 */
@Entity
@Table(name = "departamentos")
public class Departamento implements Serializable {

    @Id
    @Column(name = "clave")
    private String clave;

    @Column(name = "nombre")
    private String nombre;

    public Departamento() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

}
