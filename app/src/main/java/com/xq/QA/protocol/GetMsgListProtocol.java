package com.xq.QA.protocol;

import com.google.gson.Gson;
import com.xq.QA.base.BaseProtocol;
import com.xq.QA.repose.GetMessageListResponse;

public class GetMsgListProtocol extends BaseProtocol<GetMessageListResponse> {
	protected static final String TAG = "DiscoveryByProtocol";
	private Gson gson;

	public GetMsgListProtocol() {
		gson = new Gson();
	}

	// 1. 封装请求参数
	// 2. 处理请求返回结果
	@Override
	protected GetMessageListResponse parseJson(String json) {
		GetMessageListResponse getGoodsListResponse = gson.fromJson(json, GetMessageListResponse.class);
		return getGoodsListResponse;
	}

	@Override
	public String getApiFun() {
		// TODO Auto-generated method stub
		return "/user/getMsgList";
	}

	@Override
	public String getReqMethod() {
		// TODO Auto-generated method stub
		return "post";
	}
}
