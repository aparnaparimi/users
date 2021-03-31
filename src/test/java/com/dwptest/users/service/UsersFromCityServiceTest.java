package com.dwptest.users.service;

import com.dwptest.users.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UsersFromCityServiceTest {

    private RestTemplate restTemplate;
    private UsersFromCityService usersFromCityService;
    private String serviceUrl;

    @BeforeEach
    void setUp() {
        restTemplate = mock(RestTemplate.class);
        String url = "https://bpdts-test-app.herokuapp.com/";
        serviceUrl = url + "city/London/users";
        usersFromCityService = new UsersFromCityService(restTemplate, url);
    }

    @Test
    public void givenMockedRestTemplateReturnsOneUser_GetUsersFromCityReturnsSingleUser() {
        User userMechelle = new User(
                135,
                "Mechelle",
                "Boam",
                "mboam3q@thetimes.co.uk",
                "113.71.242.187",
                -6.5115909,
                105.652983
        );
        List<User> expectedUsers = new ArrayList<>();
        expectedUsers.add(userMechelle);

        User[] users = new User[1];
        users[0] = userMechelle;
        ResponseEntity<User[]> responseEntity = new ResponseEntity<>(users, HttpStatus.OK);
        when(restTemplate.exchange(serviceUrl, HttpMethod.GET, null, User[].class)).thenReturn(responseEntity);
        List<User> actualUsers = usersFromCityService.getUsersFromCity("London");
        assertThat("when rest template mocked users are returned", actualUsers, is(expectedUsers));
    }

    @Test
    public void givenMockedRestTemplateReturnsMultipleUsers_GetUsersFromCityReturnsMultipleUsers() {
        User userMechelle = new User(
                135,
                "Mechelle",
                "Boam",
                "mboam3q@thetimes.co.uk",
                "113.71.242.187",
                -6.5115909,
                105.652983
        );
        List<User> expectedUsers = new ArrayList<>();
        expectedUsers.add(userMechelle);
        expectedUsers.add(userMechelle);
        expectedUsers.add(userMechelle);
        expectedUsers.add(userMechelle);

        User[] users = new User[4];
        users[0] = userMechelle;
        users[1] = userMechelle;
        users[2] = userMechelle;
        users[3] = userMechelle;
        ResponseEntity<User[]> responseEntity = new ResponseEntity<>(users, HttpStatus.OK);
        when(restTemplate.exchange(serviceUrl, HttpMethod.GET, null, User[].class)).thenReturn(responseEntity);
        List<User> actualUsers = usersFromCityService.getUsersFromCity("London");
        assertThat("when rest template mocked 4 users are returned", actualUsers.size(), is(4));
        assertThat("when rest template mocked returned users are same", actualUsers, is(expectedUsers));
    }

    @Test
    public void givenMockedBadRequest_GetUsersFromCityReturnsEmptyList() {
        ResponseEntity<User[]> responseEntity = new ResponseEntity<>(new User[0], HttpStatus.BAD_REQUEST);
        when(restTemplate.exchange(serviceUrl, HttpMethod.GET, null, User[].class)).thenReturn(responseEntity);
        List<User> actualUsers = usersFromCityService.getUsersFromCity("London");
        assertThat("Service returns empty list", actualUsers, is(new ArrayList<>()));
    }
}