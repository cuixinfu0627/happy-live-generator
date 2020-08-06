package com.happy.live.passport.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.happy.live.passport.entity.ThirdUserEntity;
import com.happy.live.common.mvc.utils.PageUtils;

import java.util.Map;

/**
 * 第三方账号
 *
 * @author live
 * @email 870459550@qq.com
 * @date 2020-04-02 10:18:34
 */
public interface ThirdUserService extends IService<ThirdUserEntity> {

    PageUtils queryPage(Map<String, Object> params);

    ThirdUserEntity queryByOpenid(int origin, String openid);

}

