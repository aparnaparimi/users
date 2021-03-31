package com.dwptest.users.service;

import com.dwptest.users.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Service to return all users from london city
 */
@Service
public class UsersFromCityService {

    private final RestTemplate restTemplate;
    private final String serviceUrl;

    @Autowired
    public UsersFromCityService(RestTemplate restTemplate1, final @Value("${service_url}") String serviceUrl) {
        this.restTemplate = restTemplate1;
        this.serviceUrl = serviceUrl;
    }

    public List<User> getUsersFromCity(final String city) {
        final String requestUrl = serviceUrl + "city/" + city + "/users";
        final ResponseEntity<User[]> responseEntity = restTemplate.exchange(requestUrl, HttpMethod.GET, null, User[].class);
        final User[] londonUsers = responseEntity.getBody();
        return londonUsers != null ? Arrays.asList(londonUsers) : new ArrayList<>();
    }
}
