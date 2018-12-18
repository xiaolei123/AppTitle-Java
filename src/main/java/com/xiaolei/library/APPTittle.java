package com.xiaolei.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.constraint.ConstraintSet;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 自定义标题栏
 * [左边图标 左边文字 [提示红点]中间文字[下拉按钮] 右边文字 右边图标]
 */
public class APPTittle extends ImmersiveView
{
    // 左边图标
    private ImageView leftImgView;
    // 左边文字
    private TextView leftTextView;
    // 标题左边小图标
    private ImageView titleLeftIconImageView;
    // 标题
    private TextView titleTextView;
    // 标题右边小图标
    private ImageView titleRightIconImageView;
    // 右边文字
    private TextView rightTextView;
    // 右边图标
    private ImageView rightImgView;

    private ConstraintSet constraintSet = new ConstraintSet();

    public APPTittle(Context context)
    {
        this(context, null);
    }

    public APPTittle(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public APPTittle(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        initView(attrs);
    }

    private void initView(AttributeSet attrs)
    {
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
        //
        leftTextView.setId(View.generateViewId());
        leftTextView.setGravity(Gravity.CENTER);
        //
        titleLeftIconImageView.setId(View.generateViewId());
        titleLeftIconImageView.setScaleType(ImageView.ScaleType.CENTER);
        //
        titleTextView.setId(View.generateViewId());
        titleTextView.setGravity(Gravity.CENTER);
        //
        titleRightIconImageView.setId(View.generateViewId());
        titleRightIconImageView.setScaleType(ImageView.ScaleType.CENTER);
        //
        rightTextView.setId(View.generateViewId());
        rightTextView.setGravity(Gravity.CENTER);
        //
        rightImgView.setId(View.generateViewId());
        rightImgView.setScaleType(ImageView.ScaleType.CENTER);
        rightImgView.setPaddingRelative(Utils.dp2px(getContext(), 8), 0, Utils.dp2px(getContext(), 8), 0);
        ///-----------读取自定义属性进行初始化----------------
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.APPTittle);

        // 左边图片是否显示
        leftImgView.setVisibility(typedArray.getInt(R.styleable.APPTittle_left_img_visible, VISIBLE));
        // 左边文字是否显示
        leftTextView.setVisibility(typedArray.getInt(R.styleable.APPTittle_left_text_visible, VISIBLE));
        // 标题左边icon是否显示
        titleLeftIconImageView.setVisibility(typedArray.getInt(R.styleable.APPTittle_title_left_icon_visible, GONE));
        // 标题是否显示
        titleTextView.setVisibility(typedArray.getInt(R.styleable.APPTittle_title_text_visible, VISIBLE));
        // 标题右边icon是否显示,默认为不显示
        titleRightIconImageView.setVisibility(typedArray.getInt(R.styleable.APPTittle_title_right_icon_visible, GONE));
        // 右边文字是否显示
        rightTextView.setVisibility(typedArray.getInt(R.styleable.APPTittle_right_text_visible, GONE));
        // 右边图片是否显示
        rightImgView.setVisibility(typedArray.getInt(R.styleable.APPTittle_right_img_visible, GONE));

        // 左边图片，默认为返回图标
        int leftImgDrawId = typedArray.getResourceId(R.styleable.APPTittle_left_img, R.drawable.base_icon_back_default);
        leftImgView.setImageResource(leftImgDrawId);
        // 左边图片的内距,默认为8dp
        int leftImgPadding = typedArray.getDimensionPixelSize(R.styleable.APPTittle_left_img_padding, Utils.dp2px(getContext(), 8));
        leftImgView.setPaddingRelative(leftImgPadding, 0, leftImgPadding, 0);
        // 左边文字
        String leftTextStr = typedArray.getString(R.styleable.APPTittle_left_text);
        leftTextView.setText(leftTextStr == null ? "Back" : leftTextStr);
        // 左边文字大小,避免受到老年机字体的影响，默认单位是13DP
        int leftTextSize = typedArray.getDimensionPixelSize(R.styleable.APPTittle_left_text_size, Utils.dp2px(getContext(), 13));
        leftTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, leftTextSize);
        // 左边的文字颜色，默认为白色
        int leftTextColor = typedArray.getColor(R.styleable.APPTittle_left_text_color, Color.WHITE);
        leftTextView.setTextColor(leftTextColor);
        // 标题左边的图标，默认为红点点
        int leftIcon = typedArray.getResourceId(R.styleable.APPTittle_title_left_icon, R.drawable.base_icon_point_default);
        titleLeftIconImageView.setImageResource(leftIcon);
        // 标题文字
        String titleTextStr = typedArray.getString(R.styleable.APPTittle_title_text);
        titleTextView.setText(titleTextStr == null ? "Title" : titleTextStr);
        // 标题文字大小，默认15，避免被老年机影响，默认单位使用DP
        int titleTextSize = typedArray.getDimensionPixelSize(R.styleable.APPTittle_title_text_size, Utils.dp2px(getContext(), 15));
        titleTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleTextSize);
        // 获取标题文字颜色
        int titleTextColor = typedArray.getColor(R.styleable.APPTittle_title_text_color, Color.WHITE);
        titleTextView.setTextColor(titleTextColor);
        // 标题右边图标，默认为下拉图标
        int titleRightIcon = typedArray.getResourceId(R.styleable.APPTittle_title_right_icon, R.drawable.base_icon_dorp_down_default);
        titleRightIconImageView.setImageResource(titleRightIcon);
        // 获取右边图标的内距
        int rightImgPadding = typedArray.getDimensionPixelSize(R.styleable.APPTittle_right_img_padding, Utils.dp2px(getContext(), 8));
        rightImgView.setPaddingRelative(rightImgPadding, 0, rightImgPadding, 0);
        // 右边文字
        String rightText = typedArray.getString(R.styleable.APPTittle_right_text);
        rightTextView.setText(rightText == null ? "Menu" : rightText);
        // 右边文字大小，默认为13，避免受老年机影响，单位为DP
        int rightTextSize = typedArray.getDimensionPixelSize(R.styleable.APPTittle_right_text_size, Utils.dp2px(getContext(), 13));
        rightTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, rightTextSize);
        // 右边文字颜色，默认为白色
        int rightTextColor = typedArray.getColor(R.styleable.APPTittle_right_text_color, Color.WHITE);
        rightTextView.setTextColor(rightTextColor);
        // 右边图标，默认为菜单图标
        int rightImage = typedArray.getResourceId(R.styleable.APPTittle_right_img, R.drawable.base_icon_menu_default);
        rightImgView.setImageResource(rightImage);
        // 获取左右两个图标的内距
        int leftRightPadding = typedArray.getDimensionPixelSize(R.styleable.APPTittle_left_right_img_padding, -1);
        if (leftRightPadding != -1)
        {
            leftImgView.setPaddingRelative(leftRightPadding, 0, leftRightPadding, 0);
            rightImgView.setPaddingRelative(leftRightPadding, 0, leftRightPadding, 0);
        }
        typedArray.recycle();


        this.addView(leftImgView);
        this.addView(leftTextView);
        this.addView(titleLeftIconImageView);
        this.addView(titleTextView);
        this.addView(titleRightIconImageView);
        this.addView(rightTextView);
        this.addView(rightImgView);

        constraintSet.clone(this);

        // 左边图片的设置
        constraintSet.constrainWidth(leftImgView.getId(), ConstraintSet.WRAP_CONTENT);
        constraintSet.constrainHeight(leftImgView.getId(), 0);
        constraintSet.connect(leftImgView.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM);
        constraintSet.connect(leftImgView.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START);
        constraintSet.connect(leftImgView.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP);
        constraintSet.connect(leftImgView.getId(), ConstraintSet.END, leftTextView.getId(), ConstraintSet.START);

        // 左边文字的设置
        constraintSet.constrainWidth(leftTextView.getId(), ConstraintSet.WRAP_CONTENT);
        constraintSet.constrainHeight(leftTextView.getId(), 0);
        constraintSet.connect(leftTextView.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM);
        constraintSet.connect(leftTextView.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP);
        constraintSet.connect(leftTextView.getId(), ConstraintSet.START, leftImgView.getId(), ConstraintSet.END);
        constraintSet.setGoneMargin(leftTextView.getId(), ConstraintSet.START, Utils.dp2px(getContext(), 8));


        // 标题左边图标的设置
        constraintSet.constrainWidth(titleLeftIconImageView.getId(), ConstraintSet.WRAP_CONTENT);
        constraintSet.constrainHeight(titleLeftIconImageView.getId(), ConstraintSet.WRAP_CONTENT);
        constraintSet.connect(
                titleLeftIconImageView.getId(),
                ConstraintSet.BOTTOM,
                ConstraintSet.PARENT_ID,
                ConstraintSet.BOTTOM
        );
        constraintSet.connect(titleLeftIconImageView.getId(), ConstraintSet.END, titleTextView.getId(), ConstraintSet.START);
        constraintSet.setHorizontalChainStyle(titleLeftIconImageView.getId(), ConstraintSet.CHAIN_PACKED);
        constraintSet.connect(
                titleLeftIconImageView.getId(),
                ConstraintSet.START,
                ConstraintSet.PARENT_ID,
                ConstraintSet.START
        );
        constraintSet.connect(titleLeftIconImageView.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP);
        // 标题的设置
        constraintSet.constrainWidth(titleTextView.getId(), ConstraintSet.WRAP_CONTENT);
        constraintSet.constrainHeight(titleTextView.getId(), 0);
        constraintSet.connect(titleTextView.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM);
        constraintSet.connect(titleTextView.getId(), ConstraintSet.END, titleRightIconImageView.getId(), ConstraintSet.START);
        constraintSet.connect(titleTextView.getId(), ConstraintSet.START, titleLeftIconImageView.getId(), ConstraintSet.END);
        constraintSet.connect(titleTextView.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP);
        constraintSet.setMargin(titleTextView.getId(), ConstraintSet.START, Utils.dp2px(getContext(), 5));
        constraintSet.setMargin(titleTextView.getId(), ConstraintSet.END, Utils.dp2px(getContext(), 5));


        // 标题右边的图标的设置
        constraintSet.constrainWidth(titleRightIconImageView.getId(), ConstraintSet.WRAP_CONTENT);
        constraintSet.constrainHeight(titleRightIconImageView.getId(), ConstraintSet.WRAP_CONTENT);
        constraintSet.connect(
                titleRightIconImageView.getId(),
                ConstraintSet.BOTTOM,
                ConstraintSet.PARENT_ID,
                ConstraintSet.BOTTOM
        );
        constraintSet.connect(titleRightIconImageView.getId(), ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END);
        constraintSet.connect(titleRightIconImageView.getId(), ConstraintSet.START, titleTextView.getId(), ConstraintSet.END);
        constraintSet.connect(titleRightIconImageView.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP);

        // 右边文字的设置
        constraintSet.constrainWidth(rightTextView.getId(), ConstraintSet.WRAP_CONTENT);
        constraintSet.constrainHeight(rightTextView.getId(), 0);
        constraintSet.connect(rightTextView.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM);
        constraintSet.connect(rightTextView.getId(), ConstraintSet.END, rightImgView.getId(), ConstraintSet.START);
        constraintSet.connect(rightTextView.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP);
        constraintSet.setGoneMargin(rightTextView.getId(), ConstraintSet.END, Utils.dp2px(getContext(), 8));

        // 右边图片的设置
        constraintSet.constrainWidth(rightImgView.getId(), ConstraintSet.WRAP_CONTENT);
        constraintSet.constrainHeight(rightImgView.getId(), 0);
        constraintSet.connect(rightImgView.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM);
        constraintSet.connect(rightImgView.getId(), ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END);
        constraintSet.connect(rightImgView.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP);

        constraintSet.applyTo(this);
    }
    /**
     * 当左边图片被点击
     */
    public void onLeftImgClick(OnClickListener listener)
    {
        leftImgView.setOnClickListener(listener);
    }
    /**
     * 当左边文字被点击
     */
    public void onLeftTextClick(OnClickListener listener)
    {
        leftTextView.setOnClickListener(listener);
    }
    /**
     * 当标题被点击
     */
    public void onTitleClick(OnClickListener listener)
    {
        titleTextView.setOnClickListener(listener);
    }
    /**
     * 当标题左边Icon被点击
     */
    public void onTitleLeftIconClick(OnClickListener listener)
    {
        titleLeftIconImageView.setOnClickListener(listener);
    }
    /**
     * 当标题右边Icon被点击
     */
    public void onTitleRightIconClick(OnClickListener listener)
    {
        titleRightIconImageView.setOnClickListener(listener);
    }
    /**
     * 当右边文字被点击
     */
    public void onRightTextClick(OnClickListener listener)
    {
        rightTextView.setOnClickListener(listener);
    }
    /**
     * 当右边图片被点击
     */
    public void onRightImgClick(OnClickListener listener)
    {
        rightImgView.setOnClickListener(listener);
    }


}
