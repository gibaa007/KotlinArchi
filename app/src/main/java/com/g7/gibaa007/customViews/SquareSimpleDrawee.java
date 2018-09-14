package com.g7.gibaa007.customViews;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by newagesmb on 3/1/17.
 */

public class SquareSimpleDrawee extends android.support.v7.widget.AppCompatImageView {

    public SquareSimpleDrawee(Context context) {
        super(context);
    }

    public SquareSimpleDrawee(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareSimpleDrawee(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec); // This is the key that will make the height equivalent to its width
    }
}