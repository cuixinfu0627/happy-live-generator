package com.happy.live.common.third.wechat;

import com.happy.live.common.third.wechat.bean.WxUserInfo;
import com.happy.live.common.third.wechat.constant.WeChatPlatform;
import com.happy.live.common.third.HttpHandler;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 类名: WxUserHandler <br>  
 * 描述: 微信用户处理工具  <br>
 * 作者: cuixinfu@ralncy.com <br>
 * 时间: 2017年12月4日 下午3:59:04 <br>
 */
public class WxUserHandler {
	
	private static Logger logger = LoggerFactory.getLogger(WxUserHandler.class);
	
	/**
	 * @param token
	 * @param openid
	 * @return
	 * @Description: 通过OpenID来获取用户基本信息
	 * @author wangbing
	 *@Since:2016-12-12 18:19:59
	 */
	public static WxUserInfo getUserInfo(String token, String openid){
		String url = WeChatPlatform.USER_INFO_URL.replace("ACCESS_TOKEN", token).replace("OPENID", openid);
		JSONObject jsonObject = HttpHandler.doGetStr(url);
		WxUserInfo userInfo = new WxUserInfo();
		logger.info(jsonObject.toString());
		try {
			if(jsonObject.getInt("subscribe")==1){
				userInfo.setOpenid(jsonObject.getString("openid"));
				userInfo.setCity(jsonObject.getString("city"));
				userInfo.setProvince(jsonObject.getString("province"));
				userInfo.setCountry(jsonObject.getString("country"));
				userInfo.setNickname(jsonObject.getString("nickname"));
				userInfo.setSex(jsonObject.getInt("sex"));
				userInfo.setRemark(jsonObject.getString("remark"));
				userInfo.setGroupid(jsonObject.getInt("groupid"));
				userInfo.setHeadimgurl(jsonObject.getString("headimgurl"));
				userInfo.setLanguage(jsonObject.getString("language"));
				userInfo.setUnionid(jsonObject.getString("unionid"));
				userInfo.setTagidList(jsonObject.getString("tagid_list"));
				userInfo.setSubscribeTime(getFormatDate(jsonObject.getString("subscribe_time"), "yyyy-MM-dd HH:mm:ss"));
			}else{
				userInfo.setUnsubscribeTime(new Date());
				userInfo.setOpenid(jsonObject.getString("openid"));
				userInfo.setUnionid(jsonObject.getString("unionid"));
			}
			
		} catch (Exception e) {
			userInfo.setStatus(2);
			userInfo.setOpenid(openid);
			e.printStackTrace();
		}
		
		return userInfo;
	}
	
	/**
	 * 此方法描述的是：   获取用户列表  
	 * @param nextOpenid 第一个拉取的OPENID，不填默认从头开始拉取
	 * @param accessToken
	 * @return 
	 * Map<String,Object>
	 */
	public static Map<String, Object> getUserList(String nextOpenid ,String accessToken){
		Map<String, Object> map = new HashMap<String, Object>();
		String url = WeChatPlatform.USER_LIST_URL.replace("ACCESS_TOKEN",accessToken).replace("NEXT_OPENID", (nextOpenid==null)?"":nextOpenid);
		JSONObject jsonObject = HttpHandler.doGetStr(url);
		int count = jsonObject.getInt("count");
		int total = jsonObject.getInt("total");
		String openid = jsonObject.getString("next_openid");
		map.put("total",total );
		map.put("count",count );
		if(count!=0||StringUtils.isNotBlank(openid)){
			JSONObject data = jsonObject.getJSONObject("data");
			JSONArray openidList = data.getJSONArray("openid");
			map.put("openidList", openidList);
			map.put("next_openid", openid);
		}
		return map;
	}
	
	/**
	 * 此方法描述的是：  更新备注名 
	 * @param openId
	 * @param remark
	 * @param token
	 * @return int
	 */
	public static int updateRemark(String openId , String remark , String token){
		int result = 0;
		String url = WeChatPlatform.UPDATE_REMARK_URL.replace("ACCESS_TOKEN", token);
		Map<String, String> map = new HashMap<String, String>();
		map.put("openid", openId);
		map.put("remark", remark);
		String json = JSONObject.fromObject(map).toString();
		JSONObject jsonObject = HttpHandler.doPostStr(url, json);
		if(jsonObject!=null){
			result = jsonObject.getInt("errcode");
		}
		return result;
	}
	

	/**
	 * 此方法描述的是： 移动分组  
	 * @param openId
	 * @param groupId
	 * @param token
	 * @return int
	 */
	public static int updateGroup(String openId,Integer groupId , String token){
		int result = 0;
		String url = WeChatPlatform.UPDATE_GROUPS_URL.replace("ACCESS_TOKEN", token);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("openid", openId);
		map.put("to_groupid", groupId);
		String json = JSONObject.fromObject(map).toString();
		JSONObject jsonObject = HttpHandler.doPostStr(url, json);
		if(jsonObject!=null){
			result = jsonObject.getInt("errcode");
		}
		return result;
	}
	
	/**
	 * 此方法描述的是：  批量移动用户分组 
	 * @param openIdList
	 * @param groupId
	 * @param token
	 * @return int
	 */
	public static int updateBatchGroup(String[] openIdList,Integer groupId, String token){
		int result = 0;
		String url = WeChatPlatform.UPDATE_BATCH_GROUPS_URL.replace("ACCESS_TOKEN", token);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("openid_list", openIdList);
		map.put("to_groupid", groupId);
		String json = JSONObject.fromObject(map).toString();
		JSONObject jsonObject = HttpHandler.doPostStr(url, json);
		if(jsonObject!=null){
			result = jsonObject.getInt("errcode");
		}
		return result;
	}
	
	/**
	 * 此方法描述的是：删除一个用户分组，删除分组后，所有该分组内的用户自动进入默认分组。   
	 * @param groupId
	 * @param token
	 * @return int
	 */
	public static int deleteBatchGroup(Integer groupId , String token){
		int result = 0;
		String url = WeChatPlatform.DELETE_GROUPS_URL.replace("ACCESS_TOKEN",token);
		Map<String, Map<String, Integer>> map = new HashMap<String, Map<String, Integer>>();
		Map<String, Integer> map2 = new HashMap<String, Integer>();
		map2.put("id", groupId);
		map.put("group", map2);
		String json = JSONObject.fromObject(map).toString();
		JSONObject jsonObject = HttpHandler.doPostStr(url, json);
		if(jsonObject!=null){
			result = jsonObject.getInt("errcode");
		}
		return result;
	}

	public static Date getFormatDate(String time, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		// 解析时间 2016-01-05T15:09:54Z
		Date date = null;
		try {
			date = sdf.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static void main(String[] args) {
		getUserInfo("68zSWHXz4fCAjAY7rU5jdMhfDDpBgykLWLa5scRBNu49Ec7Mghd_OfGX0eJebJk-RAsPXvw2y7JNOox1CJ10vDMTdBNjyA9c80DCGxpHyF2z774Bxvwd7n3bYJ_DteLvTMKcAAALEC", "oNizBt8SS5MfZ-OHxMkRJaCS6Eho");
	}
}
