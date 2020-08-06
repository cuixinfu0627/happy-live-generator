package com.happy.live.common.base.http;

import com.happy.live.common.base.encrypt.Pair;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

/**
 * @name: HttpClient <tb>
 * @title: 请输入类描述  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/3/26 10:06 <tb>
 */
public class HttpClient {

    private static Logger logger = LoggerFactory.getLogger(HttpClient.class);
    public static final String CODE_NAME_UTF8 = "UTF-8";

    public HttpClient() {
    }

    public static String post(String url, Map<String, String> parametersString) {
        return post(url, getFromMap(parametersString), "UTF-8");
    }

    public static String post(String url, Map<String, String> parametersString, String encode) {
        return postWithFile(url, getFromMap(parametersString), (List)null, encode);
    }

    public static String postWithFile(String url, Map<String, File> parametersFile) {
        return postWithFile(url, getFromMap(parametersFile), "UTF-8");
    }

    public static String postWithFile(String url, Map<String, File> parametersFile, String encode) {
        return postWithFile(url, (List)null, getFromMap(parametersFile), encode);
    }

    public static String postWithFile(String url, Map<String, String> parametersString, Map<String, File> parametersFile) {
        return postWithFile(url, getFromMap(parametersString), getFromMap(parametersFile), "UTF-8");
    }

    private static <T> List<Pair<String, T>> getFromMap(Map<String, T> parametersString) {
        List<Pair<String, T>> result = new ArrayList();
        Iterator iterator = parametersString.entrySet().iterator();

        while(iterator.hasNext()) {
            Map.Entry<String, T> temp = (Map.Entry)iterator.next();
            result.add(new Pair(temp.getKey(), temp.getValue()));
        }

        return result;
    }

    public static String post(String url, List<Pair<String, String>> parametersString) {
        return post(url, parametersString, "UTF-8");
    }

    public static String post(String url, List<Pair<String, String>> parametersString, String encode) {
        return postWithFile(url, parametersString, (List)null, encode);
    }

    public static String postWithFile(String url, List<Pair<String, File>> parametersFile) {
        return postWithFile(url, parametersFile, "UTF-8");
    }

    public static String postWithFile(String url, List<Pair<String, File>> parametersFile, String encode) {
        return postWithFile(url, (List)null, parametersFile, encode);
    }

    public static String postWithFile(String url, List<Pair<String, String>> parametersString, List<Pair<String, File>> parametersFile) {
        return postWithFile(url, parametersString, parametersFile, "UTF-8");
    }

    public static String postWithFile(String url, List<Pair<String, String>> parametersString, List<Pair<String, File>> parametersFile, String encode) {
        CloseableHttpClient httpclient = HttpClients.createDefault();

        Object contentType;
        try {
            HttpPost httppost = new HttpPost(url);
            List<NameValuePair> formparams = new ArrayList();
            Pair entry;
            String value;
            if (parametersString != null) {
                for(Iterator iterator = parametersString.iterator(); iterator.hasNext(); formparams.add(new BasicNameValuePair((String)entry.getKey(), value.toString()))) {
                    entry = (Pair)iterator.next();
                    value = (String)entry.getValue();
                    if (value == null) {
                        value = "";
                    }
                }
            }

            HttpEntity httpEntity = null;
            if (parametersFile != null && !parametersFile.isEmpty()) {
                MultipartEntityBuilder builder = MultipartEntityBuilder.create();
                Iterator iteratorFile = parametersFile.iterator();

                while(iteratorFile.hasNext()) {
                    Pair<String, File> entry1 = (Pair)iteratorFile.next();
                    FileBody fileBody = new FileBody((File)entry1.getValue());
                    builder.addPart((String)entry1.getKey(), fileBody);
                }

                contentType = ContentType.create("text/plain", encode);

                NameValuePair entry2;
                StringBody stringBody;
                for(Iterator iteratorString = formparams.iterator(); iteratorString.hasNext(); builder.addPart(entry2.getName(), stringBody)) {
                    entry2 = (NameValuePair)iteratorString.next();
                    stringBody = null;

                    try {
                        stringBody = new StringBody(entry2.getValue(), ((ContentType)contentType).getMimeType(), ((ContentType)contentType).getCharset());
                    } catch (UnsupportedEncodingException var32) {
                        var32.printStackTrace();
                    }
                }

                httpEntity = builder.build();
            }

            HttpEntity httpEntity1 = httpEntity == null ? new UrlEncodedFormEntity(formparams, "UTF-8") : httpEntity;
            httppost.setEntity((HttpEntity)httpEntity1);
            CloseableHttpResponse response = httpclient.execute(httppost);

            try {
                HttpEntity entity = response.getEntity();
                if (entity == null) {
                    return null;
                }

                contentType = EntityUtils.toString(entity);
            } finally {
                response.close();
            }
        } catch (Exception var34) {
            var34.printStackTrace();
            String var6 = var34.getMessage();
            return var6;
        } finally {
            try {
                httpclient.close();
            } catch (IOException var31) {
                var31.printStackTrace();
                return var31.getMessage();
            }
        }

        return (String)contentType;
    }

    public static String get(String url) {
        return get(url, (Map)(new HashMap()), (String)null);
    }

    public static String get(String url, String encode) {
        return get(url, (Map)(new HashMap()), encode);
    }

    public static String get(String url, Map<String, String> parametersString, String encode) {
        return get(url, getFromMap(parametersString), encode);
    }

    public static String get(String url, Map<String, String> parametersString) {
        return get(url, getFromMap(parametersString), "UTF-8");
    }

    public static String get(String url, List<Pair<String, String>> parametersString) {
        return get(url, parametersString, "UTF-8");
    }

    public static String get(String url, List<Pair<String, String>> parametersString, String encode) {
        if (url != null && url.length() >= 1) {
            StringBuffer urlBuffer = new StringBuffer(url);
            if (url.indexOf("?") < 0) {
                urlBuffer.append("?");
            }

            RequestConfig defaultRequestConfig = RequestConfig.custom().setSocketTimeout(8000).setConnectTimeout(8000).setConnectionRequestTimeout(8000).setStaleConnectionCheckEnabled(true).build();
            CloseableHttpClient httpclient = HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig).build();
            Pair entry;
            String resualt;
            if (parametersString != null) {
                for(Iterator iterator = parametersString.iterator(); iterator.hasNext(); urlBuffer.append((String)entry.getKey()).append("=").append(resualt)) {
                    urlBuffer.append("&");
                    entry = (Pair)iterator.next();
                    resualt = (String)entry.getValue();

                    try {
                        resualt = URLEncoder.encode(resualt, encode);
                    } catch (UnsupportedEncodingException var15) {
                        logger.error("====== 参数转码错误 ，encode = {},value={},  =======", encode, resualt);
                    }
                }
            }

            String urlWhithParam = urlBuffer.toString();
            HttpGet httpGet = new HttpGet(urlWhithParam);
            resualt = null;

            try {
                CloseableHttpResponse response = httpclient.execute(httpGet);

                try {
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        resualt = EntityUtils.toString(entity);
                    }
                } finally {
                    response.close();
                    httpclient.close();
                }

                return resualt;
            } catch (IOException var17) {
                logger.error("请求出错，url={}", urlWhithParam, var17);
                return null;
            }
        } else {
            logger.error("====== url 为空 =======");
            return null;
        }
    }

    public static void main(String[] args) {
        List<Pair<String, String>> parametersString = new ArrayList();
        System.out.println(get("http://www.baidu.com", (List)parametersString, "UTF-8"));
    }
}
