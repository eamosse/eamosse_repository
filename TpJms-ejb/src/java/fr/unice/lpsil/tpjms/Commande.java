/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.unice.lpsil.tpjms;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author Edou
 */
@Entity
public class Commande implements Serializable {
    public static int ATTENTE_PAIEMENT=0; 
    public static int ATTENTE_LIVRAISON=1; 
    public static int REFUSE = -1; 
    public static int LIVRE = 2;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateCommande; 
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateLivraison; 
    private int statut; 
    @ManyToOne
    private Client client; 
    @OneToMany(mappedBy = "commande", cascade= CascadeType.PERSIST)
    private List<CommandeDetail> details; 
    private double montant;
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Commande)) {
            return false;
        }
        Commande other = (Commande) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.unice.lpsil.tpjms.Commande[ id=" + id + " ]";
    }

    public Date getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(Date dateCommande) {
        this.dateCommande = dateCommande;
    }

    public Date getDateLivraison() {
        return dateLivraison;
    }

    public void setDateLivraison(Date dateLivraison) {
        this.dateLivraison = dateLivraison;
    }

    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<CommandeDetail> getDetails() {
        return details;
    }

    public void setDetails(List<CommandeDetail> details) {
        this.details = details;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }
    
}
