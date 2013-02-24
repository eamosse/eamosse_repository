/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.unice.lpsil.tpjms.banque;

import fr.unice.lpsil.tpjms.Commande;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

/**
 *
 * @author Edou
 */
@MessageDriven(mappedName = "jms/loggingMessage", activationConfig = {
    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class BanqueMBB implements MessageListener {
    
    public BanqueMBB() {
    }
    
    @Override
    public void onMessage(Message message) {
        try {
            Thread.sleep(60*1000) ;
            if( message instanceof TextMessage)
            {
                try {
                    TextMessage m = (TextMessage)message;
                    System.out.println(m.getText());
                } catch (JMSException ex) {
                    Logger.getLogger(BanqueMBB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                if(message instanceof ObjectMessage){
                    ObjectMessage oMessage = (ObjectMessage) message; 
                    if(oMessage.getObject() instanceof Commande){
                        Commande commande = (Commande)oMessage.getObject(); 
                        System.out.print("Nouvelle commande :" ); 
                        System.out.println("Client " + commande.getClient().getNom() + " " + commande.getClient().getPrenom());
                        System.out.println("Montant " + commande.getMontant()); 
                        
                    }
                }
            }
        } catch (JMSException ex) {
            Logger.getLogger(BanqueMBB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(BanqueMBB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
