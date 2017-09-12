
package Controller.BO;

import Business.BoMgr;
import Business.PermissionMgr;
import Controller.BaseServlet;
import MODEL.Group;
import MODEL.GroupService;
import MODEL.GroupServiceImpl;
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
public class BOGroupService extends BaseServlet {

    /**
     * 
     * @param request
     * @param response
     * @throws Exception 
     */
    protected void action_insert(HttpServletRequest request, HttpServletResponse response) throws Exception{
    
        int idGroup   = Integer.parseInt(request.getParameter("ToServizio"));
        int idService = Integer.parseInt(request.getParameter("ToGruppo"));
    
        Group group = new Group();
        
        group.setIdgroup(idGroup);
        Service service = new Service();
        service.setIdService(idService);
        
        GroupService groupservice = new GroupServiceImpl();
        
        groupservice.setGroup(group);
        groupservice.setService(service);
        
        BoMgr.getInstance().createGroupService(groupservice);
    }
    /**
     * 
     * @param request
     * @param response
     * @throws Exception 
     */
    protected void actio_delete(HttpServletRequest request, HttpServletResponse response) throws Exception{
    
        String id = request.getParameter("delete");
        String value = id.replace("\"","");
        int idGS = Integer.parseInt(value);
        
        GroupService groupservice = new GroupServiceImpl();
        groupservice.setIDgroupservice(idGS);
        
        BoMgr.getInstance().removeGroupService(groupservice);
    }
    /**
     * 
     * @param request
     * @param response 
     */
    protected void action_default(HttpServletRequest request, HttpServletResponse response){
    
        TemplateResult template = new TemplateResult(getServletContext());
        request.setAttribute("outline_tpl", "indexBO.html");// "outline_tpl", request, response);
        request.setAttribute("page_title", "BO Group Service");
                
        try {
           
            List <GroupService> listgroupservice = BoMgr.getInstance().getListGroupService();
            List<Group> listagruppi = BoMgr.getInstance().getListGroup();
            List<Service> listaservizi = BoMgr.getInstance().getListService();
            
            request.setAttribute("listgroupservice",listgroupservice );
            request.setAttribute("listgroup",listagruppi );
            request.setAttribute("listservice",listaservizi );
        } catch (Exception ex) {
            Logger.getLogger(BOGroup.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try
        {
            template.activate("groupservice.html", request, response);
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
                
                
                if((request.getParameter("delete")!= null) && !(request.getParameter("delete").isEmpty()))
                {
                    
                    try {
                        actio_delete(request, response);
                    } catch (Exception ex) {
                        Logger.getLogger(BOGroupService.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
                
                
                if( (request.getParameter("ToServizio")!= null) && !(request.getParameter("ToServizio").isEmpty())&&
                        (request.getParameter("ToGruppo")!= null) && !(request.getParameter("ToGruppo").isEmpty()))
                    
                {
                    
                    try {
                        action_insert(request,response);
                    } catch (Exception ex) {
                        Logger.getLogger(BOGroupService.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
                
                
                action_default(request, response);
                
            }   } catch (Exception ex) {
            Logger.getLogger(BOGroupService.class.getName()).log(Level.SEVERE, null, ex);
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
