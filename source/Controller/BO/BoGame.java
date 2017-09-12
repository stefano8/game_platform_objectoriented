/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.BO;

import Business.BoMgr;
import Business.GameMgr;
import Business.PermissionMgr;
import Controller.BaseServlet;
import EXCEPTION.TemplateManagerException;
import MODEL.Game;
import MODEL.IMPL.GameImpl;
import MODEL.Group;
import MODEL.Service;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import result.FailureResult;
import result.TemplateResult;

/**
 *
 * @author tom
 */
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,    // 1 MB
        maxFileSize = 1024 * 1024 * 5,        // 5 MB
        maxRequestSize = 1024 * 1024 * 5 * 5    // 25 MB
)
/**
 *
 * @author tom
 */
public class BoGame extends BaseServlet {

    /**
     *
     * @param request
     * @param response
     */
    protected void action_default(HttpServletRequest request, HttpServletResponse response){
        
        TemplateResult template = new TemplateResult(getServletContext());
        request.setAttribute("outline_tpl", "indexBO.html");// "outline_tpl", request, response);
        request.setAttribute("page_title", "BO Game");
                
        
        try
        {
            template.activate("BOGame.html", request, response);
        }
        catch (TemplateManagerException ex) 
        {
            Logger.getLogger(BOGroup.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     *
     * @param request
     * @param response
     */
    protected void action_upload(HttpServletRequest request, HttpServletResponse response) {
    
        
        String title = request.getParameter("titologioco");
        String tipo = request.getParameter("tipogioco");
        String description = request.getParameter("descrizionegioco");
        int value = Integer.parseInt(request.getParameter("valoregioco"));
        
        Game game = new GameImpl();
        
        game.setTitle(title);
        game.setType(tipo);
        game.setDescription(description);
        game.setValue(value);
        game.setTemplate(game.getTitle()+".html");
        try {
            Part file_to_upload = request.getPart("file");
            String type=file_to_upload.getContentType();
            type="."+type.substring(type.lastIndexOf("/")+1);
             if(".html".equals(type))
            {
                String path = getServletContext().getInitParameter("uploads.directory");
               
                
                String s = "foldername";
                
               
                File permfile = new File(path, game.getTitle()+".html");
                permfile.createNewFile();
                
                 
                
                try(InputStream is = file_to_upload.getInputStream();
                        OutputStream os = new FileOutputStream(permfile))
                {
                    byte[] buffer = new byte[1024];
                    int read;
                    while((read = is.read(buffer)) > 0)
                    {
                        os.write(buffer, 0, read);
                    }
                    
                    
                    
                }
            }
            
            
            try {
                GameMgr.getInstance().insertGame(game);
            } catch (SQLException ex) {
                Logger.getLogger(BoGame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(BoGame.class.getName()).log(Level.SEVERE, null, ex);
            }
             
        } catch (IOException | ServletException ex) {
            Logger.getLogger(BoGame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response){
     
        try {
            HttpSession session = request.getSession(false);
            if(session.getAttribute("ruolo").equals("ADMIN"))request.setAttribute("admin", "");
            List<Service>permission = PermissionMgr.getIstance().getListService((String)session.getAttribute("username"))                ;
            //se non possiede i diritti mostro schermata di errore
               
            if(!PermissionMgr.getIstance().isPermitted(permission,getServletName()))
            {
            
                (new FailureResult(getServletContext())).activate("Non si dispone dei diritti necessari", request, response);
                
            }
            else
            {
                try {
                    if(request.getPart("file") != null &&
                            (request.getParameter("titologioco")!= null) && !(request.getParameter("titologioco").isEmpty())&&
                            (request.getParameter("tipogioco")!= null) && !(request.getParameter("tipogioco").isEmpty())&&
                            (request.getParameter("descrizionegioco")!= null) && !(request.getParameter("descrizionegioco").isEmpty())&&
                            (request.getParameter("valoregioco")!= null) && !(request.getParameter("valoregioco").isEmpty())
                            ){
                        
                        action_upload(request,response);
                        
                    }
                } catch (IOException | ServletException ex) {
                    Logger.getLogger(BoGame.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                action_default(request,response);
                
            }   } catch (Exception ex) {
            Logger.getLogger(BoGame.class.getName()).log(Level.SEVERE, null, ex);
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
