package com.xq.QA.activity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.webkit.CookieManager;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.xq.QA.R;
import com.xq.QA.base.BaseActivity;
import com.xq.QA.base.MyVolley;
import com.xq.QA.util.LogUtils;
import com.xq.QA.util.SharedPrefrenceUtils;
import com.xq.QA.util.UIUtils;
import com.xq.QA.weiget.MyGifView;
import com.xq.QA.weiget.ScrollWebView;

import java.util.HashMap;


public class CcfaxWebActivity extends BaseActivity {
    private View rootView;
    private ScrollWebView webView;
    private String url;
    private boolean is_error;
    private MyGifView iv_loading;
    private String cookies;
    private String CSS_STYPE = "<head><style>img{max-width:340px !important;}</style></head>";
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected View initView() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        rootView = View.inflate(this, R.layout.activity_ccfax_web, null);
        setContentView(rootView);
        iv_loading = (MyGifView) rootView.findViewById(R.id.iv_loading);
        webView = (ScrollWebView) rootView.findViewById(R.id.webview);
        initData();
        return null;
    }

    /**
     * 初始化数据
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @SuppressLint("JavascriptInterface")
    private void initData() {
        iv_loading.setMovieResource(R.raw.loading);
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
//        if (type == 0) {
//            rl_head.setVisibility(View.GONE);
//            webView.addJavascriptInterface(this, "myObj");
//        }
//        else {
            //在js中调用本地java方法
//            webView.addJavascriptInterface(new JsInterface(this), "JsInterface");
//        }

        //支持javascript
        webView.getSettings().setJavaScriptEnabled(true);
        // 设置可以支持缩放
        webView.getSettings().setSupportZoom(true);
        // 设置出现缩放工具
        webView.getSettings().setBuiltInZoomControls(true);
        //扩大比例的缩放
        webView.getSettings().setUseWideViewPort(true);
        //不显示webview缩放按钮
        webView.getSettings().setDisplayZoomControls(false);
        //自适应屏幕
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.getSettings().setLoadWithOverviewMode(true);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                if (url.contains("javacript:void(0)")) {
                    LogUtils.e("调用false");
                } else {
                    LogUtils.e("调用true" + url);
                    view.loadUrl(url);
                }
                return true;
            }

            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                //这里进行无网络或错误处理，具体可以根据errorCode的值进行判断，做跟详细的处理。
//                view.loadUrl(file:///android_asset/error.html );
                LogUtils.e("网页错误");
            }

            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);

                LogUtils.e("网页开始");
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (!is_error) {
                    webView.setVisibility(View.VISIBLE);
                    iv_loading.setVisibility(View.INVISIBLE);
                }
                //获取cookies
                CookieManager cm = CookieManager.getInstance();
                cookies = cm.getCookie(url);
                if (!TextUtils.isEmpty(cookies)) {
                    SharedPrefrenceUtils.setString(CcfaxWebActivity.this, "session_id", cookies.substring(10));
                    LogUtils.e("   cookies;" + cookies + "   id:" + cookies.substring(10));
                }

                String toVueSting = "vickylizy";
                webView.loadUrl("javascript:getDataFromNative('"+toVueSting+"')");
                LogUtils.e("网页结束");
            }

        });

        //这里加载自己部署的（也可加载本地资源）
        if (SharedPrefrenceUtils.getBoolean(CcfaxWebActivity.this, "isLogin")) {
            LogUtils.e("调用:" + url + "?token=" + SharedPrefrenceUtils.getString(CcfaxWebActivity.this, "token"));
            webView.loadUrl(url + "?token=" + SharedPrefrenceUtils.getString(CcfaxWebActivity.this, "token"));
        } else {
            webView.loadUrl(url);
            LogUtils.e("调用没登陆:" + url + "?token=" + SharedPrefrenceUtils.getString(CcfaxWebActivity.this, "token"));
        }

        webView.setOnScrollChangeListener(new ScrollWebView.OnScrollChangeListener() {

            @Override
            public void onScrollChanged(int l, int t, int oldl, int oldt) {
                //滑动中
//                rl_head.setVisibility(View.GONE);
            }

            @Override
            public void onPageTop(int l, int t, int oldl, int oldt) {
                //滑动到顶部
//                rl_head.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageEnd(int l, int t, int oldl, int oldt) {
                //滑动到底部
                LogUtils.e("底部");
            }
        });

        //Vue调用Android方法
        webView.addJavascriptInterface(this,"$App");//添加js监听 这样html就能调用客户端





    }
    @JavascriptInterface
    public void getDataFormVue(String msg) {
        //做原生操作
        LogUtils.e("调用到"+msg);
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    private class JsInterface {
        private Context mContext;

        public JsInterface(Context context) {
            this.mContext = context;
        }

        //在js中调用window.WebView.jump_login()，便会触发此方法。
        @JavascriptInterface
        public void jump_app(int type) {
            LogUtils.e("调用" + type);
//            if (type == 1) {
//                Intent intent = new Intent(CcfaxWebActivity.this, LoginActivity.class);
//                intent.putExtra("type", 200);
//                UIUtils.startActivityForResultNextAnim(intent, 1);
//            } else if (type == 2) {
//                if (SharedPrefrenceUtils.getBoolean(CcfaxWebActivity.this, "isLogin")) {
//                    UIUtils.showToastCenter(CcfaxWebActivity.this, "您已注册过！");
//                } else {
//                    Intent intent = new Intent(CcfaxWebActivity.this, RegisterActivity.class);
//                    UIUtils.startActivityNextAnim(intent);
//                }
//            } else if (type == 4) {
//                Intent intent = getIntent();
//                setResult(200, intent);
//                CcfaxWebActivity.this.finish();
//                CcfaxWebActivity.this.overridePendingTransition(R.anim.animprv_in, R.anim.animprv_out);
//            } else {
//                if (SharedPrefrenceUtils.getBoolean(CcfaxWebActivity.this, "isLogin")) {
//                    if (Integer.parseInt(SharedPrefrenceUtils.getString(CcfaxWebActivity.this, "is_open_account")) == 1) {
//                        UIUtils.showToastCenter(CcfaxWebActivity.this, "您已认证过！");
//                    } else {
//                        Intent intent = new Intent(CcfaxWebActivity.this, RealNameActivity.class);
//                        UIUtils.startActivityNextAnim(intent);
//                    }
//                } else {
//                    Intent intent = new Intent(CcfaxWebActivity.this, LoginActivity.class);
//                    intent.putExtra("type", 200);
//                    UIUtils.startActivityForResultNextAnim(intent, 1);
//                }
//            }
        }
        public void jump_main(String token){
            LogUtils.e("调用" + token);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack();// 返回前一个页面
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }





}
