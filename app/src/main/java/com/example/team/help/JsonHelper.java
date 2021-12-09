package com.example.team.help;

import com.example.team.components.Rank;
import com.example.team.components.TeamCode;
import com.example.team.components.User;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonHelper {

    public static User getUserOfJson(JSONObject json) throws JSONException {
        User user = new User();
        user.setUserID(json.getInt("user_id"));
        user.setUsername(json.getString("username"));
        user.seteMail(json.getString("email"));
        user.setVerfied(IntToBooleanConverter.convertIntToBoolean(json.getInt("verified")));
        user.setLoginToken(new Token(json.getString("login_token")));
        if (json.getInt("team_id") != 0) {
            user.setTeamID(new TeamCode(json.getInt("team_id")));
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
