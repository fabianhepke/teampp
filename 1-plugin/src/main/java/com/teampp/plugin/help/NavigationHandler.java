package com.teampp.plugin.help;

import android.content.Context;
import android.widget.ImageButton;

import com.teampp.plugin.HomeActivity;
import com.teampp.plugin.ProfileActivity;
import com.example.team.R;
import com.teampp.plugin.TeamActivity;

public class NavigationHandler {

    private Context context;

    public NavigationHandler(Context context){
        this.context = context;
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
        teams.setOnClickListener(v -> goToTeams());

        home.setOnClickListener(v -> goToHome());

        profile.setOnClickListener(v -> goToProfile());

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