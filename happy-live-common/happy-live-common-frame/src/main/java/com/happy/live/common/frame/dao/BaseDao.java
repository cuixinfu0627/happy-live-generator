package com.happy.live.common.frame.dao;


import com.happy.live.common.frame.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface BaseDao<Entity extends BaseEntity<PK>, PK extends Serializable> {

    PK insert(Entity t);

    int insertBatch(List<Entity> t);

    int deleteByPrimaryKey(PK id);

    Entity selectByPrimaryKey(PK id);

    int updateByPrimaryKey(Entity t);

    int updateBatchByPrimaryKey(List<Entity> t);

    List<Entity> queryByParamMap(Map<String, Object> param);

    int countByParamMap(Map<String, Object> param);
}
