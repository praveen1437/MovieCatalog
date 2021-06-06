package com.praveen.moviecatalogservice.resources;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.praveen.moviecatalogservice.models.CatalogItem;
import com.praveen.moviecatalogservice.models.Movie;
import com.praveen.moviecatalogservice.models.Rating;
import com.praveen.moviecatalogservice.models.UserRatings;
import com.praveen.moviecatalogservice.service.MovieInfo;
import com.praveen.moviecatalogservice.service.UserRatingInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MoviesCatalogResource {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    MovieInfo movieInfo;
    @Autowired
    UserRatingInfo userRatingInfo;



    @RequestMapping("/{userId}")
     public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){
       UserRatings ratings= userRatingInfo.getUserRating(userId);
        /*System.out.println("#### ratings : " + ratings);
        System.out.println("###### rating 1 : " + ratings.getRatings().get(0).getMovieId());
        System.out.println("###### rating 2 : " + ratings.getRatings().get(1).getMovieId());*/
    return  ratings.getRatings().stream()
            .map(rating -> movieInfo.getCatalogItem(rating))
            .collect(Collectors.toList());
    }





}
