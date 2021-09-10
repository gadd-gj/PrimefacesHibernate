/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.primehiber.dto;

import com.mycompany.primehiber.dao.EmpleadoDAO;
import com.mycompany.primehiber.util.HibernateUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author gaddiel
 */
public class LoginDAO {

    private Session session;

    public boolean validate(String clave, String nombre) {

        boolean res = false;
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {

            tx = session.beginTransaction();
            Query query = session.createQuery("FROM pojo.Empleado WHERE clave = :clave AND nombre = :nombre");
            query.setParameter("clave", clave);
            query.setParameter("nombre", nombre);
            tx.commit();

            res = true;
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            Logger.getLogger(EmpleadoDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            session.close();
        }

        return res;
    }
}
