package study.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import study.model.User;

import static org.junit.Assert.*;

public class UserTest {


    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getX() {
        new User().getLogin();
    }
    @Test
    public void getX2() {
        int actual = new User().getId();
        assertEquals("getX should be return 999", actual, actual);
    }
    @Test
    public void getX3() {
        new User().getLogin();
    }
}