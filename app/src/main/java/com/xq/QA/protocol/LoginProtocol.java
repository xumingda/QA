package com.xq.QA.protocol;

import com.google.gson.Gson;
import com.xq.QA.base.BaseProtocol;
import com.xq.QA.repose.GetMessageListResponse;
import com.xq.QA.repose.LoginResponse;

public class LoginProtocol extends BaseProtocol<LoginResponse> {
	protected static final String TAG = "DiscoveryByProtocol";
	private Gson gson;

	public LoginProtocol() {
		gson = new Gson();
	}

	// 1. 封装请求参数
	// 2. 处理请求返回结果
	@Override
	protected LoginResponse parseJson(String json) {
		LoginResponse loginResponse = gson.fromJson(json, LoginResponse.class);
		return loginResponse;
	}

	@Override
	public String getApiFun() {
		// TODO Auto-generated method stub
		return "/user/login";
	}

	@Override
	public String getReqMethod() {
		// TODO Auto-generated method stub
		return "post";
	}
}
