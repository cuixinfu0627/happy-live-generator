package com.happy.live.passport.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.happy.live.passport.dao.SysMessageUserDao;
import com.happy.live.passport.entity.SysMessageUserEntity;
import com.happy.live.passport.service.SysMessageUserService;
import com.happy.live.common.mvc.utils.PageUtils;
import com.happy.live.common.mvc.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("sysMessageUserService")
public class SysMessageUserServiceImpl extends ServiceImpl<SysMessageUserDao, SysMessageUserEntity> implements SysMessageUserService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SysMessageUserEntity> page = this.page(
                new Query<SysMessageUserEntity>().getPage(params),
                new QueryWrapper<SysMessageUserEntity>()
        );

        return new PageUtils(page);
    }

}