package com.bwie.administrator.liuxs_20190120.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bwie.administrator.liuxs_20190120.R;
import com.bwie.administrator.liuxs_20190120.ui.fragment.Fragment_my;
import com.bwie.administrator.liuxs_20190120.ui.fragment.Fragment_shopping;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.rad1)
    RadioButton rad1;
    @BindView(R.id.rad2)
    RadioButton rad2;
    @BindView(R.id.rads)
    RadioGroup rads;
    @BindView(R.id.frame)
    FrameLayout frame;
    private Fragment_shopping fragment_shopping;
    private Fragment_my fragment_my;
    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        rads.check(rads.getChildAt(0).getId());

        manager = getSupportFragmentManager();
        final FragmentTransaction transaction = manager.beginTransaction();

        fragment_shopping = new Fragment_shopping();
        fragment_my = new Fragment_my();

        transaction.add(R.id.frame, fragment_my);
        transaction.add(R.id.frame, fragment_shopping);

        transaction.hide(fragment_my).show(fragment_shopping);
        transaction.commit();

        rads.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentTransaction transaction1 = manager.beginTransaction();
                switch (checkedId) {
                    case R.id.rad1:
                        transaction1.hide(fragment_my).show(fragment_shopping);
                        break;
                    case R.id.rad2:
                        transaction1.show(fragment_my).hide(fragment_shopping);
                        break;
                }
                transaction1.commit();
            }
        });
    }





}
