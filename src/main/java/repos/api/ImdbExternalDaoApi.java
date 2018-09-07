package repos.api;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lib.Params;
import model.ImdbMovie;
import repos.dao.ImdbMovieStoreDao;
import repos.dataSource.DataSource;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ImdbExternalDaoApi implements DataSource {
    public List<ImdbMovie> getMoviesByName(String movieName) throws Exception{
        String urlString = "http://www.omdbapi.com/?apikey=fa86db67&t="+movieName;
        URL url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        Gson gson = new GsonBuilder().setDateFormat("dd MMM yyyy").create();
        ImdbMovie imdbMovie = gson.fromJson(response.toString(), ImdbMovie.class);
        return Arrays.asList(imdbMovie);
    }

    @Override
    public List<ImdbMovie> getMovies(Map<Params,String> paramValueList) throws Exception{
        List<ImdbMovie> movieList=null;
        if(paramValueList.containsKey(Params.NAME) && paramValueList.size()==1){
            movieList = getMoviesByName(paramValueList.get(Params.NAME));
        }
        return movieList;
    }
}
