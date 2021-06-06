package com.praveen.movieinfoservice.resources;

import com.praveen.movieinfoservice.models.Movie;
import com.praveen.movieinfoservice.models.MovieSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/movies")
public class MovieResource {

    @Value("${api.key}")
    private String API_KEY;
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{movieId}")
    public Movie getMovie(@PathVariable("movieId") String movieId) {
        System.out.println("##### in movie info service movie id : " + movieId);
        movieId = movieId.trim();
        String url = "https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" + API_KEY;
        String provided = "https://api.themoviedb.org/3/movie/1?api_key=bb96b8ad00589fb4333b46cf9b95ec0c";
        System.out.println("moviedb url : " + url);
        System.out.println("provided url : " + provided);
        System.out.println("my url length : " + url.length());
        System.out.println("provided url length :  " + "https://api.themoviedb.org/3/movie/1?api_key=bb96b8ad00589fb4333b46cf9b95ec0c".length());
        System.out.println("both url are same : " + url.equals("https://api.themoviedb.org/3/movie/1?api_key=bb96b8ad00589fb4333b46cf9b95ec0c"));
       /* for (int i = 0; i < url.length(); i++) {
            if(url.charAt(i) != provided.charAt(i)) {
                System.out.println("###### charcter not matched url : " + url.charAt(i));
                System.out.println("###### charcter not matched provided : " + provided.charAt(i));
                System.out.println("index : " + i);
            }
        }*/
        MovieSummary movieSummary = restTemplate.getForObject(
                "https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" + API_KEY,
                //"https://api.themoviedb.org/3/movie/250?api_key=bb96b8ad00589fb4333b46cf9b95ec0c",
                MovieSummary.class
        );
        return new Movie(movieId, movieSummary.getTitle(), movieSummary.getOverview());

    }
}
