package com.example.demo.utils;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.TreeMap;

/**
 * @title: http工具类
 * @date: 2018-10-29
 * @author: chy
 */
public class HttpUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);
    /**
     * 默认编码格式
     */
    public static final String DEFAULT_CHARSET = "UTF-8";
    /**
     * 连接超时时间
     */
    public static final int CONNECT_TIMEOUT = 1000 * 10;
    /**
     * Socket协议连接超时时间
     */
    public static final int SOCKET_TIMEOUT = 15000;

    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url   发送请求的URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param, String charts) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(), charts));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            logger.info("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url   发送请求的URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            logger.info("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * post
     *
     * @param strURL   请求地址
     * @param params   请求参数
     * @param dataType 请求类型
     * @return 响应字符串
     */
    public static String post(String strURL, String params, String dataType) {
        try {
            URL url;// 创建连接
            url = new URL(strURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            // 设置请求方式
            connection.setRequestMethod("POST");
            // 设置接收数据的格式
            connection.setRequestProperty("Accept", "application/" + dataType);
            // 设置发送数据的格式
            connection.setRequestProperty("Content-Type", "application/" + dataType);
            connection.connect();
            // utf-8编码
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
            out.append(params);
            out.flush();
            out.close();
            // 读取响应
            int length = (int) connection.getContentLength();
            InputStream is = connection.getInputStream();
            if (length != -1) {
                byte[] data = new byte[length];
                byte[] temp = new byte[512];
                int readLen = 0;
                int destPos = 0;
                while ((readLen = is.read(temp)) > 0) {
                    System.arraycopy(temp, 0, data, destPos, readLen);
                    destPos += readLen;
                }
                String result = new String(data, "UTF-8");
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }

    /**
     * POST请求
     *
     * @param clazz    对象类型
     * @param strURL   请求地址
     * @param params   请求参数
     * @param dataType 数据类型
     * @param <T>      泛型对象
     * @return 响应对象
     */
    public static <T> T post(Class<T> clazz, String strURL, String params, String dataType) {
        OutputStreamWriter out = null;
        InputStream is = null;
        try {
            URL url;// 创建连接
            url = new URL(strURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(CONNECT_TIMEOUT);
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            // 设置请求方式
            connection.setRequestMethod("POST");
            // 设置接收数据的格式
            connection.setRequestProperty("Accept", dataType);
            // 设置发送数据的格式
            connection.setRequestProperty("Content-Type", dataType);
            connection.connect();
            // utf-8编码
            out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
            out.append(params);
            out.flush();
            out.close();
            // 读取响应
            int length = (int) connection.getContentLength();
            is = connection.getInputStream();
            if (length != -1) {
                byte[] data = new byte[length];
                byte[] temp = new byte[512];
                int readLen = 0;
                int destPos = 0;
                while ((readLen = is.read(temp)) > 0) {
                    System.arraycopy(temp, 0, data, destPos, readLen);
                    destPos += readLen;
                }
                String result = new String(data, "UTF-8");
                // 类型转换
                return typeConversion(clazz, result, dataType);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * POST上传文件请求
     *
     * @param clazz      对象类型
     * @param requestURL 请求地址
     * @param fileUrl    文件地址
     * @param timeOut    超时时间
     * @param dataType   数据类型
     * @param <T>        泛型对象
     * @return 响应对象
     */
    public static <T> T postFile(Class<T> clazz, String requestURL, String fileUrl, String postData, int timeOut, String dataType) {
        OutputStream output = null;
        InputStream is = null;
        try {
            File file = null;
            String fileName = null;
            if (requestURL.indexOf("http://") > -1 || requestURL.indexOf("https://") > -1) {
                // 网络图片
                fileName = requestURL.substring(requestURL.lastIndexOf("/") + 1);
            } else {
                // 本地图片
                file = new File(fileUrl);
                if (file != null) {
                    fileName = file.getName();
                }
            }
            // 创建连接
            URL url = new URL(requestURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(timeOut);
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            // 设置请求方式
            connection.setRequestMethod("POST");
            // 设置请求头信息
            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.setRequestProperty("Cache-Control", "no-cache");
            // 设置边界
            String boundary = "-----------------------------" + System.currentTimeMillis();
            // 设置发送数据的格式
            connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
            output = connection.getOutputStream();
            output.write(("--" + boundary + "\r\n").getBytes());
            output.write(String.format("Content-Disposition: form-data; name=\"media\"; filename=\"%s\"\r\n", fileName).getBytes());
            if (StringUtils.isNotBlank(postData)) {
                output.write(postData.getBytes());
            }
            output.write("Content-Type: application/octet-stream \r\n\r\n".getBytes());
            byte[] data = new byte[1024];
            int len = 0;
            DataInputStream in;
            if (requestURL.contains("http://") || requestURL.contains("https://")) {
                // 获取网络图片
                URL mediaUrl = new URL(requestURL);
                HttpURLConnection mediaConn = (HttpURLConnection) mediaUrl.openConnection();
                mediaConn.setDoOutput(true);
                mediaConn.setRequestMethod("GET");
                in = new DataInputStream(mediaConn.getInputStream());
            } else {
                // 本地图片
                in = new DataInputStream(new FileInputStream(file));
            }
            while ((len = in.read(data)) > -1) {
                output.write(data, 0, len);
            }
            if (in != null) {
                in.close();
            }
            output.write(("\r\n--" + boundary + "--\r\n\r\n").getBytes());
            output.flush();
            output.close();
            // 获取响应流
            is = connection.getInputStream();
            StringBuffer sb = new StringBuffer();
            while ((len = is.read(data)) > -1) {
                sb.append(new String(data, 0, len, "utf-8"));
            }
            is.close();
            // 类型转换
            return typeConversion(clazz, sb.toString(), dataType);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 类型转换
     *
     * @param clazz    对象类型
     * @param data     数据字符串
     * @param dataType 数据类型
     * @param <T>      泛型对象
     * @return 转换后的对象信息
     * @throws Exception
     */
    public static <T> T typeConversion(Class<T> clazz, String data, String dataType) throws Exception {
        if (MediaType.APPLICATION_JSON_VALUE.equals(dataType)) {
            return JSON.parseObject(data, clazz);
        } else if (MediaType.APPLICATION_XML_VALUE.equals(dataType)) {
            return JavaBeanUtil.convertToJavaBean(clazz, XmlUtil.parseXmlStringToMap(dataType));
        }
        return null;
    }


    /**
     * POST 方式 上传图片
     *
     * @param urlStr
     * @param file
     * @return
     */
    public static String formUpload(String urlStr, File file) {
        logger.info("======= http post upload img start =====");
        String res = "";
        HttpURLConnection conn = null;
        String BOUNDARY = "---------------------------123821742118716"; //boundary就是request头和上传文件内容的分隔符
        try {
            URL url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(30000);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
            OutputStream out = new DataOutputStream(conn.getOutputStream());
            // file
            String filename = file.getName();
            StringBuffer strBuf = new StringBuffer();
            strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
            strBuf.append("Content-Disposition: form-data; name=image; filename=\"" + filename + "\"\r\n");
            strBuf.append("Content-Type:application/octet-stream\r\n\r\n");
            out.write(strBuf.toString().getBytes());
            DataInputStream in = new DataInputStream(new FileInputStream(file));
            int bytes = 0;
            byte[] bufferOut = new byte[1024];
            while ((bytes = in.read(bufferOut)) != -1) {
                out.write(bufferOut, 0, bytes);
            }
            in.close();

            byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();
            out.write(endData);
            out.flush();
            out.close();
            //读取返回数据
            StringBuffer sb = new StringBuffer();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    conn.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            res = sb.toString();
            logger.info("======= http post upload img end,result =====" + res);

            reader.close();
            reader = null;
        } catch (Exception e) {
            logger.info("======= http post upload img error,end =====");
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
                conn = null;
            }
        }
        return res;
    }

    /**
     * 清空集合中为空的值，并按ASCll码排序
     *
     * @param map Map集合
     * @return Map集合
     */
    public static Map<String, String> clearForm(Map<String, String> map) {
        Map<String, String> sortMap = new TreeMap<String, String>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            //过滤空值
            if (StringUtils.isNotBlank(entry.getValue())) {
                sortMap.put(entry.getKey(), entry.getValue());
            }
        }
        return sortMap;
    }

    /**
     * 创建Form请求信息
     *
     * @param param Map集合
     * @return Form请求信息
     */
    public static String createFormToString(TreeMap<String, String> param) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : param.entrySet()) {
            //拼接参数
            sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        //截取最后一个&
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    /**
     * 创建Form请求信息
     *
     * @param param Map集合
     * @param key   追加签名Key
     * @return Form请求信息
     */
    public static String createForm(TreeMap<String, Object> param, String key) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Object> entry : param.entrySet()) {
            if (entry.getValue() != null && StringUtils.isNotBlank(entry.getValue().toString())) {
                //拼接参数
                sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
        }
        sb.append("key=" + key);
        return sb.toString();
    }

    /**
     * 创建Form请求信息
     *
     * @param param Map集合
     * @param key   追加签名Key
     * @return Form请求信息
     */
    public static String createForm(TreeMap<String, Object> param, String key, boolean eliminateNull) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Object> entry : param.entrySet()) {
            //是否剔除空值
            if (eliminateNull) {
                if (entry.getValue() == null || StringUtils.isBlank(entry.getValue().toString())) {
                    continue;
                }
            }
            //拼接参数
            sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        sb.append("key=" + key);
        return sb.toString();
    }

    /**
     * 创建Form请求信息
     *
     * @param param Map集合
     * @return Form请求信息
     */
    public static String createForm(TreeMap<String, Object> param, boolean eliminateNull) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Object> entry : param.entrySet()) {
            //是否剔除空值
            if (eliminateNull) {
                if (entry.getValue() == null || StringUtils.isBlank(entry.getValue().toString())) {
                    continue;
                }
            }
            //拼接参数
            sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        return sb.toString();
    }

}
