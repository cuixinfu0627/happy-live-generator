package com.happy.live.passport.service.impl;

import com.happy.live.passport.dao.SysMessageDao;
import com.happy.live.passport.entity.SysMessageEntity;
import com.happy.live.passport.service.SysMessageService;
import com.happy.live.common.mvc.utils.PageUtils;
import com.happy.live.common.mvc.utils.Query;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("sysMessageService")
public class SysMessageServiceImpl extends ServiceImpl<SysMessageDao, SysMessageEntity> implements SysMessageService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SysMessageEntity> page = this.page(
                new Query<SysMessageEntity>().getPage(params),
                new QueryWrapper<SysMessageEntity>()
        );

        return new PageUtils(page);
    }

}