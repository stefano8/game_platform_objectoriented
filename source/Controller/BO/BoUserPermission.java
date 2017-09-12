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
import MODEL.User;
import MODEL.UserGroup;
import MODEL.UserGroupImpl;
import MODEL.UserImpl;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class BoUserPermission extends BaseServlet {

    /**
     *
     * @param request
     * @param response
     * @throws Exception
     */
    protected void action_update(HttpServletRequest request, HttpServletResponse response) throws Exception{
    
        
        String id = request.getParameter("idUser");
        String value = id.replace("\"","");
        
        
        int iduser =  Integer.parseInt(value);
        int idusergroup =  Integer.parseInt(request.getParameter("inputid"));
        int idgroup =  Integer.parseInt(request.getParameter("idGroup"));
      
        
        
        
        UserGroup usergroup = new UserGroupImpl();
        usergroup.setId(idusergroup);
        
        Group group = new Group();
        group.setIdgroup(idgroup);
        
        User user = new UserImpl();
        user.setId(iduser);
        usergroup.setGroup(group);
        usergroup.setUser(user);
        BoMgr.getInstance().updateUserGroup(usergroup);
    }
    
    /**
     *
     * @param request
     * @param response
     */
    protected void action_default(HttpServletRequest request, HttpServletResponse response){
    
        TemplateResult template = new TemplateResult(getServletContext());
        request.setAttribute("outline_tpl", "indexBO.html");// "outline_tpl", request, response);
        request.setAttribute("page_title", "BO User Permission");
                
        try {
           
            HttpSession session = request.getSession(true);
            List<Group> listagruppi;
            List<UserGroup> listausergroup ;
            if(session.getAttribute("ruolo").equals("ADMIN")){
            
                listagruppi = BoMgr.getInstance().getListGroup();
            
                listausergroup = BoMgr.getInstance().getListUserGroup();
            }
            else{
              
                listausergroup = BoMgr.getInstance().getListUserOnly();
                listagruppi = BoMgr.getInstance().getListGroupMod();
              
            }
            
            
            request.setAttribute("listgroup",listagruppi );
            request.setAttribute("listausergroup",listausergroup );
            
        } catch (Exception ex) {
            Logger.getLogger(BOGroup.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try
        {
            template.activate("BOUserPermission.html", request, response);
        }
        catch (TemplateManagerException ex) 
        {
            Logger.getLogger(BOGroup.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    
    
    }
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     */
    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    {
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
                if(
                        (request.getParameter("idUser")!= null) && !(request.getParameter("idUser").isEmpty())&&
                        (request.getParameter("inputid")!= null) && !(request.getParameter("inputid").isEmpty())&&
                        (request.getParameter("idGroup")!= null) && !(request.getParameter("idGroup").isEmpty())
                        
                        ){
                    
                    try {
                        action_update(request, response);
                    } catch (Exception ex) {
                        Logger.getLogger(BoUserPermission.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
                action_default(request,response);
                
            }   } catch (Exception ex) {
            Logger.getLogger(BoUserPermission.class.getName()).log(Level.SEVERE, null, ex);
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
