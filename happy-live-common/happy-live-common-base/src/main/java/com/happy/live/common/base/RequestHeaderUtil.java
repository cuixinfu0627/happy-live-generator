package com.happy.live.common.base;

/**
 * 类名:RequestHeaderUtil <tb>
 * 描述:请输入类描述  <tb>
 * 作者:cuixinfu@51play.com <tb>
 * 时间:2019/11/18 11:54 <tb>
 */
public class RequestHeaderUtil {
    public static final String[] deviceArray = new String[]{"android", "ios", "windows phone"};

    public RequestHeaderUtil() {
    }

    public static boolean isMobileDevice(String requestHeader) {
        if (requestHeader == null) {
            return false;
        } else {
            requestHeader = requestHeader.toLowerCase();

            for(int i = 0; i < deviceArray.length; ++i) {
                if (requestHeader.indexOf(deviceArray[i]) >= 0) {
                    return true;
                }
            }

            return false;
        }
    }
}
