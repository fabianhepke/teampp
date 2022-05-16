package com.teampp.usecase.user;

import com.teampp.domain.user.ConcreteUserBuilder;
import com.teampp.domain.user.User;
import com.teampp.domain.general.exceptions.WrongInputSyntaxException;
import com.teampp.domain.user.UserRepository;

public class LoginUser {
    private final UserRepository repository;
    private User user;
    private boolean isSuccessful;
    private String token;
    private int userID;

    public LoginUser(UserRepository repository) {
        this.repository = repository;
    }


    public void loginUser(boolean stayLoggedIn, String username, String password) {
        user = getUser(username, password);


        User loggedInUser = getLoggedInUser(user, stayLoggedIn);
        if (loggedInUser == null) {
            isSuccessful = false;
            return;
        }
        isSuccessful = true;
        if (loggedInUser.getLoginToken() != null) {
            token = loggedInUser.getLoginToken().toString();
        }
        userID = loggedInUser.getUserID().toInt();
    }

    public String getToken() {
        return token;
    }

    public int getUserID() {
        return userID;
    }

    public boolean wasLoginSuccessful() {
        return isSuccessful;
    }


    private User getLoggedInUser(User user, boolean stayLoggedIn) {
        if (user.geteMail() != null){
            return repository.loginWithEMail(user.geteMail().toString(), user.getPassword().toString(), stayLoggedIn);
        }
        return repository.loginWithUserName(user.getUsername().toString(), user.getPassword().toString(), stayLoggedIn);
    }

    private User getUser(String username, String password) {
        User user;
        try {
            user = new ConcreteUserBuilder()
                    .setEmail(username)
                    .setPassword(password)
                    .build();
        } catch (WrongInputSyntaxException e) {
            user = new ConcreteUserBuilder()
                    .setUsername(username)
                    .setPassword(password)
                    .build();
        }
        return user;
    }
}
