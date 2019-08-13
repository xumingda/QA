package com.xq.QA.activity;

import android.app.Dialog;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.xq.QA.R;
import com.xq.QA.base.BaseActivity;
import com.xq.QA.base.MyVolley;
import com.xq.QA.fragment.ControlTabFragment;
import com.xq.QA.protocol.LoginProtocol;
import com.xq.QA.repose.LoginResponse;
import com.xq.QA.util.DialogUtils;
import com.xq.QA.util.LogUtils;
import com.xq.QA.util.SharedPrefrenceUtils;

import java.util.HashMap;
import java.util.Map;

public class WeixinLoginActivity extends BaseActivity {

    // 微信登录
    private static IWXAPI WXapi;
    private String WX_APP_ID = "wx4924485b2d780ead";


    @Override
    protected View initView() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        View rootView = View.inflate(this, R.layout.activity_main, null);
        setContentView(rootView);



        WXapi = WXAPIFactory.createWXAPI(this, WX_APP_ID, true);
        WXapi.registerApp(WX_APP_ID);
        WXLogin();
        return rootView;
    }



    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 监听返回键
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            onKeyDownBack();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    /**
     * 登录微信
     */
    private void WXLogin() {

        if ( WXapi== null) {
            WXapi = WXAPIFactory.createWXAPI(this, WX_APP_ID, true);
        }
        if (!WXapi.isWXAppInstalled()) {
            Toast.makeText(this,"您手机尚未安装微信，请安装后再登录",Toast.LENGTH_LONG).show();
            return;
        }
        WXapi.registerApp(WX_APP_ID);
        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "wechat_sdk_xb_live_state";//官方说明：用于保持请求和回调的状态，授权请求后原样带回给第三方。该参数可用于防止csrf攻击（跨站请求伪造攻击），建议第三方带上该参数，可设置为简单的随机数加session进行校验
        WXapi.sendReq(req);

    }

}