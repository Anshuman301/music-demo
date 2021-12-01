package com.anshu.springboot.musicDemo.controller;

import java.util.HashMap;
import java.util.Map;

import com.anshu.springboot.musicDemo.db.UserRespo;
import com.anshu.springboot.musicDemo.model.User;
import com.anshu.springboot.musicDemo.utils.JwtTokenUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RepositoryRestController
public class UserCustomController {
    @Autowired
    private UserRespo userRespo;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @RequestMapping(path = "users", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<?> saveUser(@RequestBody User user) {
        System.out.println(user.toString());
        User dbUser = userRespo.findByEmailAddress(user.getEmailAddress());
        if(dbUser == null)
            dbUser = userRespo.save(user);
        String token = jwtTokenUtil.createToken(dbUser);
        Map<String, Boolean> body = new HashMap<>();
        body.put("success", true);
        return ResponseEntity.ok().header("music-token", "Bearer " +token).body(body);
    }

    @RequestMapping(path = "users/logout", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<?> logoutUser() {
        Map<String, Boolean> body = new HashMap<>();
        body.put("success", true);
        return ResponseEntity.ok().body(body);
    }
}
