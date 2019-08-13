package com.xq.QA.fragment;

import android.Manifest;
import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;


import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.xq.QA.R;
import com.xq.QA.base.BaseFragment;
import com.xq.QA.base.TabBasePager;
import com.xq.QA.tabpager.TabHomePager;
import com.xq.QA.tabpager.TabOrderPager;
import com.xq.QA.tabpager.TabSetPager;
import com.xq.QA.util.UIUtils;
import com.xq.QA.weiget.MyLinearLayout;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @作者: 许明达
 * @创建时间: 2016年5月25日上午10:14:18
 * @描述: 控制侧滑菜单以及 附近 找店 发现 我的四个页面
 */
public class ControlTabFragment extends BaseFragment implements
        OnCheckedChangeListener {

    @ViewInject(R.id.rg_content)
    private RadioGroup mRadioGroup;

    @ViewInject(R.id.rb_content_home)
    private RadioButton rb_content_home;
    @ViewInject(R.id.rb_content_order)
    private RadioButton rb_content_order;
    @ViewInject(R.id.rb_content_set)
    private RadioButton rb_content_set;

    // 内容区域
    @ViewInject(R.id.fl_content_fragment)
    private FrameLayout mFrameLayout;
    // 底部区域
    @ViewInject(R.id.fl_bottom)
    private FrameLayout bFrameLayout;
    @ViewInject(R.id.dl)
    private FrameLayout mDragLayout;

    // 处理事件分发的自定义LinearLayout
    @ViewInject(R.id.my_ll)
    private MyLinearLayout mLinearLayout;

    // 默认选中第0页面
    public static int mCurrentIndex = 0;
    // 中间变量
    public static int temp = 0;
    // 底部页面的集合
    private List<TabBasePager> mPagerList;
    private TabHomePager tabHomePager;
    private TabOrderPager tabOrderPager;
    private TabSetPager tabSetPager;
    private LayoutInflater mInflater;

    private PopupWindow pWindow;
    private View root;
    @Override
    protected View initView() {
        View view = View.inflate(mActivity, R.layout.control_tab, null);
        // Viewutil工具的注入
        ViewUtils.inject(this, view);
        return view;
    }

    @Override
    protected void initData() {


        // 添加实际的页面
        mPagerList = new ArrayList<TabBasePager>();
        tabHomePager = new TabHomePager(mActivity, mDragLayout,
                mLinearLayout);
        mPagerList.add(tabHomePager);
        tabOrderPager = new TabOrderPager(mActivity, mDragLayout,
                mLinearLayout);
        mPagerList.add(tabOrderPager);
        // 我的
        tabSetPager = new TabSetPager(mActivity, mDragLayout,
                mLinearLayout);
        mPagerList.add(tabSetPager);
        // 给RadioGroup 设置监听
        getmRadioGroup().setOnCheckedChangeListener(this);

        switchCurrentPage();
        //定义底部标签图片大小和位置
        Drawable drawable_news = getResources().getDrawable(R.drawable.tab_home);
        //当这个图片被绘制时，给他绑定一个矩形 ltrb规定这个矩形
        drawable_news.setBounds(0, 0, 50, 50);
        //设置图片在文字的哪个方向
        rb_content_home.setCompoundDrawables(null, drawable_news, null, null);

        //定义底部标签图片大小和位置
        Drawable drawable_live = getResources().getDrawable(R.drawable.tab_order_bg);
        //当这个图片被绘制时，给他绑定一个矩形 ltrb规定这个矩形
        drawable_live.setBounds(0, 0, 50, 50);
        //设置图片在文字的哪个方向
        rb_content_order.setCompoundDrawables(null, drawable_live, null, null);

        //定义底部标签图片大小和位置
        Drawable drawable_tuijian = getResources().getDrawable(R.drawable.tab_set_bg);
        //当这个图片被绘制时，给他绑定一个矩形 ltrb规定这个矩形
        drawable_tuijian.setBounds(0, 0, 50, 50);
        //设置图片在文字的哪个方向
        rb_content_set.setCompoundDrawables(null, drawable_tuijian, null, null);

        if (mInflater == null) {
            mInflater = (LayoutInflater) UIUtils.getContext().getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
        }
        root = mInflater.inflate(R.layout.alert_dialog, null);
        pWindow = new PopupWindow(root, ActionBar.LayoutParams.FILL_PARENT,
                ActionBar.LayoutParams.FILL_PARENT);
        root.findViewById(R.id.btn_people).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pWindow.dismiss();

            }
        });
        root.findViewById(R.id.btn_business)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        pWindow.dismiss();

                    }
                });
        root.findViewById(R.id.bg_photo).getBackground().setAlpha(100);
    }



    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        // 根据选中的RadioButton的id切换页面
        switch (checkedId) {
            case R.id.rb_content_home:

                mCurrentIndex = 0;
                break;
            case R.id.rb_content_order:
                mCurrentIndex = 1;
//                pWindow.setAnimationStyle(R.style.AnimBottom);
//                pWindow.showAtLocation(mDragLayout,
//                        Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                break;
            case R.id.rb_content_set:
                mCurrentIndex = 2;
                break;
            default:
                break;
        }
        switchCurrentPage();
    }

    /**
     * 切换RadioGroup对应的页面
     */
    public void switchCurrentPage() {
        mFrameLayout.removeAllViews();

        TabBasePager tabBasePager = mPagerList.get(mCurrentIndex);
        // 获得每个页面对应的布局
        View rootView = tabBasePager.getRootView();
        tabBasePager.initData();
        mFrameLayout.addView(rootView);
    }

    public RadioGroup getmRadioGroup() {
        return mRadioGroup;
    }


    public TabHomePager getTabNearByPager() {
        return tabHomePager;
    }



    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

}
