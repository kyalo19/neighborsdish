package com.kyalo.neighboursdish.CustomTextViews;


import android.content.Context;
import android.graphics.Typeface;
import androidx.appcompat.widget.AppCompatTextView;
import android.util.AttributeSet;

public class TextViewFontLeelawdeeBold extends AppCompatTextView {
    public TextViewFontLeelawdeeBold(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public TextViewFontLeelawdeeBold(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TextViewFontLeelawdeeBold(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode()) {

            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/leelawdbold.ttf");
            setTypeface(tf);
        }
    }
}
