package com.example.team.help;

import com.teampp.domain.user.ConcreteUserBuilder;
import com.teampp.domain.user.User;
import com.teampp.domain.user.Rank;
import com.teampp.domain.user.Token;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonHelper {

    public static User getUserOfJson(JSONObject json) throws JSONException {
        if (json.getString("status").equals("fail")) {
            return null;
        }
        User user = new ConcreteUserBuilder()
                .setUsername(json.getString("username"))
                .setUserID(json.getInt("user_id"))
                .setEmail(json.getString("email"))
                .build();
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
