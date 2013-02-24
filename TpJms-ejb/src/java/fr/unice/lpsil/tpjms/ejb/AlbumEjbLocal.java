/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.unice.lpsil.tpjms.ejb;

import fr.unice.lpsil.tpjms.Album;
import java.util.Collection;
import javax.ejb.Local;

/**
 *
 * @author Edou
 */
@Local
public interface AlbumEjbLocal {


    public java.util.Collection<Album> list();

    public fr.unice.lpsil.tpjms.Album get(java.lang.Long idAlbum);

    public void delete(Album album);

    public void persist(Object album) throws Exception;
    
}
