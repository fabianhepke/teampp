package com.teampp.domain.user;

import com.teampp.domain.general.valueobjects.BasicID;

public interface UserBuilder {

    UserBuilder setUserID(BasicID userID);

    UserBuilder setEmail(EMail email);

    UserBuilder setPassword(Password password);

    UserBuilder setLoginToken(Token loginToken);

    UserBuilder setRank(Rank rank);

    UserBuilder setActualTeamID(int teamID);

    UserBuilder setUsername(Username username);

    User build();
}
