package Controller;


import Business.UserMgr;
import MODEL.User;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author tom
 */
public abstract class BaseServlet extends HttpServlet {

   private static final String ROLEUSER = "USER"; 
    
    
   /**
    * 
    * @param request
    * @param response
    * @throws Exception 
    */
    protected void action_Not_Log(HttpServletRequest request,HttpServletResponse response) throws Exception {
    
        if(( request.getParameter("username") != null && !(request.getParameter("username").isEmpty()))&&
            (request.getParameter("password") != null && !(request.getParameter("password").isEmpty())))
        {
         
            String username = (String) request.getParameter("username");
            
            String password = (String) request.getParameter("password");
            
            int id_log  = UserMgr.getIstance().checkLogin(username, password);
            
            if(id_log != 0){
            
                HttpSession session = request.getSession(true);
                
                User user = UserMgr.getIstance().getUser(id_log);
                
                request.setAttribute("login", true);
                
                request.setAttribute("username", user.getUsername());
                
                session.setAttribute("username", user.getUsername());
                
                session.setAttribute("id_user", id_log);
                
                session.setAttribute("ruolo", user.getGroup().getName());
                if(!user.getGroup().getName().equals(ROLEUSER))
                  
                request.setAttribute("bo", true);
            }
        }
        
        processRequest(request, response);
    
    }
    /**
     * 
     * @param request
     * @param response 
     */
    protected void action_Log(HttpServletRequest request,HttpServletResponse response) {
       
        HttpSession session = request.getSession(false);
        
        request.setAttribute("username",session.getAttribute("username"));
       
        request.setAttribute("login",true); 
        System.out.println(session.getAttribute("ruolo")+" è il ruolo");
        if(!session.getAttribute("ruolo").equals(ROLEUSER))
         request.setAttribute("bo", true);
        
        processRequest(request, response);
    
    }
    /**
     * 
     * @param request
     * @param response 
     */
    protected void processBaseRequest(HttpServletRequest request, HttpServletResponse response){
    
           HttpSession session = request.getSession(false);
    
           if(session != null) action_Log(request, response);
        
           else
               try {
            
                   action_Not_Log(request, response);
        
               } catch (Exception ex) {
            
                   Logger.getLogger(BaseServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    /**
     * 
     * @param request
     * @param response 
     */
    protected abstract void processRequest(HttpServletRequest request, HttpServletResponse response);

    /**
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        
        processBaseRequest(request, response);
       
     }
    
    /**
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
    {
    
        processBaseRequest(request, response);
    
    }

}
