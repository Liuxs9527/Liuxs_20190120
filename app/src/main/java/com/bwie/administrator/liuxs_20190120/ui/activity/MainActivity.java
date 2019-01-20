package com.bwie.administrator.liuxs_20190120.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.bwie.administrator.liuxs_20190120.R;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_ed_name)
    EditText mainEdName;
    @BindView(R.id.main_ed_pwd)
    EditText mainEdPwd;
    @BindView(R.id.cb_jz)
    CheckBox cbJz;
    @BindView(R.id.main_btn_dl)
    Button mainBtnDl;
    @BindView(R.id.main_btn_zc)
    Button mainBtnZc;
    @BindView(R.id.btn_qq)
    Button btnQq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //Android6.0权限配置
        if (Build.VERSION.SDK_INT >= 23) {
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE, Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP, Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS, Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(this, mPermissionList, 123);
        }
    }

    @OnClick({R.id.main_btn_dl, R.id.main_btn_zc, R.id.btn_qq})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.main_btn_dl:

                String name = mainEdName.getText().toString();
                String str = "/^[1][3,4,5,7,8][0-9]{9}$/";
                if (!name.equals(str) && name.equals("")){
                    Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    Intent intent1 = new Intent(MainActivity.this,HomeActivity.class);
                    startActivity(intent1);
                }
                break;
            case R.id.main_btn_zc:
                Intent intent = new Intent(MainActivity.this,ZhuceActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_qq:
                //获取友盟封装的分享对象
                UMShareAPI umShareAPI = UMShareAPI.get(this);
                //切记平台切换
                umShareAPI.getPlatformInfo(MainActivity.this, SHARE_MEDIA.QQ, authListener);
                break;
        }
    }
    UMAuthListener authListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }

        @Override
        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {


            Toast.makeText(MainActivity.this, "登录成功了", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this,HomeActivity.class);
            startActivity(intent);
        }

        @Override
        public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
            Toast.makeText(MainActivity.this, "登录失败了", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media, int i) {

        }
    };


}
