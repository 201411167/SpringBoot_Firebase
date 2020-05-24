package me.minjun.firebase.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService service;

    @Before
    public void init(){
        service.deleteUser("test_email");
    }

    @After
    public void cleanUp(){
        service.deleteUser("test_email");
    }

    @Test
    public void 유저_서비스_테스트(){
        service.insertUser("test_email","test_name");
        int size = service.listUser().size();
        String email = service.findUser("test_email").getEmail();
        String name = service.findUser("test_email").getName();

        assertThat(size, not(0));
        assertThat(email, is("test_email"));
        assertThat(name, is("test_name"));
    }

    @Test
    public void 유저_토큰_테스트(){
        service.insertUser("test_email", "test_name");
        service.addIdTokenToUser("test_email", "test_token");
        String token = service.getTokenByEmail("test_email");

        assertThat(token, is("test_token"));
    }

}