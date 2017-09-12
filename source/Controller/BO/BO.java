package Controller.BO;

import Business.PermissionMgr;
import Controller.BaseServlet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import result.FailureResult;
import EXCEPTION.TemplateManagerException;
import MODEL.Service;
import result.TemplateResult;

/**
 *
 * @author tom
 */
public class BO extends BaseServlet {

    /**
     * 
     * @param request
     * @param response 
     */
    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response){
        
        HttpSession session = request.getSession(false);
        if(session!=null){
        
            try {
            
            List<Service>permission = PermissionMgr.getIstance().getListService((String)session.getAttribute("username"))                ;
            //se non possiede i diritti mostro schermata di errore
               
            if(!PermissionMgr.getIstance().isPermitted(permission,getServletName()))
            {
            
                (new FailureResult(getServletContext())).activate("Non si dispone dei diritti necessari", request, response);
                
            }
            else
            {
            
                if(session.getAttribute("ruolo").equals("ADMIN"))request.setAttribute("admin", "");
                TemplateResult template = new TemplateResult(getServletContext());
                request.setAttribute("outline_tpl", "indexBO.html");// "outline_tpl", request, response);
                template.activate("insertgroup.html", request, response);
        
            }
        
            }
        catch (TemplateManagerException ex) {
        
        }
        catch (Exception ex) {
        
            Logger.getLogger(BO.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
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
