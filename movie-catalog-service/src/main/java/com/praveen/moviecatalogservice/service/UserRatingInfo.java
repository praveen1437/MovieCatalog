package com.praveen.moviecatalogservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.praveen.moviecatalogservice.models.Rating;
import com.praveen.moviecatalogservice.models.UserRatings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class UserRatingInfo {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallbackUserRating")
    public UserRatings getUserRating(@PathVariable("userId") String userId) {
        return restTemplate.getForObject("http://RATING-DATA-SERVICE/ratingsData/user/"+userId, UserRatings.class);
    }

    private UserRatings getFallbackUserRating(@PathVariable("userId") String userId) {
        UserRatings userRatings = new UserRatings();
        userRatings.setUserId(userId);
        userRatings.setRatings(Arrays.asList(
                new Rating("0", 0)
        ));
        return userRatings;
    }
}
