package com.happy.live.common.third.weather;

import net.sf.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liujian on 2017/7/12.
 */
public class WeatherUtils {

    /**
     * @param year 年份
     * @param name 节气的名称
     * @return 返回节气是相应月份的第几天
     */
    public static int getSolarTermNum(int year,String name){

        double centuryValue = 0;//节气的世纪值，每个节气的每个世纪值都不同
        name = name.trim().toUpperCase();
        int ordinal = Weather.SolarTermsEnum.valueOf(name).ordinal();

        int centuryIndex = -1;
        if(year>=1901 && year<=2000){//20世纪
            centuryIndex = 0;
        } else if(year>=2001 && year <= 2100){//21世纪
            centuryIndex = 1;
        } else {
            throw new RuntimeException("不支持此年份："+year+"，目前只支持1901年到2100年的时间范围");
        }
        centuryValue = Weather.CENTURY_ARRAY[centuryIndex][ordinal];
        int dateNum = 0;
        /**
         * 计算 num =[Y*D+C]-L这是传说中的寿星通用公式
         * 公式解读：年数的后2位乘0.2422加C(即：centuryValue)取整数后，减闰年数
         */
        int y = year%100;//步骤1:取年分的后两位数
        if(year%4 == 0 && year%100 !=0 || year%400 ==0){//闰年
            if(ordinal == Weather.SolarTermsEnum.XIAOHAN.ordinal() || ordinal == Weather.SolarTermsEnum.DAHAN.ordinal()
                    || ordinal == Weather.SolarTermsEnum.LICHUN.ordinal() || ordinal == Weather.SolarTermsEnum.YUSHUI.ordinal()){
                //注意：凡闰年3月1日前闰年数要减一，即：L=[(Y-1)/4],因为小寒、大寒、立春、雨水这两个节气都小于3月1日,所以 y = y-1
                y = y-1;//步骤2
            }
        }
        dateNum = (int)(y*Weather.D+centuryValue)-(int)(y/4);//步骤3，使用公式[Y*D+C]-L计算
        dateNum += specialYearOffset(year,name);//步骤4，加上特殊的年分的节气偏移量
        return dateNum;
    }

    /**
     * 特例,特殊的年分的节气偏移量,由于公式并不完善，所以算出的个别节气的第几天数并不准确，在此返回其偏移量
     * @param year 年份
     * @param name 节气名称
     * @return 返回其偏移量
     */
    public static int specialYearOffset(int year,String name) {
        int offset = 0;
        offset += getOffset(Weather.DECREASE_OFFSETMAP,year,name,-1);
        offset += getOffset(Weather.INCREASE_OFFSETMAP,year,name,1);

        return offset;
    }

    public static int getOffset(Map<String,Integer[]> map,int year,String name,int offset){
        int off = 0;
        Integer[] years = map.get(name);
        if(null != years){
            for(int i:years){
                if(i == year){
                    off = offset;
                    break;
                }
            }
        }
        return off;
    }
    /**
     * 获取当天天气预报
     * @return
     */
    public static JSONObject getWeacher(Weather weather, String cityName) {
            try {
                String api = weather.getApiUrl().replace("WEAID",cityName).replace("APPKEY",weather.getAppKey()).replace("SECRETKEY",weather.getSecret());
                URL u = new URL(api);    InputStream in = u.openStream();
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                try {
                    byte buf[] = new byte[1024];
                    int read = 0;
                    while ((read = in.read(buf)) > 0) {
                        out.write(buf, 0, read);
                    }
                } finally {
                    if (in != null) {
                        in.close();
                    }
                }
                byte b[] = out.toByteArray();
                String result = new String(b, "utf-8");
                JSONObject json = JSONObject.fromObject(result);
                JSONObject jb = json.getJSONObject("result");
                return json.getJSONObject("result");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    /**
     * 获取当天空气指数
     * @return
     */
    public static JSONObject getPM(Weather weather,String  cityName) {
        try {
            String api = weather.getApiUrl().replace("WEAID",cityName).replace("APPKEY",weather.getAppKey()).replace("SECRETKEY",weather.getSecret());
            URL u = new URL(api);
            InputStream in = u.openStream();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            try {
                byte buf[] = new byte[1024];
                int read = 0;
                while ((read = in.read(buf)) > 0) {
                    out.write(buf, 0, read);
                }
            } finally {
                if (in != null) {
                    in.close();
                }
            }
            byte b[] = out.toByteArray();
            String pm = new String(b, "utf-8");
            JSONObject json = JSONObject.fromObject(pm);
            return json.getJSONObject("result");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Map solarTermMap(int year) {
        StringBuffer sb = new StringBuffer();
        sb.append("---").append(year);
        if(year%4 == 0 && year%100 !=0 || year%400 ==0){//闰年
            sb.append(" 闰年");
        } else {
            sb.append(" 平年");
        }
        Map<String,Integer> param = new HashMap<String,Integer>();
        param.put("夏至",getSolarTermNum(year,Weather.SolarTermsEnum.XIAZHI.name()));
        param.put("冬至",getSolarTermNum(year, Weather.SolarTermsEnum.DONGZHI.name()));
        return param;
    }

    public static String myPercent(int y, int z) {
        String baifenbi = "";// 接受百分比的值
        if(y == 0 && z == 0 ){
            return "0%";
        }
        double baiy = y * 1.0;
        double baiz = z * 1.0;
        double fen = baiy / baiz;
        NumberFormat nf = NumberFormat.getPercentInstance(); //注释掉的也是一种方法
        nf.setMinimumFractionDigits(2 ); //保留到小数点后几位2
        // 百分比格式，后面不足2位的用0补齐
        baifenbi=nf.format(fen);
        return baifenbi;
    }

}
