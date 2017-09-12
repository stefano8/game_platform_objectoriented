/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.Mysql.MySqlDaoFactory;

/**
 *
 * @author tom
 */
public abstract class DaoFactory {
    
    /**
     *
     */
    public static final int MYSQL = 1;
    
    /**
     *
     * @return
     */
    public abstract LivelloDao getLivelloDao();

    /**
     *
     * @return
     */
    public abstract TrofeoDao getTrofeoDao();

    /**
     *
     * @return
     */
    public abstract UserDao getUserDao();

    /**
     *
     * @return
     */
    public abstract GiocoDao getGiocoDao();

    /**
     *
     * @return
     */
    public abstract ImmagineDao getImmagineDao();

    /**
     *
     * @return
     */
    public abstract RecensioneDao getRecensioneDao();

    /**
     *
     * @return
     */
    public abstract PartitaDao getPartitaDao();

    /**
     *
     * @return
     */
    public abstract RankDao getRankDao();

    /**
     *
     * @return
     */
    public abstract PermissionHandlerDao getPermissionHandlerDao();

    /**
     *
     * @return
     */
    public abstract GroupDao getGroupDao();

    /**
     *
     * @return
     */
    public abstract ServiceDao getServiceDao();

    /**
     *
     * @return
     */
    public abstract GroupServiceDao getGroupServiceDao();

    /**
     *
     * @return
     */
    public abstract UserGroupDao getUserGroupDao();
    
    /**
     *
     * @param factoryType
     * @return
     */
    public static DaoFactory getDaoFactory(int factoryType)  {
    
        
        switch (factoryType)  {
        
            case MYSQL:
                return new MySqlDaoFactory();
            
            default:
                return null;
        }
    }
}


