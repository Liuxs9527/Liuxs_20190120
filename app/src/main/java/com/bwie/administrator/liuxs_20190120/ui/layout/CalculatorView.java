package com.bwie.administrator.liuxs_20190120.ui.layout;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bwie.administrator.liuxs_20190120.R;

public class CalculatorView extends LinearLayout {

    private Button btn_jian;
    private Button btn_add;
    private TextView text_number;


    public CalculatorView(Context context) {
        super(context);
    }

    public CalculatorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View rootview = LayoutInflater.from(context).inflate(R.layout.calculator_item, null);




    }

    public CalculatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
