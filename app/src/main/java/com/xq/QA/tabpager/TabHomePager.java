package com.xq.QA.tabpager;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lidroid.xutils.ViewUtils;
import com.superluo.textbannerlibrary.TextBannerView;
import com.xq.QA.R;
import com.xq.QA.activity.CcfaxWebActivity;
import com.xq.QA.base.MyVolley;
import com.xq.QA.base.TabBasePager;
import com.xq.QA.protocol.GetMsgListProtocol;
import com.xq.QA.repose.GetMessageListResponse;
import com.xq.QA.util.Constants;
import com.xq.QA.util.DialogUtils;
import com.xq.QA.util.LogUtils;
import com.xq.QA.util.UIUtils;
import com.xq.QA.weiget.MyLinearLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.xq.QA.util.UIUtils.getResources;

/**
 * @作者: 许明达
 * @创建时间: 2016年3月23日上午11:10:20
 * @版权: 特速版权所有
 * @描述: TODO
 */
public class TabHomePager extends TabBasePager implements View.OnClickListener {


    RelativeLayout view;
    LayoutInflater mInflater;
    private FrameLayout mDragLayout;
    private MyLinearLayout mLinearLayout;
    private Dialog loadingDialog;
    private String url;
    private Gson gson;
    private LinearLayout ll_merchant,ll_money;
    private Button btn_submit;
    private RelativeLayout rl_tongji,rl_kefu;
    /**
     * @param context
     */
    public TabHomePager(Context context, FrameLayout mDragLayout,
                        MyLinearLayout mLinearLayout) {
        super(context, mDragLayout);
        this.mDragLayout = mDragLayout;
        this.mLinearLayout = mLinearLayout;

    }


    @Override
    protected View initView() {
        view = (RelativeLayout) View.inflate(mContext, R.layout.home_pager, null);
        ViewUtils.inject(this, view);
        if (mInflater == null) {
            mInflater = (LayoutInflater) UIUtils.getContext().getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
        }
        return view;
    }

    public void initData() {
        ll_money=(LinearLayout)view.findViewById(R.id.ll_money);
        ll_merchant=(LinearLayout)view.findViewById(R.id.ll_merchant);
        btn_submit=(Button) view.findViewById(R.id.btn_submit);
        rl_kefu=(RelativeLayout) view.findViewById(R.id.rl_kefu);
        rl_tongji=(RelativeLayout) view.findViewById(R.id.rl_tongji);
        gson = new GsonBuilder()
                .setPrettyPrinting()
                .disableHtmlEscaping()
                .create();
        loadingDialog = DialogUtils.createLoadDialog(mContext, true);
        //初始化TextBannerView
        TextBannerView tvBanner = (TextBannerView) view.findViewById(R.id.tv_banner);

        //设置数据
        List<String> list = new ArrayList<>();

        list.add("学好Java、Android、C#、C、ios、html+css+js");
        list.add("走遍天下都不怕！！！！！");
        list.add("不是我吹，就怕你做不到，哈哈");
        list.add("superluo");
        list.add("你是最棒的，奔跑吧孩子！");

        tvBanner.setDatas(list);

//        Drawable drawable = getResources().getDrawable(R.mipmap.ic_launcher);
//        /**这里可以设置带图标的数据（1.0.2新方法），比setDatas方法多了带图标参数；
//         第一个参数：数据 。
//         第二参数：drawable.
//         第三参数:drawable尺寸。
//         第四参数:图标位置仅支持Gravity.LEFT、Gravity.TOP、Gravity.RIGHT、Gravity.BOTTOM
//         */
//        tvBanner.setDatasWithDrawableIcon(list,drawable,18, Gravity.LEFT);
        ll_merchant.setOnClickListener(this);
        ll_money.setOnClickListener(this);
        btn_submit.setOnClickListener(this);
        rl_kefu.setOnClickListener(this);
        rl_tongji.setOnClickListener(this);
    }



    private void getMsgList() {
        loadingDialog.show();
        GetMsgListProtocol getMsgListProtocol=new GetMsgListProtocol();
        url = getMsgListProtocol.getApiFun();
        Map<String, String> params = new HashMap<String, String>();
        MyVolley.uploadNoFile(MyVolley.POST, url, params, new MyVolley.VolleyCallback() {

            @Override
            public void dealWithJson(String address, String json) {

                loadingDialog.dismiss();
                GetMessageListResponse  getMessageListResponse = gson.fromJson(json, GetMessageListResponse.class);
                LogUtils.e("getMessageListResponse:" + getMessageListResponse.toString());
                if (getMessageListResponse.getCode() == 0) {


                } else {
                    DialogUtils.showAlertDialog(mContext, getMessageListResponse.getMsg());
                }

            }

            @Override
            public void dealWithError(String address, String error) {
                loadingDialog.dismiss();
                DialogUtils.showAlertDialog(mContext, error);

            }

            @Override
            public void dealTokenOverdue() {

            }
        });
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_tongji:{
                Intent intent=new Intent(mContext, CcfaxWebActivity.class);
                intent.putExtra("url", Constants.SERVER_URL+"rank.html");
                UIUtils.startActivityNextAnim(intent);
                break;
            }
            case R.id.rl_kefu:{
                Intent intent=new Intent(mContext, CcfaxWebActivity.class);
                intent.putExtra("url", Constants.SERVER_URL+"rule.html");
                UIUtils.startActivityNextAnim(intent);
                break;
            }
            case R.id.btn_submit:{
                Intent intent=new Intent(mContext, CcfaxWebActivity.class);
                intent.putExtra("url", Constants.SERVER_URL+"answer.html");
                UIUtils.startActivityNextAnim(intent);
                break;
            }
            case R.id.ll_merchant:{
                Intent intent=new Intent(mContext, CcfaxWebActivity.class);
                intent.putExtra("url", Constants.SERVER_URL+"burse.html");
                UIUtils.startActivityNextAnim(intent);
                break;
            }
            case R.id.ll_money:{
                Intent intent=new Intent(mContext, CcfaxWebActivity.class);
                intent.putExtra("url", Constants.SERVER_URL+"account.html");
                UIUtils.startActivityNextAnim(intent);
                break;
            }
        }
    }




}
