package com.teampp.plugin.help;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.teampp.domain.user.UserRepository;

import java.util.ArrayList;

import static android.view.ViewGroup.LayoutParams.*;

public class InsertUsersOfDate {
    private final UserRepository userRepository;
    private LinearLayout container;
    private final int dateID;
    private final Context context;

    public InsertUsersOfDate(Context context, UserRepository userRepository, int dateID) {
        this.userRepository = userRepository;
        this.dateID = dateID;
        this.context = context;
    }


    public void insertPromisedUsers(LinearLayout container) {
        this.container = container;
        ArrayList<String> usernames = userRepository.getPromisedUsers(dateID);
        insertUsersIntoContainer(container, usernames);
    }

    public void insertCanceledUsers(LinearLayout container) {
        this.container = container;
        ArrayList<String> usernames = userRepository.getCanceledUsers(dateID);
        insertUsersIntoContainer(container, usernames);
    }

    private void insertUsersIntoContainer(LinearLayout container, ArrayList<String> usernames) {
        LinearLayout infoContainer = GetUIElement.getLinearlayout(context, LinearLayout.VERTICAL, MATCH_PARENT, WRAP_CONTENT, 0, 10);
        container.addView(infoContainer);
        for (int i = 0; i < usernames.size(); i++) {
            CardView cardView = GetUIElement.getCardView(context, MATCH_PARENT, WRAP_CONTENT, false, 10);
            LinearLayout cardViewContainer = GetUIElement.getLinearlayout(context, LinearLayout.VERTICAL, MATCH_PARENT, WRAP_CONTENT, 0, 20);
            TextView usernameTextView = GetUIElement.getTextView(context, usernames.get(i));
            infoContainer.addView(cardView);
            cardView.addView(cardViewContainer);
            cardViewContainer.addView(usernameTextView);
        }
    }

}