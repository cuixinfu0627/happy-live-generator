package com.happy.live.common.frame.service;


import com.happy.live.common.frame.dao.BaseDao;
import com.happy.live.common.frame.entity.BaseEntity;
import com.happy.live.common.frame.exception.ServiceException;
import com.happy.live.common.frame.service.remote.BaseServiceRemote;

import java.io.Serializable;
import java.util.List;

public interface BaseService<Entity extends BaseEntity<PK>, PK extends Serializable, Dao extends BaseDao<Entity, PK>> extends BaseServiceRemote<Entity,PK> {

    PK insert(Entity t) throws ServiceException;

    int insertBatch(List<Entity> t) throws ServiceException;

    int updateByPrimaryKey(Entity t) throws ServiceException;

    int updateBatchByPrimaryKey(List<Entity> t) throws ServiceException;

    int deleteByPrimaryKey(PK id) throws ServiceException;

}
