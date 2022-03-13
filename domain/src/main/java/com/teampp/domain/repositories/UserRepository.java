package com.teampp.domain.repositories;

import com.teampp.domain.entities.*;
import com.teampp.domain.valueobjects.Token;

public interface UserRepository {

    boolean registerUser(User user);

    void changeUserData(User user);

    void changeUserLoginToken(User user, Token newToken);

    User login(User user, boolean stayLoggedIn);

    boolean doesUserEmailExist(String userEmail);

    boolean doesUserNameExist(String username);

    boolean doesPasswordMatchUser(User user);

    boolean doesUserHasTeamConnection(User user, Team team);

    boolean doesUserHasTeam(String username);

    boolean isUserLoggedIn(int userID, String loginToken);

    void changeCurrentTeam(User user);

    Team getCurrentTeam(User user);

    User getUserByID(int userID);
}
