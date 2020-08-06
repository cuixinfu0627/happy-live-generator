//package com.happy.live.common.base.vcode;
//
//import org.springframework.ui.ModelMap;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//
///**
// * 类名: VrifyCodeHandler <br>
// * 描述: 验证码工具类 <br>
// * 作者: cuixinfu@ralncy.com <br>
// * 时间: 2017年12月7日 下午4:37:33 <br>
// */
//public class VrifyCodeHandler {
//
//	public static final String PARAMETERNAME = "vrifyCode";
//
//	private VrifyCodeHandler() {
//	}
//
//	/** 验证码校验逻辑，传入的验证码参数名必须叫 vrifyCode
//	 *
//	 * @param httpServletRequest
//	 * @param model
//	 * @return */
//	public static boolean checkvrifyCode(HttpServletRequest httpServletRequest, ModelMap model) {
//		HttpSession session = httpServletRequest.getSession();
//		String captchaId = (String) httpServletRequest.getSession().getAttribute(PARAMETERNAME);
//		String parameter = httpServletRequest.getParameter(PARAMETERNAME);
//		if (parameter == null) {
//			// cleanSession(session);
//			return false;
//		}
//		if (captchaId == null) {
//			model.addAttribute("error", "验证码已过期！");
//			return false;
//		}
//		if (!captchaId.equals(parameter)) {
//			model.addAttribute("error", "验证码不正确！");
//			cleanSession(session);
//			return false;
//		}
//		cleanSession(session);
//		return true;
//	}
//
//	/** @param vrifyCode
//	 *            验证码
//	 * @param httpServletRequest
//	 * @return */
//	public static boolean checkvrifyCode(String vrifyCode, HttpServletRequest httpServletRequest) {
//		HttpSession session = httpServletRequest.getSession();
//		String captchaId = (String) httpServletRequest.getSession().getAttribute(PARAMETERNAME);
//		if (vrifyCode == null) {
//			// cleanSession(session);
//			return false;
//		}
//		if (captchaId == null) {
//			return false;
//		}
//		if (!captchaId.equals(vrifyCode)) {
//			cleanSession(session);
//			return false;
//		}
//		cleanSession(session);
//		return true;
//	}
//
//	/** @param vrifyCode
//	 *            验证码
//	 * @param session
//	 * @return */
//	public static boolean checkvrifyCode(String vrifyCode, HttpSession session) {
//		String captchaId = (String) session.getAttribute(PARAMETERNAME);
//		if (vrifyCode == null) {
//			// cleanSession(session);
//			return false;
//		}
//		if (captchaId == null) {
//			return false;
//		}
//		if (!captchaId.equals(vrifyCode)) {
//			cleanSession(session);
//			return false;
//		}
//		cleanSession(session);
//		return true;
//	}
//
//	/** 验证是验证码 否正确后清除 验证码会话 防止重复利用攻击API
//	 *
//	 * @param session
//	 */
//	private static void cleanSession(HttpSession session) {
//		session.removeAttribute(PARAMETERNAME);
//	}
//}
