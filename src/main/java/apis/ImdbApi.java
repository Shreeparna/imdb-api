package apis;

import static spark.Spark.get;
import spark.Request;
import spark.Response;
import spark.Route;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

public class ImdbApi {

    public static void getUserDetails() {
        get(new Route("/users/:id") {
            @Override
            public Object handle(Request request, Response response) {
                return "User: username=test, email=test@test.net";
            }
        });
    }

    public static void getImdbMoviesByDate(){
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

    public static void getImdbMoviesByName(){
        get(new Route("/getMovies/name/:name"){
            @Override
            public Object handle(Request request, Response response) {
                String name = request.params(":name");

                return "Movie Name:"+name;
            }
        });
    }

    public static void getImdbMoviesByMultipleParams(){
        get(new Route("/getMovies/"){
            @Override
            public Object handle(Request request, Response response) {
                Set<String> queryParams = request.queryParams();
                String name = request.queryParams(":name");
                String date = request.queryParams(":date");
                StringBuilder str = new StringBuilder();
                
                for(String param : queryParams){
                    str.append(param).append(" ").append(request.queryParams(param)).append("<br />");
                }
                return str;
            }
        });
    }




}
