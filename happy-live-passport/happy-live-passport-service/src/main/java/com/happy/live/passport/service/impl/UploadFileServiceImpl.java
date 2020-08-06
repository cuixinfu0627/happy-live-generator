package com.happy.live.passport.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.happy.live.passport.dao.UploadFileDao;
import com.happy.live.passport.entity.UploadFileEntity;
import com.happy.live.passport.service.UploadFileService;
import com.happy.live.common.mvc.utils.PageUtils;
import com.happy.live.common.mvc.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("uploadFileService")
public class UploadFileServiceImpl extends ServiceImpl<UploadFileDao, UploadFileEntity> implements UploadFileService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UploadFileEntity> page = this.page(
                new Query<UploadFileEntity>().getPage(params),
                new QueryWrapper<UploadFileEntity>()
        );

        return new PageUtils(page);
    }

}