package com.taotao.http;

import org.apache.http.HttpMessage;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.http.client.utils.URIBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpUtil {
    /***
     * 无参doPost
     * @param url
     * @return
     * @throws Exception
     */
    public static HttpResult doPost(String url) throws  Exception{
        return doPost(url,null);
    }

    /***
     * Post请求
     * @param url
     * @param datamap
     * @return
     * @throws Exception
     */
    public static HttpResult doPost(String url, Map<String,Object> datamap) throws  Exception{
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        try {
            //创建CloseableHttpClient
            httpClient = HttpClients.createDefault();
            //确定提交方式
            HttpPost httpPost = new HttpPost(url);
            if(datamap!=null){
                //表单数据打包
                List<NameValuePair> formdata = new ArrayList<NameValuePair>();
                for (Map.Entry<String, Object> entry : datamap.entrySet()) {
                    //BasicNameValuePair(参数名字，参数值)
                    formdata.add(new BasicNameValuePair(entry.getKey(),entry.getValue().toString()));
                }
                UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(formdata);
                //设置要提交表单的数据
                httpPost.setEntity(formEntity);
            }
            //头文件
            setHeader(httpPost);
            //执行请求
            response = httpClient.execute(httpPost);
            //解析数据   解析状态+解析数据结果
            int statusCode = response.getStatusLine().getStatusCode();
            //数据
            String result = EntityUtils.toString(response.getEntity(), "UTF-8");
            HttpResult httpResult = new HttpResult(statusCode, result);
            return httpResult;
        } finally {
            //关闭资源
            if (response != null) {
                response.close();
            }
            if (httpClient != null) {
                httpClient.close();
            }
        }
    }


    /****
     * 无参get请求
     * @param url
     * @return
     * @throws Exception
     */
    public static HttpResult doGet(String url) throws  Exception{
        return doGet(url,null);
    }

    /***
     * 带参数的GET请求
     */
    public static HttpResult doGet(String url, Map<String,Object> datamap) throws  Exception{
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        try {
            //创建CloseableHttpClient
            httpClient = HttpClients.createDefault();
            //确定提交方式
            URIBuilder builder = new URIBuilder(url);
            if (datamap != null) {
                for (Map.Entry<String, Object> entry : datamap.entrySet()) {
                    builder.addParameter(entry.getKey(), entry.getValue().toString());
                }
            }
            //HttpGet httpGet = new HttpGet(url);
            HttpGet httpGet = new HttpGet(builder.build());
            //头文件
            setHeader(httpGet);
            //执行请求
            response = httpClient.execute(httpGet);
            //解析数据   解析状态+解析数据结果
            int statusCode = response.getStatusLine().getStatusCode();
            //数据
            String result = EntityUtils.toString(response.getEntity(), "UTF-8");
            HttpResult httpResult = new HttpResult(statusCode, result);
            return httpResult;
        } finally {
            if (response != null) {
                response.close();
            }
            if (httpClient != null) {
                httpClient.close();
            }
        }
    }


    /***
     * 设置头文件
     * @param httpMessage
     */
    public static void setHeader(HttpMessage httpMessage){
        httpMessage.setHeader("User-Agent","xiaohong");
        httpMessage.setHeader("Upgrade-Insecure-Requests","1");
        httpMessage.setHeader("Accept-Encoding","gzip, deflate");
        httpMessage.setHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
    }
}
