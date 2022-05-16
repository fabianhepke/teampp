package com.teampp.plugin.help;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;

public class GetUIElement {

    public static LinearLayout getLinearlayout(Context context, int orientation, int width, int height, float weight, int margin) {
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(orientation);
        LinearLayout.LayoutParams lp;
        if (weight != 0) {
            lp = new LinearLayout.LayoutParams(width, height, weight);
        }
        else {
            lp = new LinearLayout.LayoutParams(width, height);
        }
        lp.setMargins(margin, margin, margin, margin);
        linearLayout.setLayoutParams(lp);
        return linearLayout;
    }

    public static MaterialCardView getCardView(Context context, int width, int height, boolean clickable, int margin) {
        MaterialCardView materialCardView = new MaterialCardView(context);
        materialCardView.setClickable(clickable);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width, height);
        lp.setMargins(margin, margin, margin, margin);
        materialCardView.setLayoutParams(lp);
        return materialCardView;
    }

    public static TextView getTextView(Context context, String text) {
        TextView textView = new TextView(context);
        textView.setText(text);
        textView.setTextColor(Color.BLACK);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PT, 12);
        return textView;
    }
}
