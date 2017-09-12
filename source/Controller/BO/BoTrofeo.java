/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.BO;

import Business.ImageMgr;
import Business.PermissionMgr;
import Business.TrofeoMgr;
import Controller.BaseServlet;
import MODEL.Image;
import MODEL.IMPL.ImageImpl;
import MODEL.Trophy;
import MODEL.TrophyImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import EXCEPTION.TemplateManagerException;
import MODEL.Service;
import javax.servlet.http.HttpSession;
import result.FailureResult;
import result.TemplateResult;

/**
 *
 * @author tom
 */
public class BoTrofeo extends BaseServlet {

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
            List<Trophy> listatrofei = TrofeoMgr.getIstance().getListaTrofei();
            List<Image> listaimage = ImageMgr.getIstance().listImage();
            request.setAttribute("listatrofei",listatrofei );
            request.setAttribute("listaimage",listaimage );
        } catch (Exception ex) {
            Logger.getLogger(BOGroup.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try
        {
            template.activate("BOtrofeo.html", request, response);
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
     * @throws Exception
     */
    protected void action_insert(HttpServletRequest request, HttpServletResponse response) throws Exception{
    
        Trophy trofeo = new TrophyImpl();
        
        Image image = new ImageImpl();
        image.setId_image(Integer.parseInt(request.getParameter("id_image")));
        
        trofeo.setName(request.getParameter("nomeTrofeo"));
        trofeo.setImage(image);
        TrofeoMgr.getIstance().createTrofeo(trofeo);
    
    }

    /**
     *
     * @param request
     * @param response
     * @throws Exception
     */
    protected void action_delete(HttpServletRequest request, HttpServletResponse response) throws Exception{
    
        
        String id = request.getParameter("deletetrofeo");
        
        String value = id.replace("\"","");
        
        int idtrofeo = Integer.parseInt(value);
        
        Trophy trofeo = new TrophyImpl();
        trofeo.setId_trophy(idtrofeo);
    
        TrofeoMgr.getIstance().removeTrofeo(trofeo);
        
    }

    /**
     *
     * @param request
     * @param response
     * @throws Exception
     */
    protected void action_update(HttpServletRequest request, HttpServletResponse response) throws Exception{
    
        Trophy trofeo = new TrophyImpl();
        Image image = new ImageImpl();
        
        image.setId_image(Integer.parseInt(request.getParameter("id_image")));
        trofeo.setImage(image);
        trofeo.setId_trophy(Integer.parseInt(request.getParameter("inputid")));
        trofeo.setName(request.getParameter("inputname"));
    
        TrofeoMgr.getIstance().storeTrofeo(trofeo);
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
                if (
                        (request.getParameter("nomeTrofeo")!= null) && !(request.getParameter("nomeTrofeo").isEmpty())&&
                        (request.getParameter("id_image")!= null) && !(request.getParameter("id_image").isEmpty())
                        )
                {
                    
                    try {
                        action_insert(request, response);
                    } catch (Exception ex) {
                        Logger.getLogger(BoTrofeo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
                
                //deletetrofeo
                if((request.getParameter("deletetrofeo")!= null) && !(request.getParameter("deletetrofeo").isEmpty())){
                    
                    try {
                        action_delete(request, response);
                    } catch (Exception ex) {
                        Logger.getLogger(BoTrofeo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
                
                //update
                if (
                        (request.getParameter("inputname")!= null) && !(request.getParameter("inputname").isEmpty())&&
                        (request.getParameter("inputid")!= null) && !(request.getParameter("inputid").isEmpty())&&
                        (request.getParameter("id_image")!= null) && !(request.getParameter("id_image").isEmpty())
                        )
                {
                    
                    try {
                        action_update(request, response);
                    } catch (Exception ex) {
                        Logger.getLogger(BoTrofeo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
                
                action_default(request,response);
            }
        } catch (Exception ex) {        
            Logger.getLogger(BoTrofeo.class.getName()).log(Level.SEVERE, null, ex);
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
