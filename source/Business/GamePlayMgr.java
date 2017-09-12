package Business;

import DAO.DaoFactory;
import GAME.GameSession;
import MODEL.Match;
import GAME.Rule;
import GAME.TetrisRole;
import MODEL.LevelI;
import java.util.List;

/**
 *
 * @author tom
 */
public class GamePlayMgr {
    
    private DaoFactory dbfactory ;
    
    private GamePlayMgr()throws Exception{
    
        dbfactory = DaoFactory.getDaoFactory(1);
    
    }
    
    /**
     *
     * @return
     * @throws Exception
     */
    public static GamePlayMgr getIstance()throws Exception{
    
        return new GamePlayMgr();
    
    }

    /**
     * 
     * @param username
     * @return
     * @throws Exception 
     */
    public int recuperaNumeroLivello(String username)throws Exception{
    
        int numlvl = dbfactory.getRankDao().recuperaNumeroLivello(username);
        return numlvl;
    
    }
    
    
    
    /**
     * 
     * @param username
     * @return
     * @throws Exception 
     */
    public int recuperaPunteggio(String username)throws Exception{
    
        int totalpoint = dbfactory.getPartitaDao().sumTotalPoint(username);        
        
        return totalpoint;
    
    }
    
    /**
     * 
     * @param match
     * @return
     * @throws Exception 
     */
    public int creaPartita(Match match) throws Exception{
    
        int id = dbfactory.getPartitaDao().create(match);
        
        return id;
    }
    
    /**
     * 
     * @param match
     * @param current
     * @throws java.lang.Exception
     */
    public void calcola(Match match,LevelI current) throws Exception{
    
        Rule role = null;
        role = new TetrisRole();
        GameSession gamesession = new GameSession( role);
        int punteggiorandom = gamesession.assegnaPunteggio(null);
        match.setPunteggionuovo(match.getPunteggionuovo()+ punteggiorandom);
        match.setPunteggiototale(match.getPunteggiototale()+ punteggiorandom);
        dbfactory.getPartitaDao().store(match);
        
        //aggiorno il punteggio nella classifica
        dbfactory.getRankDao().updatePoint(match);
    
    }
    
    /**
     * 
     * @param id_match
     * @return
     * @throws Exception 
     */
    public Match recuperapartita(int id_match) throws Exception{
    
        Match match = (Match) dbfactory.getPartitaDao().load(id_match);
        
        int lvl = dbfactory.getRankDao().recuperaNumeroLivello(match.getUsername());
        
        match.setLivello((LevelI)dbfactory.getLivelloDao().load(lvl));
        
        return match;
    
    }

    /**
     * 
     * @param match
     * @param level
     * @throws Exception 
     */
    public void win(Match match,LevelI level) throws Exception{

        
        //assegna trofeo del livello all utente
        dbfactory.getTrofeoDao().assignTrofeo( match,level);
       //incrementa livello nel rank
        
       dbfactory.getRankDao().updateLevel(match);
                
    }
    
    /**
     * 
     * @param username
     * @return
     * @throws Exception 
     */
    public List getListGame(String username) throws Exception{
    
        List<Match> listmatch = dbfactory.getPartitaDao().getlistMatch(username);
        
        return listmatch;
    
    }

    

}
