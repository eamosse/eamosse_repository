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
public interface ArtisteEjbLocal {

    public fr.unice.lpsil.tpjms.Artiste get(long idArtiste);

    public java.util.List<fr.unice.lpsil.tpjms.Artiste> list();

    
}
