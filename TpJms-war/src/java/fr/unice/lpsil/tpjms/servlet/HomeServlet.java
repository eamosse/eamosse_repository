/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.unice.lpsil.tpjms.servlet;

import fr.unice.lpsil.tpjms.Album;
import fr.unice.lpsil.tpjms.Artiste;
import fr.unice.lpsil.tpjms.Musique;
import fr.unice.lpsil.tpjms.ejb.AlbumEjbLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Edou
 */
public class HomeServlet extends HttpServlet {
    @EJB
    private AlbumEjbLocal albumEjb;
    public String produit ="Je fais un test de merde";

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet HomeServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HomeServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
            Initialize();
//            Artiste artiste = new Artiste(); 
//            artiste.setNationalite("Haitienne");
//            artiste.setNom("Edouard");
//            artiste.setPrenom("Amosse");
//            artiste.setSexe("Masculin");
//                    try {
//                        albumEjb.persist(artiste);
//                    } catch (Exception ex) {
//                        Logger.getLogger(HomeServlet.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//             List<Artiste> artists = albumEjb.list(Album.class);
             
            
        } finally {            
            out.close();
        }
    }
    
    public void Initialize(){
       
        try {
        Artiste bolton = new Artiste(); 
        bolton.setNom("Bolton");
        bolton.setPrenom("Michale");
        bolton.setNationalite("Americaine");
        bolton.setSexe("Masculin");
        albumEjb.persist(bolton);
       Artiste dion = new Artiste(); 
        dion.setNom("Dion");
        dion.setPrenom("Celine");
        dion.setNationalite("Quebecquoise");
        dion.setSexe("Féminin");
        albumEjb.persist(dion);
        Artiste iglesias = new Artiste(); 
        iglesias.setNom("Iglesias");
        iglesias.setPrenom("Enrike");
        iglesias.setNationalite("Espagnole");
        iglesias.setSexe("Masculin");
         albumEjb.persist(iglesias);
        //Creer quelques albums 
        Album album = new Album(); 
        album.setArtiste(bolton);
        album.setDate_de_sortie(new Date(2000,12,12));
        album.setNom("You are the one");
        album.setPrix(120);
        album.setGenre("POP");
        albumEjb.persist(album);
        Musique musique = new Musique(); 
        musique.setDuree(12);
        musique.setPosition(1);
        musique.setAlbum(album);
        musique.setTitre("You are the one");
        albumEjb.persist(musique);
        } catch (Exception ex) {
            Logger.getLogger(HomeServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   
    
    public Collection<Album> test(){
        System.out.println("Heeeeeeeeeeeeeeeeeeeeee");
        if (albumEjb!=null){
            System.out.println("pas null");
        for(Album alb : albumEjb.list()){
            System.out.println("Album " + alb.getNom());
        }
        return albumEjb.list();
        }else{
            System.out.println("nullllllllllllllll");
            return null;
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
