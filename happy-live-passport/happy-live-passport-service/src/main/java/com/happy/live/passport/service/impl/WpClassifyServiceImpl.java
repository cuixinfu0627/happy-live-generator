package com.happy.live.passport.service.impl;

import com.happy.live.passport.dao.WpClassifyDao;
import com.happy.live.passport.entity.WpClassifyEntity;
import com.happy.live.passport.service.WpClassifyService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.happy.live.common.mvc.utils.PageUtils;
import com.happy.live.common.mvc.utils.Query;



@Service("wpClassifyService")
public class WpClassifyServiceImpl extends ServiceImpl<WpClassifyDao, WpClassifyEntity> implements WpClassifyService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<WpClassifyEntity> page = this.page(
                new Query<WpClassifyEntity>().getPage(params),
                new QueryWrapper<WpClassifyEntity>()
        );

        return new PageUtils(page);
    }

}