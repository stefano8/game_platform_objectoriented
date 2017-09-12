/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

import MODEL.User;
import MODEL.UserGroup;

/**
 *
 * @author tom
 */
public class UserGroupImpl implements UserGroup {
    
    
    private int id ;
    
    private User user ;
    
    private Group group ;
    
    /**
     *
     */
    public UserGroupImpl(){
    
        this.id = 0 ;
        
        this.user = null ;
        
        this.group = null ;
        
    }

    /**
     *
     * @return
     */
    @Override
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    @Override
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    @Override
    public User getUser() {
        return user;
    }

    /**
     *
     * @param user
     */
    @Override
    public void setUser(User user) {
        this.user = user;
    }

    /**
     *
     * @return
     */
    @Override
    public Group getGroup() {
        return group;
    }

    /**
     *
     * @param group
     */
    @Override
    public void setGroup(Group group) {
        this.group = group;
    }



}
