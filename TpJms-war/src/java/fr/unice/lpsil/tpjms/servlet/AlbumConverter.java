/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.unice.lpsil.tpjms.servlet;

import fr.unice.lpsil.tpjms.Album;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Edou
 */
@FacesConverter(forClass=Album.class,value="albumConverter")
public class AlbumConverter  implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        System.out.println("lolllllllll "+value);
        return new Album();
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Album album = (Album)value; 
        return album.getNom();
    }
    
}
