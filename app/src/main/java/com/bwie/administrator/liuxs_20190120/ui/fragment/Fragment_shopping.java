package com.bwie.administrator.liuxs_20190120.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bwie.administrator.liuxs_20190120.R;
import com.bwie.administrator.liuxs_20190120.data.bean.ShoppingCarBean;
import com.bwie.administrator.liuxs_20190120.di.contrant.ShoppingCartContract;
import com.bwie.administrator.liuxs_20190120.di.presenter.IShoppingPresenterImpl;
import com.bwie.administrator.liuxs_20190120.ui.adapter.BusinessAdapter;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class Fragment_shopping extends Fragment implements ShoppingCartContract.IView,View.OnClickListener {

    @BindView(R.id.cb_all)
    CheckBox cbAll;
    Unbinder unbinder;
    @BindView(R.id.rv_business)
    RecyclerView rvBusiness;
    @BindView(R.id.srl_container)
    SmartRefreshLayout srlContainer;
    @BindView(R.id.btn_price)
    Button btnPrice;
    @BindView(R.id.tv_count)
    TextView tvCount;
    private ShoppingCartContract.IPresenter presenter;
    private Context mContext;
    private List<ShoppingCarBean.DataBean> businessList;
    private BusinessAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_shopping, container, false);

        unbinder = ButterKnife.bind(this, view);

        mContext = getActivity();
        presenter = new IShoppingPresenterImpl();
        presenter.attachView(this);
        presenter.requestData();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showData(String mCartString) {
        cbAll.setOnCheckedChangeListener(null);
        ShoppingCarBean cartBean = new Gson().fromJson(mCartString, ShoppingCarBean.class);
        businessList = cartBean.getData();
        LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        rvBusiness.setLayoutManager(manager);
        adapter = new BusinessAdapter(R.layout.recycler_business_item, businessList);
        rvBusiness.setAdapter(adapter);
        adapter.setOnBusinessItemClickLisenter(new BusinessAdapter.OnBusinessItemClickLisenter() {
            @Override
            public void onCallBack() {
                boolean result = true;
                for (int i = 0; i < businessList.size(); i++) {
                    boolean businessChecked = businessList.get(i).getBusinessChecked();
                    result = result & businessChecked;
                    for (int j = 0; j < businessList.get(i).getList().size(); j++) {
                        boolean goodsChecked = businessList.get(i).getList().get(j).getGoodsChecked();
                        result = result & goodsChecked;
                    }
                }
                cbAll.setChecked(result);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detachView(this);
    }

    @Override
    public void onClick(View v) {
        for (int i = 0; i < businessList.size(); i++) {
            businessList.get(i).setBusinessChecked(cbAll.isChecked());
            for (int j = 0; j < businessList.get(i).getList().size(); j++) {
                businessList.get(i).getList().get(j).setGoodsChecked(cbAll.isChecked());
            }
        }
        adapter.notifyDataSetChanged();
    }
}
