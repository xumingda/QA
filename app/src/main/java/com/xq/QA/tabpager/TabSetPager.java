package com.xq.QA.tabpager;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;


import com.lidroid.xutils.ViewUtils;
import com.xq.QA.R;
import com.xq.QA.activity.CcfaxWebActivity;
import com.xq.QA.base.TabBasePager;
import com.xq.QA.util.Constants;
import com.xq.QA.util.UIUtils;
import com.xq.QA.weiget.MyLinearLayout;

/**
 * @作者: 许明达
 * @创建时间: 2016年3月23日上午11:10:20
 * @版权: 特速版权所有
 * @描述: TODO
 */
public class TabSetPager extends TabBasePager implements View.OnClickListener {


    RelativeLayout view;
    LayoutInflater mInflater;
    private FrameLayout mDragLayout;
    private MyLinearLayout mLinearLayout;
    private RelativeLayout rl_member,rl_rank;
    private RelativeLayout rl_information,rl_burse,rl_team,rl_discount,rl_merchant,rl_issued,rl_tpl,rl_client,rl_question;
    /**
     * @param context
     */
    public TabSetPager(Context context, FrameLayout mDragLayout,
                       MyLinearLayout mLinearLayout) {
        super(context, mDragLayout);
        this.mDragLayout = mDragLayout;
        this.mLinearLayout = mLinearLayout;

    }


    @Override
    protected View initView() {
        view = (RelativeLayout) View.inflate(mContext, R.layout.my_pager, null);
        ViewUtils.inject(this, view);
        if (mInflater == null) {
            mInflater = (LayoutInflater) UIUtils.getContext().getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
        }
        return view;
    }

    public void initData() {
        rl_rank=(RelativeLayout)view.findViewById(R.id.rl_rank);
        rl_member=(RelativeLayout)view.findViewById(R.id.rl_member);
        rl_information=(RelativeLayout)view.findViewById(R.id.rl_information);
        rl_burse=(RelativeLayout)view.findViewById(R.id.rl_burse);
        rl_team=(RelativeLayout)view.findViewById(R.id.rl_team);
        rl_discount=(RelativeLayout)view.findViewById(R.id.rl_discount);
        rl_merchant=(RelativeLayout)view.findViewById(R.id.rl_merchant);
        rl_issued=(RelativeLayout)view.findViewById(R.id.rl_issued);
        rl_tpl=(RelativeLayout)view.findViewById(R.id.rl_tpl);
        rl_client=(RelativeLayout)view.findViewById(R.id.rl_client);
        rl_question=(RelativeLayout)view.findViewById(R.id.rl_question);
        rl_member.setOnClickListener(this);
        rl_rank.setOnClickListener(this);
        rl_information.setOnClickListener(this);
        rl_burse.setOnClickListener(this);
        rl_team.setOnClickListener(this);
        rl_discount.setOnClickListener(this);
        rl_merchant.setOnClickListener(this);
        rl_issued.setOnClickListener(this);
        rl_tpl.setOnClickListener(this);
        rl_client.setOnClickListener(this);
        rl_question.setOnClickListener(this);
    }








    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_member:{
                Intent intent=new Intent(mContext, CcfaxWebActivity.class);
                intent.putExtra("url", Constants.SERVER_URL+"member.html");
                UIUtils.startActivityNextAnim(intent);
                break;
            }
            case R.id.rl_rank:{
                Intent intent=new Intent(mContext, CcfaxWebActivity.class);
                intent.putExtra("url", Constants.SERVER_URL+"rank.html");
                UIUtils.startActivityNextAnim(intent);
                break;
            }
            case R.id.rl_information:{
                Intent intent=new Intent(mContext, CcfaxWebActivity.class);
                intent.putExtra("url", Constants.SERVER_URL+"information.html");
                UIUtils.startActivityNextAnim(intent);
                break;
            }
            case R.id.rl_burse:{
                Intent intent=new Intent(mContext, CcfaxWebActivity.class);
                intent.putExtra("url", Constants.SERVER_URL+"burse.html");
                UIUtils.startActivityNextAnim(intent);
                break;
            }
            case R.id.rl_team:{
                Intent intent=new Intent(mContext, CcfaxWebActivity.class);
                intent.putExtra("url", Constants.SERVER_URL+"team.html");
                UIUtils.startActivityNextAnim(intent);
                break;
            }
            case R.id.rl_discount:{
                Intent intent=new Intent(mContext, CcfaxWebActivity.class);
                intent.putExtra("url", Constants.SERVER_URL+"discount.html");
                UIUtils.startActivityNextAnim(intent);
                break;
            }
            case R.id.rl_merchant:{
                Intent intent=new Intent(mContext, CcfaxWebActivity.class);
                intent.putExtra("url", Constants.SERVER_URL+"merchant.html");
                UIUtils.startActivityNextAnim(intent);
                break;
            }
            case R.id.rl_issued:{
                Intent intent=new Intent(mContext, CcfaxWebActivity.class);
                intent.putExtra("url", Constants.SERVER_URL+"issued.html");
                UIUtils.startActivityNextAnim(intent);
                break;
            }
            case R.id.rl_question:{
                Intent intent=new Intent(mContext, CcfaxWebActivity.class);
                intent.putExtra("url", Constants.SERVER_URL+"question.html");
                UIUtils.startActivityNextAnim(intent);
                break;
            }
            case R.id.rl_client:{
                Intent intent=new Intent(mContext, CcfaxWebActivity.class);
                intent.putExtra("url", Constants.SERVER_URL+"client.html");
                UIUtils.startActivityNextAnim(intent);
                break;
            }
            case R.id.rl_tpl:{
                Intent intent=new Intent(mContext, CcfaxWebActivity.class);
                intent.putExtra("url", Constants.SERVER_URL+"tpl.html");
                UIUtils.startActivityNextAnim(intent);
                break;
            }
        }
    }




}
