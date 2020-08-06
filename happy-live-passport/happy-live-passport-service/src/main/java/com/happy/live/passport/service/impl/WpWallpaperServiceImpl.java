package com.happy.live.passport.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.happy.live.common.mvc.utils.PageUtils;
import com.happy.live.common.mvc.utils.Query;
import com.happy.live.passport.dao.WpWallpaperDao;
import com.happy.live.passport.entity.WpWallpaperEntity;
import com.happy.live.passport.service.WpWallpaperService;
import com.happy.live.passport.service.remote.IWallpaperServiceRemote;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
@com.alibaba.dubbo.config.annotation.Service(version = "${application.dubbo.service-provide.version}", interfaceClass = IWallpaperServiceRemote.class)
@Transactional(rollbackFor = Exception.class)
@Service("wpWallpaperService")
public class WpWallpaperServiceImpl extends ServiceImpl<WpWallpaperDao, WpWallpaperEntity> implements WpWallpaperService, IWallpaperServiceRemote{

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<WpWallpaperEntity> page = this.page(
                new Query<WpWallpaperEntity>().getPage(params),
                new QueryWrapper<>()
        );
        return new PageUtils(page);
    }

}