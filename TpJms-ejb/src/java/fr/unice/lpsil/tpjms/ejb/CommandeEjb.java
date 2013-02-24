/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.unice.lpsil.tpjms.ejb;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Edou
 */
@Stateless
public class CommandeEjb implements CommandeEjbLocal {

    @Resource(mappedName = "jms/loggingMessage")
    private Queue loggingMessage;
    @Resource(mappedName = "jms/loggingMessageFactory")
    private ConnectionFactory loggingMessageFactory;
    @PersistenceContext(unitName = "TpJms-ejbPU")
    private EntityManager em;

    @Override
    public void persist(Object object) {
            em.persist(object);
    }
}
