package Controller;

import Business.GameMgr;
import MODEL.Game;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import result.FailureResult;
import EXCEPTION.TemplateManagerException;
import result.TemplateResult;

/**
 *
 * @author tom
 */
public class Home extends BaseServlet {

/**
 * 
 * @param request
 * @param response 
 */
    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    {    
        request.setAttribute("page_title", "home");
        
        TemplateResult template = new TemplateResult(getServletContext());
        
        try{
            
            List<Game> listgames = GameMgr.getInstance().getGames();
            
            request.setAttribute("listagiochi", listgames);
            template.activate("listagiochi.html", request, response);
         
        
        
            
            
        } catch (Exception ex) {
        
             request.setAttribute("exception", ex.getMessage());
                
            try {
             
                (new FailureResult(getServletContext())).activate(ex.getMessage(), request, response);
            } catch (IOException | TemplateException | TemplateManagerException ex1) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex1);
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
