/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.primehiber.beans;

import com.mycompany.primehiber.factory.FactoryMethod;
import com.mycompany.primehiber.inter.IDAO;
import com.mycompany.primehiber.pojo.Empleado;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author gaddiel
 */
@Named(value = "empleadoBean")
@SessionScoped
public class EmpleadoBean implements Serializable {

    private Empleado empleado = new Empleado();
    private IDAO daoEmp = FactoryMethod.create(FactoryMethod.TypeDAO.EMPLEADO);
    private List<Empleado> emps;

    /**
     * Creates a new instance of EmpleadoBean
     */
    public EmpleadoBean() {
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public List<Empleado> getEmps() {
        return emps = daoEmp.showAll();
    }

    public void guardar() {
        if (daoEmp.insert(getEmpleado())) {
            addMessage(FacesMessage.SEVERITY_INFO, "Guardado", "El empleado se ha guardado exitosamente");
        } else {
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se ha podido guardar");
        }
    }

    public void actualizar() {
        if (daoEmp.update(getEmpleado())) {
            addMessage(FacesMessage.SEVERITY_INFO, "Actualizado", "El empleado se ha actualizado exitosamente");
        } else {
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se ha podido actualizar");
        }
    }

    public void eliminar() {
        if (daoEmp.delete(getEmpleado())) {
            addMessage(FacesMessage.SEVERITY_INFO, "Eliminado", "El empleado se ha eliminado exitosamente");
        } else {
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se ha podido eliminar");
        }
    }

    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }

}
