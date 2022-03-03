package com.example.team;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.team.database.TeamRepositoryImpl;
import com.example.team.database.UserRepositoryImpl;
import com.example.team.database.UserTeamConnectionRepositoryImpl;
import com.example.team.help.ActivityChanger;
import com.example.team.help.NavigationHandler;
import com.google.android.material.card.MaterialCardView;
import com.teampp.domain.entities.Team;
import com.teampp.domain.entities.Teams;
import com.teampp.domain.entities.User;
import com.teampp.domain.entities.UserTeamConnection;
import com.teampp.domain.entities.valueobjects.BasicID;
import com.teampp.domain.entities.valueobjects.TeamID;
import com.teampp.domain.repositories.TeamRepository;
import com.teampp.domain.repositories.UserRepository;
import com.teampp.usecase.GetTeamsOfUser;

public class ProfileActivity extends AppCompatActivity {
    private ImageButton teams, home, profile, editProfile;
    private LinearLayout sv;
    private User user;
    private Team actualTeam;
    private Teams otherTeams;
    private UserRepository userRepository;
    private TeamRepository teamRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        user = getUserInfos();
        assignElements();
        assignButtonEvents();

        NavigationHandler nav = new NavigationHandler(this);
        nav.setNavigaionBarColor(teams, home, profile, 3);
        nav.addNavigationBarEvents(teams, home, profile);

        insertTeams();
    }

    private void assignButtonEvents() {
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityChanger.changeActivityTo(ProfileActivity.this, EditProfileActivity.class);
            }
        });
    }

    private void insertTeams() {
        insertOtherTeams();
    }

    private void insertOtherTeams() {
        GetTeamsOfUser getTeamsOfUserUseCase = new GetTeamsOfUser(teamRepository);
        otherTeams = getTeamsOfUserUseCase.getTeams(user);
        for (int i = 0; i < otherTeams.getTeams().size(); i++) {
            isertOneTeam(otherTeams.getTeams().get(i));
        }
    }

    private void isertOneTeam(Team team) {
        MaterialCardView cardView = getNewCardView();
        LinearLayout container = getContainer();
        LinearLayout infoLayout = getLinearlayout(8);
        LinearLayout buttonLayout = getLinearlayout(1);
        TextView title = getTitleTextView(team.getTeamName());
        TextView memberNum = getMembersTextView(team.getMembers());
        ImageButton button = getButton(team.getTeamID().toInt());
        infoLayout.addView(title);
        infoLayout.addView(memberNum);
        buttonLayout.addView(button);
        container.addView(infoLayout);
        container.addView(buttonLayout);
        cardView.addView(container);
        sv.addView(cardView);
    }

    private ImageButton getButton(int teamID) {
        ImageButton button = new ImageButton(this);
        button.setImageDrawable(getDrawable(R.drawable.ic_delete));
        button.setBackgroundColor(getColor(R.color.invisible));
        addButtonClickAction(button, teamID);
        return button;
    }

    private void addButtonClickAction(ImageButton button, int teamID) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new UserTeamConnectionRepositoryImpl().removeUserTeamConnection(new UserTeamConnection(user, new Team(new TeamID(teamID))));
                ActivityChanger.changeActivityTo(ProfileActivity.this, ProfileActivity.class);
            }
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

    @Override
    protected void onResume() {
        super.onResume();
    }

    private User getUserInfos() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        return new User(new BasicID(sharedPref.getInt("user_id", 0)));
    }

    private void assignElements() {
        userRepository = new UserRepositoryImpl();
        teamRepository = new TeamRepositoryImpl();
        teams = findViewById(R.id.nav_teams);
        home = findViewById(R.id.nav_home);
        profile = findViewById(R.id.nav_profile);
        editProfile = findViewById(R.id.profile_edit_btn);
        sv = findViewById(R.id.profile_teams_container);
        actualTeam = userRepository.getCurrentTeam(user);
        actualTeam.setTeamName(teamRepository.getTeamName(actualTeam.getTeamID()));
        GetTeamsOfUser getTeamsOfUserUseCase = new GetTeamsOfUser(teamRepository);
        otherTeams = getTeamsOfUserUseCase.getTeams(user);
    }
}