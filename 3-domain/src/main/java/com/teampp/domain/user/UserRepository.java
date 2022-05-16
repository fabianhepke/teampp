package com.teampp.domain.user;

import com.teampp.domain.team.Team;

import java.util.ArrayList;

public interface UserRepository {

    boolean registerUser(String username, String password, String eMail, String loginToken, String rank, String name);

    User loginWithUserName(String username, String password, boolean stayLoggedIn);

    User loginWithEMail(String eMail, String password, boolean stayLoggedIn);

    boolean doesUserEmailExist(String userEmail);

    boolean doesUserNameExist(String username);

    boolean doesPasswordMatchUserName(String usernameOrMail, String password);

    boolean doesPasswordMatchUserEMail(String eMail, String password);

    boolean doesUserHasTeamConnection(int userID, int teamID);

    boolean doesUserHasTeam(String username);

    boolean isUserLoggedIn(int userID, String loginToken);

    void changeCurrentTeam(int userID, int teamID);

    Team getCurrentTeam(int userID);

    ArrayList<String> getPromisedUsers(int dateID);

    ArrayList<String> getCanceledUsers(int dateID);

    String getUsername(int userID);

    String getName(int userID);
}
