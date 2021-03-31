package com.dwptest.users.service;

import com.dwptest.users.model.User;
import org.apache.lucene.util.SloppyMath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service to return all users from fifty miles radius of london
 */
@Service
public class UsersWithinFiftyMilesRadiusService {

    private final String serviceUrl;
    private final RestTemplate restTemplate;

    @Autowired
    public UsersWithinFiftyMilesRadiusService(RestTemplate restTemplate, final @Value("${service_url}") String serviceUrl) {
        this.restTemplate = restTemplate;
        this.serviceUrl = serviceUrl;
    }

    public List<User> getUsersInRadius(final double london_latitude, final double london_longitude, final double radius) {
        final String requestUrl = serviceUrl + "users";
        final ResponseEntity<User[]> responseEntity = restTemplate.exchange(requestUrl, HttpMethod.GET, null, User[].class);
        User[] users = responseEntity.getBody();
        List<User> usersWithinRadius = users != null ? Arrays.asList(users) : new ArrayList<>();
        return usersWithinRadius.stream().filter(user -> SloppyMath.haversinMeters(london_latitude, london_longitude, user.getLatitude(), user.getLongitude()) <= radius).collect(Collectors.toList());
    }
}
