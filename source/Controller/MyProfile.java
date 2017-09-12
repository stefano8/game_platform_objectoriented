package Controller;

import Business.GamePlayMgr;
import Business.UserMgr;
import EXCEPTION.DatabaseException;
import MODEL.Match;
import MODEL.Trophy;
import MODEL.User;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Map;
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
public class MyProfile extends BaseServlet {

    /**
     * 
     * @param request
     * @param response 
     */
    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    {
        
        HttpSession session = request.getSession(false);
        if(session != null){
            
            try {
                
                User user = UserMgr.getIstance().getUser((int)session.getAttribute("id_user"));
                
                List<Match> listmatch = GamePlayMgr.getIstance().getListGame(user.getUsername());
                Map<Integer,Map<Date,Trophy>> mappa = UserMgr.getIstance().getMyTrophyWin((String)session.getAttribute("username"));
                request.setAttribute("utente", user);
                request.setAttribute("listmatch", listmatch);
                request.setAttribute("page_title", "MyProfile");

                request.setAttribute("mappa", mappa);
                
                TemplateResult template = new TemplateResult(getServletContext());
                
                template.activate("profilo.html", request, response);
            
            } catch (Exception ex) {
            
                Logger.getLogger(MyProfile.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        else {
                try {
            
                    response.sendRedirect("Home");
                
                } catch (IOException ex) {
                
                    Logger.getLogger(MyProfile.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public String getServletInfo() {

        return "MyProfile";

    }// </editor-fold>

}
