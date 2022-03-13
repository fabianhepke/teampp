package com.teampp.domain.builder;

import com.teampp.domain.entities.enums.Rank;
import com.teampp.domain.valueobjects.BasicID;
import com.teampp.domain.valueobjects.EMail;
import com.teampp.domain.valueobjects.Password;
import com.teampp.domain.valueobjects.Token;
import com.teampp.domain.valueobjects.Username;

public interface UserBuilder {

    UserBuilder setUserID(BasicID userID);

    UserBuilder setEmail(EMail email);

    UserBuilder setPassword(Password password);

    UserBuilder setLoginToken(Token loginToken);

    UserBuilder setRank(Rank rank);

    UserBuilder setActualTeamID(int teamID);

    UserBuilder setUsername(Username username);
}
