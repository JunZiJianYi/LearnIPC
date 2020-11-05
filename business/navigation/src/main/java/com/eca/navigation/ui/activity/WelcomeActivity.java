package com.eca.navigation.ui.activity;


import android.view.WindowManager;
import com.eca.common.base.BaseMvpActivity;
import com.eca.navigation.R;
import com.eca.navigation.mvp.contract.WelcomeContract;
import com.eca.navigation.mvp.presenter.WelcomePresenter;



public class WelcomeActivity extends BaseMvpActivity<WelcomePresenter> implements WelcomeContract.View {


    @Override
    protected int getLayoutId() {
        return R.layout.navigation_activity_welcome;
    }

    @Override
    protected void initView() {

        //region 去除系统任务栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //endregion


    }

    @Override
    protected void initData() {



    }

    @Override
    protected void setListener() {

    }

    @Override
    protected WelcomePresenter createPresenter() {
        return new WelcomePresenter();
    }



}
