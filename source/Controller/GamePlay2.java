package Controller;

import Business.GameMgr;
import Business.GamePlayMgr;
import Business.PlatformMgr;
import EXCEPTION.TemplateManagerException;
import MODEL.Game;
import MODEL.LevelI;
import MODEL.Match;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import result.TemplateResult;

/**
 *
 * @author tom
 */
public class GamePlay2 extends BaseServlet {

    /**
     * 
     * @param request
     * @param response
     * @param game 
     */
    protected void action_default(HttpServletRequest request, HttpServletResponse response,Game game){
    
        TemplateResult template = new TemplateResult(getServletContext());
        request.setAttribute("page_title", "AAAA");
        try {
            template.activate("game2.html", request, response);
        } catch (TemplateManagerException ex) {
            Logger.getLogger(GamePlay2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * 
     * @param request
     * @param response
     * @param game
     * @param session
     * @throws Exception 
     */
    protected void action_creatematch(HttpServletRequest request, HttpServletResponse response,Game game,HttpSession session) throws Exception{
        
        int numlvl= GamePlayMgr.getIstance().recuperaNumeroLivello((String)session.getAttribute("username"));
        System.out.println("numero livello :" + numlvl);
              
            LevelI livello = PlatformMgr.getInstance().getLevel(numlvl);
            int punteggio = GamePlayMgr.getIstance().recuperaPunteggio((String)session.getAttribute("username"));
            Match match = new Match();
            match.setUsername((String)session.getAttribute("username"));
            match.setTitologioco(game.getTitle());
            
            match.setLivello(livello);
            match.setPunteggionuovo(0);
            match.setPunteggiototale(punteggio);
            
            try 
            {
                int id_match = GamePlayMgr.getIstance().creaPartita(match);
                match.setId_match(id_match);
                session.setAttribute("match", match);
                action_default(request, response, game);//"Game\\"+game.getTemplate()
            }
            catch (TemplateManagerException ex) {
                Logger.getLogger(GamePlay2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
    }
    /**
     * 
     * @param request
     * @param response
     * @param session 
     */
    protected void action_assegnaPunteggio(HttpServletRequest request, HttpServletResponse response,HttpSession session){
    
        try 
        {

            Match match = (Match)session.getAttribute("match");
            LevelI level = match.getLivello();
            System.out.println("num lvl :" +level.getLevelnumber()+"point :"+level.getPointnextlevel());
            GamePlayMgr.getIstance().calcola(match,level);
            System.out.println("sumtotalpoint : "+match.getPunteggiototale() + "level : " + level.getPointnextlevel());
            if(match.getPunteggiototale() >= level.getPointnextlevel())
            {
                GamePlayMgr.getIstance().win(match, level);
            }
        }
        catch (Exception ex) 
        {
            Logger.getLogger(GamePlay2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * 
     * @param request
     * @param response 
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    {
        
       HttpSession session = request.getSession(false);
       
       if(session != null){
         
            if(session.getAttribute("match")!= null && request.getParameter("title")!= null){
                try {
                    //crea una nuova partita su un gioco diverso/stesso
                    String titlegame = request.getParameter("title");
                    Game game = GameMgr.getInstance().getGame(titlegame);
                    session.setAttribute("game", game);
                    action_creatematch(request, response,game,session);
                } catch (Exception ex) {
                    Logger.getLogger(GamePlay2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else if(session.getAttribute("match")!= null){
            //assegna punteggio
                action_assegnaPunteggio(request, response,session);
            }
            else if(request.getParameter("title")!= null){
                try {
                    // crea una nuova partita
                    String titlegame = request.getParameter("title");
                    Game game = GameMgr.getInstance().getGame(titlegame);
                    session.setAttribute("game", game);
                    action_creatematch(request, response,game,session);
                } catch (Exception ex) {
                    Logger.getLogger(GamePlay2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else System.out.println("errore");
            
        }else{
       
           try {
               response.sendRedirect("Home");
           } catch (IOException ex) {
               Logger.getLogger(GamePlay2.class.getName()).log(Level.SEVERE, null, ex);
           }
       }//end session gestire errore
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
