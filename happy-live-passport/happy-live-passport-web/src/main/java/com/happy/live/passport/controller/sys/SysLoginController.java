/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.happy.live.passport.controller.sys;

import com.happy.live.common.base.RequestHeaderUtil;
import com.happy.live.common.mvc.validator.AppKeys;
import com.happy.live.common.mvc.view.R;
import com.happy.live.passport.entity.SysLoginLogEntity;
import com.happy.live.passport.entity.SysUserEntity;
import com.happy.live.passport.entity.constant.LoginLogOperationTypeEnum;
import com.happy.live.passport.entity.constant.LoginLogTypeEnum;
import com.happy.live.passport.form.SysLoginForm;
import com.happy.live.passport.service.SysCaptchaService;
import com.happy.live.passport.service.SysLoginLogService;
import com.happy.live.passport.service.SysUserService;
import com.happy.live.passport.service.SysUserTokenService;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * 登录相关
 *
 * @author Mark sunlightcs@gmail.com
 */
@RestController
public class SysLoginController extends AbstractController {
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysUserTokenService sysUserTokenService;
	@Autowired
	private SysCaptchaService sysCaptchaService;
	@Autowired
	private SysLoginLogService sysLoginLogService;
	/**
	 * 验证码
	 */
	@GetMapping("captcha")
	public void captchaCode(HttpServletResponse response, String uuid)throws IOException {
		response.setHeader("Cache-Control", "no-store, no-cache");
		response.setContentType("image/jpeg");

		//获取图片验证码
		BufferedImage image = sysCaptchaService.getCaptcha(uuid);

		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(image, "jpg", out);
		IOUtils.closeQuietly(out);
	}

	/**
	 * 验证码
	 */
	@GetMapping("captcha.jpg")
	public void captcha(HttpServletResponse response, String uuid)throws IOException {
		response.setHeader("Cache-Control", "no-store, no-cache");
		response.setContentType("image/jpeg");

		//获取图片验证码
		BufferedImage image = sysCaptchaService.getCaptcha(uuid);

		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(image, "jpg", out);
		IOUtils.closeQuietly(out);
	}

	/**
	 * 登录
	 */
	@PostMapping("/sys/login")
	public Map<String, Object> login(@RequestBody SysLoginForm form, HttpServletRequest request)throws IOException {
		boolean captcha = sysCaptchaService.validate(form.getUuid(), form.getCaptcha());
		if(!captcha){
			return R.error("验证码不正确");
		}

		//用户信息
		SysUserEntity user = sysUserService.queryByUserName(form.getUsername());

		//账号不存在、密码错误
		if(user == null || !user.getPassword().equals(new Sha256Hash(form.getPassword(), user.getSalt()).toHex())) {
			return R.error("账号或密码不正确");
		}

		//账号锁定
		if(user.getStatus() == 0){
			return R.error("账号已被锁定,请联系管理员");
		}

		//生成token，并保存到数据库
		R r = sysUserTokenService.createToken(user.getUserId());

		//添加登录日志
		SysLoginLogEntity sysLoginLog = new SysLoginLogEntity();
		sysLoginLog.setUserId(user.getUserId());
		sysLoginLog.setUsername(user.getUsername());
		sysLoginLog.setNickname(user.getNickname());
		sysLoginLog.setCreateTime(new Date());
		sysLoginLog.setOperationType(LoginLogOperationTypeEnum.LOGIN.getType());
		//判断请求来自PC还是手机端
		String requestHeader = request.getHeader(AppKeys.USER_AGENT_KEY);
		boolean isMobileDevice = RequestHeaderUtil.isMobileDevice(requestHeader);
		if (!isMobileDevice) {
			sysLoginLog.setType(LoginLogTypeEnum.LOGIN_PC.getType());
		} else {
			sysLoginLog.setType(LoginLogTypeEnum.LOGIN_APP.getType());
		}
		sysLoginLog.setIp(getRemoteIp(request));
		sysLoginLogService.save(sysLoginLog);

		return r;
	}


	/**
	 * 退出
	 */
	@PostMapping("/sys/logout")
	public R logout() {
		sysUserTokenService.logout(getUserId());
		return R.ok();
	}
	
}
