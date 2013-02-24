/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.unice.lpsil.tpjms.servlet;

import fr.unice.lpsil.tpjms.Album;
import fr.unice.lpsil.tpjms.Client;
import fr.unice.lpsil.tpjms.CommandeDetail;
import fr.unice.lpsil.tpjms.ejb.AlbumEjbLocal;
import fr.unice.lpsil.tpjms.ejb.ClientEjbLocal;
import fr.unice.lpsil.tpjms.ejb.CommandeEjbLocal;
import java.util.Date;
import java.util.ArrayList;
import java.util.Collection;
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
public class CommandeManagedBean {

    @EJB
    private ClientEjbLocal clientEjb;
    @EJB
    private AlbumEjbLocal albumEjb;
    @EJB
    private CommandeEjbLocal commandeEjb;
    private fr.unice.lpsil.tpjms.Commande commande;
    private Long album;
    private int quantite;
    private int size;
    private Date date_livraison;
    //Clients attributes 
    private String client_nom;
    private String client_prenom;
    private String client_adresse;
    private String client_telephone;
    private String client_email;

    //End clients attributes
    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Long getAlbum() {
        return album;
    }

    public void setAlbum(Long album) {
        this.album = album;
    }

    public Collection<Album> albums() {
        return albumEjb.list();
    }

    public void finish_commande() {
        Client client = new Client();
        client.setAdresse(client_adresse);
        client.setEmail(client_email);
        client.setPhone(client_telephone);
        client.setPrenom(client_prenom);
        client.setNom(client_nom);
        clientEjb.persist(client);
        commande.setClient(client);
        commande.setDateLivraison(date_livraison);
        commande.setStatut(fr.unice.lpsil.tpjms.Commande.ATTENTE_PAIEMENT);
        commandeEjb.persist(commande);
        display("Thank you", "Merci de votre visite, nous vous tiendrons informer de l'evolution de votre commande");
    }

    public void display(String title, String message) {
        FacesMessage msg = new FacesMessage(title, message);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void addToBasket() {
        if (getCommande() == null) {
            setCommande(new fr.unice.lpsil.tpjms.Commande());
        }
        if (getCommande().getDetails() == null) {
            getCommande().setDetails(new ArrayList<CommandeDetail>());
        }
        CommandeDetail detail = new CommandeDetail();
        detail.setAlbum(albumEjb.get(album));
        detail.setQuantite(quantite);
        detail.setCommande(commande);
        getCommande().getDetails().add(detail);
        getCommande().setMontant(getCommande().getMontant() + quantite * detail.getAlbum().getPrix());
    }

    /**
     * @return the commande
     */
    public fr.unice.lpsil.tpjms.Commande getCommande() {
        if (commande == null) {
            commande = new fr.unice.lpsil.tpjms.Commande();
            commande.setDateCommande(new Date(System.currentTimeMillis()));
        }
        return commande;
    }

    /**
     * @param commande the commande to set
     */
    public void setCommande(fr.unice.lpsil.tpjms.Commande commande) {
        this.commande = commande;
    }

    /**
     * @return the size
     */
    public int getSize() {
        size  = (commande==null || commande.getDetails()==null) ? 0 : commande.getDetails().size();
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(int size) {
        this.size = size;
    }

    public String getClient_nom() {
        return client_nom;
    }

    public void setClient_nom(String client_nom) {
        this.client_nom = client_nom;
    }

    public String getClient_prenom() {
        return client_prenom;
    }

    public void setClient_prenom(String client_prenom) {
        this.client_prenom = client_prenom;
    }

    public String getClient_adresse() {
        return client_adresse;
    }

    public void setClient_adresse(String client_adresse) {
        this.client_adresse = client_adresse;
    }

    public String getClient_telephone() {
        return client_telephone;
    }

    public void setClient_telephone(String client_telephone) {
        this.client_telephone = client_telephone;
    }

    public String getClient_email() {
        return client_email;
    }

    public void setClient_email(String client_email) {
        this.client_email = client_email;
    }

    /**
     * @return the date_livraison
     */
    public Date getDate_livraison() {
        return date_livraison;
    }

    /**
     * @param date_livraison the date_livraison to set
     */
    public void setDate_livraison(Date date_livraison) {
        this.date_livraison = date_livraison;
    }
}
