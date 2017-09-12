/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.BO;

import Business.BoMgr;
import Business.PermissionMgr;
import Controller.BaseServlet;
import MODEL.Group;
import MODEL.Service;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import EXCEPTION.TemplateManagerException;
import javax.servlet.http.HttpSession;
import result.FailureResult;
import result.TemplateResult;

/**
 *
 * @author tom
 */
public class BOServizi extends BaseServlet {

    /**
     *
     * @param request
     * @param response
     * @throws Exception
     */
    protected void action_addServiceToGroup(HttpServletRequest request, HttpServletResponse response) throws Exception{
        
        int idser = Integer.parseInt(request.getParameter("ToServizio"));
        int idgrup = Integer.parseInt(request.getParameter("ToGruppo"));
        
        PermissionMgr.getIstance().addServiceToGroup(idser, idgrup);
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
            List<Service> listservice = BoMgr.getInstance().getListService();
            request.setAttribute("listservizi", listservice);
            
            List<Group> listgroup = BoMgr.getInstance().getListGroup();
            request.setAttribute("listgroup", listgroup);
            
        } catch (Exception ex) {
            Logger.getLogger(BOServizi.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        try {
           template.activate("servizi.html", request, response);
        } catch (TemplateManagerException ex) {
           Logger.getLogger(BOServizi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     *
     * @param request
     * @param response
     * @throws Exception
     */
    protected void action_insert(HttpServletRequest request, HttpServletResponse response) throws Exception{
    
        Service service = new Service();
    
        service.setName(request.getParameter("nomeServizio"));
    
        BoMgr.getInstance().createService(service);
    
    }
    
    /**
     *
     * @param request
     * @param response
     * @throws Exception
     */
    protected void action_delete(HttpServletRequest request, HttpServletResponse response) throws Exception{
        
        Service service = new Service();
        
        String id = request.getParameter("deleteservizi");
        
        String value = id.replace("\"","");
        
        int idservice = Integer.parseInt(value);
        
        service.setIdService(idservice);
        
        BoMgr.getInstance().removeService(service);
    
    }
    
    /**
     *
     * @param request
     * @param response
     * @throws Exception
     */
    protected void action_update(HttpServletRequest request, HttpServletResponse response) throws Exception{
    
        Service service = new Service();
        int id = Integer.parseInt(request.getParameter("inputid"));
            
        service.setIdService(id);
        service.setName(request.getParameter("inputname"));
            
        BoMgr.getInstance().storeService(service);
    
    
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
                if((request.getParameter("deleteservizi")!= null) && !(request.getParameter("deleteservizi").isEmpty())){
                    
                    try
                    {
                        action_delete(request, response);
                    }
                    catch (Exception ex)
                    {
                        
                        Logger.getLogger(BOServizi.class.getName()).log(Level.SEVERE, null, ex);
                        
                    }
                }
                
                
                if
                        (
                        (request.getParameter("inputname")!= null) && !(request.getParameter("inputname").isEmpty())
                        &&
                        (request.getParameter("inputid")!= null) && !(request.getParameter("inputid").isEmpty())
                        )
                {
                    
                    try {
                        action_update(request, response);
                    } catch (Exception ex) {
                        Logger.getLogger(BOServizi.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
                
                if((request.getParameter("nomeServizio")!= null) && !(request.getParameter("nomeServizio").isEmpty())){
                    
                    try {
                        action_insert(request, response);
                    } catch (Exception ex) {
                        Logger.getLogger(BOServizi.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
                
                if(
                        (request.getParameter("ToServizio")!= null) && !(request.getParameter("ToServizio").isEmpty())&&
                        (request.getParameter("ToGruppo")!= null) && !(request.getParameter("ToGruppo").isEmpty()))
                {
                    try {
                        action_addServiceToGroup(request, response);
                    } catch (Exception ex) {
                        Logger.getLogger(BOServizi.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
                action_default(request,response);
                
            }
        } catch (Exception ex) {
            Logger.getLogger(BOServizi.class.getName()).log(Level.SEVERE, null, ex);
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
