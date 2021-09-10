/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.primehiber.inter;

import java.util.List;

/**
 *
 * @author gaddiel
 */
public interface IDAO<T> {

    public boolean insert(T pojo);

    public boolean delete(T pojo);

    public boolean update(T pojo);

    public List searchById(String clave);

    public List showAll();

}
