package com.example.team.help;

import com.teampp.domain.entities.User;
import com.teampp.domain.entities.enums.Rank;
import com.teampp.domain.entities.valueobjects.*;
import com.teampp.domain.entities.valueobjects.EMail;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonHelper {

    public static User getUserOfJson(JSONObject json) throws JSONException {
        if (json.getString("status").equals("fail")) {
            return null;
        }
        User user = new User(new BasicID(json.getInt("user_id")));
        user.setUsername(new Username(json.getString("username")));
        user.seteMail(new EMail(json.getString("email")));

        try {
            user.setLoginToken(new Token(json.getString("login_token")));
        }catch (JSONException e) {

        }

        String rank = json.getString("rank");
        switch (rank) {
            case "player":
                user.setRank(Rank.PLAYER);
            case "trainer":
                user.setRank(Rank.Trainer);
            case "playeradmin":
                user.setRank(Rank.PLAYERADMIN);
            default:
                user.setRank(Rank.NORANK);
        }
        return user;
    }
}
