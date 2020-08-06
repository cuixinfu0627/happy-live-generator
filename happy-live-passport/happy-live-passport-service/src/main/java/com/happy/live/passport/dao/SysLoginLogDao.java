package com.happy.live.passport.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.happy.live.passport.entity.SysLoginLogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 后台登录日志表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-03-31 14:14:15
 */
@Mapper
public interface SysLoginLogDao extends BaseMapper<SysLoginLogEntity> {
	
}
