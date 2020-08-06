package com.happy.live.passport.service.impl;

import com.happy.live.passport.dao.WpDownloadDao;
import com.happy.live.passport.entity.WpDownloadEntity;
import com.happy.live.passport.service.WpDownloadService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.happy.live.common.mvc.utils.PageUtils;
import com.happy.live.common.mvc.utils.Query;



@Service("wpDownloadService")
public class WpDownloadServiceImpl extends ServiceImpl<WpDownloadDao, WpDownloadEntity> implements WpDownloadService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<WpDownloadEntity> page = this.page(
                new Query<WpDownloadEntity>().getPage(params),
                new QueryWrapper<WpDownloadEntity>()
        );

        return new PageUtils(page);
    }

}