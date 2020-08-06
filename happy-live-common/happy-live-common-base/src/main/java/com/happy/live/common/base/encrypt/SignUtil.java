package com.happy.live.common.base.encrypt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类名:SignUtil <tb>
 * 描述:请输入类描述  <tb>
 * 作者:cuixinfu@51play.com <tb>
 * 时间:2019/11/18 11:30 <tb>
 */

public abstract class SignUtil {
    private static final String DEFAULT_SIGN_KEY = "bf7f68b058794296be36e3ac2e55d33fa4ffdffa0d28465c94f678062dc7c68a";
    private static final String DEFAULT_CHAT_SET = "UTF-8";

    public SignUtil() {
    }

    private static String createLinkString(Map<String, String> params) {
        if (params != null && !params.isEmpty()) {
            List<String> keys = new ArrayList(params.keySet());
            Collections.sort(keys);
            String preStr = "";

            for(int i = 0; i < keys.size(); ++i) {
                String key = (String)keys.get(i);
                String value = (String)params.get(key);
                if (value == null) {
                    value = "";
                }

                if (i == keys.size() - 1) {
                    preStr = preStr + key + "=" + value;
                } else {
                    preStr = preStr + key + "=" + value + "&";
                }
            }

            return preStr;
        } else {
            throw new RuntimeException("sign params error");
        }
    }

    public static String getSign(Map<String, String> params) {
        return getSign(params, "bf7f68b058794296be36e3ac2e55d33fa4ffdffa0d28465c94f678062dc7c68a", "UTF-8");
    }

    public static String getSign(Map<String, String> params, String key, String charset) {
        String paramStr = createLinkString(params);
        System.out.println(paramStr);
        return MD5.sign(paramStr, key, charset);
    }

    public static boolean verify(Map<String, String> params, String sign) {
        return verify(params, sign, "bf7f68b058794296be36e3ac2e55d33fa4ffdffa0d28465c94f678062dc7c68a", "UTF-8");
    }

    public static boolean verify(Map<String, String> params, String sign, String key, String charset) {
        String paramStr = createLinkString(params);
        return MD5.verify(paramStr, sign, key, charset);
    }

    public static void main(String[] args) {
        Map<String, String> params = new HashMap();
        params.put("b", "987654");
        System.out.println(getSign(params));
        System.out.println(verify(params, "697eb3d07c7e24c7449fe5cd13a0d200"));
    }
}
