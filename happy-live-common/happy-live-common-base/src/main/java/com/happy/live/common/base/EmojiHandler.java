package com.happy.live.common.base;

import java.nio.charset.Charset;

/**
 * 类名:EmojiHandler <tb>
 * 描述:处理表情符号  <tb>
 * 作者:cuixinfu@51play.com <tb>
 * 时间:2018/11/8 11:30 <tb>
 */
public class EmojiHandler {
    /**
     * @描述: 去除昵称中表情符号
     * @参数: src 昵称
     * @创建时间: 2018/11/1 15:10
     * @返回值: 微信昵称
     */
    private static String removeEmoji(String src) {
        if(src == null || src.getBytes().length == 0) {
            return src;
        }
        byte[] bytes = src.getBytes(Charset.forName("UTF-8"));
        StringBuilder sb = new StringBuilder();
        int count;
        for(int i=0; i<bytes.length;i+=count) {
            count = countUTF8CharBytes(bytes[i]);
            if(count < 2) {
                count = 1;
                sb.append((char)bytes[i]);
            } else if (count < 4) {
                sb.append(new String(bytes, i, count, Charset.forName("UTF-8")));
            } else {
                sb.append("☺");
            }
        }
        return sb.toString();
    }

    private static byte countUTF8CharBytes(byte b) {
        byte offset = 0;
        while (offset < 8) {
            if (((b >> (7 - offset)) & 1) != 1) {
                break;
            }
            offset += 1;
        }
        return offset;
    }

    public static String filterEmoji( String source ) {
        int len = source.length();
        StringBuilder buf = new StringBuilder(len);
        for ( int i = 0 ; i < len ; i++ ) {
            char codePoint = source.charAt(i);
            if ( isNotEmojiCharacter(codePoint) ) {
                buf.append(codePoint);
            } else {
                buf.append("☺");

            }
        }
        return buf.toString();
    }

    private static boolean isNotEmojiCharacter( char codePoint ) {
        return (codePoint == 0x0)
            || (codePoint == 0x9) || (codePoint == 0xA) || (codePoint == 0xD)
            || ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) || ((codePoint >= 0xE000) && (codePoint <= 0xFFFD))
            || ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
    }
}
