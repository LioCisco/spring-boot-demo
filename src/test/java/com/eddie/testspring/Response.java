package com.eddie.testspring;

public class Response{
	private String ret;
	private String msg;
	private String su;
	private Data data;
	private String driverMd5;
	private String requestUri;
	private String token;

	public String getRet(){
		return ret;
	}

	public String getMsg(){
		return msg;
	}

	public String getSu(){
		return su;
	}

	public Data getData(){
		return data;
	}

	public String getDriverMd5(){
		return driverMd5;
	}

	public String getRequestUri(){
		return requestUri;
	}

	public String getToken(){
		return token;
	}
}
