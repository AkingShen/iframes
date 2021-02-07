package com.iframe.common.httpUtils;

import com.alibaba.fastjson.JSONArray;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class HttpUtil {


    /**
     * 有参get请求
     */
    public static String  doGetRequest(String url,String  params) {

        try {
            HttpClient client = new DefaultHttpClient();
            //发送get请求
            HttpGet request = new HttpGet(url+"?"+params);
            HttpResponse response = client.execute(request);

            /**请求发送成功，并得到响应**/
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                /**读取服务器返回过来的json字符串数据**/
                String strResult = EntityUtils.toString(response.getEntity());
//                System.out.println(strResult);

                return strResult;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

//        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
//
//        HttpGet httpGet = new HttpGet(url+ "?" + params);
//        // 响应模型
//        CloseableHttpResponse response = null;
//        try {
//            // 配置信息
//            RequestConfig requestConfig = RequestConfig.custom()
//                    // 设置连接超时时间(单位毫秒)
//                    .setConnectTimeout(5000)
//                    // 设置请求超时时间(单位毫秒)
//                    .setConnectionRequestTimeout(5000)
//                    // socket读写超时时间(单位毫秒)
//                    .setSocketTimeout(5000)
//                    // 设置是否允许重定向(默认为true)
//                    .setRedirectsEnabled(true).build();
//
//            // 将上面的配置信息 运用到这个Get请求里
//            httpGet.setConfig(requestConfig);
//
//            // 由客户端执行(发送)Get请求
//            response = httpClient.execute(httpGet);
//
//            // 从响应模型中获取响应实体
//            HttpEntity responseEntity = response.getEntity();
//            System.out.println("响应状态为:" + response.getStatusLine());
//            if (responseEntity != null) {
//                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
//                System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
//            }
//            return EntityUtils.toString(responseEntity);
//        } catch (ClientProtocolException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                // 释放资源
//                if (httpClient != null) {
//                    httpClient.close();
//                }
//                if (response != null) {
//                    response.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
        return null;
    }

}
