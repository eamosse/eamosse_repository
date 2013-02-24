/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.unice.lpsil.tpjms.servlet;

import fr.unice.lpsil.tpjms.Album;
import fr.unice.lpsil.tpjms.Artiste;
import fr.unice.lpsil.tpjms.ejb.AlbumEjbLocal;
import fr.unice.lpsil.tpjms.ejb.ArtisteEjbLocal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Edou
 */
@ManagedBean
@SessionScoped
public class AlbumBean {
    @EJB
    private ArtisteEjbLocal artisteEjb;
    @EJB
    private AlbumEjbLocal albumEjb;
    
    private String nom; 
    private String genre; 
    private double prix; 
    private Long artiste; 
    private List<Artiste> artistes;
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
   
    
    public void save(){
        try {
            Album album = new Album(); 
            album.setNom(nom);
            album.setPrix(prix);
            album.setGenre(genre);
            album.setArtiste(artisteEjb.get(getArtiste()));
            albumEjb.persist(album);
             FacesMessage msg = new FacesMessage("Created", "Album ajouté");  
             FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (Exception ex) {
            FacesMessage msg = new FacesMessage("Error", ex.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
            Logger.getLogger(AlbumBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @return the artistes
     */
    public List<Artiste> getArtistes() {
        if(artistes==null) {
            artistes = artisteEjb.list();
        }
        return artistes;
    }

    /**
     * @param artistes the artistes to set
     */
    public void setArtistes(List<Artiste> artistes) {
        this.artistes = artistes;
    }

    /**
     * @return the artiste
     */
    public Long getArtiste() {
        return artiste;
    }

    /**
     * @param artiste the artiste to set
     */
    public void setArtiste(Long artiste) {
        this.artiste = artiste;
    }
}
