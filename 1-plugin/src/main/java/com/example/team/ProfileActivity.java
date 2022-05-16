package com.example.team;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Typeface;
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
import android.widget.TextView;

import com.example.team.database.TeamRepositoryImpl;
import com.example.team.database.UserRepositoryImpl;
import com.example.team.database.UserTeamConnectionRepositoryImpl;
import com.example.team.help.ChangeActivity;
import com.example.team.help.NavigationHandler;
import com.google.android.material.card.MaterialCardView;
import com.teampp.domain.userteamconnection.UserTeamConnectionRepository;
import com.teampp.usecase.team.TeamsOfUser;
import com.teampp.usecase.team.LeaveTeam;
import com.teampp.usecase.user.LogoutUser;

import static android.content.ContentValues.TAG;

public class ProfileActivity extends AppCompatActivity {
    private ImageButton teams, home, profile;
    private Button logout;
    private LinearLayout sv;
    private int userID;
    private TeamsOfUser getTeamsOfUserUseCase;
    private TeamRepositoryImpl teamRepository;
    private UserRepositoryImpl userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        userID = getUserID();
        assignElements();
        assignButtonEvents();
        adjustScrollView();

        NavigationHandler nav = new NavigationHandler(this);
        nav.setNavigaionBarColor(teams, home, profile, 3);
        nav.addNavigationBarEvents(teams, home, profile);

        insertTeams();
    }

    private void adjustScrollView() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        findViewById(R.id.profile_scrollview).getLayoutParams().height = height - 900;
        findViewById(R.id.profile_scrollview).requestLayout();
    }

    private void assignButtonEvents() {
        logout.setOnClickListener(view -> logout());
    }

    private void logout() {
        LogoutUser logoutUserUseCase = new LogoutUser(userRepository, this);
        logoutUserUseCase.logout(userID);
        ChangeActivity.changeActivity(ProfileActivity.this, LoginActivity.class);
    }


    private void insertTeams() {
        try {

            getTeamsOfUserUseCase = new TeamsOfUser(teamRepository, userRepository, userID);
            insertOneTeam(getTeamsOfUserUseCase.getCurrentTeam(userID).getTeamName(), getTeamsOfUserUseCase.getCurrentTeam(userID).getMembers(), getTeamsOfUserUseCase.getCurrentTeam(userID).getTeamID().toInt());
            while (!getTeamsOfUserUseCase.isFinished()) {
                insertOneTeam(getTeamsOfUserUseCase.getTeamName(), getTeamsOfUserUseCase.getTeamMemberNum(), getTeamsOfUserUseCase.getTeamId());
            }
        } catch (Exception e) {

        }
    }

    private void insertOneTeam(String name, int memberNum, int id) {
        MaterialCardView cardView = getNewCardView();
        LinearLayout container = getContainer();
        LinearLayout infoLayout = getLinearlayout(8);
        LinearLayout buttonLayout = getLinearlayout(1);
        TextView title = getTitleTextView(name);
        TextView memberNumView = getMembersTextView(memberNum);
        ImageButton button = getButton(id);
        infoLayout.addView(title);
        infoLayout.addView(memberNumView);
        buttonLayout.addView(button);
        container.addView(infoLayout);
        container.addView(buttonLayout);
        cardView.addView(container);
        sv.addView(cardView);
        getTeamsOfUserUseCase.nextTeam();
    }

    private ImageButton getButton(int teamID) {
        ImageButton button = new ImageButton(this);
        button.setImageDrawable(getDrawable(R.drawable.ic_delete));
        button.setBackgroundColor(getColor(R.color.invisible));
        addButtonClickAction(button, teamID);
        return button;
    }

    private void addButtonClickAction(ImageButton button, int teamID) {
        Log.i(TAG, "addButtonClickAction: set event for team: " + teamID);
        button.setOnClickListener(view -> {
            Log.i(TAG, "onClick: event ausgelöst für team " + teamID);
            UserTeamConnectionRepository userTeamConnectionRepository = new UserTeamConnectionRepositoryImpl();
            LeaveTeam leaveTeamUseCase = new LeaveTeam(userTeamConnectionRepository, teamRepository, userRepository);
            leaveTeamUseCase.leaveTeam(userID, teamID);
            ChangeActivity.changeActivity(ProfileActivity.this, ProfileActivity.class);
        });
    }

    private TextView getMembersTextView(int memberNum) {
        TextView members = new TextView(this);
        members.setTextSize(TypedValue.COMPLEX_UNIT_PT,8);
        members.setText(memberNum + " Mitglieder");
        return members;
    }

    private TextView getTitleTextView(String teamname) {
        TextView title = new TextView(this);
        title.setTextColor(getColor(R.color.black));
        title.setTypeface(null, Typeface.BOLD);
        title.setTextSize(TypedValue.COMPLEX_UNIT_PT, 12);
        title.setText(teamname);
        return title;
    }

    private LinearLayout getLinearlayout(float weight) {
        LinearLayout container = new LinearLayout(this);
        container.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(1 , ViewGroup.LayoutParams.WRAP_CONTENT, weight);
        container.setLayoutParams(lp);
        return container;
    }

    private LinearLayout getContainer() {
        LinearLayout lContainer = new LinearLayout(this);
        lContainer.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(20, 20, 20, 20);
        lContainer.setLayoutParams(lp);
        return lContainer;
    }

    private MaterialCardView getNewCardView() {
        MaterialCardView cardView = new MaterialCardView(this);
        cardView.setCardElevation(6);
        cardView.setClickable(true);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(5,10,5,10);
        cardView.setLayoutParams(lp);
        return cardView;
    }

    private int getUserID() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        return sharedPref.getInt("user_id", 0);
    }

    private void assignElements() {
        userRepository = new UserRepositoryImpl();
        teamRepository = new TeamRepositoryImpl();
        teams = findViewById(R.id.nav_teams);
        home = findViewById(R.id.nav_home);
        profile = findViewById(R.id.nav_profile);
        sv = findViewById(R.id.profile_teams_container);
        logout = findViewById(R.id.profile_logout_btn);
    }
}