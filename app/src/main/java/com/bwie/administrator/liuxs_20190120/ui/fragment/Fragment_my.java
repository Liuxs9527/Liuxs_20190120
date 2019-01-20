package com.bwie.administrator.liuxs_20190120.ui.fragment;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bwie.administrator.liuxs_20190120.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class Fragment_my extends Fragment {

    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.btn_donghua)
    Button btnDonghua;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_my, container, false);

        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btn_donghua)
    public void onViewClicked() {

        //属性动画，沿Y轴旋转和放大两倍效果
        ObjectAnimator animator = ObjectAnimator.ofFloat(image,"scaleX",1,2);
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(image,"scaleY",1,2);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(image,"rotationY",0,180);
        AnimatorSet set = new AnimatorSet();
        set.play(animator).with(animator1).with(animator2);
        set.setDuration(3000);
        set.start();

    }
}
