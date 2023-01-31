package com.youtube.jwt.controller;

import com.youtube.jwt.entity.CUser;
import com.youtube.jwt.entity.Product;
import com.youtube.jwt.entity.User;
import com.youtube.jwt.service.UserService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@RestController
@EnableAsync
public class UserController {

    @Autowired
    private UserService userService;

    @PostConstruct
    public void initRoleAndUser() {
        userService.initRoleAndUser();
    }
    @Autowired
    private JavaMailSender javaMailSender;

    @Async
    public Future<Void> sendSimpleEmail(String toEmail,
                                        String subject,
                                        String body
    ) {
        System.out.println("Email is call");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("akiladissanayaka255@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);
        javaMailSender.send(message);
        System.out.println("Mail Send...");
        return new AsyncResult<Void>(null);

    }
    @PostMapping({"/registerNewUser"})
    public User registerNewUser(@RequestBody User user) {
//        sendSimpleEmail(user.getUsermail(),"Test subject","Successfully registerd");
        System.out.println("Email is end call");

        return userService.registerNewUser(user);

    }
    @PostMapping({"/registerNewUsersList"})
    public void registerNewUsersList() throws IOException {
        Path path = Path.of("src","main","resources","Users.csv");
        Files.lines(path)
                .skip(1)
                .map(UserController::getCUser)
                .forEach((user)-> {
                    System.out.println(user);
                    userService.registerNewUser(user);
                });

    }
    private static User getCUser(String line) {
        String[] fields=line.split(",");
        if(fields.length!=5)
            throw new RuntimeException("Invalid CSV line - " + line);
        String userName=fields[0];
        String userFirstName=fields[1];
        String userLastName=fields[2];
        String userPassword=fields[3];
        String usermail=fields[4];
        User user= new User();
        user.setUserName(userName);
        user.setUserFirstName(userFirstName);
        user.setUserLastName(userLastName);
        user.setUserPassword(userPassword);
        user.setUsermail(usermail);
        return user;
    }

    @GetMapping({"/forAdmin"})
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin(){
        return "This URL is only accessible to the admin";
    }

    @GetMapping({"/forUser"})
    @PreAuthorize("hasRole('User')")
    public String forUser(){
        return "This URL is only accessible to the user";
    }
}
