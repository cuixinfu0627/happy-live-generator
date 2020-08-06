package com.happy.live.passport.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.happy.live.passport.dao.ThirdUserDao;
import com.happy.live.passport.entity.SysUserEntity;
import com.happy.live.passport.entity.ThirdUserEntity;
import com.happy.live.passport.service.SysRoleService;
import com.happy.live.passport.service.SysUserService;
import com.happy.live.passport.service.ThirdUserService;
import com.happy.live.common.mvc.utils.PageUtils;
import com.happy.live.common.mvc.utils.Query;
import com.happy.live.passport.service.remote.IThirdUserServiceRemote;
import com.happy.live.passport.service.remote.IWallpaperServiceRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@com.alibaba.dubbo.config.annotation.Service(version = "${application.dubbo.service-provide.version}", interfaceClass = IThirdUserServiceRemote.class)
@Transactional(rollbackFor = Exception.class)
@Service("thirdUserService")
public class ThirdUserServiceImpl extends ServiceImpl<ThirdUserDao, ThirdUserEntity> implements ThirdUserService, IThirdUserServiceRemote {

    @Autowired
    private SysUserService sysUserService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ThirdUserEntity> page = this.page(
                new Query<ThirdUserEntity>().getPage(params),
                new QueryWrapper<ThirdUserEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public ThirdUserEntity queryByOpenid(int origin, String openid) {
        ThirdUserEntity thirdUser = baseMapper.queryByOpenid(origin, openid);
        if (thirdUser.getUserId() != null){
            SysUserEntity sysUser = sysUserService.getById(thirdUser.getUserId());
            thirdUser.setSysUser(sysUser);
        }
        return thirdUser;
    }
}