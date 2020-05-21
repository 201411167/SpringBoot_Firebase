package me.minjun.firebase.controller;

import com.google.firebase.auth.FirebaseAuthException;
import lombok.extern.slf4j.Slf4j;
import me.minjun.firebase.service.FirebaseUtil;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class ClientController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    FirebaseUtil firebaseUtil;

    @PostMapping("/request/customToken")
    public Map<String, Object> requestCustomToken(@RequestBody Map<String, Object> param) throws FirebaseAuthException {
        Map<String, Object> rtn = new HashMap<>();

        String email = param.get("email").toString();
        String name = param.get("name").toString();

        String customToken = firebaseUtil.createCustomToken(email);

        rtn.put("result", true);
        rtn.put("customToken", customToken);

        logger.info("Post request from the client for customToken");
        return rtn;
    }
}
