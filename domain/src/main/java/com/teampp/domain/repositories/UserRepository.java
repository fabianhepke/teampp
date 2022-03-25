package com.teampp.domain.repositories;

import com.teampp.domain.entities.*;
import com.teampp.domain.valueobjects.Token;

public interface UserRepository {

    boolean registerUser(String username, String password, String eMail, String loginToken, String rank, String name);

    void changeUserData(int userID, String username, String password, String eMail, String name);

    void changeUserLoginToken(int userID, Token newToken);

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

    User getUserByID(int userID);
}
