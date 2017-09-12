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
public abstract class AbstractionGame {
    
    
    
    Rule rule;
    
    /**
     *
     * @param rule
     */
    public AbstractionGame(Rule rule){
    
        this.rule = rule;
    
    }
    
    /**
     *
     * @param bj
     * @return
     */
    public abstract int assegnaPunteggio(Object bj);
}
