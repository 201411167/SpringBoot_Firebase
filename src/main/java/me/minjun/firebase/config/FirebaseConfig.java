package me.minjun.firebase.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
@Slf4j
public class FirebaseConfig {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Bean
    public void firebaseInit() throws IOException {
        FileInputStream serviceAccount = new FileInputStream("src/main/resources/fcm-test-604c4-firebase-adminsdk-ftumb-00f8a5c960.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://fcm-test-604c4.firebaseio.com")
                .build();

        FirebaseApp.initializeApp(options);
        logger.info("Firebase Init : " + FirebaseApp.getApps().toString());
        // Firebase Init : [FirebaseApp{name=[DEFAULT]}]
    }
}
