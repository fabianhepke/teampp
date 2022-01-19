package com.example.team.database;

import com.example.team.components.Team;
import com.example.team.components.User;
import com.example.team.help.EMail;

public interface DatabaseConnection {

    String registerUser(User user);

    User login(String username, String password, boolean stayLoggedin);

    User login(EMail email, String password, boolean stayLoggedin);

    User getUserByUsername(String username);

    User getUserByEmail(String email);

    boolean doesUserEmailExist(String usernameOrEmail);

    boolean doesUserNameExist(String usernameOrEmail);

    boolean doesPasswordMatchUsername(String usernameOrEmail, String password);

    boolean doesPasswordMatchEmail(String usernameOrEmail, String password);

    boolean isVerified(String username);

    boolean doesTeamExist(int teamID);

    String createTeam(Team team);

    int getMaxTeamID();
}
