package com.bwie.administrator.liuxs_20190120.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bwie.administrator.liuxs_20190120.R;
import com.bwie.administrator.liuxs_20190120.data.bean.ShoppingCarBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class GoodsAdapter extends BaseQuickAdapter<ShoppingCarBean.DataBean.ListBean, BaseViewHolder> {
    OnGoodsItemClickLisenter onGoodsItemClickLisenter;

    public void setOnGoodsItemClickLisenter(OnGoodsItemClickLisenter onGoodsItemClickLisenter) {
        this.onGoodsItemClickLisenter = onGoodsItemClickLisenter;
    }

    public interface OnGoodsItemClickLisenter {
        public void onCallBack();
    }

    public GoodsAdapter(int layoutResId, @Nullable List<ShoppingCarBean.DataBean.ListBean> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, final ShoppingCarBean.DataBean.ListBean item) {
        helper.setText(R.id.tv_goodsPrice, "￥：" + item.getPrice());
        helper.setText(R.id.tv_goodsTitle, item.getTitle());
        ImageView iv_goodsIcon = helper.getView(R.id.iv_goodsIcon);
        String imagesString = item.getImages();
        String[] imagesStr = imagesString.split("\\|");
        Glide.with(mContext).load(imagesStr[0]).into(iv_goodsIcon);
        final CheckBox cb_goods = helper.getView(R.id.cb_goods);
        cb_goods.setOnCheckedChangeListener(null);
        cb_goods.setChecked(item.getGoodsChecked());
        cb_goods.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                item.setGoodsChecked(isChecked);
                onGoodsItemClickLisenter.onCallBack();
            }
        });

    }
}
