package com.happy.live.api.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.happy.live.common.mvc.config.DubboConfigUtil;
import com.happy.live.common.mvc.utils.PageUtils;
import com.happy.live.common.mvc.view.R;
import com.happy.live.passport.entity.WpWallpaperEntity;
import com.happy.live.passport.service.remote.IWallpaperServiceRemote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @name: WallpaperController <tb>
 * @title: 壁纸管理  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/6/16 16:41<tb>
 */
@RestController
@RequestMapping("/wallpaper")
public class WallpaperController {

    private static final Logger logger = LoggerFactory.getLogger(WallpaperController.class);

    @Reference(url = "${application.dubbo.service-remote.passport.url}", version = "${application.dubbo.service-remote.passport.version}",check = false, lazy = true, retries = DubboConfigUtil.DUBBO_RETRIES,timeout = DubboConfigUtil.DUBBO_TIMEOUT)
    private IWallpaperServiceRemote wallpaperServiceRemote;

    @RequestMapping("/list")
    public R list2(@RequestParam Map<String, Object> params){
        List<WpWallpaperEntity> list = wallpaperServiceRemote.list();
//        list.forEach(wallpaperEntity ->{
//            String url= wallpaperEntity.getPicUrl();
//            logger.info("开始下载文件：{}",url);
//            String fileName = url.substring(url.lastIndexOf("/")+1);
//            String localFilePath = "E:\\测试文件\\wallpaper";
//            try {
//                HttpDownload.downLoadFromUrl(url, fileName, localFilePath);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
        return R.ok().put("list", list);
    }

    @RequestMapping("/pageList")
    public R pageList(@RequestParam Map<String, Object> params){
        PageUtils page = wallpaperServiceRemote.queryPage(params);
        return R.ok().put("page", page);
    }
}
