package Controller.BO;


import Business.PermissionMgr;
import Business.PlatformMgr;
import Controller.BaseServlet;
import MODEL.LevelI;
import MODEL.LevelImpl;
import MODEL.Trophy;
import MODEL.TrophyImpl;
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
public class BOLivello extends BaseServlet {

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
            List<LevelI> listlevel = PlatformMgr.getInstance().LoadAllLevel();
            
            request.setAttribute("listlevel",listlevel);
        } catch (Exception ex) {
            
            Logger.getLogger(BOGroup.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        try
        {
            
            template.activate("BOlivello.html", request, response);
        
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
    
        LevelI level = new LevelImpl();
        level.setLevelnumber(Integer.parseInt(request.getParameter("numLVL")));
        level.setPointnextlevel(Integer.parseInt(request.getParameter("pointNextLVL")));
        Trophy trophy = new TrophyImpl();
        trophy.setId_trophy(Integer.parseInt(request.getParameter("id_trofeo")));
        level.setTrophy(trophy);
        PlatformMgr.getInstance().insertLevel(level);
        
        
    }

    /**
     *
     * @param request
     * @param response
     */
    protected void action_delete(HttpServletRequest request, HttpServletResponse response){}

    /**
     *
     * @param request
     * @param response
     */
    protected void action_update(HttpServletRequest request, HttpServletResponse response){}
    
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
                if  (
                        (request.getParameter("numLVL")!= null) && !(request.getParameter("numLVL").isEmpty())&&
                        (request.getParameter("pointNextLVL")!= null) && !(request.getParameter("pointNextLVL").isEmpty())&&
                        (request.getParameter("id_trofeo")!= null) && !(request.getParameter("id_trofeo").isEmpty())
                        )
                {
                    
                    try {
                        action_insert(request, response);
                    } catch (Exception ex) {
                        Logger.getLogger(BOLivello.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
                
                action_default(request, response);
            }   } catch (Exception ex) {
            Logger.getLogger(BOLivello.class.getName()).log(Level.SEVERE, null, ex);
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
