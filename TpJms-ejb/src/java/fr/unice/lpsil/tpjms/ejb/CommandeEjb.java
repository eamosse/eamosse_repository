/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.unice.lpsil.tpjms.ejb;

import fr.unice.lpsil.tpjms.Commande;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
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
        try {
            em.persist(object);
            sendJMSMessageToLoggingMessage("Validation de paiement");
        } catch (JMSException ex) {
            Logger.getLogger(CommandeEjb.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    private Message createJMSMessageForjmsLoggingMessage(Session session, Object messageData) throws JMSException {
        // TODO create and populate message to send
        if (messageData instanceof Commande) {
            ObjectMessage o = session.createObjectMessage();
            o.setObject((Commande) messageData);
            return o;
        } else {
            TextMessage tm = session.createTextMessage(messageData.toString());
            tm.setText(messageData.toString());
            return tm;
        }
    }

    private void sendJMSMessageToLoggingMessage(Object messageData) throws JMSException {
        Connection connection = null;
        Session session = null;
        try {
            connection = loggingMessageFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(loggingMessage);
            messageProducer.send(createJMSMessageForjmsLoggingMessage(session, messageData));
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (JMSException e) {
                    Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Cannot close session", e);
                }
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}
