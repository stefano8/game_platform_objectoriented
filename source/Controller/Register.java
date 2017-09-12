package Controller;

import Business.RankMgr;
import Business.UserMgr;
import MODEL.User;
import MODEL.UserImpl;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import result.FailureResult;
import EXCEPTION.TemplateManagerException;
import result.TemplateResult;


/**
 *
 * @author tom
 */
public class Register extends BaseServlet {

    private void action_formSubmition(HttpServletRequest request, HttpServletResponse response){
    
        int id_log=0;
             
        User user  = new UserImpl() ;
        user.setName(request.getParameter("nome"));
        
        user.setSurname( request.getParameter("cognome") );
        
        user.setAddress(request.getParameter("indirizzo"));
        
        user.setCity(request.getParameter("citta"));
        
        user.setUsername(request.getParameter("username"));
        
        user.setPassword( request.getParameter("password"));
        
        user.setEmail( request.getParameter("email"));
        
       
                    
                
        try {
        
            id_log = UserMgr.getIstance().registerUser(user);
               
            int k = RankMgr.getInstance().initializeRank(user.getUsername());
            
            user = UserMgr.getIstance().getUser(id_log);
            
            HttpSession session = request.getSession(true);

            
            request.setAttribute("login", true);

            session.setAttribute("username", user.getUsername());

            session.setAttribute("id_user", id_log);

            session.setAttribute("ruolo", user.getGroup().getName());

            if(!user.getGroup().getName().equals("USER")) 
                
                {
                    request.setAttribute("bo", true);
                }

                response.sendRedirect("Home");

        } catch (Exception ex) {
        
            try {
                (new FailureResult(getServletContext())).activate((String) ex.getMessage(), request, response);
                
            }  catch (UnsupportedEncodingException | MalformedTemplateNameException | TemplateException | TemplateManagerException ex1) {
            
                Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex1);

            } catch (IOException ex1) {
            
                Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex1);

            }
        }
    }
    
    /**
     * 
     * @param request
     * @param response 
     */
    private void action_default(HttpServletRequest request, HttpServletResponse response){
            
            try {//carico il template di registrazione
                
                TemplateResult template = new TemplateResult(getServletContext());
                
                template.activate("registrazione.html", request, response);
                
            } catch (TemplateManagerException ex) {
            
                Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
                
            }
    }
    
    /**
     * 
     * @param request
     * @param response 
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    {
        request.setAttribute("page_title", "registrazione pagina");
            
            if  (    
                    request.getParameter("nome") != null && !(request.getParameter("nome").isEmpty()) &&
                    request.getParameter("cognome") != null && !(request.getParameter("cognome").isEmpty())&&
                    request.getParameter("indirizzo") != null && !(request.getParameter("indirizzo").isEmpty())&&
                    request.getParameter("citta") != null && !(request.getParameter("citta").isEmpty())&&
                    request.getParameter("username") != null && !(request.getParameter("username").isEmpty())&&
                    request.getParameter("password") != null && !(request.getParameter("password").isEmpty())&&
                    request.getParameter("email") != null && !(request.getParameter("email").isEmpty())
                )
            {
            
                action_formSubmition(request,response); 
        
            }
            
            else
            
            {
            
                action_default(request,response);
            
            }
        }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * 
     * @return 
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
