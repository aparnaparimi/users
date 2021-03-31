package com.dwptest.users.service;

import com.dwptest.users.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UsersInAndAroundLondonServiceTest {
    private UsersFromCityService usersFromCityService;
    private UsersWithinFiftyMilesRadiusService usersWithinFiftyMilesRadiusService;
    private UsersInAndAroundLondonService usersInAndAroundLondonService;

    @BeforeEach
    void setUp() {
        usersFromCityService = mock(UsersFromCityService.class);
        usersWithinFiftyMilesRadiusService = mock((UsersWithinFiftyMilesRadiusService.class));
        usersInAndAroundLondonService = new UsersInAndAroundLondonService(usersFromCityService, usersWithinFiftyMilesRadiusService);

    }
    @Test
        public void givenNoUsersFromBothServices_GetUsersInAndAroundLondon_ReturnsEmptyList() {

        when(usersFromCityService.getUsersFromCity("London"))
                .thenReturn(new ArrayList<>());
        when(usersWithinFiftyMilesRadiusService.getUsersInRadius(anyDouble(), anyDouble(), anyDouble()))
                .thenReturn(new ArrayList<>());

        List<User> actualUsers = usersInAndAroundLondonService.getUsersInAndAroundLondonService();

        assertThat("Service returns empty list when both called services return empty lists",
                actualUsers, is(new ArrayList()));
    }

        @Test
        public void givenOneUserAndNoUser_GetUsersInAndAroundLondon_ReturnsOneUserInList() {

        User userJohn = new User(
                1,
                "John",
                "Smith",
                "email@domain.com",
                "0.0.0.0",
                123.456,
                654.321);

        List<User> londonUsers = new ArrayList<>();
        londonUsers.add(userJohn);

        when(usersFromCityService.getUsersFromCity("London"))
                .thenReturn(londonUsers);
        when(usersWithinFiftyMilesRadiusService.getUsersInRadius(anyDouble(), anyDouble(), anyDouble()))
                .thenReturn(new ArrayList<>());


        List<User> actualUsers = usersInAndAroundLondonService.getUsersInAndAroundLondonService();

        assertThat("Service returns one user when one users in London and none within 50 miles",
                actualUsers, is(londonUsers));
    }

        @Test
        public void givenNoUserAndOneUser_GetUsersInAndAroundLondon_ReturnsOneUserInList() {

        User userJohn = new User(
                1,
                "John",
                "Smith",
                "email@domain.com",
                "0.0.0.0",
                123.456,
                654.321);

        List<User> usersWithinFiftyMiles = new ArrayList<>();
        usersWithinFiftyMiles.add(userJohn);

        when(usersFromCityService.getUsersFromCity("London"))
                .thenReturn(new ArrayList<>());
        when(usersWithinFiftyMilesRadiusService.getUsersInRadius(anyDouble(), anyDouble(), anyDouble()))
                .thenReturn(usersWithinFiftyMiles);


        List<User> actualUsers = usersInAndAroundLondonService.getUsersInAndAroundLondonService();

        assertThat("Service returns one user when no users in London and one within 50 miles",
                actualUsers, is(usersWithinFiftyMiles));
    }

        @Test
        public void givenMultipleNoneOverlappingUserLists_GetUsersInAndAroundLondon_ReturnsMultipleUsersInList() {

        User userJohn = new User(
                1,
                "John",
                "Smith",
                "email@domain.com",
                "0.0.0.0",
                123.456,
                654.321);

        User userJane = new User(
                2,
                "Jane",
                "Smith",
                "email@domain.com",
                "0.0.0.0",
                123.456,
                654.321);

        List<User> londonUsers = new ArrayList<>();
        londonUsers.add(userJohn);

        List<User> usersWithinFiftyMiles = new ArrayList<>();
        usersWithinFiftyMiles.add(userJane);

        when(usersFromCityService.getUsersFromCity("London")).thenReturn(londonUsers);
        when(usersWithinFiftyMilesRadiusService.getUsersInRadius(anyDouble(), anyDouble(), anyDouble()))
                .thenReturn(usersWithinFiftyMiles);

        List<User> actualUsers = usersInAndAroundLondonService.getUsersInAndAroundLondonService();

        assertThat("Returned list has exactly two users in it",
                actualUsers.size(), is(2));
        assertThat("Returned list contains exactly userJohn and userJane ",
                actualUsers, contains(userJohn, userJane));
    }

        @Test
        public void givenMultipleAndOverlappingUserLists_GetUsersInAndAroundLondon_ReturnsMultipleUsersInList() {

        User userJohn = new User(
                1,
                "John",
                "Smith",
                "email@domain.com",
                "0.0.0.0",
                123.456,
                654.321);

        User userJane = new User(
                2,
                "Jane",
                "Smith",
                "email@domain.com",
                "0.0.0.0",
                123.456,
                654.321);

        List<User> londonUsers = new ArrayList<>();
        londonUsers.add(userJohn);
        londonUsers.add(userJane);

        List<User> usersWithinFiftyMiles = new ArrayList<>();
        usersWithinFiftyMiles.add(userJane);

        when(usersFromCityService.getUsersFromCity("London")).thenReturn(londonUsers);
        when(usersWithinFiftyMilesRadiusService.getUsersInRadius(anyDouble(), anyDouble(), anyDouble()))
                .thenReturn(usersWithinFiftyMiles);

        List<User> actualUsers = usersInAndAroundLondonService.getUsersInAndAroundLondonService();

        assertThat("Returned list has exactly two users in it",
                actualUsers.size(), is(2));
        assertThat("Returned list contains exactly userJohn and userJane ",
                actualUsers, contains(userJohn, userJane));
    }
}