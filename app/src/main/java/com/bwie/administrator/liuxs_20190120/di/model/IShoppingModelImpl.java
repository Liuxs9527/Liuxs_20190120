package com.bwie.administrator.liuxs_20190120.di.model;

import com.bwie.administrator.liuxs_20190120.data.Constant;
import com.bwie.administrator.liuxs_20190120.di.contrant.ShoppingCartContract;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class IShoppingModelImpl implements ShoppingCartContract.IModel {

    @Override
    public void containData(final OnCallBackLisenter onCallBackLisenter) {
        OkGo.<String>get(Constant.SHOPPINGCART_URL).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String resposneData = response.body().toString();
                //回传给P层
                onCallBackLisenter.onCallBack(resposneData);
            }
        });
    }
}
