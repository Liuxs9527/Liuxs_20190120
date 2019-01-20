package com.bwie.administrator.liuxs_20190120.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.bwie.administrator.liuxs_20190120.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ZhuceActivity extends AppCompatActivity {

    @BindView(R.id.btn_zc1)
    Button btnZc1;
    @BindView(R.id.zc_ed_name)
    EditText zcEdName;
    @BindView(R.id.zc_ed_pwd)
    EditText zcEdPwd;
    @BindView(R.id.zc_ed_pwd2)
    EditText zcEdPwd2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuce);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_zc1)
    public void onViewClicked() {
        Intent intent = new Intent(ZhuceActivity.this,MainActivity.class);
        startActivity(intent);
    }
}
