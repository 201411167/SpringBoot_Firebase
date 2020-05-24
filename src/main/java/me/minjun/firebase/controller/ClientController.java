package me.minjun.firebase.controller;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.extern.slf4j.Slf4j;
import me.minjun.firebase.domain.user.User;
import me.minjun.firebase.service.FirebaseUtil;
import me.minjun.firebase.service.UserService;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class ClientController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FirebaseUtil firebaseUtil;

    @Autowired
    private UserService userService;

    @PostMapping("/request/customToken")
    public Map<String, Object> requestCustomToken(@RequestBody Map<String, Object> param) throws FirebaseAuthException {
        Map<String, Object> rtn = new HashMap<>();
        rtn.put("result", false);

        String email = param.get("email").toString();
        String name = param.get("name").toString();

        String customToken = firebaseUtil.createCustomToken(email);

        rtn.put("result", true);
        rtn.put("customToken", customToken);

        logger.info("Post request from the client for customToken");

        if(userService.findUser(email) == null) {
            userService.insertUser(email,name);
        }
        userService.addCustomTokenToUser(email, customToken);
        return rtn;
    }

    @PostMapping("/receiveIdToken")
    public Map<String, Object> receivingIdToken(@RequestBody Map<String, Object> param) throws FirebaseAuthException {
        Map<String, Object> rtn = new HashMap<>();
        rtn.put("result", false);

        String idToken = param.get("idToken").toString();
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
        String email = decodedToken.getUid();

        userService.addIdTokenToUser(email, idToken);

        rtn.put("result", true);
        return rtn;
    }

    @PostMapping("/receiveRegistrationToken")
    public Map<String, Object> receiveRegistrationToken(@RequestBody Map<String, Object> param){
        Map<String, Object> rtn = new HashMap<>();
        rtn.put("result", false);

        String registrationToken = param.get("registrationToken").toString();
        String email = param.get("email").toString();

        userService.addRegistrationTokenToUser(email, registrationToken);

        rtn.put("result", true);
        return rtn;
    }

    @GetMapping("/fcmtest/{email}")
    public String fcmTest(@PathVariable String email) throws FirebaseMessagingException {
        String registrationToken = userService.findUser(email).getRegistrationToken();
        String title = "fcm 테스트";
        String content = "hello word";
        firebaseUtil.sendFCMMessage(registrationToken, title, content);
        return "test finished";
    }
}
