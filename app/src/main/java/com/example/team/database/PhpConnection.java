package com.example.team.database;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.team.components.Rank;
import com.example.team.components.Team;
import com.example.team.components.TeamCode;
import com.example.team.components.User;
import com.example.team.help.ApiHelper;
import com.example.team.help.EMail;
import com.example.team.help.IntToBooleanConverter;
import com.example.team.help.JsonHelper;
import com.example.team.help.Token;
import com.example.team.help.URLHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

import static android.content.ContentValues.TAG;

public class PhpConnection implements DatabaseConnection {

    public PhpConnection() {
    }

    public String registerUser(User user){
        String url ="https://www.memevz.h10.de/teamPP.php?op=register&username=" + user.getUsername() + "&password=" + user.getPassword() + "&email=" + user.geteMail() + "&login_token=" + user.getLoginToken().getToken() + "&verify_token=" + user.getVerifyToken().getToken() + "&rank=" + user.getRank();
        String result;
        try {
            result = new ApiHelper(url).execute().get();
        }catch (ExecutionException | InterruptedException e) {
            e.fillInStackTrace();
            result = null;
        }
        try {
            return new JSONObject(result).getString("status");
        }catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User login(String username, String password, boolean stayLoggedin) {
        String url ="https://www.memevz.h10.de/teamPP.php?op=login&username=" + username + "&password=" + password + "&login_token=" + stayLoggedin;
        String result;

        try {
            result = new ApiHelper(url).execute().get();
        }catch (ExecutionException | InterruptedException e) {
            e.fillInStackTrace();
            result = null;
        }

        User user = new User();
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(result);
            user = JsonHelper.getUserOfJson(jsonObject);
        }catch (JSONException e) {
            Log.e(TAG, "login: couldn't convert answer to json", e);
        }
        return user;
    }

    @Override
    public User login(EMail email, String password, boolean stayLoggedin) {
        String url ="https://www.memevz.h10.de/teamPP.php?op=login&email=" + email.toString() + "&password=" + password + "&login_token=" + stayLoggedin;
        String result;

        try {
            result = new ApiHelper(url).execute().get();
        }catch (ExecutionException | InterruptedException e) {
            e.fillInStackTrace();
            result = null;
        }

        User user = new User();
        JSONObject jsonObject;
        try{
            jsonObject = new JSONObject(result);
            user = JsonHelper.getUserOfJson(jsonObject);
        }catch (JSONException e) {
            Log.e(TAG, "login: can't convert the api answer to json", e);
        }
        return user;
    }

    @Override
    public User getUserByUsername(String username) {
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        return null;
    }
    @Override
    public boolean doesUserEmailExist(String email) {
        boolean userExistance = false;
        String url ="https://www.memevz.h10.de/teamPP.php?op=emailexist&email=" + email;
        String result;

        try {
            result = new ApiHelper(url).execute().get();
        }catch (ExecutionException | InterruptedException e) {
            e.fillInStackTrace();
            result = null;
        }

        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(result);
            userExistance = jsonObject.getBoolean("result");
        }catch (JSONException e) {
            Log.e(TAG, "doesUserEmailExist: can't convert answer to json", e);
        }
        return userExistance;
    }

    @Override
    public boolean doesUserNameExist(String username) {
        boolean userExistance = false;
        String url ="https://www.memevz.h10.de/teamPP.php?op=usernameexist&username=" + username;
        String result;

        try {
            result = new ApiHelper(url).execute().get();
        }catch (ExecutionException | InterruptedException e) {
            e.fillInStackTrace();
            result = null;
        }

        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(result);
            userExistance = jsonObject.getBoolean("result");
        }catch (JSONException e) {
            Log.e(TAG, "doesUserNameExist: can't convert answer to json", e);
        }
        return userExistance;
    }

    @Override
    public boolean doesPasswordMatchUsername(String username, String password) {
        boolean passwortMatchUser = false;
        String url ="https://www.memevz.h10.de/teamPP.php?op=passwordmatchusername&username=" + username + "&password=" + password;
        String result;

        try {
            result = new ApiHelper(url).execute().get();
        }catch (ExecutionException | InterruptedException e) {
            e.fillInStackTrace();
            result = null;
        }

        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(result);
            passwortMatchUser = jsonObject.getBoolean("result");
        }catch (JSONException e) {
            Log.e(TAG, "doesPasswordMatchUsername: can't convert answer to json", e);
        }
        return passwortMatchUser;
    }

    @Override
    public boolean doesPasswordMatchEmail(String email, String password) {
        boolean passwortMatchEmail = false;
        String url ="https://www.memevz.h10.de/teamPP.php?op=passwordmatchemail&email=" + email + "&password=" + password;
        String result;

        try {
            result = new ApiHelper(url).execute().get();
        }catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            result = null;
        }

        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(result);
            passwortMatchEmail = jsonObject.getBoolean("result");
        }catch (JSONException e) {
            Log.e(TAG, "doesPasswordMatchUsername: can't convert answer to json", e);
        }
        return passwortMatchEmail;
    }

    @Override
    public boolean isVerified(String username) {
        String url = "https://www.memevz.h10.de/teamPP.php?op=isusernameverified&username=" + username;
        int isVerified = 0;
        String result;
        try {
            result = new ApiHelper(url).execute().get();
        }catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            result = null;
        }
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(result);
            isVerified = jsonObject.getInt("result");
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return IntToBooleanConverter.convertIntToBoolean(isVerified);

    }

    @Override
    public boolean doesTeamExist(int teamID) {
        String url = "https://www.memevz.h10.de/teamPP.php?op=doesTeamExist&team_id=" + teamID;
        String result;
        try {
            result = new ApiHelper(url).execute().get();
        }catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            result = null;
        }
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(result);
            return jsonObject.getBoolean("result");
        }catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String createTeam(Team team) {
        String url = "https://www.memevz.h10.de/teamPP.php?op=registerTeam&team_id=" + team.getTeamID() + "&teamname=" + team.getTeamName() + "&description=" + team.getDescription();
        String result = null;
        try {
            result = new ApiHelper(url).execute().get();
        }catch(InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        try {
            return new JSONObject(result).getString("status");
        }catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int getMaxTeamID() {
        String url = "https://www.memevz.h10.de/teamPP.php?op=maxTeamID";
        String result;
        try {
            result = new ApiHelper(url).execute().get();
        }catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            result = null;
        }
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(result);
            return jsonObject.getInt("team_id");
        }catch (JSONException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public void registerTeam(Team team) {
        String url ="https://www.memevz.h10.de/teamPP.php?op=registerTeam&teamname=" + URLHelper.convertString(team.getTeamName()) + "&description=" + URLHelper.convertString(team.getDescription()) + "&team_id=" + team.getTeamID().getCode() + "&pin=" + team.getPin()  ;
        String result;
        Log.i(TAG, "registerTeam: " + url);
        try {
            result = new ApiHelper(url).execute().get();
        }catch (ExecutionException | InterruptedException e) {
            e.fillInStackTrace();
            result = null;
        }

    }

    @Override
    public void addConnection(int userId, int teamId, Rank rank) {
        String url ="https://www.memevz.h10.de/teamPP.php?op=addConnection&team_id=" + teamId + "&user_id=" + userId + "&rank=" + rank.rank;
        String result;
        Log.i(TAG, "registerTeam: " + url);
        try {
            result = new ApiHelper(url).execute().get();
        }catch (ExecutionException | InterruptedException e) {
            e.fillInStackTrace();
            result = null;
        }
    }
}