package com.praveen.ratingdataservice.resources;

import com.praveen.ratingdataservice.model.UserRatings;
import com.praveen.ratingdataservice.model.Rating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jws.soap.SOAPBinding;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratingsData")
public class RatingResource {

    @RequestMapping("/{movieId}")
    public Rating getRating(@PathVariable("movieId") String movieId){
       return new Rating(movieId,4);
    }
    @RequestMapping("user/{userId}")
    public UserRatings getUserRatings(@PathVariable("userId") String userId){
        List<Rating> ratings= Arrays.asList(
                new Rating("3",4),
                new Rating("250",5)
        );
        UserRatings userRatings=new UserRatings(ratings);

        return userRatings;
    }
}
