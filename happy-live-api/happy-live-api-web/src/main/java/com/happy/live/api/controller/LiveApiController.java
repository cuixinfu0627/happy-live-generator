package com.happy.live.api.controller;

import com.happy.live.common.mvc.view.ResultVo;
import com.happy.live.common.mvc.view.ResultVoWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类名:HappyLiveApiController <tb>
 * 描述:请输入类描述  <tb>
 * 作者:cuixinfu@51play.com <tb>
 * 时间:2019/11/21 11:02 <tb>
 */
@RestController
@RequestMapping("/api")
public class LiveApiController {
    private static final Logger logger = LoggerFactory.getLogger(LiveApiController.class);

    @RequestMapping("/test")
    public ResultVo Test(String keyword) {
        logger.info("LiveApiController request revice params = {}" ,keyword);
        return new ResultVoWrapper().buildSuccess(keyword);
    }
}
