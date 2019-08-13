package com.xq.QA.util;



public class LoginUtils {


	public static boolean isLogin() {
		if (SharedPrefrenceUtils.getBoolean(UIUtils.getContext(), "isLogin")) {
			return SharedPrefrenceUtils.getBoolean(UIUtils.getContext(),
					"isLogin");
		}
		return false;
	}

}
