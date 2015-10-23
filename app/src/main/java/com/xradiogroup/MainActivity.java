package com.xradiogroup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements XRadioGroup.OnRadioButtonClickListener {
    private XRadioGroup xRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        xRadioGroup = (XRadioGroup) findViewById(R.id.xRadioGroup);
        List<String> datas = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            datas.add("数据" + i);
        }
        xRadioGroup.setDatas(datas);
        xRadioGroup.setRadioButtonClickListener(this);
    }

    @Override
    public void onRadioButtonClick(String data) {
        Log.i("android", data);
    }
}
