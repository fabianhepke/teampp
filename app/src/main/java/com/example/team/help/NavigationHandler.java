package com.example.team.help;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;

import com.example.team.HomeActivity;
import com.example.team.ProfileActivity;
import com.example.team.R;
import com.example.team.TeamActivity;
import com.teampp.usecase.ChangeActivity;

public class NavigationHandler {

    private Context context;
    private int userID;

    public NavigationHandler(Context context){
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
        ChangeActivity.changeActivity(context, TeamActivity.class);
    }

    private void goToHome() {
        ChangeActivity.changeActivity(context, HomeActivity.class);
    }

    private void goToProfile() {
        ChangeActivity.changeActivity(context, ProfileActivity.class);
    }
}