package com.example.team.database;

import android.util.Log;

import com.example.team.help.ApiHelper;
import com.example.team.help.JsonHelper;
import com.example.team.help.URLHelper;
import com.teampp.domain.team.ConcreteTeamBuilder;
import com.teampp.domain.team.Team;
import com.teampp.domain.user.Token;
import com.teampp.domain.user.User;
import com.teampp.domain.user.UserRepository;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static android.content.ContentValues.TAG;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public boolean registerUser(String username, String password, String eMail, String loginToken, String rank, String name) {
        String result = null;
        String url ="https://www.memevz.h10.de/teamPP.php?op=register&username="
                + username
                + "&password=" + password
                + "&email=" + eMail
                + "&login_token=" + loginToken
                + "&rank=" + rank
                + "&name=" + URLHelper.convertStringForUrl(name);
        try {
            result = new ApiHelper(url).execute().get();
        }catch (ExecutionException | InterruptedException e) {
            e.fillInStackTrace();
        }
        JSONObject jsonObject;
        boolean isSuccessfull = false;
        try {
            jsonObject = new JSONObject(result);
            isSuccessfull = jsonObject.getString("status").equals("successfull");
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return isSuccessfull;
    }

    @Override
    public void changeUserData(int userID, String username, String password, String eMail, String name) {
        String url ="https://www.memevz.h10.de/teamPP.php?op=changeUserData&user_id="
                + userID
                + "username=" + username
                + "&password=" + password
                + "&email=" + eMail
                + "&name=" + name;
        url = URLHelper.convertStringForUrl(url);
        try {
            new ApiHelper(url).execute().get();
        }catch (ExecutionException | InterruptedException e) {
            e.fillInStackTrace();
        }
    }

    @Override
    public void changeUserLoginToken(int userID, Token newToken) {
        String url ="https://www.memevz.h10.de/teamPP.php?op=changeUserLogin&user_id="
                + userID
                + "login_token=" + newToken;
        try {
            new ApiHelper(url).execute().get();
        }catch (ExecutionException | InterruptedException e) {
            e.fillInStackTrace();
        }
    }

    @Override
    public User loginWithUserName(String username, String password, boolean stayLoggedIn) {
        String result = null;
        User user = new User();
        String url = "https://www.memevz.h10.de/teamPP.php?op=login&username="
                + username
                + "&password=" + password
                + "&login_token=" + stayLoggedIn;

        try {
            result = new ApiHelper(url).execute().get();
        } catch (ExecutionException | InterruptedException e) {
            e.fillInStackTrace();
            result = null;
        }
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(result);
            user = JsonHelper.getUserOfJson(jsonObject);
        } catch (JSONException e) {
            Log.e(TAG, "login: couldn't convert answer to json", e);
        }
        return user;
    }

    @Override
    public User loginWithEMail(String eMail, String password, boolean stayLoggedIn) {
        User user = new User();
        String result = null;
        String url = "https://www.memevz.h10.de/teamPP.php?op=login&email="
                + eMail
                + "&password=" + password
                + "&login_token=" + stayLoggedIn;
        try {
            result = new ApiHelper(url).execute().get();
        } catch (ExecutionException | InterruptedException e) {
            e.fillInStackTrace();
            result = null;
        }
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(result);
            user = JsonHelper.getUserOfJson(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean doesUserEmailExist(String userEmail) {
        boolean userExistance = false;
        String url ="https://www.memevz.h10.de/teamPP.php?op=emailexist&email=" + userEmail;
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
            e.printStackTrace();
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
            e.printStackTrace();
        }
        return userExistance;
    }

    @Override
    public boolean doesPasswordMatchUserName(String username, String password) {
        String result = null;
        String url = "https://www.memevz.h10.de/teamPP.php?op=passwordmatchusername&username="
                + username
                + "&password=" + password;
        try {
            result = new ApiHelper(url).execute().get();
        } catch (ExecutionException | InterruptedException e) {
            e.fillInStackTrace();
            result = null;
        }

        boolean passwortMatchUser = false;

        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(result);
            passwortMatchUser = jsonObject.getBoolean("result");
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return passwortMatchUser;
    }

    @Override
    public boolean doesPasswordMatchUserEMail(String eMail, String password) {
        String result = null;
        String url = "https://www.memevz.h10.de/teamPP.php?op=passwordmatchemail&username="
                + eMail
                + "&password=" + password;

        try {
            result = new ApiHelper(url).execute().get();
        } catch (ExecutionException | InterruptedException e) {
            e.fillInStackTrace();
            result = null;
        }

        boolean passwortMatchUser = false;

        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(result);
            passwortMatchUser = jsonObject.getBoolean("result");
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return passwortMatchUser;
    }

    @Override
    public boolean doesUserHasTeamConnection(int userID, int teamID) {
        String url = "https://www.memevz.h10.de/teamPP.php?op=doesUserHasTeamConnection&team_id="
                + teamID
                + "&user_id=" + userID;
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
    public boolean doesUserHasTeam(String username) {
        String url = "https://www.memevz.h10.de/teamPP.php?op=doesUserHasTeam&username=" + username;
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
    public boolean isUserLoggedIn(int userID, String loginToken) {
        String url = "https://www.memevz.h10.de/teamPP.php?op=isUserLoggedIn&user_id=" + userID
                + "&login_token=" + loginToken;
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
    public void changeCurrentTeam(int userID, int teamID) {
        String url ="https://www.memevz.h10.de/teamPP.php?op=changeCurrentTeam&user_id="
                + userID
                + "&team_id=" + teamID;
        try {
            new ApiHelper(url).execute().get();
        }catch (ExecutionException | InterruptedException e) {
            e.fillInStackTrace();
        }
    }

    @Override
    public Team getCurrentTeam(int userID) {
        String url = "https://www.memevz.h10.de/teamPP.php?op=getCurrentTeam&user_id=" + userID;
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
            return new ConcreteTeamBuilder().setTeamID(jsonObject.getInt("team_id")).build();
        }catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User getUserByID(int userID) {
        //TODO
        return null;
    }

    @Override
    public ArrayList<String> getPromisedUsers(int dateID) {
        ArrayList<String> names = new ArrayList<>();
        String url = "https://www.memevz.h10.de/teamPP.php?op=getPromisedUsers&date_id=" + dateID;
        String result;
        try {
            result = new ApiHelper(url).execute().get();
            JSONArray jsonArray = new JSONArray(URLHelper.convertUrlString(result));
            for (int i = 0; i < jsonArray.length(); i++) {
                names.add(jsonArray.getJSONObject(i).getString("name"));
            }
            return names;
        }catch (ExecutionException | InterruptedException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<String> getCanceledUsers(int dateID) {
        ArrayList<String> names = new ArrayList<>();
        String url = "https://www.memevz.h10.de/teamPP.php?op=getCanceledUsers&date_id=" + dateID;
        String result;
        try {
            result = new ApiHelper(url).execute().get();
            JSONArray jsonArray = new JSONArray(URLHelper.convertUrlString(result));
            for (int i = 0; i < jsonArray.length(); i++) {
                names.add(jsonArray.getJSONObject(i).getString("name"));
            }
            return names;
        }catch (ExecutionException | InterruptedException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


}
