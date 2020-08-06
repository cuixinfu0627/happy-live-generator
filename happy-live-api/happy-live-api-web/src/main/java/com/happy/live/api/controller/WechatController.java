package com.happy.live.api.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.happy.live.api.config.WeachtConfig;
import com.happy.live.api.controller.request.param.AesCbcUtil;
import com.happy.live.api.controller.request.param.WechatLoginParam;
import com.happy.live.common.base.DateHandler;
import com.happy.live.common.base.JSONHandler;
import com.happy.live.common.mvc.config.DubboConfigUtil;
import com.happy.live.common.mvc.utils.PageUtils;
import com.happy.live.common.mvc.view.R;
import com.happy.live.common.third.wechat.WeixinHandler;
import com.happy.live.common.third.wechat.message.MessageUtil;
import com.happy.live.passport.entity.ThirdUserEntity;
import com.happy.live.passport.entity.constant.ThirdOriginTypeEnum;
import com.happy.live.passport.service.remote.IThirdUserServiceRemote;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @name: WallpaperController <tb>
 * @title: 壁纸管理  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/6/16 16:41<tb>
 */
@RestController
@RequestMapping("/wechat")
public class WechatController {

    private static final Logger logger = LoggerFactory.getLogger(WechatController.class);

    @Autowired
    WeachtConfig weachtConfig ;

    private static final int FIVE_MINUTE = 1000 * 60 * 5;

    @Reference(url = "${application.dubbo.service-remote.passport.url}", version = "${application.dubbo.service-remote.passport.version}",check = false, lazy = true, retries = DubboConfigUtil.DUBBO_RETRIES,timeout = DubboConfigUtil.DUBBO_TIMEOUT)
    private IThirdUserServiceRemote thirdUserServiceRemote;

    @RequestMapping("/list")
    public R pageList(@RequestParam Map<String, Object> params){
        PageUtils page = thirdUserServiceRemote.queryPage(params);
        return R.ok().put("page", page);
    }

    @RequestMapping("getUserIndex")
    public R getUserIndex(HttpServletRequest request,
                  @RequestParam(value = "openid") String openid) {
        ThirdUserEntity thirdUserEntity = thirdUserServiceRemote.queryByOpenid(ThirdOriginTypeEnum.THIRD_ORIGIN_WECHAT.getValue(), openid);
        return R.ok().put("data",thirdUserEntity);
    }

    /**
     * @描述: 小程序登录
     * @创建人: cuixinfu@51play.com
     * @创建时间: 2018/11/1 15:13
     * @返回值: 登录结果
     */
    @RequestMapping("miniCallback")
    public R miniCallback(HttpServletRequest request) {
        String code = request.getParameter("code");
        logger.info("appCallback step1 ===> 授权成功{微信授权回调得到的code={}和跳转地址={}}",code);
        Map loginInfo = WeixinHandler.getMiniAppLoginInfo(code);
        logger.info("appCallback step2 ===> 微信授权后得到的用户信息为:{}", JSONHandler.getGson().toJson(loginInfo));
        if(loginInfo == null){
            return R.error("小程序登录失败出错!!!");
        }
        String openid = loginInfo.get("openid").toString();
        String unionId = loginInfo.get("unionId").toString();
        ThirdUserEntity thirdUser= thirdUserServiceRemote.queryByOpenid(ThirdOriginTypeEnum.THIRD_ORIGIN_WECHAT.getValue(),openid);
        if (thirdUser == null){
            return R.ok()
                    .put("userInfo", thirdUser)
                    .put("login",loginInfo);
        }
        return R.ok();
    }

