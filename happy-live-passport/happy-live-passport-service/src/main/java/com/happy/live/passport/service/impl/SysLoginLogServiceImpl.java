package com.happy.live.passport.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.happy.live.passport.dao.SysLoginLogDao;
import com.happy.live.passport.entity.SysLoginLogEntity;
import com.happy.live.passport.service.SysLoginLogService;
import com.happy.live.common.mvc.utils.PageUtils;
import com.happy.live.common.mvc.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("sysLoginLogService")
public class SysLoginLogServiceImpl extends ServiceImpl<SysLoginLogDao, SysLoginLogEntity> implements SysLoginLogService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SysLoginLogEntity> page = this.page(
                new Query<SysLoginLogEntity>().getPage(params),
                new QueryWrapper<SysLoginLogEntity>()
        );

        return new PageUtils(page);
    }

}