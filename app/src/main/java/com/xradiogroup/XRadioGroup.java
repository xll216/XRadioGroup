package com.xradiogroup;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lilyxiao on 15/10/23.
 * <p/>
 * RadioGroup的一个扩展类 横向排列的单选控件
 */
public class XRadioGroup extends LinearLayout {
    private OnRadioButtonClickListener radioButtonClickListener;
    private List<String> datas;
    private Context mContext;
    private AttributeSet attrs;
    private List<View> views = null;

    private int radio_button_margin_left = 0;
    private int radio_button_margin_right = 0;
    private int radio_button_margin_top = 0;
    private int radio_button_margin_bottom = 0;

    private int radio_button_textColor;
    private int radio_button_checked_textColor;

    /***
     * 设置radiobutton上显示的文字
     **/
    public void setDatas(List<String> datas) {
        this.datas = datas;
        init(datas);
        invalidate();
    }

    public XRadioGroup(Context context) {
        super(context);
        initAttrs(context, null);
        mContext = context;
    }

    public XRadioGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
        mContext = context;
        this.attrs = attrs;
    }

    public XRadioGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
        mContext = context;
        this.attrs = attrs;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public XRadioGroup(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initAttrs(context, attrs);
        mContext = context;
        this.attrs = attrs;
    }

    public void initAttrs(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.XRadioGroup);

        if (ta.hasValue(R.styleable.XRadioGroup_radio_button_margin_left)) {
            radio_button_margin_left = ta.getDimensionPixelSize(R.styleable.XRadioGroup_radio_button_margin_left, 0);
        }
        if (ta.hasValue(R.styleable.XRadioGroup_radio_button_margin_top)) {
            radio_button_margin_top = ta.getDimensionPixelSize(R.styleable.XRadioGroup_radio_button_margin_top, 0);
        }
        if (ta.hasValue(R.styleable.XRadioGroup_radio_button_margin_right)) {
            radio_button_margin_right = ta.getDimensionPixelSize(R.styleable.XRadioGroup_radio_button_margin_right, 0);
        }
        if (ta.hasValue(R.styleable.XRadioGroup_radio_button_margin_bottom)) {
            radio_button_margin_bottom = ta.getDimensionPixelSize(R.styleable.XRadioGroup_radio_button_margin_bottom, 0);
        }

        if (ta.hasValue(R.styleable.XRadioGroup_radio_button_textColor)) {
            radio_button_textColor = ta.getColor(R.styleable.XRadioGroup_radio_button_textColor, -1);
        } else {
            radio_button_textColor = getResources().getColor(android.R.color.darker_gray);
        }

        if (ta.hasValue(R.styleable.XRadioGroup_radio_button_checked_textColor)) {
            radio_button_checked_textColor = ta.getColor(R.styleable.XRadioGroup_radio_button_checked_textColor, -1);
        } else {
            radio_button_checked_textColor = getResources().getColor(android.R.color.black);
        }

    }

    public void init(List<String> datas) {
        removeAllViews();//先将之前的移除
        views = new ArrayList<>();

        for (int i = 0; i < datas.size(); i++) {
            final View view = LayoutInflater.from(mContext).inflate(R.layout.xradio_button, null);
            LayoutParams params = new LayoutParams(0, LayoutParams.MATCH_PARENT, 1);
            params.setMargins(radio_button_margin_left, radio_button_margin_top, radio_button_margin_right, radio_button_margin_bottom);
            view.setLayoutParams(params);

            TextView textView = (TextView) view.findViewById(R.id.radioButton);
            textView.setText(datas.get(i));
            view.setTag(datas.get(i));
            textView.setTextColor(radio_button_textColor);

            addView(view);
            views.add(view);

            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClick(view);
                }
            });
        }
    }

    /***
     * 设置点击之后的选中状态
     **/
    public void onItemClick(View v) {

        for (View view : views) {
            view.setBackgroundResource(R.drawable.choise_check_bg_selector);
            TextView textView = (TextView) view.findViewById(R.id.radioButton);
            textView.setTextColor(radio_button_textColor);
        }
        v.setBackgroundResource(R.drawable.choise_checked_bg_selector);
        TextView textView = (TextView) v.findViewById(R.id.radioButton);
        textView.setTextColor(radio_button_checked_textColor);

        if (radioButtonClickListener != null)
            radioButtonClickListener.onRadioButtonClick((String) v.getTag());
    }


    public void setRadioButtonClickListener(OnRadioButtonClickListener radioButtonClickListener) {
        this.radioButtonClickListener = radioButtonClickListener;
    }

    public interface OnRadioButtonClickListener {
        void onRadioButtonClick(String data);
    }
}
