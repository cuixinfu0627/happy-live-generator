package com.happy.live.passport.service.remote;

import com.baomidou.mybatisplus.extension.service.IService;
import com.happy.live.common.mvc.utils.PageUtils;
import com.happy.live.passport.entity.WpWallpaperEntity;

import java.util.Map;

/**
 * @name: IWallpaperServiceRemote <tb>
 * @title: 请输入类描述  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/6/16 16:19 <tb>
 */
public interface IWallpaperServiceRemote extends IService<WpWallpaperEntity> {
    PageUtils queryPage(Map<String, Object> params);
}
