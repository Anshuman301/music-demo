package com.anshu.springboot.musicDemo.controller.customerController;

import java.util.HashMap;
import java.util.Map;

import com.anshu.springboot.musicDemo.db.UserRespo;
import com.anshu.springboot.musicDemo.model.entity.User;
import com.anshu.springboot.musicDemo.utils.JwtTokenUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/users")
public class UserCustomController {
    @Autowired
    private UserRespo userRespo;
    @Value("${key.secret1}")
    private String SECRET;

    @Bean
    public JwtTokenUtil jwtTokenUtil() {
        return new JwtTokenUtil(SECRET);
    }

    @PostMapping(path = "/isSuccess")
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        System.out.println(user.toString());
        User dbUser = userRespo.findByEmailAddress(user.getEmailAddress());
        if(dbUser == null)
            dbUser = userRespo.save(user);
        String token = jwtTokenUtil().createToken(dbUser);
        return ResponseEntity.ok().header("Authorization", "Bearer " +token).body(new HashMap<>(Map.of("success", true)));
    }

    @GetMapping(path = "/logout")
    public @ResponseBody ResponseEntity<?> logoutUser() {
        return ResponseEntity.ok().body(new HashMap<>(Map.of("success", true)));
    }
}
