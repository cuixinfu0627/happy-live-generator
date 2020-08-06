package com.happy.live.common.base.encrypt;


import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Security;

/**
 * Created by Administrator on 2015/10/30.
 */
public class ThreeDes {
    public static final byte[] KEY = {0x11, 0x22, 0x4F, 0x58, (byte)0x88, 0x10, 0x40, 0x38
            , 0x28, 0x25, 0x79, 0x51, (byte)0xCB, (byte)0xDD, 0x55, 0x66
            , 0x77, 0x29, 0x74, (byte)0x98, 0x30, 0x40, 0x36, (byte)0xE2} ;
    private static final String Algorithm = "DESede"; //定义 加密算法,可用 DES,DESede,Blowfish

    //keybyte为加密密钥，长度为24字节
    //src为被加密的数据缓冲区（源）
    private static byte[] desEncrypt(byte[] keybyte, byte[] src) {
        try {
            //生成密钥
            SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);

            //加密
            Cipher c1 = Cipher.getInstance(Algorithm);
            c1.init(Cipher.ENCRYPT_MODE, deskey);
            return c1.doFinal(src);
        } catch (java.security.NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (javax.crypto.NoSuchPaddingException e2) {
            e2.printStackTrace();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }

    //keybyte为加密密钥，长度为24字节
    //src为加密后的缓冲区
    private static byte[] desDecrypt(byte[] keybyte, byte[] src) {
        try {
            //生成密钥
            SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);

            //解密
            Cipher c1 = Cipher.getInstance(Algorithm);
            c1.init(Cipher.DECRYPT_MODE, deskey);
            return c1.doFinal(src);
        } catch (java.security.NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (javax.crypto.NoSuchPaddingException e2) {
            e2.printStackTrace();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }

    public static String encrypt(String input) throws Exception
    {
        return Base64.encodeBase64String(desEncrypt(KEY,input.getBytes()));
    }

  public static String encrypt(byte[] key,String input) throws Exception
{
    return Base64.encodeBase64String(desEncrypt(key,input.getBytes()));
}

    public static String decrypt(String input) throws Exception
    {
        byte[] result = Base64.encodeBase64(input.getBytes());
        return new String(desDecrypt(KEY,result));
    }

    public static String decrypt(byte[] key,String input) throws Exception
    {
        byte[] result = Base64.encodeBase64(input.getBytes());
        return new String(desDecrypt(key,result));
    }

    @SuppressWarnings("restriction")
	public static void main(String[] args) throws Exception {
        //添加新安全算法,如果用JCE就要把它添加进去
        Security.addProvider(new com.sun.crypto.provider.SunJCE());

        String szSrc = "123";

        System.out.println("加密前的字符串:" + szSrc);

        String result = encrypt(szSrc);
        System.out.println("加密后的字符串:" + result);


        result = decrypt("boDrO8W9Y+g=");
        System.out.println("解密后的字符串:" + result);
    }

}
