/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

/**
 *
 * @author tom
 */
public interface UserGroup {

    /**
     *
     * @return
     */
    Group getGroup();

    /**
     *
     * @return
     */
    int getId();

    /**
     *
     * @return
     */
    User getUser();

    /**
     *
     * @param group
     */
    void setGroup(Group group);

    /**
     *
     * @param id
     */
    void setId(int id);

    /**
     *
     * @param user
     */
    void setUser(User user);
    
}
