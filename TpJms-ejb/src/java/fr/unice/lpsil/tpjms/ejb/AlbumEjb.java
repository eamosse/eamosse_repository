/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.unice.lpsil.tpjms.ejb;

import fr.unice.lpsil.tpjms.Album;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Edou
 */
@Stateless
public class AlbumEjb implements AlbumEjbLocal {
    @PersistenceContext(unitName = "TpJms-ejbPU")
    private EntityManager em;
 
    @Override
    public void persist(Object object) throws Exception {
        if(object!=null) {
            em.persist(object);
        }
        else {
            throw new Exception("L'objet ne doit pas etre nul");
        }
    }

    @Override
    public Album get(Long idAlbum)
    {
        Album album = em.find(Album.class, idAlbum);
        return album;
    }

    @Override
    public Collection<Album> list(){
        System.out.println("Call me");
        Query query = em.createQuery("select c from Album c");
        return query.getResultList();
    }
    
     @Override
    public void delete(Album album){
        em.remove(album);
    }


}
