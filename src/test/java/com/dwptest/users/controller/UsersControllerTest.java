package com.dwptest.users.controller;

import com.dwptest.users.model.User;
import com.dwptest.users.service.UsersInAndAroundLondonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UsersControllerTest {

    private UsersInAndAroundLondonService usersInAndAroundLondonService;
    private UsersController usersController;

    @BeforeEach
    void setUp() {
        usersInAndAroundLondonService = mock(UsersInAndAroundLondonService.class);
        usersController = new UsersController(usersInAndAroundLondonService);
    }

    @Test
    public void givenNoUsersExists_GetLondonUsers_ReturnsEmptyList() {
        when(usersInAndAroundLondonService.getUsersInAndAroundLondonService()).thenReturn(new ArrayList<>());
        ResponseEntity<List<User>> responseEntity = usersController.getLondonUsers();
        assertThat("User Controller returns empty list when no users found", responseEntity.getBody(), is(new ArrayList<>()));
    }

    @Test
    public void givenOneMockedUser_GetLondonUsers_ReturnsOneUserInList() {

        User userMechelle = new User(
                135,
                "Mechelle",
                "Boam",
                "mboam3q@thetimes.co.uk",
                "113.71.242.187",
                -6.5115909,
                105.652983
        );
        List<User> users = new ArrayList<>();
        users.add(userMechelle);
        when(usersInAndAroundLondonService.getUsersInAndAroundLondonService()).thenReturn(users);
        ResponseEntity<List<User>> responseEntity = usersController.getLondonUsers();
        assertThat("User Controller returns expected list", responseEntity.getBody(), is(users));
    }

    @Test
    public void givenMultipleMockedUser_GetLondonUsers_ReturnsMultipleUsersInList() {

        User userMechelle = new User(
                135,
                "Mechelle",
                "Boam",
                "mboam3q@thetimes.co.uk",
                "113.71.242.187",
                -6.5115909,
                105.652983
        );
        List<User> users = new ArrayList<>();
        users.add(userMechelle);
        users.add(userMechelle);
        users.add(userMechelle);
        users.add(userMechelle);
        when(usersInAndAroundLondonService.getUsersInAndAroundLondonService()).thenReturn(users);
        ResponseEntity<List<User>> responseEntity = usersController.getLondonUsers();
        assertThat("User Controller returns expected list", responseEntity.getBody(), is(users));
    }
}