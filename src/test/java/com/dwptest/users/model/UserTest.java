package com.dwptest.users.model;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    public void testConstructor(){
        User user = new User(
                135,
                "Mechelle",
                "Boam",
                "mboam3q@thetimes.co.uk",
                "113.71.242.187",
                -6.5115909,
                105.652983
        );
        assertThat("user id has been set",user.getId(),is(135));
        assertThat("user first name has been set",user.getFirstName(),is("Mechelle"));
        assertThat("user last name has been set",user.getLastName(),is("Boam"));
        assertThat("user email has been set",user.getEmail(),is("mboam3q@thetimes.co.uk"));
        assertThat("user ip address has been set",user.getIpAddress(),is("113.71.242.187"));
        assertThat("user latitude has been set",user.getLatitude(),is(-6.5115909));
        assertThat("user longitude has been set",user.getLongitude(),is(105.652983));
    }

}