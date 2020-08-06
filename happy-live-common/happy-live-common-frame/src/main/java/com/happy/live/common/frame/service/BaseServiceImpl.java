package com.happy.live.common.frame.service;

import com.happy.live.common.frame.dao.BaseDao;
import com.happy.live.common.frame.entity.BaseEntity;
import com.happy.live.common.frame.exception.ErrorCode;
import com.happy.live.common.frame.exception.ServiceException;
import com.happy.live.common.frame.mybatis.Pageable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class BaseServiceImpl<Entity extends BaseEntity<PK>, PK extends Serializable, Dao extends BaseDao<Entity, PK>>
        implements BaseService<Entity, PK, Dao> {

    private Logger logger = LoggerFactory.getLogger(BaseServiceImpl.class);

    protected Dao dao;

    public BaseServiceImpl(Dao dao) {
        this.dao = dao;
    }

    @Override
    public PK insert(Entity entity) throws ServiceException {
        if (entity == null) {
            logger.error(ErrorCode.INSERT_NOT_NULL.getMsg());
            throw new ServiceException(ErrorCode.INSERT_NOT_NULL);
        } else {
            logger.debug("插入对象：{}", entity);

            try {
                dao.insert(entity);
            } catch (Exception e) {
                logger.error("批量插入错误：", e);
                throw e;
            }

            return entity.getId();
        }
    }

    @Override
    public int insertBatch(List<Entity> entityList) throws ServiceException {
        if (entityList != null && entityList.size() >= 1) {
            logger.debug("批量插入对象：{} 条", entityList.size());

            try {
                dao.insertBatch(entityList);
            } catch (Exception e) {
                logger.error("批量插入错误：", e);
                throw e;
            }

            return entityList.size();
        } else {
            logger.error(ErrorCode.BATCH_INSERT_NOT_NULL.getMsg());
            throw new ServiceException(ErrorCode.BATCH_INSERT_NOT_NULL);
        }
    }

    @Override
    public int updateByPrimaryKey(Entity entity) throws ServiceException {
        if (entity == null) {
            logger.error(ErrorCode.UPDATE_NOT_NULL.getMsg());
            throw new ServiceException(ErrorCode.UPDATE_NOT_NULL);
        } else {
            logger.info("更新对象：{}", entity);
            return dao.updateByPrimaryKey(entity);
        }
    }

    @Override
    public int updateBatchByPrimaryKey(List<Entity> entityList) throws ServiceException {
        if (entityList != null && entityList.size() >= 1) {
            logger.debug("批量更新对象：{} 条", entityList.size());
            return dao.updateBatchByPrimaryKey(entityList);
        } else {
            logger.error(ErrorCode.BATCH_UPDATE_NOT_NULL.getMsg());
            throw new ServiceException(ErrorCode.BATCH_UPDATE_NOT_NULL);
        }
    }

    @Override
    public int deleteByPrimaryKey(PK id) throws ServiceException {
        if (id == null) {
            logger.error(ErrorCode.DELETE_NOT_NULL.getMsg());
            throw new ServiceException(ErrorCode.DELETE_NOT_NULL);
        } else {
            return dao.deleteByPrimaryKey(id);
        }
    }


    @Override
    public Entity selectByPrimaryKey(PK id) throws ServiceException {
        if (id == null) {
            this.logger.error(ErrorCode.GET_ID_NOT_NULL.getMsg());
            throw new ServiceException(ErrorCode.GET_ID_NOT_NULL);
        } else {
            return dao.selectByPrimaryKey(id);
        }
    }

    @Override
    public List<Entity> queryByParamMap(Map<String, Object> param) throws ServiceException {
        if (param == null) {
            this.logger.error(ErrorCode.GET_PARAM_NOT_NULL.getMsg());
            throw new ServiceException(ErrorCode.GET_PARAM_NOT_NULL);
        } else {
            return dao.queryByParamMap(param);
        }
    }

    @Override
    public Pageable<Entity> queryPageByParamMap(Pageable pageableVo, Map<String, Object> params) throws ServiceException {
        if (pageableVo == null) {
            this.logger.error(ErrorCode.GET_PARAM_NOT_NULL.getMsg());
            throw new ServiceException(ErrorCode.GET_PARAM_NOT_NULL);
        } else if (params == null) {
            this.logger.error(ErrorCode.GET_PARAM_NOT_NULL.getMsg());
            throw new ServiceException(ErrorCode.GET_PARAM_NOT_NULL);
        } else {
            params.put("pParam", pageableVo);
            List<?> listBean = dao.queryByParamMap(params);
            Integer count = dao.countByParamMap(params);
            pageableVo.setRows(listBean);
            pageableVo.setTotalRow(count);
            return pageableVo;
        }
    }

}
