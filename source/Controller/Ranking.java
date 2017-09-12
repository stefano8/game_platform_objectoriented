/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Business.RankMgr;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import EXCEPTION.TemplateManagerException;
import result.TemplateResult;

/**
 *
 * @author tom
 */
public class Ranking extends BaseServlet {

    /**
     * 
     * @param request
     * @param response 
     */
    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response){
        try {
            TemplateResult template = new TemplateResult(getServletContext());
            Map<String,Integer> mappa = RankMgr.getInstance().getRank();
            request.setAttribute("page_title", "rank");
            request.setAttribute("mappa", mappa);
            
            try {
                template.activate("classifica.html", request, response);
            } catch (TemplateManagerException ex) {
                Logger.getLogger(Ranking.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (Exception ex) {
            Logger.getLogger(Ranking.class.getName()).log(Level.SEVERE, null, ex);
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
