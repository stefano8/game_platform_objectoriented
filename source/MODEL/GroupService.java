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
public interface GroupService {

    /**
     *
     * @return
     */
    Group getGroup();

    /**
     *
     * @return
     */
    Service getService();

    /**
     *
     * @param group
     */
    void setGroup(Group group);

    /**
     *
     * @param service
     */
    void setService(Service service);
    
    /**
     *
     * @return
     */
    int getIDgroupservice();
    
    /**
     *
     * @param id
     */
    void setIDgroupservice( int id);
    
    
}
