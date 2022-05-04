package com.example.team;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.team.database.TeamRepositoryImpl;
import com.example.team.database.UserRepositoryImpl;
import com.teampp.usecase.ChangeActivity;
import com.example.team.help.NavigationHandler;
import com.google.android.material.card.MaterialCardView;
import com.teampp.usecase.user.ChangeCurrentTeamOfUser;
import com.teampp.usecase.team.GetCurrentTeam;
import com.teampp.usecase.team.TeamsOfUser;

public class TeamActivity extends AppCompatActivity {

    private ImageButton teams, home, profile;
    private Button addTeam;
    private TextView title, members;
    private LinearLayout sv;
    private int userID;
    private TeamsOfUser getTeamsOfUserUseCase;
    private GetCurrentTeam getCurrentTeamUseCase;
    private TeamRepositoryImpl teamRepository;
    private UserRepositoryImpl userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);

        assignElements();
        prepareNavigationBar();
    }

    private void prepareNavigationBar() {
        NavigationHandler nav = new NavigationHandler(this);
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
        ChangeActivity.changeActivity(this, JoinOrCreateTeamActivity.class);
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
        getTeamsOfUserUseCase = new TeamsOfUser(teamRepository, userRepository, userID);
        getTeamsOfUserUseCase.nextTeam();
        while (!getTeamsOfUserUseCase.isFinished()) {
            insertOneTeam();
        }

    }

    private void insertOneTeam() {
        MaterialCardView cardView = getNewCardView();
        addClickEvent(cardView, getTeamsOfUserUseCase.getTeamId());
        LinearLayout container = getLinearlayout();
        TextView title = getTitleTextView(getTeamsOfUserUseCase.getTeamName());
        TextView memberNum = getMembersTextView(getTeamsOfUserUseCase.getTeamMemberNum());
        container.addView(title);
        container.addView(memberNum);
        cardView.addView(container);
        sv.addView(cardView);
        getTeamsOfUserUseCase.nextTeam();
    }

    private void addClickEvent(MaterialCardView cardView, int teamID) {
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTeamInfo(teamID);
                restartActivity();
            }
        });
    }

    private void restartActivity() {
        ChangeActivity.changeActivity(this, TeamActivity.class);
    }

    private void saveTeamInfo(int newTeamID) {
        ChangeCurrentTeamOfUser changeCurrentTeamUseCase = new ChangeCurrentTeamOfUser(userRepository);
        changeCurrentTeamUseCase.changeTeam(userID, newTeamID);
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
        title.setText(getCurrentTeamUseCase.getCurrentTeamName());
        members.setText(getCurrentTeamUseCase.getCurrentTeamMemberNum() + " Mitglieder");
    }

    private int getUserID() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        return sharedPref.getInt("user_id", 0);
    }

    private void assignElements() {
        teams = findViewById(R.id.nav_teams);
        home = findViewById(R.id.nav_home);
        profile = findViewById(R.id.nav_profile);
        title = findViewById(R.id.teams_actual_title);
        members = findViewById(R.id.teams_actual_member);
        sv = findViewById(R.id.teams_scroll_container);
        addTeam = findViewById(R.id.teams_addteam);
        userRepository = new UserRepositoryImpl();
        teamRepository = new TeamRepositoryImpl();
        userID = getUserID();
        getCurrentTeamUseCase = new GetCurrentTeam(teamRepository, userRepository, userID);
    }
}