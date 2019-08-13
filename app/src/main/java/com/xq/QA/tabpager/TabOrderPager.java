package com.xq.QA.tabpager;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.lidroid.xutils.ViewUtils;
import com.xq.QA.R;
import com.xq.QA.activity.CcfaxWebActivity;
import com.xq.QA.activity.WebContentActivity;
import com.xq.QA.base.TabBasePager;
import com.xq.QA.util.Constants;
import com.xq.QA.util.UIUtils;
import com.xq.QA.weiget.MyLinearLayout;

/**
 * @作者: 许明达
 * @创建时间: 2016年3月23日上午11:10:20
 * @描述: TODO
 */
public class TabOrderPager extends TabBasePager implements View.OnClickListener {


    LinearLayout view;
    LayoutInflater mInflater;
    private FrameLayout mDragLayout;
    private MyLinearLayout mLinearLayout;
    private RelativeLayout rl_buesssion,rl_person;
    private ImageView iv_person,iv_buesssion;
    private Button btn_send;
    private TextView tv_buesssion,tv_person;
    //fasle选择个人，true选商业
    private boolean type;


    /**
     * @param context
     */
    public TabOrderPager(Context context, FrameLayout mDragLayout,
                         MyLinearLayout mLinearLayout) {
        super(context, mDragLayout);
        this.mDragLayout = mDragLayout;
        this.mLinearLayout = mLinearLayout;

    }


    @Override
    protected View initView() {
        view = (LinearLayout) View.inflate(mContext, R.layout.send_pager, null);
        ViewUtils.inject(this, view);
        if (mInflater == null) {
            mInflater = (LayoutInflater) UIUtils.getContext().getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
        }

        return view;
    }

    public void initData() {
        rl_person=(RelativeLayout)view.findViewById(R.id.rl_person);
        rl_buesssion=(RelativeLayout)view.findViewById(R.id.rl_buesssion);
        iv_person=(ImageView) view.findViewById(R.id.iv_person);
        iv_buesssion=(ImageView)view.findViewById(R.id.iv_buesssion);
        btn_send=(Button) view.findViewById(R.id.btn_send);
        tv_person=(TextView)view.findViewById(R.id.tv_person);
        tv_buesssion=(TextView)view.findViewById(R.id.tv_buesssion);
        rl_person.setOnClickListener(this);
        rl_buesssion.setOnClickListener(this);
        btn_send.setOnClickListener(this);
    }








    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send:{
                Intent intent=new Intent(mContext, CcfaxWebActivity.class);
                if(type){
                    intent.putExtra("url", Constants.SERVER_URL+"business.html");
                }else{
                    intent.putExtra("url", Constants.SERVER_URL+"personage.html");
                }
                UIUtils.startActivityNextAnim(intent);

                break;
            }
            case R.id.rl_person:{
                if(type){
                    rl_person.setBackground(UIUtils.getDrawable(R.drawable.shape_normal));
                    rl_buesssion.setBackground(UIUtils.getDrawable(R.drawable.shape_select));
                    tv_buesssion.setTextColor(UIUtils.getColor(R.color.selectColor));
                    tv_person.setTextColor(UIUtils.getColor(R.color.tab_background));
                    iv_buesssion.setVisibility(View.VISIBLE);
                    iv_person.setVisibility(View.GONE);
                }else{
                    iv_buesssion.setVisibility(View.GONE);
                    iv_person.setVisibility(View.VISIBLE);
                    rl_person.setBackground(UIUtils.getDrawable(R.drawable.shape_select));
                    rl_buesssion.setBackground(UIUtils.getDrawable(R.drawable.shape_normal));
                    tv_buesssion.setTextColor(UIUtils.getColor(R.color.tab_background));
                    tv_person.setTextColor(UIUtils.getColor(R.color.selectColor));
                }
                break;
            }
            case R.id.rl_buesssion:{
                if(type){
                    iv_buesssion.setVisibility(View.GONE);
                    iv_person.setVisibility(View.VISIBLE);
                    rl_person.setBackground(UIUtils.getDrawable(R.drawable.shape_select));
                    rl_buesssion.setBackground(UIUtils.getDrawable(R.drawable.shape_normal));
                    tv_buesssion.setTextColor(UIUtils.getColor(R.color.tab_background));
                    tv_person.setTextColor(UIUtils.getColor(R.color.selectColor));
                }else{
                    iv_buesssion.setVisibility(View.VISIBLE);
                    iv_person.setVisibility(View.GONE);
                    rl_person.setBackground(UIUtils.getDrawable(R.drawable.shape_normal));
                    rl_buesssion.setBackground(UIUtils.getDrawable(R.drawable.shape_select));
                    tv_buesssion.setTextColor(UIUtils.getColor(R.color.selectColor));
                    tv_person.setTextColor(UIUtils.getColor(R.color.tab_background));
                }
                break;
            }
        }
    }




}
