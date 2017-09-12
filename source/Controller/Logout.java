package Controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author tom
 */
public class Logout extends BaseServlet {

    /**
     * 
     * @param request
     * @param response 
     */
    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    {
    
        try {
            
            HttpSession sessione = request.getSession(true);
            
            sessione.invalidate();
            
            response.sendRedirect("Home");
        } catch (IOException ex) {
            
            Logger.getLogger(Logout.class.getName()).log(Level.SEVERE, null, ex);
        
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
