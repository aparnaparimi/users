package com.dwptest.users.service;

import com.dwptest.users.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Service to return all users from london city or within fifty miles radius of london
 */
@Service
public class UsersInAndAroundLondonService {

    final private String LONDON="London";
    final private double LONDON_LONGITUDE = -0.1275;
    final private double LONDON_LATITUDE = 51.507222;
    final private double RADIUS = 80467.2;

    private final UsersFromCityService usersFromCityService;
    private final UsersWithinFiftyMilesRadiusService usersWithinFiftyMilesRadiusService;

    @Autowired
    public UsersInAndAroundLondonService(UsersFromCityService usersFromCityService,UsersWithinFiftyMilesRadiusService usersWithinFiftyMilesRadiusService){
        this.usersFromCityService = usersFromCityService;
        this.usersWithinFiftyMilesRadiusService = usersWithinFiftyMilesRadiusService;
    }

    public List<User> getUsersInAndAroundLondonService() {
        List<User> londonUsers = usersFromCityService.getUsersFromCity("London");
        List<User> usersWithInFiftyMilesOfLondon = usersWithinFiftyMilesRadiusService.getUsersInRadius(LONDON_LATITUDE,LONDON_LONGITUDE,RADIUS);
        return Stream.of(londonUsers,usersWithInFiftyMilesOfLondon).flatMap(Collection::stream).distinct().collect(Collectors.toList());
    }
}
