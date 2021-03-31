package com.dwptest.users.controller;

import com.dwptest.users.model.User;
import com.dwptest.users.service.UsersInAndAroundLondonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller defining the Rest endpoint of the application
 */
@RestController
public class UsersController {

    private final UsersInAndAroundLondonService usersInAndAroundLondonService;

    @Autowired
    public UsersController(UsersInAndAroundLondonService usersInAndAroundLondonService){
        this.usersInAndAroundLondonService = usersInAndAroundLondonService;
    }

    @GetMapping(value="/london-users", produces={"application/json"})
    public ResponseEntity<List<User>> getLondonUsers(){

        List<User> londonUsers = usersInAndAroundLondonService.getUsersInAndAroundLondonService();
        return ResponseEntity.ok().body(londonUsers);
    }

}
