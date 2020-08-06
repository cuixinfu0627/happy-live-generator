/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.happy.live.passport.controller.oss.cloud;


import com.happy.live.common.mvc.validator.group.AliyunGroup;
import com.happy.live.common.mvc.validator.group.QcloudGroup;
import com.happy.live.common.mvc.validator.group.QiniuGroup;
import com.happy.live.common.mvc.validator.group.VsftpGroup;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 云存储配置信息
 *
 * @author Mark sunlightcs@gmail.com
 */
@Data
public class CloudStorageConfig implements Serializable {
    private static final long serialVersionUID = 1L;

    //类型 1：七牛  2：阿里云  3：腾讯云  4：本地vsftp
    @Range(min=1, max=4, message = "类型错误")
    private Integer type;

    //七牛绑定的域名
    @NotBlank(message="七牛绑定的域名不能为空", groups = QiniuGroup.class)
    @URL(message = "七牛绑定的域名格式不正确", groups = QiniuGroup.class)
    private String qiniuDomain;
    //七牛路径前缀
    private String qiniuPrefix;
    //七牛ACCESS_KEY
    @NotBlank(message="七牛AccessKey不能为空", groups = QiniuGroup.class)
    private String qiniuAccessKey;
    //七牛SECRET_KEY
    @NotBlank(message="七牛SecretKey不能为空", groups = QiniuGroup.class)
    private String qiniuSecretKey;
    //七牛存储空间名
    @NotBlank(message="七牛空间名不能为空", groups = QiniuGroup.class)
    private String qiniuBucketName;

    //阿里云绑定的域名
    @NotBlank(message="阿里云绑定的域名不能为空", groups = AliyunGroup.class)
    @URL(message = "阿里云绑定的域名格式不正确", groups = AliyunGroup.class)
    private String aliyunDomain;
    //阿里云路径前缀
    private String aliyunPrefix;
    //阿里云EndPoint
    @NotBlank(message="阿里云EndPoint不能为空", groups = AliyunGroup.class)
    private String aliyunEndPoint;
    //阿里云AccessKeyId
    @NotBlank(message="阿里云AccessKeyId不能为空", groups = AliyunGroup.class)
    private String aliyunAccessKeyId;
    //阿里云AccessKeySecret
    @NotBlank(message="阿里云AccessKeySecret不能为空", groups = AliyunGroup.class)
    private String aliyunAccessKeySecret;
    //阿里云BucketName
    @NotBlank(message="阿里云BucketName不能为空", groups = AliyunGroup.class)
    private String aliyunBucketName;

    //腾讯云绑定的域名
    @NotBlank(message="腾讯云绑定的域名不能为空", groups = QcloudGroup.class)
    @URL(message = "腾讯云绑定的域名格式不正确", groups = QcloudGroup.class)
    private String qcloudDomain;
    //腾讯云路径前缀
    private String qcloudPrefix;
    //腾讯云AppId
    @NotNull(message="腾讯云AppId不能为空", groups = QcloudGroup.class)
    private Integer qcloudAppId;
    //腾讯云SecretId
    @NotBlank(message="腾讯云SecretId不能为空", groups = QcloudGroup.class)
    private String qcloudSecretId;
    //腾讯云SecretKey
    @NotBlank(message="腾讯云SecretKey不能为空", groups = QcloudGroup.class)
    private String qcloudSecretKey;
    //腾讯云BucketName
    @NotBlank(message="腾讯云BucketName不能为空", groups = QcloudGroup.class)
    private String qcloudBucketName;
    //腾讯云COS所属地区
    @NotBlank(message="所属地区不能为空", groups = QcloudGroup.class)
    private String qcloudRegion;

    //vsftp绑定的Host
    @NotBlank(message="vsftp的Host不能为空", groups = VsftpGroup.class)
    private String vsftpHost;
    @NotBlank(message="vsftp绑定的访问域名不能为空", groups = VsftpGroup.class)
    @URL(message = "vsftp绑定的访问域名格式不正确", groups = VsftpGroup.class)
    private String vsftpVisitDomain;
    //vsftp文件存储根目录
    @NotNull(message="vsftp上传地址不能为空", groups = VsftpGroup.class)
    private String vsftpRootDir;
    //vsftp 文件目录
    @NotNull(message="vsftp文件目录", groups = VsftpGroup.class)
    private String vsftpSourceDir;
    @NotNull(message="vsftp访问目录", groups = VsftpGroup.class)
    //vsftp 访问目录
    private String vsftpWebUrl;

    //vsftp用户名
    @NotBlank(message="vsftp用户名不能为空", groups = VsftpGroup.class)
    private String vsftpUsername;
    //vsftp密码
    @NotBlank(message="vsftp密码不能为空", groups = VsftpGroup.class)
    private String vsftpPassword;
    //vsftp端口
    @NotNull(message="vsftp端口不能为空", groups = VsftpGroup.class)
    private Integer vsftpPort;

}
