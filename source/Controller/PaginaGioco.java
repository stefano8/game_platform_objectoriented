
package Controller;

import Business.GameMgr;
import Business.ReviewMgr;
import MODEL.Game;
import MODEL.Review;
import MODEL.ReviewImpl;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import EXCEPTION.TemplateManagerException;
import result.TemplateResult;

/**
 *
 * @author tom
 */
public class PaginaGioco extends BaseServlet {
    /**
     * 
     * @param request
     * @param response
     * @param session
     * @throws SQLException 
     */
    private void action_vote(HttpServletRequest request , HttpServletResponse response ,HttpSession session) throws SQLException{
        
        try {
            int vote = Integer.parseInt(request.getParameter("rating-input-1"));
            
            GameMgr.getInstance().insertVote((String)session.getAttribute("username"), request.getParameter("titolo"),vote);
        } catch (Exception ex) {
            Logger.getLogger(PaginaGioco.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    /**
     * 
     * @param request
     * @param response
     * @param session
     * @throws SQLException 
     */
    private void action_Review(HttpServletRequest request , HttpServletResponse response ,HttpSession session) throws SQLException{
    
        try {
            Review rec = new ReviewImpl(request.getParameter("titolorec"),request.getParameter("message"),(String)session.getAttribute("username"), request.getParameter("titolo"));
            
            ReviewMgr.getIstance().insertReview(rec);
        } catch (Exception ex) {
            Logger.getLogger(PaginaGioco.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    /**
     * 
     * @param request
     * @param response
     * @throws SQLException
     * @throws TemplateManagerException 
     */
    private void action_base(HttpServletRequest request, HttpServletResponse response) throws SQLException, TemplateManagerException
    {
        try {
            TemplateResult template = new TemplateResult(getServletContext());
        
            Game game = GameMgr.getInstance().getGame(request.getParameter("titolo"));
            
            List<Review> listrev = ReviewMgr.getIstance().getListReview(game.getTitle());
            
            request.setAttribute("gioco", game);
            
            request.setAttribute("listarec", listrev);
            
            request.setAttribute("page_title", game.getTitle());
            
            template.activate("pagina-gioco.html", request, response);
        } catch (Exception ex) {
            Logger.getLogger(PaginaGioco.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    /**
     * 
     * @param request
     * @param response 
     */
    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response){
    
        HttpSession session = request.getSession(false);
        
        if(session != null)
        {
        
            if(request.getParameter("rating-input-1")!= null) 
            {
                try {
            
                    action_vote(request,response,session);
                    
                } catch (SQLException ex) {
                
                    Logger.getLogger(PaginaGioco.class.getName()).log(Level.SEVERE, null, ex);
                    
                }
                
            } 
            
            else if(request.getParameter("titolorec")!= null && !(request.getParameter("titolorec").isEmpty()) 
                    && request.getParameter("message")!= null && !(request.getParameter("message").isEmpty()))
                {
                    try {
                    
                        action_Review(request, response,session);
                    
                    } catch (SQLException ex) {
                    
                        Logger.getLogger(PaginaGioco.class.getName()).log(Level.SEVERE, null, ex);
                    
                    }
                }
            }
        
            try {
        
                action_base(request, response);
            
            } catch (SQLException | TemplateManagerException ex) {
            
                Logger.getLogger(PaginaGioco.class.getName()).log(Level.SEVERE, null, ex);
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