    /**
     * @title: 微信小程序登录 <tb>
     * @author: cuixinfu@51play.com <tb>
     * @date: 2020/7/4 14:54<tb>
     */
    @RequestMapping("miniLogin")
    public R miniLogin(HttpServletRequest request,
                       @Valid WechatLoginParam wxLoginReq,
                       @RequestParam(value = "userId") String userId) {
        // WxUserInfo wxUserInfo = JSONHandler.getGson().fromJson(userInfo, WxUserInfo.class);
        // 到微信服务器获取openid和session_key
        Map loginInfo = WeixinHandler.getMiniAppLoginInfo(wxLoginReq.getCode());
        if (loginInfo == null) {// 请求错误
            return R.error("请求错误！") ;
        } else {
            // 用户的唯一标识（openid）
            String openid = loginInfo.get("openid").toString();
            String unionid = loginInfo.get("unionid").toString();
            // 获取会话密钥（session_key）
            String session_key = loginInfo.get("session_key").toString();

            //获取微信用户信息
            ThirdUserEntity thirdUser= thirdUserServiceRemote.queryByOpenid(ThirdOriginTypeEnum.THIRD_ORIGIN_WECHAT.getValue(),openid);
            // 查询是否存在
            if (thirdUser == null) {// null代表未注册
                try {// 注册
                    thirdUser = new ThirdUserEntity();
                    thirdUser.setOrigin(ThirdOriginTypeEnum.THIRD_ORIGIN_WECHAT.getValue());
                    thirdUser.setOpenId(openid);
                    thirdUser.setUnionId(unionid);

                    //获取解密数据：
                    String encryptedData = wxLoginReq.getEncryptedData();
                    String iv = wxLoginReq.getIv();
                    // 开始解密
                    String result = AesCbcUtil.decrypt(encryptedData, session_key, iv, "UTF-8");
                    if (null != result && result.length() > 0) {
                        JSONObject userInfoJSON = JSONObject.fromObject(result);
                        thirdUser.setNickname(userInfoJSON.get("nickName").toString());
                        thirdUser.setAvatar(userInfoJSON.get("avatarUrl").toString());
                        thirdUser.setCountry(userInfoJSON.get("country").toString());
                        thirdUser.setProvince(userInfoJSON.get("province").toString());
                        thirdUser.setCity(userInfoJSON.get("city").toString());
                        thirdUser.setGender(Integer.valueOf(userInfoJSON.get("gender").toString()));
                        thirdUser.setCreateTime(new Date());
                        // 1、注册用户-创建保存用户信息
                        thirdUserServiceRemote.save(thirdUser);
                        // 2、用户登录-获取用户信息
                        thirdUser = thirdUserServiceRemote.queryByOpenid(ThirdOriginTypeEnum.THIRD_ORIGIN_WECHAT.getValue(), openid);
                        return R.ok().put("data",thirdUser);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                return R.ok().put("data",thirdUser);
            }
        }
        return R.ok();
    }

    /**
     * @描述: 发送小程序模板消息
     * @创建人: cuixinfu@51play.com
     * @创建时间: 2018/11/1 18:16
     */
    @RequestMapping("sendMiniTemplateMsg")
    public @ResponseBody R sendMiniTemplateMsg(HttpServletRequest request, HttpServletResponse response,
                               @RequestParam("first") String first,
                               @RequestParam("keyword1") String keyword1,
                               @RequestParam("keyword2") String keyword2,
                               @RequestParam("keyword3") String keyword3,
                               @RequestParam("keyword4") String keyword4,
                               @RequestParam("keyword5") String keyword5,
                               @RequestParam("keyword6") String keyword6,
                               @RequestParam("formId") String formId,
                               @RequestParam("remark") String remark) {
        try {
            //1、获取getAccessToken
            String accessToken = WeixinHandler.getMiniAccessToken();

            //2、小程序参数
            //openid: "o35K45dtFxwV_QSdINke-3vpX4bw"
            //session_key: "i8UgcfJzQdWJStj1Om7a5Q=="
            //unionid: "ozecexINQ-SigN19P_0XChDYUpLQ"
            String openid = "o35K45dtFxwV_QSdINke-3vpX4bw" ;
            String templateId = "33LGTgYOKmSzRYRsvSK4hR1-4afJiAWacszbOpy8SAs";
            String page = "index";
            //String formId = "wx_"+ DateUtilsHandler.getLongDate(); //搞不懂啊
            String emphasisKeyword = "keyword1.DATA" ;

            //3、添加参数
            HashMap<String, Object> miniData = new HashMap<>();
            miniData.put("first", first);
            miniData.put("keyword1", keyword1);
            miniData.put("keyword2", keyword2);
            miniData.put("keyword3", keyword3);
            miniData.put("keyword4", keyword4);
            miniData.put("keyword5", keyword5);
            miniData.put("keyword6", keyword6);
            miniData.put("remark", remark);

            //4、初始化模板消息：
            HashMap<String, Object> initTemplateMessage = MessageUtil.initTemplateMessage(miniData, "#173177");
            int cont = 0;
            String sendTemplateMessage = WeixinHandler.sendMiniTemplateMessage(openid,templateId,page,formId ,initTemplateMessage, emphasisKeyword,accessToken);

            JSONObject jsonObjectSend =JSONObject.fromObject(sendTemplateMessage);
            logger.error("微信发送模板消息result：{}",sendTemplateMessage);
            Map<String, Object> data = new HashMap<>();
            if(jsonObjectSend.getInt("errcode") == 0){
                cont++;
                data.put("cont", cont);
                data.put("msg", "发送成功！");
                return R.ok(data);
            }
            return R.error("微信发送模板消息失败："+sendTemplateMessage);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error("微信发送模板消息失败："+e.getMessage());
        }
    }


    /**
     * @描述: 发送小程序订阅消息
     * @创建人: cuixinfu@51play.com
     * @创建时间: 2018/11/1 18:16
     */
    @RequestMapping("sendMiniSubscribeMsg")
    public @ResponseBody R sendMiniSubscribeMsg(HttpServletRequest request,
                                                     @RequestParam("keyword1") String keyword1,
                                                     @RequestParam("keyword2") String keyword2,
                                                     @RequestParam("keyword3") String keyword3,
                                                     @RequestParam("keyword4") String keyword4,
                                                     @RequestParam("keyword5") String keyword5,
                                                     @RequestParam("remark") String remark) {
        try {
            //1、获取getAccessToken
            String accessToken = WeixinHandler.getMiniAccessToken();

            String openid = "o35K45dtFxwV_QSdINke-3vpX4bw" ;
            String templateId = "x_106LS2USnUHYKFmsJMUD4A_a9TljxY_Jvl38V4Mdg";
            String page = "pages/index/index";

            //3、添加参数
            //String pagepath = "pages/maps/maps?pointx="+pointX+"&pointy="+pointY+"&title="+message.getTitle();
            //String thing5 = "";

            HashMap<String, Object> miniData = new HashMap<>();
            miniData.put("thing1", keyword1);
            miniData.put("thing2", keyword2);
            miniData.put("thing3", keyword3);
            miniData.put("date4", DateHandler.getStringDate());
            miniData.put("thing5", keyword5);

            //4、初始化模板消息：
            HashMap<String, Object> initTemplateMessage = MessageUtil.initTemplateMessage(miniData, "#173177");
            String sendTemplateMessage = WeixinHandler.sendMiniSubscribeMessage(openid,templateId,page ,initTemplateMessage,accessToken);

            JSONObject jsonObjectSend =JSONObject.fromObject(sendTemplateMessage);
            logger.error("微信发送模板消息result：{}",sendTemplateMessage);
            Map<String, Object> data = new HashMap<>();
            if(jsonObjectSend.getInt("errcode") == 0){
                data.put("msg", "微信发送发送订阅消息成功！");
                return R.ok(data);
            }
            return R.error("微信发送发送订阅消息失败："+sendTemplateMessage);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("微信发送发送订阅消息失败：{}",e.getMessage());
            return R.error("微信发送发送订阅消息失败："+e.getMessage());
        }
    }
}
