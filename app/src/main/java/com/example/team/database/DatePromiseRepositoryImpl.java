package com.example.team.database;

import com.example.team.help.ApiHelper;
import com.teampp.domain.entities.DatePromise;
import com.teampp.domain.repositories.DatePromiseRepository;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class DatePromiseRepositoryImpl implements DatePromiseRepository {
    @Override
    public void addDatePromise(DatePromise datePromise) {
        String url ="https://www.memevz.h10.de/teamPP.php?op=addPromise&date_id="
                + datePromise.getDateID()
                + "&user_id=" + datePromise.getUserID()
                + "&answer=" + datePromise.isPromise();
        try {
            new ApiHelper(url).execute().get();
        }catch (ExecutionException | InterruptedException e) {
            e.fillInStackTrace();
        }
    }

    @Override
    public void changeDatePromise(DatePromise datePromise) {
        String url ="https://www.memevz.h10.de/teamPP.php?op=changePromise&date_id="
                + datePromise.getDateID()
                + "&user_id=" + datePromise.getUserID()
                + "&answer=" + datePromise.isPromise();
        try {
            new ApiHelper(url).execute().get();
        }catch (ExecutionException | InterruptedException e) {
            e.fillInStackTrace();
        }
    }

    @Override
    public boolean doesUserPromisedDate(int dateID, int userID) {
        String url ="https://www.memevz.h10.de/teamPP.php?op=doesUserPromised&date_id="
                + dateID
                + "&user_id=" + userID;
        String result;
        try {
            result =new ApiHelper(url).execute().get();
        }catch (ExecutionException | InterruptedException e) {
            e.fillInStackTrace();
            result = null;
        }
        boolean commitExistance = false;
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(result);
            commitExistance = jsonObject.getBoolean("result");
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return commitExistance;
    }
}
