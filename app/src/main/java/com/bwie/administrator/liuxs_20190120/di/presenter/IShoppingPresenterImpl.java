package com.bwie.administrator.liuxs_20190120.di.presenter;

import com.bwie.administrator.liuxs_20190120.di.contrant.ShoppingCartContract;
import com.bwie.administrator.liuxs_20190120.di.model.IShoppingModelImpl;

import java.lang.ref.SoftReference;

public class IShoppingPresenterImpl implements ShoppingCartContract.IPresenter{
    ShoppingCartContract.IView iView;
    private SoftReference<ShoppingCartContract.IView> reference;
    private ShoppingCartContract.IModel model;

    @Override
    public void attachView(ShoppingCartContract.IView iView) {
        this.iView = iView;
        reference = new SoftReference<>(iView);
        model = new IShoppingModelImpl();
    }

    @Override
    public void detachView(ShoppingCartContract.IView iView) {
        reference.clear();
    }

    @Override
    public void requestData() {
        iView.showLoading();
        model.containData(new ShoppingCartContract.IModel.OnCallBackLisenter() {
            @Override
            public void onCallBack(String mCartString) {
                iView.hideLoading();
                iView.showData(mCartString);
            }
        });

    }
}
