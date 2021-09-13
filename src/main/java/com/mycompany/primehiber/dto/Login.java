package com.mycompany.primehiber.dto;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean
@SessionScoped
public class Login implements Serializable {

    private static final long serialVersionUID = 1094801825228386363L;

    private String pwd;
    private String msg;
    private String user;

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    //validate login
    public String validateUsernamePassword() {
        addMessage(FacesMessage.SEVERITY_INFO, "Datos", "Clave: " + this.user + " Nombre: " + this.pwd );
        boolean valid;

        valid = LoginDAO.validate(this.user, this.pwd);

        if (valid) {
            HttpSession session = SessionUtil.getSession();
            session.setAttribute("name", user);
            return "admin";
        } else {
            addMessage(FacesMessage.SEVERITY_ERROR, "Incorrect Username and Passowrd", "Please enter correct username and Password");
            return "login";
        }
    }
    
    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }

    //logout event, invalidate session
    public String logout() {
        HttpSession session = SessionUtil.getSession();
        session.invalidate();
        return "login";
    }
}
