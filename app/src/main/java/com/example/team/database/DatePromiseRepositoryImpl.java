package com.example.team.database;

import com.example.team.help.ApiHelper;
import com.teampp.domain.entities.DatePromise;
import com.teampp.domain.repositories.DatePromiseRepository;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class DatePromiseRepositoryImpl implements DatePromiseRepository {

    @Override
    public void addDatePromise(int dateID, int userID, boolean promised) {
        String url ="https://www.memevz.h10.de/teamPP.php?op=addPromise&date_id="
                + dateID
                + "&user_id=" + userID
                + "&answer=" + promised;
        try {
            new ApiHelper(url).execute().get();
        }catch (ExecutionException | InterruptedException e) {
            e.fillInStackTrace();
        }
    }

    @Override
    public void changeDatePromise(int dateID, int userID, boolean promised) {
        String url ="https://www.memevz.h10.de/teamPP.php?op=changePromise&date_id="
                + dateID
                + "&user_id=" + userID
                + "&answer=" + promised;
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
        boolean promiseExistance = false;
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(result);
            promiseExistance = jsonObject.getBoolean("result");
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return promiseExistance;
    }

    @Override
    public boolean isDatePromisePositive(int dateID, int userID) {
        String url ="https://www.memevz.h10.de/teamPP.php?op=doesUserPromisedTrue&date_id="
                + dateID
                + "&user_id=" + userID;
        String result;
        try {
            result =new ApiHelper(url).execute().get();
        }catch (ExecutionException | InterruptedException e) {
            e.fillInStackTrace();
            result = null;
        }
        boolean promise = false;
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(result);
            promise = jsonObject.getBoolean("promise");
        }catch (JSONException e) {
            e.printStackTrace();
        }

        return promise;
    }
}
