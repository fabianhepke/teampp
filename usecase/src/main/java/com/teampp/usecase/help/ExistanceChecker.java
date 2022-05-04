package com.teampp.usecase.help;

import com.teampp.domain.user.User;
import com.teampp.domain.user.UserRepository;

public class ExistanceChecker {

    public static boolean doesUsernameExists(User user, UserRepository repository) {
        return repository.doesUserNameExist(user.getUsername().toString());
    }

    public static boolean doesEmailExists(User user, UserRepository repository) {
        return repository.doesUserEmailExist(user.geteMail().toString());
    }
}
