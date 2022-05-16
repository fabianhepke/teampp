package com.example.team.help;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.team.database.DatePromiseRepositoryImpl;
import com.example.team.database.TeamDateRepositoryImpl;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.teampp.usecase.teamdate.DatesOfTeam;
import com.teampp.usecase.teamdate.PromiseTeamDate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class InsertDates {

    private final Context context;
    private final TeamDateRepositoryImpl teamDateRepository;
    private final DatesOfTeam getDatesOfTeamUseCase;
    private final Class dateActivity;
    private final DatePromiseRepositoryImpl datePromiseRepository;
    private final int userID;
    private final Class homeActivity;


    public InsertDates(Context context, TeamDateRepositoryImpl teamDateRepository, DatePromiseRepositoryImpl datePromiseRepository, int teamID, int userID, Class dateActivity, Class homeActivity) {
        this.context = context;
        this.dateActivity = dateActivity;
        this.userID = userID;
        this.teamDateRepository = teamDateRepository;
        this.datePromiseRepository = datePromiseRepository;
        this.getDatesOfTeamUseCase = new DatesOfTeam(teamDateRepository, teamID);
        this.homeActivity = homeActivity;
    }

    public void insertDates(LinearLayout container) {
        while (!getDatesOfTeamUseCase.isFinished()) {
            insertOneDate(container);
            getDatesOfTeamUseCase.nextDate();
        }
    }

    private void insertOneDate(LinearLayout container) {
        MaterialCardView cardView = GetUIElement.getCardView(context, MATCH_PARENT, WRAP_CONTENT, true, 10);
        setCardViewColors(cardView);
        setOnClickEvent(cardView, getDatesOfTeamUseCase.getDateID());
        LinearLayout dateContainer = GetUIElement.getLinearlayout(context, LinearLayout.VERTICAL, MATCH_PARENT, WRAP_CONTENT, 0, 20);
        LinearLayout infoContainer = GetUIElement.getLinearlayout(context, LinearLayout.HORIZONTAL, MATCH_PARENT, WRAP_CONTENT, 0, 0);
        LinearLayout textContainer = GetUIElement.getLinearlayout(context, LinearLayout.VERTICAL, 1, WRAP_CONTENT, 6, 0);
        LinearLayout buttonContainer = GetUIElement.getLinearlayout(context, LinearLayout.HORIZONTAL, MATCH_PARENT, WRAP_CONTENT, 0, 0);
        TextView dateTitle = getDateTitleView();
        TextView dateDate = getDateDateView();
        TextView place = getPlaceView();
        TextView remainingTime = getRemainingTimeView();
        Button promiseButton = getButton(Color.GREEN, "Zusagen");
        Button cancelButton = getButton(Color.RED, "Absagen");
        setUpButtons(promiseButton, cancelButton, getDatesOfTeamUseCase.getDateID());
        buttonContainer.addView(cancelButton);
        buttonContainer.addView(promiseButton);
        textContainer.addView(dateTitle);
        textContainer.addView(dateDate);
        textContainer.addView(place);
        infoContainer.addView(textContainer);
        infoContainer.addView(remainingTime);
        dateContainer.addView(infoContainer);
        dateContainer.addView(buttonContainer);
        cardView.addView(dateContainer);
        container.addView(cardView);
    }

    private void setUpButtons(Button promiseButton, Button cancelButton, int dateID) {
        PromiseTeamDate promiseTeamDate = new PromiseTeamDate(datePromiseRepository);
        promiseButton.setOnClickListener(v -> {
            promiseTeamDate.promiseTeamDate(dateID, userID, true);
            ChangeActivity.changeActivity(context, homeActivity);
        });
        cancelButton.setOnClickListener(v -> {
            promiseTeamDate.promiseTeamDate(dateID, userID, false);
            ChangeActivity.changeActivity(context, homeActivity);
        });
    }

    private TextView getPlaceView() {
        TextView place = new TextView(context);
        if (getDatesOfTeamUseCase.getPlace().equals("Daheim")) {
            place.setText("Daheim");
            return place;
        }
        String adressString = getDatesOfTeamUseCase.getPLZ() + " "
                + getDatesOfTeamUseCase.getPlace() + "\n"
                + getDatesOfTeamUseCase.getStreet() + " "
                + getDatesOfTeamUseCase.getHnr();
        place.setText(adressString);
        return place;
    }

    private Button getButton(int color, String text) {
        MaterialButton button = new MaterialButton(context);
        button.setText(text);
        button.setTextColor(color);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT, 3);
        lp.setMargins(10, 0, 10, 0);
        button.setBackgroundColor(Color.WHITE);
        button.setLayoutParams(lp);
        button.setStrokeWidth(4);
        button.setStrokeColor(ColorStateList.valueOf(color));
        return button;
    }

    private TextView getRemainingTimeView() {
        TextView remainingTimeView = new TextView(context);
        remainingTimeView.setTextSize(TypedValue.COMPLEX_UNIT_PT, 9);
        remainingTimeView.setTextColor(Color.BLACK);
        remainingTimeView.setText(getRemainingTime());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(1, WRAP_CONTENT, 3);
        remainingTimeView.setLayoutParams(lp);
        return remainingTimeView;
    }

    private String getRemainingTime() {
        Date dateDate = getDatesOfTeamUseCase.getDate();
        Date nowDate = new Date(System.currentTimeMillis());
        long difference = dateDate.getTime() - nowDate.getTime();
        long dayDifference = TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS);
        String time = "in ";
        if (dayDifference > 0) {
            time += (dayDifference + " Tagen\n");
        }
        difference -= dayDifference *1000*60*60*24;
        long hourDifference = TimeUnit.HOURS.convert(difference, TimeUnit.MILLISECONDS);
        difference -= hourDifference *1000*60*60;
        long minDifference = TimeUnit.MINUTES.convert(difference, TimeUnit.MILLISECONDS);
        time += (((hourDifference - 1)%24) + "h " + (minDifference + 1) + "min");
        return time;
    }

    private TextView getDateDateView() {
        TextView dateView = new TextView(context);
        dateView.setText(convertDateToString(getDatesOfTeamUseCase.getDate()));
        dateView.setTextSize(TypedValue.COMPLEX_UNIT_PT, 8);
        return dateView;
    }

    private String convertDateToString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy, HH:mm");
        return dateFormat.format(date) + " Uhr";
    }

    private TextView getDateTitleView() {
        TextView titleView = new TextView(context);
        titleView.setText(getDatesOfTeamUseCase.getDateName());
        titleView.setTextColor(Color.BLACK);
        titleView.setTextSize(TypedValue.COMPLEX_UNIT_PT, 12);
        titleView.setTypeface(null , Typeface.BOLD);
        return titleView;
    }

    private MaterialCardView getCardView() {
        MaterialCardView cardView = new MaterialCardView(context);
        cardView.setClickable(true);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, WRAP_CONTENT);
        lp.setMargins(5,10,5,10);
        cardView.setLayoutParams(lp);
        return cardView;
    }

    private void setCardViewColors(MaterialCardView cardView) {
        cardView.setStrokeWidth(5);
        if (!datePromiseRepository.doesUserPromisedDate(getDatesOfTeamUseCase.getDateID(), userID)) {
            cardView.setStrokeColor(Color.GRAY);
            return;
        }
        if (datePromiseRepository.isDatePromisePositive(getDatesOfTeamUseCase.getDateID(), userID)) {
            cardView.setStrokeColor(Color.GREEN);
            return;
        }
        cardView.setStrokeColor(Color.RED);
    }

    private void setOnClickEvent(MaterialCardView cardView, int dateID) {
        cardView.setOnClickListener(v -> {
            Intent intent = new Intent(context, dateActivity);
            intent.putExtra("date_id", dateID);
            context.startActivity(intent);
        });
    }


}
