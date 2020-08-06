/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.happy.live.passport.controller.oss.cloud;

import com.happy.live.common.mvc.exception.RRException;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 七牛云存储
 *
 * @author Mark sunlightcs@gmail.com
 */
public class VsftpStorageService extends CloudStorageService {

    private static Logger logger = LoggerFactory.getLogger(VsftpStorageService.class);

    private FTPClient ftpClient;
    boolean success = false;
    int reply;
    public VsftpStorageService(CloudStorageConfig config){
        this.config = config;
        //初始化
        init();
    }

    private void init(){
        try {
            ftpClient = new FTPClient();
            ftpClient.connect(config.getVsftpHost(), config.getVsftpPort());//连接FTP服务器
            //如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
            boolean loginStatus = ftpClient.login(config.getVsftpUsername(), config.getVsftpPassword());//登录
            logger.info("step-1 upload file login vsftp status:{}",loginStatus);
            if (!loginStatus) {
                throw new RuntimeException("vsftp登录验证错误,请核对vsftp配置信息");
            }
            ftpClient.setConnectTimeout(10000);
            ftpClient.setFileType(2);
            reply = ftpClient.getReplyCode();
            logger.info("step-2 upload file vsftp replyCode:{}",reply);
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                logger.info("step-2 upload file vsftp replyCode error");
                throw new RuntimeException("vsftp登录验证错误,请核对vsftp配置信息");
            }
        } catch (Exception e) {
            throw new RRException("上传文件失败，请核对vsftp配置信息", e);
        }

    }

    @Override
    public String upload(byte[] data, String path) {
        try {
            InputStream inputStream = new ByteArrayInputStream(data);
            return this.upload(inputStream, path);
        } catch (Exception e) {
            throw new RRException("上传文件失败，请核对vsftp配置信息", e);
        }
    }

    @Override
    public String upload(InputStream inputStream, String fileName) {
        try {
            String uploadPath = config.getVsftpRootDir() + config.getVsftpSourceDir();
            boolean changeDirectoryFlag = ftpClient.changeWorkingDirectory(uploadPath);
            logger.info("step-3 upload file vsftp changeDirectory result:{}",changeDirectoryFlag);
            ftpClient.enterLocalPassiveMode();
            success = ftpClient.storeFile(fileName, inputStream);
            String httpUrl = config.getVsftpVisitDomain() + config.getVsftpWebUrl() + fileName ;
            logger.info("step-4 upload file vsftp result:{},weburl:{}",success,httpUrl);
            if (!success){
                throw new RuntimeException("上传vsftp文件出错");
            }
            inputStream.close();
            ftpClient.logout();
            return httpUrl;
        } catch (IOException e) {
            throw new RRException("上传文件失败", e);
        }
    }

    @Override
    public String uploadSuffix(byte[] data, String suffix) {
        return upload(data, getFileName(suffix));
    }

    @Override
    public String uploadSuffix(InputStream inputStream, String suffix) {
        return upload(inputStream, getFileName(suffix));
    }

    @Override
    public String getFilePath(String httpUrl) {
        String vsftpDomain = config.getVsftpVisitDomain();
        return httpUrl.replaceAll(vsftpDomain,"");
    }
}
