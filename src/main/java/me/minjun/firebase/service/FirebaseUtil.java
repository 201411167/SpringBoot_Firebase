package me.minjun.firebase.service;

import com.google.firebase.auth.*;
import com.google.firebase.messaging.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class FirebaseUtil {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public String createCustomToken(String uid) throws FirebaseAuthException {
        String customToken = FirebaseAuth.getInstance().createCustomToken(uid);
        logger.info("Creating custom token : " + customToken);
        return customToken;
    }

    public String getUid(String idToken) throws FirebaseAuthException {
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
        String uid = decodedToken.getUid();
        logger.info("Getting uid from idToken : " + uid);
        return uid;
    }

    public List<UserRecord> getUserRecords() throws FirebaseAuthException {
        List<UserRecord> records = new ArrayList<>();
        ListUsersPage page = FirebaseAuth.getInstance().listUsers(null);
        while(page != null){
            for(ExportedUserRecord user : page.getValues()){
                records.add(user);
            }
            page = page.getNextPage();
        }
        logger.info("Getting user list : " + records.toString());
        return records;
    }

    public void sendFCMMessage(String token, String title, String content) throws FirebaseMessagingException {
        Message msg = Message.builder()
                .setAndroidConfig(AndroidConfig.builder()
                    .setTtl(3600*1000)
                    .setPriority(AndroidConfig.Priority.NORMAL)
                    .setNotification(AndroidNotification.builder()
                        .setTitle(title)
                        .setBody(content)
                        .setIcon("stock_ticker_update")
                        .setColor("#f45342")
                        .build())
                    .build())
                .setToken(token)
                .build();

        String response = FirebaseMessaging.getInstance().send(msg);
        logger.info("Sending message to android : " + response);
    }
}
