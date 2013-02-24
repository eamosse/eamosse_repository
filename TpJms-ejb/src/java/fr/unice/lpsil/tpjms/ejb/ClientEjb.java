/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.unice.lpsil.tpjms.ejb;

import fr.unice.lpsil.tpjms.Client;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Edou
 */
@Stateless
public class ClientEjb implements ClientEjbLocal {
    @PersistenceContext(unitName = "TpJms-ejbPU")
    private EntityManager em;

    @Override
    public void persist(Object object) {
        em.persist(object);
    }
    
    @Override
    public Client get(Long id){
        return em.find(Client.class, id);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
     
}
