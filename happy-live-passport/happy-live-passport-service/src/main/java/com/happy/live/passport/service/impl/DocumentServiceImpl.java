package com.happy.live.passport.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.happy.live.passport.dao.DocumentDao;
import com.happy.live.passport.entity.DocumentEntity;
import com.happy.live.passport.service.DocumentService;
import com.happy.live.common.mvc.utils.PageUtils;
import com.happy.live.common.mvc.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("documentService")
public class DocumentServiceImpl extends ServiceImpl<DocumentDao, DocumentEntity> implements DocumentService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<DocumentEntity> page = this.page(
                new Query<DocumentEntity>().getPage(params),
                new QueryWrapper<DocumentEntity>()
        );

        return new PageUtils(page);
    }

}