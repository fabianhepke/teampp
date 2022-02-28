package com.example.team.help;

import android.content.Context;
import android.content.Intent;

public class ActivityChanger {
    public static void changeActivityTo(Context context, Class cl) {
        Intent intent = new Intent(context, cl);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        context.startActivity(intent);
    }
}
