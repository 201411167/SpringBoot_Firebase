package me.minjun.firebase.service;

import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class FirebaseServiceTest {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FirebaseUtil firebaseUtil;

    @Test
    public void firebase_서비스_테스트() throws FirebaseAuthException {
        String uid = "test_uid";
        String customToken = firebaseUtil.createCustomToken(uid);

        assertNotNull(customToken);

        logger.info("Test for creating customToken : " + customToken);
    }

    @Test
    public void 유저_리스트_테스트() throws FirebaseAuthException {
        List<UserRecord> userRecords = firebaseUtil.getUserRecords();

        assertNotNull(userRecords);

        logger.info("Test for getting user list : " + userRecords.toString());
    }
}