/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.unice.lpsil.tpjms.servlet;

import fr.unice.lpsil.tpjms.Album;
import fr.unice.lpsil.tpjms.Artiste;
import fr.unice.lpsil.tpjms.ejb.AlbumEjbLocal;
import fr.unice.lpsil.tpjms.ejb.ArtisteEjbLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Edou
 */
public class AlbumServlet extends HttpServlet {
    @EJB
    private ArtisteEjbLocal artisteEjb;
    @EJB
    private AlbumEjbLocal albumEjb;

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
            out.println("<title>Servlet AlbumServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AlbumServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
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
       // processRequest(request, response);
        //ce parametre est envoyé dans l'url de la requete 
        String action = request.getParameter("action"); 
        if(action!=null){
            if(action.equalsIgnoreCase("save")){
               //On recuperer les parametres envoyees dans le corps de la requete 
              save(request, response);  
            }
        }
    }
    
    private void save (HttpServletRequest request, HttpServletResponse response){
            //On appelle la session bean qui est responsable de la persistence 
        try{
                //Les conversions peuvent generer des exceptions
                String nom = request.getParameter("nom");  
               String genre = request.getParameter("genre");  
               Date date_de_sortie = Date.valueOf(request.getParameter("date_de_sortie"));
               double prix = Double.parseDouble(request.getParameter("prix")); 
               long idArtist = Integer.parseInt(request.getParameter("idArtist"));
               //On cree une instance d'album avec les parametres 
               Album album = new Album(); 
               album.setNom(nom);
               album.setDate_de_sortie(date_de_sortie);
               album.setGenre(genre);
               album.setPrix(prix);
               //On essaie de recuperer l'artiste de l'album a partir de l'id 
               Artiste artiste = artisteEjb.get(idArtist);
               //Ajouter l'artiste dans l'album 
               album.setArtiste(artiste);
               albumEjb.persist(album);
               forward ("Album sauvegardé","success.jsp", request, response);
               //save(album);  
                }catch(Exception e){
                    //Afficher des messages d'erreur 
                    forward (e.getMessage(),"erreur.jsp", request, response);
                }
    }
    
     protected void forward(String message,String page, HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute("Message", message);
            RequestDispatcher dp = request.getRequestDispatcher(page);
            dp.forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(AlbumServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AlbumServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
