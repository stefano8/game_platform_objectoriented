/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.BO;

import Business.ImageMgr;
import Business.PermissionMgr;
import Controller.BaseServlet;
import MODEL.Image;
import MODEL.IMPL.ImageImpl;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import result.FailureResult;
import EXCEPTION.TemplateManagerException;
import MODEL.Service;
import javax.servlet.http.HttpSession;
import result.TemplateResult;

/**
 *
 * @author tom
 */
public class BoImmagine extends BaseServlet {

    /**
     *
     * @param request
     * @param response
     */
    protected void action_delete(HttpServletRequest request, HttpServletResponse response){
    
        String id = request.getParameter("deleteimage");
            
            String value = id.replace("\"","");
            int idimage = Integer.parseInt(value);
            
            Image image = new ImageImpl();
            image.setId_image(idimage);
            try {
                ImageMgr.getIstance().removeimage(image);
            } catch (Exception ex) {
                Logger.getLogger(BOGroup.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    /**
     *
     * @param request
     * @param response
     * @throws Exception
     */
    protected void action_update(HttpServletRequest request, HttpServletResponse response) throws Exception{
    
        Image image = new ImageImpl();
        int id = Integer.parseInt(request.getParameter("inputid"));
        
        image.setPath(request.getParameter("inputpath"));
        image.setTitle(request.getParameter("inputtitle"));
        image.setId_image(id);
    
        ImageMgr.getIstance().updateimage(image);
    
    }

    /**
     *
     * @param request
     * @param response
     * @throws Exception
     */
    protected void action_insert(HttpServletRequest request, HttpServletResponse response) throws Exception{
    
        Image image = new ImageImpl();
        image.setPath(request.getParameter("pathimage"));
        image.setTitle(request.getParameter("titleimage"));
        
        ImageMgr.getIstance().insertimage(image);

    }
    
    /**
     *
     * @param request
     * @param response
     */
    protected void action_default(HttpServletRequest request, HttpServletResponse response){
       
        TemplateResult template = new TemplateResult(getServletContext());
        request.setAttribute("outline_tpl", "indexBO.html");// "outline_tpl", request, response);
        request.setAttribute("page_title", "BO Group");
                
        try {
            List<Image> listaimage = ImageMgr.getIstance().listImage();
            
            request.setAttribute("listaimage",listaimage );
        } catch (Exception ex) {
            Logger.getLogger(BOGroup.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try
        {
            template.activate("BOimmagine.html", request, response);
        }
        catch (TemplateManagerException ex) 
        {
            Logger.getLogger(BOGroup.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
    
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
                if((request.getParameter("deleteimage")!= null) && !(request.getParameter("deleteimage").isEmpty())){
                    
                    action_delete( request, response);
                }
                if
                        (
                        (request.getParameter("inputpath")!= null) && !(request.getParameter("inputpath").isEmpty())
                        &&
                        
                        (request.getParameter("inputtitle")!= null) && !(request.getParameter("inputtitle").isEmpty())
                        &&
                        (request.getParameter("inputid")!= null) && !(request.getParameter("inputid").isEmpty())
                        ){
                    
                    try {
                        action_update(request,response);
                    } catch (Exception ex) {
                        Logger.getLogger(BOGroup.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
                
                if(
                        (request.getParameter("titleimage")!= null) && !(request.getParameter("titleimage").isEmpty())&&
                        (request.getParameter("pathimage")!= null) && !(request.getParameter("pathimage").isEmpty())
                        ){
                    
                    try {
                        
                        action_insert(request, response);
                    }
                    catch (Exception ex) {
                        
                        request.setAttribute("exception", ex);
                        
                        try {
                            
                            (new FailureResult(getServletContext())).activate(ex.toString(), request, response);
                            
                        }
                        catch (IOException | TemplateException | TemplateManagerException ex1) {
                            
                            Logger.getLogger(BOGroup.class.getName()).log(Level.SEVERE, null, ex1);
                            
                        }
                    }
                }
                
                action_default(request, response);
                
            }   } catch (Exception ex) {
            Logger.getLogger(BoImmagine.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        
    
    }
    
    /**
     *
     * @return
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
