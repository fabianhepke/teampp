package com.teampp.domain.repositories;

import com.teampp.domain.entities.*;
import com.teampp.domain.entities.valueobjects.BasicID;
import com.teampp.domain.entities.valueobjects.EMail;
import com.teampp.domain.entities.valueobjects.TeamID;
import com.teampp.domain.entities.valueobjects.Token;

public interface UserRepository {

    boolean registerUser(User user);

    void changeUserData(User user);

    void changeUserLoginToken(User user, Token newToken);

    User login(User user, boolean stayLoggedIn);

    boolean doesUserEmailExist(String userEmail);

    boolean doesUserNameExist(String username);

    boolean doesPasswordMatchUser(User user);

    boolean doesUserHasTeamConnection(User user, Team team);

    boolean doesUserHasTeam(User user);

    void changeCurrentTeam(User user);

    Team getCurrentTeam(User user);
}
