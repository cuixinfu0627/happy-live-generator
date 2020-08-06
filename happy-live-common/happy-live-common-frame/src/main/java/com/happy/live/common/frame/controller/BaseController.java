package com.happy.live.common.frame.controller;

import com.happy.live.common.base.JSONHandler;
import com.happy.live.common.base.JSONResult;
import com.happy.live.common.base.StringHandler;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class BaseController {
    public static final String REQUEST_ERROR_FIELD = "REQUEST_ERROR_FIELD" ;
    public static final String REQUEST_ERROR_MSG = "REQUEST_ERROR_MSG" ;
    
	/**
	 * 获取请求ip地址。
	 * 
	 * @param request
	 * @return
	 */
	public String getRemoteAdd(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip.trim();
	}
	
	/**
	 * 获取绝对uri根地址。
	 * 
	 * @param request
	 * @return
	 */
	public String getBasePath(HttpServletRequest request) {
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + path + "/";
		return basePath;
	}

	/**
	 * 获取跨域返回值
	 * @param request
	 * @param jsonResult
	 * @return
	 */
	public String getAcrossDomainResult(HttpServletRequest request, JSONResult jsonResult){
		String callback = request.getParameter("callback") ;
		if(StringHandler.isNotEmpty(callback)){
			callback = filterDangerString(callback);
			return callback + "(" + JSONHandler.getJsonStr(jsonResult) + ")" ;
		}
		return JSONHandler.getJsonStr(jsonResult) ;
	}

    /**
     * 根据错误域或类型和错误键获取错误json返回
     * @return
     */
    public JSONResult getErrorJSONResult(String field,String message){
        return JSONResult.faild(field,message) ;
    }

    /**
     * 根据错误域或类型和错误键获取错误json返回
     * @return
     */
    public String getJSONStr(Object obj){
        return JSONHandler.getJsonStr(obj) ;
    }


    
    public void writeImg(HttpServletResponse response, BufferedImage img){
    	response.setDateHeader("Expires", 0);  
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");  
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");  
        response.setHeader("Pragma", "no-cache");  
        response.setContentType("image/jpeg");  
        ServletOutputStream out;
		try {
			out = response.getOutputStream();
			ImageIO.write(img, "jpg", out);  
	        try {  
	            out.flush();  
	        } finally {  
	            out.close();  
	        } 
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    /**
     * 过滤掉html非法字符  
     * @param value
     * @return
     */
    public static String filterDangerString(String value) {
        if (value == null) {
            return null;
        }
        value = value.replaceAll("\\{", "｛");
        // content = content.replaceAll("&", "&amp;");
        value = value.replaceAll("<", "&lt;");
        value = value.replaceAll(">", "&gt;");
        value = value.replaceAll("\t", "    ");
        value = value.replaceAll("\r\n", "\n");
        value = value.replaceAll("\n", "<br/>");
        value = value.replaceAll("'", "&#39;");
        value = value.replaceAll("\\\\", "&#92;");
        value = value.replaceAll("\"", "&quot;");
        value = value.replaceAll("\\}", "﹜").trim();
        return value;
    }
    
   /**
    * 方法描述：   
    * @param request
    * @param response
    * @returnObject
    * @throws IOException 
    */
   public Object getLoginUser(HttpServletRequest request, HttpServletResponse response){
	    /*String token = CookieHandler.getCookieValue(request, "CacheConstant.MERCHANT_ADMIN_TOKEN");
		String userInfo = stringRedisClusterUtil.get("CacheConstant.MERCHANT_ADMIN_SESSION_KEY"+token);
		String result = "校验失败";
		Manager manager = null ;
		if(userInfo != null){
			 manager = JSONHandler.getGson().fromJson(userInfo, Manager.class);
			return manager;
		}else{
			return manager;
		}*/
	   return response;
  	}
}
