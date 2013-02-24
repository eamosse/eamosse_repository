/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.unice.lpsil.tpjms.ejb;

import fr.unice.lpsil.tpjms.Artiste;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Edou
 */
@Stateless
public class ArtisteEjb implements ArtisteEjbLocal {
    @PersistenceContext(unitName = "TpJms-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @Override
    public Artiste get(long idArtiste){
        return em.find(Artiste.class, idArtiste);
    }
    
    @Override
    public List<Artiste> list(){
        return em.createQuery("select c from Artiste c").getResultList();
    }
}
