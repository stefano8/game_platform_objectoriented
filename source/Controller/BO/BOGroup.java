package Controller.BO;

import Business.BoMgr;
import Business.PermissionMgr;
import Controller.BaseServlet;
import MODEL.Group;
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
public class BOGroup extends BaseServlet {

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
            List<Group> listagruppi = BoMgr.getInstance().getListGroup();
            
            request.setAttribute("listgroup",listagruppi );
        } catch (Exception ex) {
            Logger.getLogger(BOGroup.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try
        {
            template.activate("insertgroup.html", request, response);
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
    protected void action_delete(HttpServletRequest request, HttpServletResponse response){
    
        String id = request.getParameter("deletegroup");
            
            String value = id.replace("\"","");
            int idgroup = Integer.parseInt(value);
            
            Group group = new Group();
            group.setIdgroup(idgroup);
            try {
                BoMgr.getInstance().removeGroup(group);
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
    protected void action_insert(HttpServletRequest request, HttpServletResponse response) throws Exception{
    
        Group group = new Group();
        
        group.setName(request.getParameter("nomeGruppo"));
        
        BoMgr.getInstance().createGroup(group);
    
    }
    /**
     * 
     * @param request
     * @param response
     * @throws Exception 
     */
    protected void action_update(HttpServletRequest request, HttpServletResponse response) throws Exception{
        
        
        Group group = new Group();
        int id = Integer.parseInt(request.getParameter("inputid"));
            
        group.setIdgroup(id);
        group.setName(request.getParameter("inputname"));
    
        BoMgr.getInstance().storeGroup(group);
    }
    
    /**
     * 
     * @param request
     * @param response 
     */ 
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
                if((request.getParameter("deletegroup")!= null) && !(request.getParameter("deletegroup").isEmpty())){
                    
                    action_delete( request, response);
                }
                if
                        (
                        (request.getParameter("inputname")!= null) && !(request.getParameter("inputname").isEmpty())
                        &&
                        (request.getParameter("inputid")!= null) && !(request.getParameter("inputid").isEmpty())
                        ){
                    
                    try {
                        action_update(request,response);
                    } catch (Exception ex) {
                        Logger.getLogger(BOGroup.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
                
                if((request.getParameter("nomeGruppo")!= null) && !(request.getParameter("nomeGruppo").isEmpty())){
                    
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
                
                action_default(request,response);
                
            }   } catch (Exception ex) {
            Logger.getLogger(BOGroup.class.getName()).log(Level.SEVERE, null, ex);
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
