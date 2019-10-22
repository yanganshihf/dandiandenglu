package com.example.demo.utils;

import org.apache.commons.lang3.StringUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * Title: Md5工具类
 * Date: 2018-11-14
 * Author: chy
 */
public class Md5Util {

    private final static String DEFAULT_CHARSET = "UTF-8";

    private final static String GBK_CHARSET = "GBK";

    private Md5Util() {

    }

    /**
     * Used building output as Hex
     */
    private static final char[] DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    private static final char[] hexChars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

    public static String encoderHmacSha256(String data, String key) {
        try {
            byte[] dataByte = data.getBytes();
            byte[] keyByte = key.getBytes();

            SecretKeySpec signingKey = new SecretKeySpec(keyByte, "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(signingKey);
            return byte2hex(mac.doFinal(dataByte));
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        } catch (InvalidKeyException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    private static String byte2hex(byte[] b) {
        return byte2hex(b, '\u0000');
    }
    private static String byte2hex(byte[] b, char delimeter) {
        StringBuffer sb = new StringBuffer();

        for(int n = 0; n < b.length; ++n) {
            byte2hex(b[n], sb);
            if(n < b.length - 1 && delimeter != 0) {
                sb.append(delimeter);
            }
        }

        return sb.toString().toLowerCase();
    }
    private static void byte2hex(byte b, StringBuffer buf) {
        int high = (b & 240) >> 4;
        int low = b & 15;
        buf.append(hexChars[high]);
        buf.append(hexChars[low]);
    }


    /**
     * 根据content,key,按预定算法计算hash
     *
     * @param text
     * @param key
     * @return 加密结果
     */
    public static String hash(String text, String key) {
        if (text == null) {
            throw new IllegalArgumentException("text can't be null");
        }
        if (key == null) {
            throw new IllegalArgumentException("key can't be null");
        }
        try {
            String S = md5(key);
            byte[] textData = text.getBytes(DEFAULT_CHARSET);
            int len = textData.length;
            int n = (len + 15) / 16;
            byte[] tempData = new byte[n * 16];
            for (int i = len; i < n * 16; i++) {
                tempData[i] = 0;
            }
            System.arraycopy(textData, 0, tempData, 0, len);
            textData = tempData;
            String[] c = new String[n];
            for (int i = 0; i < n; i++) {
                c[i] = new String(textData, 16 * i, 16, DEFAULT_CHARSET);
            }
            String[] b = new String[n];
            String hash;
            String temp = S;
            String target = "";
            for (int i = 0; i < n; i++) {
                b[i] = md5(temp + c[i]);
                temp = b[i];
                target += b[i];
            }
            hash = md5(target);
            return hash;
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    /**
     * Converts an array of bytes into an array of characters representing the
     * hexidecimal values of each byte in order. The returned array will be
     * double the length of the passed array, as it takes two characters to
     * represent any given byte.
     *
     * @param data a byte[] to convert to Hex characters
     * @return A char[] containing hexidecimal characters
     */
    private static char[] encodeHex(byte[] data) {
        int l = data.length;
        char[] out = new char[l << 1];
        for (int i = 0, j = 0; i < l; i++) {
            out[j++] = DIGITS[(0xF0 & data[i]) >>> 4];
            out[j++] = DIGITS[0x0F & data[i]];
        }
        return out;
    }

    /**
     * 初始化MessageDigest对象
     *
     * @return MessageDigest对象
     * @throws Exception
     */
    private static MessageDigest getMD5Digest() throws Exception {
        MessageDigest md5MessageDigest = MessageDigest.getInstance("MD5");
        md5MessageDigest.reset();
        return md5MessageDigest;
    }

    /**
     * 计算md5摘要
     *
     * @param content 需计算Md5摘要的字符串
     * @return md5结果
     */
    public static String md5(String content) {
        try {
            byte[] data = getMD5Digest().digest(content.getBytes(DEFAULT_CHARSET));
            char[] chars = encodeHex(data);
            return new String(chars).toUpperCase();
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 指定编码格式计算Md5摘要
     *
     * @param content 需计算Md5摘要的字符串
     * @param charset 编码格式
     * @return md5结果
     */
    public static String md5(String content, String charset) {
        try {
            byte[] data = getMD5Digest().digest(content.getBytes(charset));
            char[] chars = encodeHex(data);
            return new String(chars);
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 计算文件Md5摘要
     *
     * @param file 文件信息对象
     * @return Md5摘要
     */
    public static String getMd5ByFile(File file) {
        String value = null;
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            MappedByteBuffer byteBuffer = in.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(byteBuffer);
            BigInteger bi = new BigInteger(1, md5.digest());
            value = bi.toString(16);
            while (value.length() < 32) {
                value = "0" + value;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return value;
    }

    /**
     * 排序并计算Md5摘要
     *
     * @param paramsMap 请求参数Map集
     * @param key       密钥
     * @return Md5摘要
     */
    public static String md5AndSort(Map<String, String> paramsMap, String key) {
        Map<String, String> sortMap = new TreeMap<String, String>();
        for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
            //过滤空值
            if (StringUtils.isNotBlank(entry.getValue())) {
                sortMap.put(entry.getKey(), entry.getValue());
            }
        }
        //拼接待签名字符串
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> keyValue : sortMap.entrySet()) {
            sb.append(keyValue.getKey() + "=" + keyValue.getValue() + "&");
        }
        //拼接密钥
        sb.append("key=").append(key);
        return md5(sb.toString());
    }
}
