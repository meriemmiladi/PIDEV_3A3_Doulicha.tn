/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doulicha.services;

import java.util.List;

/**
 *
 * @author aziz
 */
public interface Crud <T> {
    public T AddOne(T entity);
    public T RetreiveOne(int id_Entity);
    public List<T> RetreiveAll();
    public T DeleteOne(int id_Entity);
    public T UpdateOne(T entity);
}
