package com.happy.live.passport.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.happy.live.passport.entity.ThirdUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 第三方账号
 * 
 * @author live
 * @email 870459550@qq.com
 * @date 2020-04-02 10:18:34
 */
@Mapper
public interface ThirdUserDao extends BaseMapper<ThirdUserEntity> {

    ThirdUserEntity queryByOpenid(@Param("origin") int origin, @Param("openid") String openid);
}
