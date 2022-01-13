package com.example.team.database;

import com.example.team.components.User;
import com.example.team.help.EMail;

public interface DatabaseConnection {

    public String registerUser(User user);

    public User login(String username, String password, boolean stayLoggedin);

    public User login(EMail email, String password, boolean stayLoggedin);

    public User getUserByUsername(String username);

    public User getUserByEmail(String email);

    boolean doesUserEmailExist(String usernameOrEmail);

    boolean doesUserNameExist(String usernameOrEmail);

    boolean doesPasswordMatchUsername(String usernameOrEmail, String password);

    boolean doesPasswordMatchEmail(String usernameOrEmail, String password);
}
