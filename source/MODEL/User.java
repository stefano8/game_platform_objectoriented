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
public interface User {

    /**
     *
     * @return
     */
    String getAddress();

    /**
     *
     * @return
     */
    String getCity();

    /**
     *
     * @return
     */
    String getEmail();

    /**
     *
     * @return
     */
    String getName();

    /**
     *
     * @return
     */
    String getPassword();

    /**
     *
     * @return
     */
    String getSurname();

    /**
     *
     * @return
     */
    String getUsername();
    
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
     * @param username
     */
    void setUsername(String username);

    /**
     *
     * @param name
     */
    void setName(String name);

    /**
     *
     * @param surname
     */
    void setSurname(String surname);

    /**
     *
     * @param password
     */
    void setPassword(String password);

    /**
     *
     * @param address
     */
    void setAddress(String address);

    /**
     *
     * @param email
     */
    void setEmail(String email);

    /**
     *
     * @param city
     */
    void setCity(String city);

    /**
     *
     * @param id
     */
    void setId(int id);

    /**
     *
     * @param group
     */
    void setGroup(Group group);


}
