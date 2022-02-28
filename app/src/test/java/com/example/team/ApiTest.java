package com.example.team;
import com.example.team.components.Teams;
import com.example.team.components.User;
import com.example.team.database.PhpConnection;
import com.example.team.help.EMailHelper;
import com.example.team.help.Token;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = 28)
public class ApiTest {

    @Test
    public void getTeams() throws JSONException {
        PhpConnection conn = new PhpConnection();
        Teams teams = conn.getTeamsOfUser(30);
        assertEquals(teams.getTeams().get(0).getTeamID().toInt(), 100001);
    }

    @Test
    public void getTeamnameTest() {
        PhpConnection conn = new PhpConnection();
        assertEquals("FC Bayern München", conn.getTeamName(100002));
    }

    @Test
    public void failedRegisterTest() {
        PhpConnection conn = new PhpConnection();

        //Test existing username -> status: fail
        User user = new User("admin", "administrator@mail.de", "admin");
        user.setLoginToken(new Token());
        user.setVerifyToken(new Token());
        String expected = "fail";
        assertEquals(expected, conn.registerUser(user));

        //Test existing email -> status: fail
        user.setUsername("administrator");
        user.seteMail("admin@mail.de");
        assertEquals(expected, conn.registerUser(user));
    }

    @Test
    public void successfullRegisterTest() {
        PhpConnection conn = new PhpConnection();

        //ceate username and email as UIID so that username and email is not in database
        User user = new User(UUID.randomUUID().toString(), UUID.randomUUID().toString(), "12345678");
        user.setVerifyToken(new Token());
        user.setLoginToken(new Token());
        String expected = "successfull";
        assertEquals(expected, conn.registerUser(user));
    }

    @Test
    public void successfullLoginTest() {
        PhpConnection conn = new PhpConnection();
        User user = conn.login("derneue", "12345678", true);
        assertEquals(27, user.getUserID());
        user = conn.login(new EMailHelper("hepkefa@gmail.com"), "12345678", true);
        assertEquals(27, user.getUserID());
        user = conn.login("derneue", "12345678", false);
        assertEquals(27, user.getUserID());
        user = conn.login(new EMailHelper("hepkefa@gmail.com"), "12345678", false);
        assertEquals(27, user.getUserID());
    }

    @Test
    public void failLoginTest() {
        PhpConnection conn = new PhpConnection();
        User user = conn.login("admin", "12345678", true);
        assertEquals(-1, user.getUserID());
        user = conn.login("admin", "falschesPW", true);
        assertEquals(-1, user.getUserID());
        user = conn.login(new EMailHelper("admin@mail.com"), "falschesPW", true);
        assertEquals(-1, user.getUserID());
        user = conn.login(new EMailHelper("noAdmin@mail.com"), "12345678", true);
        assertEquals(-1, user.getUserID());
        user = conn.login("nonexist", "12345678", false);
        assertEquals(-1, user.getUserID());
        user = conn.login("admin", "falschesPW", false);
        assertEquals(-1, user.getUserID());
        user = conn.login(new EMailHelper("admin@mail.com"), "falschesPW", false);
        assertEquals(-1, user.getUserID());
        user = conn.login(new EMailHelper("noadmin@gmail.com"), "12345678", false);
        assertEquals(-1, user.getUserID());
    }

    @Test
    public void SuccessfullUserExistsTest() {
        PhpConnection conn = new PhpConnection();
        assertTrue(conn.doesUserNameExist("admin"));
        assertTrue(conn.doesUserEmailExist("admin@mail.de"));
    }
}



