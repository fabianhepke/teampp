package com.teampp.usecase;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.teampp.domain.domainservice.DateConverter;
import com.teampp.domain.repositories.DatePromiseRepository;
import com.teampp.domain.repositories.TeamDateRepository;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class InsertDates {

    private final Context context;
    private final TeamDateRepository teamDateRepository;
    private final DatesOfTeam getDatesOfTeamUseCase;
    private final Class dateActivity;
    private final DatePromiseRepository datePromiseRepository;
    private final int userID;


    public InsertDates(Context context, TeamDateRepository teamDateRepository, DatePromiseRepository datePromiseRepository, int teamID, int userID, Class dateActivity) {
        this.context = context;
        this.dateActivity = dateActivity;
        this.userID = userID;
        this.teamDateRepository = teamDateRepository;
        this.datePromiseRepository = datePromiseRepository;
        getDatesOfTeamUseCase = new DatesOfTeam(teamDateRepository, teamID);
    }

    public void insertDates(LinearLayout container) {
        while (!getDatesOfTeamUseCase.isFinished()) {
            insertOneDate(container);
            getDatesOfTeamUseCase.nextDate();
        }
    }

    private void insertOneDate(LinearLayout container) {
        CardView cardView = getCardView();
        LinearLayout dateContainer = getLinearLayoutDateContainer();
        LinearLayout textContainer = getLinearLayourTextContainer();
        TextView dateTitle = getDateTitleView();
        TextView dateDate = getDateDateView();
        TextView place = getPlaceView();
        TextView remainingTime = getRemainingTimeView();
        //TODO make Buttons work and add to View!
        Button promiseButton = getButton(Color.GREEN, "Zusagen");
        Button cancelButton = getButton(Color.RED, "Absagen");
        textContainer.addView(dateTitle);
        textContainer.addView(dateDate);
        textContainer.addView(place);
        dateContainer.addView(textContainer);
        dateContainer.addView(remainingTime);
        cardView.addView(dateContainer);
        cardView.addView(promiseButton);
        cardView.addView(cancelButton);
        container.addView(cardView);
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
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 3);
        button.setLayoutParams(lp);
        return button;
    }

    private TextView getRemainingTimeView() {
        TextView remainingTimeView = new TextView(context);
        remainingTimeView.setTextSize(TypedValue.COMPLEX_UNIT_PT, 9);
        remainingTimeView.setTextColor(Color.BLACK);
        remainingTimeView.setText(getRemainingTime());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(1, ViewGroup.LayoutParams.WRAP_CONTENT);
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
        dateView.setText(DateConverter.convertDateToViewString(getDatesOfTeamUseCase.getDate()));
        dateView.setTextSize(TypedValue.COMPLEX_UNIT_PT, 8);
        return dateView;
    }

    private TextView getDateTitleView() {
        TextView titleView = new TextView(context);
        titleView.setText(getDatesOfTeamUseCase.getDateName());
        titleView.setTextColor(Color.BLACK);
        titleView.setTextSize(TypedValue.COMPLEX_UNIT_PT, 12);
        titleView.setTypeface(null , Typeface.BOLD);
        return titleView;
    }

    private LinearLayout getLinearLayourTextContainer() {
        LinearLayout tContainer = new LinearLayout(context);
        tContainer.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(1 , ViewGroup.LayoutParams.WRAP_CONTENT, 6);
        tContainer.setLayoutParams(lp);
        return tContainer;
    }

    private LinearLayout getLinearLayoutDateContainer() {
        LinearLayout lContainer = new LinearLayout(context);
        lContainer.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(20, 20, 20, 20);
        lContainer.setLayoutParams(lp);
        return lContainer;
    }

    private CardView getCardView() {
        MaterialCardView cardView = new MaterialCardView(context);
        setCardViewColors(cardView);
        cardView.setClickable(true);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(5,10,5,10);
        cardView.setLayoutParams(lp);
        setOnClickEvent(cardView, getDatesOfTeamUseCase.getDateID());
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
