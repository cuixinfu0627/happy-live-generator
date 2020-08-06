/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.happy.live.passport.controller.oss.cloud;


import com.happy.live.common.base.ConfigConstant;
import com.happy.live.common.base.Constant;
import com.happy.live.common.frame.spring.SpringContextUtils;
import com.happy.live.passport.service.SysConfigService;
import com.happy.live.common.mvc.utils.FileUploadParam;

/**
 * 文件上传Factory
 *
 * @author Mark sunlightcs@gmail.com
 */
public final class OSSFactory {
    private static SysConfigService sysConfigService;

    static {
        OSSFactory.sysConfigService = (SysConfigService) SpringContextUtils.getBean("sysConfigService");
    }

    public static CloudStorageService build(FileUploadParam uploadParam){
        //获取云存储配置信息
        CloudStorageConfig config = sysConfigService.getConfigObject(ConfigConstant.CLOUD_STORAGE_CONFIG_KEY, CloudStorageConfig.class);

        if(config.getType() == Constant.CloudService.QINIU.getValue()){
            return new QiniuCloudStorageService(config);
        }else if(config.getType() == Constant.CloudService.ALIYUN.getValue()){
            return new AliyunCloudStorageService(config);
        }else if(config.getType() == Constant.CloudService.QCLOUD.getValue()){
            return new QcloudCloudStorageService(config);
        }else if(config.getType() == Constant.CloudService.VSFTP.getValue()){
            if (uploadParam != null){
                config.setVsftpSourceDir(uploadParam.getSourceDir());
                config.setVsftpWebUrl(uploadParam.getWebUrl());
            }
            return new VsftpStorageService(config);
        }
        return null;
    }

}
