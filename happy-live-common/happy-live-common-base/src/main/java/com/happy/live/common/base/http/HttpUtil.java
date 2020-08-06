package com.happy.live.common.base.http;

/**
 * @name: HttpUtil <tb>
 * @title: 请输入类描述  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/3/26 11:03 <tb>
 */
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class HttpUtil {
    public HttpUtil() {
    }

    public static String getDomain(HttpServletRequest request) {
        return getDomain(request, (String)null, 0);
    }

    public static String getDomain(HttpServletRequest request, String defaultDomain, int domainLevel) {
        if (!StringUtils.isEmpty(defaultDomain)) {
            return defaultDomain;
        } else {
            String fullDomainStr = request.getServerName();
            if (domainLevel == 0) {
                return fullDomainStr;
            } else {
                Pattern p = Pattern.compile("([0-9a-z\\-]*[^\\.\\S]*\\.([0-9a-z\\-]*[^\\.\\S]*\\.(com.cn|net.cn|org.cn|gov.cn|com|cn|net|org|gov|biz|info|cc|tv|me)))", 2);
                Matcher matcher = p.matcher(fullDomainStr);
                return matcher.find() ? matcher.group(domainLevel) : fullDomainStr;
            }
        }
    }
}
