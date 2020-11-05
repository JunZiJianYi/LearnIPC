package com.eca.navigation.mvp.presenter;

import com.eca.common.base.mvp.BasePresenter;

import com.eca.navigation.mvp.contract.WelcomeContract;
import com.eca.navigation.mvp.model.WelcomeModel;




public class WelcomePresenter extends BasePresenter<WelcomeContract.Model,WelcomeContract.View> implements WelcomeContract.Presenter{

    @Override
    protected WelcomeContract.Model createModule() {
        return new WelcomeModel();
    }

    @Override
    public void start() {

    }


}
