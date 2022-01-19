package com.example.team;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.team.database.PhpConnection;
import com.example.team.help.MailSender;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static android.content.ContentValues.TAG;

public class VerifyActivity extends AppCompatActivity {

    private Button loginbtn;
    private Handler handler;
    private String username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);

        loginbtn = findViewById(R.id.verify_loginbtn);

        Bundle extra = getIntent().getExtras();
        username = extra.getString("username");
    }

    @Override
    protected void onResume() {
        super.onResume();

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLogin();
            }
        });
        handler = new Handler();
        startCheckingVerification();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    Runnable verifyChecker = new Runnable() {

        @Override
        public void run() {
            Log.i(TAG, "run: one run is taken! lookin forward for the next in 2 secs!");
            if (checkVerification()) {
                stopRunnable();
                goToLogin();
                return;
            }
            handler.postDelayed(verifyChecker, 2000);
        }
    };
    Future verifyCheckerFuture = executorService.submit(verifyChecker);
    private void stopRunnable() {
        verifyCheckerFuture.cancel(true);
    }

    private boolean checkVerification() {
        PhpConnection conn = new PhpConnection();
        if(conn.isVerified(username)) {
            return true;
        }
        return false;
    }

    void startCheckingVerification() {
        verifyChecker.run();
    }

    private void goToLogin() {
        Intent intent = new Intent(VerifyActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}