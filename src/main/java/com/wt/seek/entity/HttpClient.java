package com.wt.seek.entity;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpClient {

	private String url;
	private String resultJson;
	private HttpURLConnection con = null;
	
	
	public HttpClient(String url){
		this.url = url;
	}
	
	public String getResultJson() {
		return resultJson;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	/**
	 * 每次调用connect都会重新连接
	 */
	public boolean connect(String method) {
		if(null!=url) {
			try {
				con = (HttpURLConnection) new URL(url).openConnection();
				con.setRequestMethod(method);
			} catch (IOException e) {
				return false;//发生异常,比如URL不合法
			}
			return true;//顺利完成连接
		}
		return false;//URL为空
	}
	
	/**
	 * @return 获取响应的主体内容
	 */
	public String fetchResponseContentInfo() {
		if(null!=con) {
			StringBuilder result = new StringBuilder();
			String eachLine = "";
			BufferedReader is;
			try {
				is = new BufferedReader(new InputStreamReader(con.getInputStream()));
				while(null!=(eachLine = is.readLine())) {
					result.append(new String(eachLine.getBytes(),"UTF-8"));
				}
				con.getInputStream().close();
				is.close();
				con.disconnect();
			} catch (IOException e) {
				return null;
			}
			this.resultJson = result.toString();
		}
		return resultJson;
	}

	
	public Object doPost(String param,String contentType) {
        InputStream is = null;
        OutputStream os = null;
        ByteArrayOutputStream baos = null;
        byte[] finalBytes = null;
        try {
            this.connect("POST");
            
            con.setDoOutput(true);
            if(null != contentType && contentType.length() > 0) {
            	con.setRequestProperty("Content-type", contentType);
            }
            os = con.getOutputStream();
            os.write(param.getBytes());
            is = con.getInputStream();
            baos = new ByteArrayOutputStream(2048);
            byte[] bytes = new byte[2048];
            int len = -1;
            while((len= is.read(bytes)) != -1) {
            	baos.write(bytes, 0, len);
            }
            finalBytes = baos.toByteArray();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != baos) {
                try {
                	baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != os) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // 断开与远程地址url的连接
            con.disconnect();
        }
        return finalBytes;
    }
}
