package com.xiaolei.library;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 自定义标题栏
 * [左边图标 左边文字 [提示红点]中间文字[下拉按钮] 右边文字 右边图标]
 */
public class APPTittle extends ImmersiveView {

    private ImageView leftImgView;
    private TextView leftTextView;

    private ImageView titleLeftIconImageView;
    private TextView titleTextView;
    private ImageView titleRightIconImageView;

    private TextView rightTextView;
    private ImageView rightImgView;

    public APPTittle(Context context) {
        this(context, null);
    }

    public APPTittle(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public APPTittle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
        initEvent();
    }

    private void initView() {
        leftImgView = new ImageView(getContext());
        leftTextView = new TextView(getContext());
        titleLeftIconImageView = new ImageView(getContext());
        titleTextView = new TextView(getContext());
        titleRightIconImageView = new ImageView(getContext());
        rightTextView = new TextView(getContext());
        rightImgView = new ImageView(getContext());
        //  
        leftImgView.setId(View.generateViewId());
        leftImgView.setScaleType(ImageView.ScaleType.CENTER);
        leftImgView.setPaddingRelative(Utils.dp2px(getContext(), 8), 0, Utils.dp2px(getContext(), 8), 0);
    }

    private void initEvent() {

    }
}
