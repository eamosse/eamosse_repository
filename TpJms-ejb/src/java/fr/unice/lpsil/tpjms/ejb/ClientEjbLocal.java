/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.unice.lpsil.tpjms.ejb;

import javax.ejb.Local;

/**
 *
 * @author Edou
 */
@Local
public interface ClientEjbLocal {

    public void persist(java.lang.Object object);

    public fr.unice.lpsil.tpjms.Client get(java.lang.Long id);
    
}
