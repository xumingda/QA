package com.xq.QA.activity;

import android.app.Dialog;
import android.content.ComponentName;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
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
import com.xq.QA.protocol.GetMsgListProtocol;
import com.xq.QA.protocol.LoginProtocol;
import com.xq.QA.repose.GetMessageListResponse;
import com.xq.QA.repose.LoginResponse;
import com.xq.QA.util.DialogUtils;
import com.xq.QA.util.LogUtils;
import com.xq.QA.util.SharedPrefrenceUtils;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends BaseActivity {

    private static final int MSG_SET_ALIAS = 1001;
    public static boolean isForeground = false;
    public static ControlTabFragment ctf;
    // 微信登录
    private static IWXAPI WXapi;
    private String WX_APP_ID = "wx4924485b2d780ead";

    private Dialog loadingDialog;
    private String url;
    private Gson gson;
    private String response;
    private Button btn;


    @Override
    protected View initView() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        View rootView = View.inflate(this, R.layout.activity_main, null);
        setContentView(rootView);
//        initFragment();

        gson = new GsonBuilder()
                .setPrettyPrinting()
                .disableHtmlEscaping()
                .create();
        loadingDialog = DialogUtils.createLoadDialog(this, true);
        response=getIntent().getStringExtra("response");
        btn=(Button) rootView.findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                /**知道要跳转应用的包命与目标Activity*/
                ComponentName componentName = new ComponentName("com.example.serialportdemo", "com.example.serialportdemo.MainActivity");
                intent.setComponent(componentName);
                intent.putExtra("xmd", "我发数据来了");//这里Intent传值
                startActivity(intent);
            }
        });

        return rootView;
    }

    private void initFragment() {
        FragmentManager fm = getSupportFragmentManager();
        // 1. 开启事务
        FragmentTransaction transaction = fm.beginTransaction();
        // 添加主页fragment
        ctf = new ControlTabFragment();
        transaction.replace(R.id.main_container, ctf);
        transaction.commit();

    }

    public static ControlTabFragment getCtf() {
        return ctf;
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
        isForeground = true;
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }




}