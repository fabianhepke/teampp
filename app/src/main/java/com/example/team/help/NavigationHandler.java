package com.example.team.help;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.team.HomeActivity;
import com.example.team.ProfileActivity;
import com.example.team.R;
import com.example.team.TeamActivity;

public class NavigationHandler {

    private Context context;
    private int userID;

    public NavigationHandler(Context context, int userID){
        this.context = context;
        this.userID = userID;
    }

    public void setNavigaionBarColor(ImageButton teams, ImageButton home, ImageButton profile, int active) {
        switch (active) {
            case 1:
                teams.setBackgroundColor(context.getResources().getColor(R.color.grey));
                break;
            case 2:
                home.setBackgroundColor(context.getResources().getColor(R.color.grey));
                break;
            case 3:
                profile.setBackgroundColor(context.getResources().getColor(R.color.grey));
                break;
        }
    }

    public void addNavigationBarEvents(ImageButton teams, ImageButton home, ImageButton profile) {
        teams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToTeams();
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToHome();
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToProfile();
            }
        });

    }

    private void goToTeams() {
        Intent intent = new Intent(context, TeamActivity.class);
        intent.putExtra("user_id", userID);
        context.startActivity(intent);
    }

    private void goToHome() {
        Intent intent = new Intent(context, HomeActivity.class);
        intent.putExtra("user_id", userID);
        context.startActivity(intent);
    }

    private void goToProfile() {
        Intent intent = new Intent(context, ProfileActivity.class);
        intent.putExtra("user_id", userID);
        context.startActivity(intent);
    }
}