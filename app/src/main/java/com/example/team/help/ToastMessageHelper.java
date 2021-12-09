package com.example.team.help;

import android.content.Context;
import android.widget.Toast;

import com.example.team.LoginActivity;

public class ToastMessageHelper {

    public static void showMessage(Context context, String message) {
        Toast.makeText(context, message,
                Toast.LENGTH_LONG).show();
    }

}
