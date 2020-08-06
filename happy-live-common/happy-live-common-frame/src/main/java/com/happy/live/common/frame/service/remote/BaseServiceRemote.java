package com.happy.live.common.frame.service.remote;


import com.happy.live.common.frame.exception.ServiceException;
import com.happy.live.common.frame.mybatis.Pageable;
import com.happy.live.common.frame.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface BaseServiceRemote<Entity extends BaseEntity<PK>,PK extends Serializable> {

    Entity selectByPrimaryKey(PK id) throws ServiceException;


    List<Entity> queryByParamMap(Map<String, Object> param) throws ServiceException;


    Pageable<Entity> queryPageByParamMap(Pageable pager, Map<String, Object> params)  throws ServiceException;
}
