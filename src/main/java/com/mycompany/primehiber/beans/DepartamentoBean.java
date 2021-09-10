package com.mycompany.primehiber.beans;

import com.mycompany.primehiber.factory.FactoryMethod;
import com.mycompany.primehiber.inter.IDAO;
import com.mycompany.primehiber.pojo.Departamento;
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
@Named(value = "departamentoBean")
@SessionScoped
public class DepartamentoBean implements Serializable {

    private Departamento departamento = new Departamento();
    private IDAO daoDep = FactoryMethod.create(FactoryMethod.TypeDAO.DEPARTAMENTO);
    private List<Departamento> depas;

    /**
     * Creates a new instance of DepartamentoBean
     */
    public DepartamentoBean() {
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public void guardar() {
        if (daoDep.insert(getDepartamento())) {
            addMessage(FacesMessage.SEVERITY_INFO, "Guardado", "El departamento se ha guardado exitosamente");
        } else {
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "El departamento no se ha podido guardar exitosamente");
        }
    }

    public void eliminar() {
        if (daoDep.delete(getDepartamento())) {
            addMessage(FacesMessage.SEVERITY_INFO, "Eliminado", "El departamento se ha eliminado exitosamente");
        } else {
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "El departamento no se ha podido eliminar");
        }
    }

    public void actualizar() {
        if (daoDep.update(getDepartamento())) {
            addMessage(FacesMessage.SEVERITY_INFO, "Actualizado", "El departamento se ha actualizado exitosamente");
        } else {
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "El departamento no se ha encontrado");
        }
    }

    public List<Departamento> getDepas() {
        return depas = daoDep.showAll();
    }

    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }

}
