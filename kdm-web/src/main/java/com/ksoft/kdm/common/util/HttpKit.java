package com.ksoft.kdm.common.util;

import com.alibaba.dubbo.common.utils.StringUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.ExecutionException;

/**
 * @Title: HttpKit.java
 * 
 * @Description: TODO(https 请求 微信为https的请求)
 */
public class HttpKit {
	private static final String DEFAULT_CHARSET = "UTF-8";

	/**
	 * @return 返回类型:
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchProviderException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 * @description 功能描述: get 请求
	 */
	public static String get(String url, Map<String, Object> parameterMap, Map<String, String> headers) throws IOException, ExecutionException, InterruptedException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		String result = null;
		try {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			if (parameterMap != null) {
				for (Entry<String, Object> entry : parameterMap.entrySet()) {
					String name = entry.getKey();
					String value = ConvertUtils.convert(entry.getValue());
					if (StringUtils.isNotEmpty(name)) {
						nameValuePairs.add(new BasicNameValuePair(name, value));
					}
				}
			}
			HttpGet httpGet = new HttpGet(url + (StringUtils.isContains(url, "?") ? "&" : "?") + EntityUtils.toString(new UrlEncodedFormEntity(nameValuePairs, DEFAULT_CHARSET)));
			if (headers != null && !headers.isEmpty()) {
				Set<String> keys = headers.keySet();
				for (String key : keys) {
					httpGet.addHeader(key, headers.get(key));
				}
			}
			HttpResponse httpResponse = httpClient.execute(httpGet);
			HttpEntity httpEntity = httpResponse.getEntity();
			result = EntityUtils.toString(httpEntity, DEFAULT_CHARSET);
			EntityUtils.consume(httpEntity);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @return 返回类型:
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchProviderException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 * @description 功能描述: get 请求
	 */
	public static String get(String url) throws KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException, UnsupportedEncodingException, IOException, ExecutionException, InterruptedException {
		return get(url, null);
	}

	/**
	 * @return 返回类型:
	 * @throws IOException
	 * @throws NoSuchProviderException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 * @throws UnsupportedEncodingException
	 * @description 功能描述: get 请求
	 */
	public static String get(String url, Map<String, Object> params) throws KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException, UnsupportedEncodingException, IOException, ExecutionException, InterruptedException {
		return get(url, params, null);
	}



	//public static void main(String[] args) throws Exception {

		//System.out.println(get("http://1.jiajiag.com/lottery/buy_history.cgi?time=1430363119327&pid=1981&_=1430363119327", new HashMap<String, Object>()));

		// String accessToken = WeChat.getAccessToken();
		// String openId = "osTF-t31M8c2WbOrcm0F4dEisUso";
		//
		// Articles at = new Articles();
		// at.setTitle("用户登陆提示");
		// at.setDescription("你的账号在微信公众平台上被登录\n账号登陆时间:2015-04-10
		// 14:55:00\n登陆IP:192.168.0.1\n如果本次登陆不是你本人所为,说明你的账号已经被盗,为减少你的损失,请及时联系客服锁定账号");
		// List<Articles> list = new ArrayList<Articles>();
		// list.add(at);
		//
		// System.err.println(WeChat.message.SendNews(accessToken, openId,
		// list));
		// System.out.println(WeChat.message.sendText(accessToken , openId ,
		// "测试"));
		// Map<String, Object> mgs = WeChat.uploadMedia(accessToken, "image",
		// new File("C:\\Users\\郭华\\Pictures\\13.jpg"));
		// System.out.println(JSON.toJSONString(mgs));
	//}

	/**
	 * 获取请求的IP地址
	 * @param request
	 * @return
	 */
	public static String getRemortIP(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("http_client_ip");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		// 如果是多级代理，那么取第一个ip为客户ip
		if (ip != null && ip.indexOf(",") != -1) {
			ip = ip.substring(ip.lastIndexOf(",") + 1, ip.length()).trim();
		}
		return ip;
	}
}