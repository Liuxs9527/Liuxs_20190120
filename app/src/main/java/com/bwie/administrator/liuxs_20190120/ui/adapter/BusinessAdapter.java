package com.bwie.administrator.liuxs_20190120.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;

import com.bwie.administrator.liuxs_20190120.R;
import com.bwie.administrator.liuxs_20190120.data.bean.ShoppingCarBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class BusinessAdapter extends BaseQuickAdapter<ShoppingCarBean.DataBean, BaseViewHolder> {
    OnBusinessItemClickLisenter onBusinessItemClickLisenter;

    public interface OnBusinessItemClickLisenter {
        public void onCallBack();
    }

    public void setOnBusinessItemClickLisenter(OnBusinessItemClickLisenter onBusinessItemClickLisenter) {
        this.onBusinessItemClickLisenter = onBusinessItemClickLisenter;
    }

    public BusinessAdapter(int layoutResId, @Nullable List<ShoppingCarBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final ShoppingCarBean.DataBean item) {

        helper.setText(R.id.tv_business_name, item.getSellerName());
        RecyclerView rv_goods = helper.getView(R.id.rv_goods);
        final CheckBox cb_business = helper.getView(R.id.cb_business);
        cb_business.setOnCheckedChangeListener(null);

        cb_business.setChecked(item.getBusinessChecked());
        List<ShoppingCarBean.DataBean.ListBean> goodsList = item.getList();
        LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);

        final GoodsAdapter goodsAdapter = new GoodsAdapter(R.layout.recycler_goods_item, goodsList);
        rv_goods.setLayoutManager(manager);
        rv_goods.setAdapter(goodsAdapter);

        goodsAdapter.setOnGoodsItemClickLisenter(new GoodsAdapter.OnGoodsItemClickLisenter() {
            @Override
            public void onCallBack() {
                boolean result = true;
                for (int i = 0; i < item.getList().size(); i++) {
                    result = result & item.getList().get(i).getGoodsChecked();
                }
                cb_business.setChecked(result);
                goodsAdapter.notifyDataSetChanged();
                onBusinessItemClickLisenter.onCallBack();
            }
        });
        cb_business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < item.getList().size(); i++) {
                    item.getList().get(i).setGoodsChecked(cb_business.isChecked());
                }
                item.setBusinessChecked(cb_business.isChecked());
                notifyDataSetChanged();
                onBusinessItemClickLisenter.onCallBack();
            }
        });


    }
}
