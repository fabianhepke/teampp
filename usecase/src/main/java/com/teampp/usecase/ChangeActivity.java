package com.teampp.usecase;

import android.content.Context;
import android.content.Intent;

public class ChangeActivity {
    public static void changeActivity(Context from, Class to) {
        Intent intent = new Intent(from, to);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        from.startActivity(intent);
    }
}
