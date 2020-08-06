package com.happy.live.passport.service.remote;

import com.baomidou.mybatisplus.extension.service.IService;
import com.happy.live.common.mvc.utils.PageUtils;
import com.happy.live.passport.entity.ThirdUserEntity;
import com.happy.live.passport.entity.WpWallpaperEntity;

import java.util.Map;

/**
 * @name: IThirdUserServiceRemote <tb>
 * @title: 第三方用户暴露接口  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/6/16 16:19 <tb>
 */
public interface IThirdUserServiceRemote extends IService<ThirdUserEntity> {
    PageUtils queryPage(Map<String, Object> params);

    ThirdUserEntity queryByOpenid(int origin, String openid);
}
