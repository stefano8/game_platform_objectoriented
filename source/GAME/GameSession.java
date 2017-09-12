/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GAME;

/**
 *
 * @author tom
 */
public class GameSession  extends AbstractionGame  {

    /**
     *
     * @param role
     */
    public GameSession(Rule role)  {
        super(role);
    }

    /**
     *
     * @param bj
     * @return
     */
    @Override
    public int assegnaPunteggio(Object bj) {
        
       return  rule.assegnaPunteggio(bj);
    
    }

    
    
   
    
    
}
