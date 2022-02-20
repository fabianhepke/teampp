package com.example.team;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.team.components.Team;
import com.example.team.components.TeamCode;
import com.example.team.components.Teams;
import com.example.team.components.User;
import com.example.team.database.PhpConnection;
import com.example.team.help.ActivityChanger;
import com.example.team.help.NavigationHandler;
import com.google.android.material.card.MaterialCardView;

import static android.content.ContentValues.TAG;

public class TeamActivity extends AppCompatActivity {

    ImageButton teams, home, profile;
    Button addTeam;
    TextView title, members;
    LinearLayout sv;
    User user = new User();
    Teams otherTeams;
    Team team;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);

        assignElements();
        getUserInfos();
        getTeamInfos();
        prepareNavigationBar();
    }

    private void getTeamInfos() {
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        team = new Team(new TeamCode(sharedPref.getInt("team_id", 100000)));
    }

    private void prepareNavigationBar() {
        NavigationHandler nav = new NavigationHandler(this, user.getUserID());
        nav.setNavigaionBarColor(teams, home, profile, 1);
        nav.addNavigationBarEvents(teams, home, profile);
    }

    @Override
    protected void onResume() {
        super.onResume();
        adjustScrollView();
        insertTeams();
        assignButtonEvents();
    }

    private void assignButtonEvents() {
        addTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAddTeam();
            }
        });
    }

    private void goToAddTeam() {
        ActivityChanger.changeActivityTo(this, JoinOrCreateTeamActivity.class);
    }

    private void adjustScrollView() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        findViewById(R.id.team_scrollview).getLayoutParams().height = height - 900;
        findViewById(R.id.team_scrollview).requestLayout();
    }

    private void insertTeams() {
        insertActualTeam();
        insertOtherTeams();
    }

    private void insertOtherTeams() {
        PhpConnection conn = new PhpConnection();
        otherTeams = conn.getTeamsOfUser(user.getUserID());
        for (int i = 0; i < otherTeams.getTeams().size(); i++) {
            isertOneTeam(otherTeams.getTeams().get(i));
        }
    }

    private void isertOneTeam(Team team) {
        MaterialCardView cardView = getNewCardView();
        LinearLayout container = getLinearlayout();
        TextView title = getTitleTextView(team.getTeamName());
        TextView memberNum = getMembersTextView(team.getMembers());
        container.addView(title);
        container.addView(memberNum);
        cardView.addView(container);
        sv.addView(cardView);
    }

    private TextView getMembersTextView(int memberNum) {
        TextView members = new TextView(this);
        members.setTextSize(TypedValue.COMPLEX_UNIT_PT,8);
        members.setLayoutParams(findViewById(R.id.teams_actual_member).getLayoutParams());
        members.setText(memberNum + " Mitglieder");
        return members;
    }

    private TextView getTitleTextView(String teamname) {
        TextView title = new TextView(this);
        title.setTextColor(getColor(R.color.black));
        title.setTypeface(null, Typeface.BOLD);
        title.setTextSize(TypedValue.COMPLEX_UNIT_PT, 12);
        title.setText(teamname);
        title.setLayoutParams(findViewById(R.id.teams_actual_title).getLayoutParams());
        return title;
    }

    private LinearLayout getLinearlayout() {
        LinearLayout container = new LinearLayout(this);
        container.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(20,20,20,20);
        container.setLayoutParams(lp);
        return container;
    }

    private MaterialCardView getNewCardView() {
        MaterialCardView cardView = new MaterialCardView(this);
        cardView.setCardElevation(6);
        cardView.setClickable(true);
        ViewGroup.LayoutParams lp = findViewById(R.id.teams_actual_card).getLayoutParams();
        cardView.setLayoutParams(lp);
        return cardView;
    }

    private void insertActualTeam() {
        PhpConnection conn = new PhpConnection();
        title.setText(conn.getTeamName(team.getTeamID().getCode()));
        members.setText(conn.getTeamMemberNum(team.getTeamID().getCode()) + " Mitglieder");
    }

    private void getUserInfos() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        user.setUserID(sharedPref.getInt("user_id", 0));
    }

    private void assignElements() {
        teams = findViewById(R.id.nav_teams);
        home = findViewById(R.id.nav_home);
        profile = findViewById(R.id.nav_profile);
        title = findViewById(R.id.teams_actual_title);
        members = findViewById(R.id.teams_actual_member);
        sv = findViewById(R.id.teams_scroll_container);
        addTeam = findViewById(R.id.teams_addteam);
    }
}