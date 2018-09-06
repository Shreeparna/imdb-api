package apis;

import static spark.Spark.get;

import model.ImdbMovie;
import repos.dao.ImdbMovieStoreDao;
import services.ImdbService;
import spark.Request;
import spark.Response;
import spark.Route;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ImdbApi {
    private ImdbService imdbService;
    public ImdbApi(ImdbService imdbService){
        this.imdbService = imdbService;
    }

    public void getUserDetails() {
        get(new Route("/users/:id") {
            @Override
            public Object handle(Request request, Response response) {
                return "User: username=test, email=test@test.net";
            }
        });
    }

    public void getImdbMoviesByDate(){
        get(new Route("/getMovies/date/:date"){
            @Override
            public Object handle(Request request, Response response) {
                String date = request.params("date");
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                Date date2=null;
                try {
                    date2 = dateFormat.parse(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return "Date:"+date2;
            }
        });
    }

    public void getImdbMoviesByName(){
        get(new Route("/getMovies/name/:name"){
            @Override
            public Object handle(Request request, Response response) {
                String name = request.params(":name");

                return "Movie Name:"+name;
            }
        });
    }

    public void getImdbMoviesByMultipleParams() {
        get(new Route("/getMovies"){
            @Override
            public Object handle(Request request, Response response) {
                Map<String, String> paramValueMap = new HashMap<>();
                for(String param : request.queryParams()) {
                    paramValueMap.put(param, request.queryParams(param));
                }
                String movies = null;
                try {
                    movies = imdbService.getMovieList(paramValueMap);
                } catch (Exception e) {
                    e.printStackTrace();
                }

//                Set<String> queryParams = request.queryParams();
//                StringBuilder str = new StringBuilder();
//                for(String param : queryParams){
//                    str.append(param).append(" ").append(request.queryParams(param)).append("<br />");
//                }
                return movies;
            }
        });
    }

}
