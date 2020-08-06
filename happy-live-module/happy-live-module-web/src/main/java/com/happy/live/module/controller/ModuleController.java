package com.happy.live.module.controller;

import com.happy.live.common.mvc.view.ResultVoWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类名:HappyLiveController <tb>
 * 描述: 测试 <tb>
 * 作者:cuixinfu@51play.com <tb>
 * 时间:2019/8/15 19:48 <tb>
 */
@RestController
@RequestMapping("/module")
public class ModuleController {

    private static final Logger logger = LoggerFactory.getLogger(ModuleController.class);

    @RequestMapping("/test")
    public Object Test(String keyword) {
        logger.info("HappyLiveApiApp - request revice params = {}" ,keyword);
        return new ResultVoWrapper().buildSuccess(keyword);
    }
}
