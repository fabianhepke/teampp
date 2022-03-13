package com.example.team.database;

import android.util.Log;

import com.example.team.help.ApiHelper;
import com.example.team.help.JsonHelper;
import com.example.team.help.URLHelper;
import com.teampp.domain.builder.ConcreteTeamBuilder;
import com.teampp.domain.entities.Team;
import com.teampp.domain.entities.User;
import com.teampp.domain.valueobjects.TeamID;
import com.teampp.domain.valueobjects.Token;
import com.teampp.domain.repositories.UserRepository;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

import static android.content.ContentValues.TAG;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public boolean registerUser(User user) {
        String result = null;
        String url ="https://www.memevz.h10.de/teamPP.php?op=register&username="
                + user.getUsername().toString()
                + "&password=" + user.getPassword().toString()
                + "&email=" + user.geteMail().toString()
                + "&login_token=" + user.getLoginToken().toString()
                + "&rank=" + user.getRank()
                + "&name=" + URLHelper.convertStringForUrl(user.getName());
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
    public void changeUserData(User user) {
        String url ="https://www.memevz.h10.de/teamPP.php?op=changeUserData&user_id="
                + user.getUserID().toString()
                + "username=" + user.getUsername().toString()
                + "&password=" + user.getPassword().toString()
                + "&email=" + user.geteMail().toString()
                + "&name=" + user.getName();
        url = URLHelper.convertStringForUrl(url);
        try {
            new ApiHelper(url).execute().get();
        }catch (ExecutionException | InterruptedException e) {
            e.fillInStackTrace();
        }
    }

    @Override
    public void changeUserLoginToken(User user, Token newToken) {
        String url ="https://www.memevz.h10.de/teamPP.php?op=changeUserLogin&user_id="
                + user.getUserID().toString()
                + "login_token=" + user.getLoginToken().toString();
        try {
            new ApiHelper(url).execute().get();
        }catch (ExecutionException | InterruptedException e) {
            e.fillInStackTrace();
        }
    }

    @Override
    public User login(User user, boolean stayLoggedIn) {
        String result = null;
        if (user.getUsername() != null) {
            String url = "https://www.memevz.h10.de/teamPP.php?op=login&username="
                    + user.getUsername().toString()
                    + "&password=" + user.getPassword().toString()
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
        if (user.geteMail() != null) {
            String url = "https://www.memevz.h10.de/teamPP.php?op=login&email="
                    + user.geteMail().toString()
                    + "&password=" + user.getPassword().toString()
                    + "&login_token=" + stayLoggedIn;
            try {
                result = new ApiHelper(url).execute().get();
            } catch (ExecutionException | InterruptedException e) {
                e.fillInStackTrace();
                result = null;
            }
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
    public boolean doesPasswordMatchUser(User user) {
        String result = null;
        if (user.getUsername() != null) {
            String url = "https://www.memevz.h10.de/teamPP.php?op=passwordmatchusername&username="
                    + user.getUsername().toString()
                    + "&password=" + user.getPassword().toString();

            try {
                result = new ApiHelper(url).execute().get();
            } catch (ExecutionException | InterruptedException e) {
                e.fillInStackTrace();
                result = null;
            }
        }
        if (user.geteMail() != null) {
            String url = "https://www.memevz.h10.de/teamPP.php?op=passwordmatchemail&username="
                    + user.getUsername().toString()
                    + "&password=" + user.getPassword().toString();

            try {
                result = new ApiHelper(url).execute().get();
            } catch (ExecutionException | InterruptedException e) {
                e.fillInStackTrace();
                result = null;
            }
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
    public boolean doesUserHasTeamConnection(User user, Team team) {
        String url = "https://www.memevz.h10.de/teamPP.php?op=doesUserHasTeamConnection&team_id="
                + team.getTeamID().toInt()
                + "&user_id=" + user.getUserID().toInt();
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
    public void changeCurrentTeam(User user) {
        String url ="https://www.memevz.h10.de/teamPP.php?op=changeCurrentTeam&user_id="
                + user.getUserID().toInt()
                + "&team_id=" + user.getActualTeamID();
        try {
            new ApiHelper(url).execute().get();
        }catch (ExecutionException | InterruptedException e) {
            e.fillInStackTrace();
        }
    }

    @Override
    public Team getCurrentTeam(User user) {
        String url = "https://www.memevz.h10.de/teamPP.php?op=getCurrentTeam&user_id=" + user.getUserID().toInt();
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
}
