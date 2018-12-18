package com.xiaolei.library;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
/**
 * 沉浸式，兼容凹口屏的控件
 */
public class ImmersiveView extends ConstraintLayout {

    public ImmersiveView(Context context) {
        this(context, null);
    }

    public ImmersiveView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ImmersiveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initEvent();
    }

    private void initEvent() {
        final ViewTreeObserver observer = this.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (getMeasuredWidth() > 0 && getMeasuredHeight() > 0) {
                    observer.removeOnGlobalLayoutListener(this);

                    int titleLocationOnScreens[] = new int[2];
                    getLocationOnScreen(titleLocationOnScreens);
                    // int titleLocationOnScreenX = titleLocationOnScreens[0];
                    int titleLocationOnScreenY = titleLocationOnScreens[1];
                    // 如果在屏幕顶部
                    if (titleLocationOnScreenY == 0) {
                        // 这里就需要对本身的高度进行改变啦
                        ViewGroup.LayoutParams params = getLayoutParams();
                        int stateBarHeight = Utils.getStatbarHeight(getContext());
                        params.height += stateBarHeight;
                        setPaddingRelative(
                                getPaddingStart(),
                                getPaddingTop() + stateBarHeight,
                                getPaddingEnd(),
                                getPaddingBottom()
                        );
                    }
                }
            }
        });
    }
}
