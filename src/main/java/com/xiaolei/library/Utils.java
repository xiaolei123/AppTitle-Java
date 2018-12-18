package com.xiaolei.library;

import android.content.Context;
import java.lang.reflect.Field;

public class Utils {
    /**
     * 获取标题栏高度
     * @param context
     * @return
     */
    public static int getStatbarHeight(Context context)
    {
        int result = 0;
        
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0)
        {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }

        if(result == 0)
        {
            try
            {
                Class c = Class.forName("com.android.internal.R$dimen");
                Object obj = c.newInstance();
                Field field = c.getField("status_bar_height");
                int x = Integer.parseInt(field.get(obj).toString());
                result = context.getResources().getDimensionPixelSize(x);
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return result;
    }
    
    public static int dp2px(Context context,int dp)
    {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
}
