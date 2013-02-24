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
public interface CommandeEjbLocal {

    public void persist(java.lang.Object object);
    
}
