/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

import MODEL.GroupService;

/**
 *
 * @author tom
 */
public class GroupServiceImpl implements GroupService {
    
    private Group group ;
    
    private Service service ;

    private int IDgroupservice ;
    
    /**
     *
     */
    public GroupServiceImpl(){

        this.group = new Group();
    
        this.service = new Service();
    
        this.IDgroupservice = 0 ;
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

    /**
     *
     * @return
     */
    @Override
    public Service getService() {
        return service;
    }

    /**
     *
     * @param service
     */
    @Override
    public void setService(Service service) {
        this.service = service;
    }

    /**
     *
     * @return
     */
    @Override
    public int getIDgroupservice() {
        return IDgroupservice;
    }

    /**
     *
     * @param IDgroupservice
     */
    @Override
    public void setIDgroupservice(int IDgroupservice) {
        this.IDgroupservice = IDgroupservice;
    }
    
    
    
}