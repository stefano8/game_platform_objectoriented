/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

import MODEL.LevelI;
import java.util.Date;

/**
 *
 * @author tom
 */
public class Match {
   
    private int id_match;
    
    private String username;
    
    private String titologioco;
    
    private LevelI livello;
    
    private int punteggiototale;
    
    private int punteggionuovo;
    
    private Date data;
    
    /**
     *
     */
    public Match(){
    
        this.id_match=0;
        
        this.username = "";
        
        this.titologioco = "";
        
        this.livello   = null;
        
        this.punteggiototale =0;
        
        this.punteggionuovo  = 0;
        
        this.data = null;
    
    }

    /**
     *
     * @return
     */
    public int getId_match() {
        
        return id_match;
    
    }

    /**
     *
     * @param id_match
     */
    public void setId_match(int id_match) {
    
        this.id_match = id_match;
    
    }
    
    /**
     *
     * @return
     */
    public String getUsername() {
    
        return username;
    
    }

    /**
     *
     * @param username
     */
    public void setUsername(String username) {
    
        this.username = username;
    
    }

    /**
     *
     * @return
     */
    public String getTitologioco() {
    
        return titologioco;
    
    }

    /**
     *
     * @param titologioco
     */
    public void setTitologioco(String titologioco) {
    
        this.titologioco = titologioco;
    
    }

    /**
     *
     * @return
     */
    public LevelI getLivello() {
    
        return livello;
    
    }

    /**
     *
     * @param livello
     */
    public void setLivello(LevelI livello) {
    
        this.livello = livello;
    
    }

    /**
     *
     * @return
     */
    public int getPunteggiototale() {
    
        return punteggiototale;
    
    }

    /**
     *
     * @param punteggiototale
     */
    public void setPunteggiototale(int punteggiototale) {
    
        this.punteggiototale = punteggiototale;
    
    }

    /**
     *
     * @return
     */
    public int getPunteggionuovo() {
    
        return punteggionuovo;
    
    }

    /**
     *
     * @param punteggionuovo
     */
    public void setPunteggionuovo(int punteggionuovo) {
    
        this.punteggionuovo = punteggionuovo;
    
    }

    /**
     *
     * @return
     */
    public Date getData() {
    
        return data;
    
    }

    /**
     *
     * @param data
     */
    public void setData(Date data) {
    
        this.data = data;
    
    }
    
}
