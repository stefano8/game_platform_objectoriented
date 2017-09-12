/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import DAO.DaoFactory;
import MODEL.Game;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author tom
 */
public class GameMgr {
    
    private DaoFactory dbFactory ;  
    
    
    
    private GameMgr() throws Exception{
    dbFactory = DaoFactory.getDaoFactory(1);
    }
    
    /**
     *
     * @return
     * @throws Exception
     */
    public static GameMgr getInstance()throws Exception{
    
    return new GameMgr();
    
    }
    /**
     * metodo che ritorna la lista dei giochi 
     * @return
     * @throws Exception 
     */
    public List<Game> getGames() throws Exception{
    
        List<Game> listgames = dbFactory.getGiocoDao().loadAll();
    
        return listgames;

    }
    /**
     * 
     * @param titlegame
     * @return Game
     * @throws Exception 
     */
    public Game getGame(String titlegame) throws Exception{
    
        Game game =(Game) dbFactory.getGiocoDao().load(titlegame);
        
        return game;
    
    }
    /**
     * metodo per inserire la votazione di un gioco
     * @param username
     * @param titologioco
     * @param voto
     * @throws Exception 
     */
    public void insertVote(String username,String titologioco,int voto) throws Exception{

        dbFactory.getGiocoDao().voto(username, titologioco, voto);
    
    }
    /**
     * metodo per aggiungere un game
     * @param game
     * @throws SQLException 
     */
    public void insertGame(Game game) throws SQLException{
    
        dbFactory.getGiocoDao().create(game);
    
    }
    
}
