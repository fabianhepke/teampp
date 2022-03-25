package com.teampp.usecase.help;

import android.content.Context;
import android.widget.LinearLayout;

import androidx.cardview.widget.CardView;

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

}
