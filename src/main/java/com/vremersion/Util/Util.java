package com.vremersion.Util;

/**
 * @author XP
 * @date 2018/5/16 17:00
 */
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Random;


public class Util {


    public static final String key = "1q2w3e4r5t6y7u8i";

    public static String randString(){
        char[] letter = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz0123456789".toCharArray();
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<10;i++)
            sb.append(letter[random.nextInt(62)]);
        return sb.toString();
    }

    public static String mdPassword(String password)  {

        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        byte[] bytes = md5.digest(password.getBytes());
        StringBuffer sb = new StringBuffer();
        for(byte b : bytes) {
            int val = (int) b & 0xff;
            if(val<16)
                sb.append('0');
            sb.append(Integer.toHexString(val));
        }
        return sb.toString();
    }

    public static String encodeStr(String string) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        String iv = "aabbccddeeffgghh";
        Key keySpec = new SecretKeySpec(key.getBytes(),"AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes());

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE,keySpec,ivParameterSpec);
        byte [] b = cipher.doFinal(string.getBytes());
        String ret = Base64.encode(b);

        return ret;
    }

    public static String decodeStr(String string) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        byte[] bytes = Base64.decode(string);
        IvParameterSpec ivParameterSpec = new IvParameterSpec("aabbccddeeffgghh".getBytes());
        Key keySpec = new SecretKeySpec(key.getBytes(),"AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE,keySpec,ivParameterSpec);
        byte[] ret = cipher.doFinal(bytes);
        return new String(ret);

    }

    public static String myStrMix(String a,String b){
        StringBuffer sb = new StringBuffer();
        a = a.split("@")[0];
        int al = a.length();
        int bl = b.length()-1;
        int i=0;
        while(i<al||bl>=0)
        {
            if(bl>=0)
            {
                sb.append(b.charAt(bl));
                bl--;
            }
            if(i<al)
            {sb.append(a.charAt(i));
                i++;
            }
        }
        return  sb.toString();
    }

    public static void rsa() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(1024); //一般加密位数为1024 对安全要求较高的情况下可以使用2048
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        System.out.println("pubkey "+Base64.encode(publicKey.getEncoded()));
        System.out.println("privatekey "+Base64.encode(privateKey.getEncoded()));

    }

    public static PrivateKey getPrivateKey(String privateKeyData){
        PrivateKey privateKey = null;
        try {
            byte[] decodeKey = Base64.decode(privateKeyData); //将字符串Base64解码
            PKCS8EncodedKeySpec x509= new PKCS8EncodedKeySpec(decodeKey);//创建x509证书封装类
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");//指定RSA
            privateKey = keyFactory.generatePrivate(x509);//生成私钥
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return privateKey;
    }

    public static PublicKey getPublicKey(String publicKeyData){
        PublicKey publicKey = null;
        try {
            byte[] decodeKey = Base64.decode(publicKeyData);
            X509EncodedKeySpec x509 = new X509EncodedKeySpec(decodeKey);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            publicKey = keyFactory.generatePublic(x509);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return publicKey;
    }
    public static byte[] encryRSA(String data){
        String publickey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCyXdwti3u5lUUHFZK/J+oczTiluiNr/AqW1ctXs0bj5GX8toTxzFopO2PSLFT/7zsDYlu+8I8CRnopni4vpmkvKZ5phjrsKtpq97hNpVqSaX0BGIwbV4wKMflied/OH0u9XzRJ85lvken1MXf4SqNxxNEwwc9vP2oAXnv8z8IgaQIDAQAB";
        Key key = getPublicKey(publickey);
        KeyFactory keyFactory  = null;
        try {
            keyFactory = KeyFactory.getInstance("RSA");
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.ENCRYPT_MODE,key);
            return cipher.doFinal(data.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }



        return null;
    }

    public static byte[] decryRSA(byte[] data){
        String privatekey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALJd3C2Le7mVRQcVkr8n6hzNOKW6I2v8CpbVy1ezRuPkZfy2hPHMWik7Y9IsVP/vOwNiW77wjwJGeimeLi+maS8pnmmGOuwq2mr3uE2lWpJpfQEYjBtXjAox+WJ5384fS71fNEnzmW+R6fUxd/hKo3HE0TDBz28/agBee/zPwiBpAgMBAAECgYArPQOV6t04VM4h9IHZYwSmrL0Rt7jEgZnTjMo5XVVPA1wR0fESaJZmrmX5WhPU4xpsLJjPBaO1Sn5svlK17GJMy2R5eCcfqOWlzD81J5Dd/Z0liHSJWruR20BeeP7goZ7a7L4WAV/Xm/2rsLKX+Yhs/YpKPrAzlveHiKkgSaLXgQJBAOzVJPgz58QIqwsKBH/MI5oYn3ig1mkAbvbiOfqC4HPEQmezLlVyGBURHNN8GIv2Oofe4Hv7WoTvLafnk/9YJfECQQDAzWEIrRbNovXLcBy5fzJDR8boBvSyZMt6eoEV2Bu7O3qWKSFtbZDjQwpQYRcPvRyWHaWk4GdPGHC0u2v568n5AkEA0HYUonxdpsnSdFcO+sZfGAZQaEd6MtZ01c7vJbgJhRzdLpYjVIam9/QmIWj72kaT4oClq7vlQ1mcIEzQklOuoQJAbrZAzxudqY23OQ/y3q3kre/MjzZXUw1ALt+jumhzXCuGdC3ALTcqaOX5STjHZllAzg7OLIRTrn6pUY8CLOwx+QJAGCnlvhrOBPgNA/4q3ZSGGeZJ/+RlCVSsNk0n6fZB3H8vb+UqTH0VQmxCSYFinlcSb3weWAaH6/FJYBdACKWa5A==";
        Key key = getPrivateKey(privatekey);
        try{
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE,key);
            return cipher.doFinal(data);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {

        System.out.println(Base64.encode(encryRSA("1234")));
        System.out.println(new String(decryRSA(Base64.decode("niIEJELXlRYgJ6g11oMYPz7XDLK5z1PyV0EA5+mpxc7s4GmZvgEp015zJI9gejszhtxtf6ChZcYdX8i0iXku5jvFk84vOkfnVGlypKIwJdxQHG+b3PCCCuVp8TriSFuU0sd1sV37hnybXG4Yar4W+Zkt31bW+LRqhEIuNqRgr2Y="))));





    }
}
