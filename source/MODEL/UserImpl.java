/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

import MODEL.User;

/**
 *
 * @author tom
 */
public class UserImpl implements User {
    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
   
        private String username;
    
        private String name;

        private String surname;

        private String password;

        private String address;

        private String email;

        private String city;

        private int id;
       
        private Group group ;
       
        
        /*Costruttore privato*/

    /**
     *
     */

        
        public UserImpl()
        {
        this.username = "";
        this.password = "";
        this.name = "" ;
        this.surname = "";
        this.city = "";
        this.group = null;
        }
        
    /**
     *
     * @param username
     */
    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @param name
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @param surname
     */
    @Override
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     *
     * @param password
     */
    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @param address
     */
    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     *
     * @param email
     */
    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @param city
     */
    @Override
    public void setCity(String city) {
        this.city = city;
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
     * @param group
     */
    @Override
    public void setGroup(Group group) {
        this.group = group;
    }

    /**
     *
     * @return
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     *
     * @return
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     *
     * @return
     */
    @Override
    public String getSurname() {
        return surname;
    }

    /**
     *
     * @return
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     *
     * @return
     */
    @Override
    public String getAddress() {
        return address;
    }

    /**
     *
     * @return
     */
    @Override
    public String getEmail() {
        return email;
    }

    /**
     *
     * @return
     */
    @Override
    public String getCity() {
        return city;
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
     * @return
     */
    @Override
    public Group getGroup() {
        return group;
    }

    

        

}