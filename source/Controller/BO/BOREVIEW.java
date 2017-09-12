/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.BO;

import Business.PermissionMgr;
import Business.ReviewMgr;
import Controller.BaseServlet;
import MODEL.Review;
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
public class BOREVIEW extends BaseServlet {

    /**
     * 
     * @param request
     * @param response 
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          {
        try {
            HttpSession session = request.getSession(false);
            if(session.getAttribute("ruolo").equals("ADMIN"))request.setAttribute("admin", "");
            List<Service>permission = PermissionMgr.getIstance().getListService((String)session.getAttribute("username"))                ;
            //se non possiede i diritti mostro schermata di errore
               
            if(!PermissionMgr.getIstance().isPermitted(permission,getServletName()))
            {
            
                (new FailureResult(getServletContext())).activate("Non si dispone dei diritti necessari", request, response);
                
            }
            else{
                if(request.getParameter("app")!=null){
                    
                    int app = Integer.parseInt(request.getParameter("rev"));
                    
                    ReviewMgr.getIstance().acceptReview(app);
                }
                
                else if(request.getParameter("del")!=null){
                    int del = Integer.parseInt(request.getParameter("rev"));
                    
                    ReviewMgr.getIstance().removeReview(del);
                }
                
                List<Review> listrev = ReviewMgr.getIstance().getListReviewBO();
                TemplateResult template = new TemplateResult(getServletContext());
            
                request.setAttribute("listreview", listrev);
                request.setAttribute("outline_tpl", "indexBO.html");// "outline_tpl", request, response);
                request.setAttribute("page_title", "BO Review");
                template.activate("boreview.html", request, response);
                
                
            }
        } catch (Exception ex) {
            Logger.getLogger(BOREVIEW.class.getName()).log(Level.SEVERE, null, ex);
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
