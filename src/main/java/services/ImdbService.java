package services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.ImdbMovie;
import repos.dao.ImdbMovieStoreDao;

import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ImdbService {
    private ImdbMovieStoreDao imdbMovieStoreDao;
    public ImdbService(ImdbMovieStoreDao imdbMovieStoreDao) {
        this.imdbMovieStoreDao = imdbMovieStoreDao;
    }
    public  String getMovieList(Map<String,String> params) throws SQLException {
        boolean hasNameParam = params.containsKey("name");
        boolean hasDateParam = params.containsKey("date");
        List<ImdbMovie> movieList = null;

        if(hasNameParam) {
            movieList = imdbMovieStoreDao.getMoviesByName(params.get("name"));
        }
        if(movieList.size()==0){

        }

        Gson gson = new Gson();
        Type type = new TypeToken<List<ImdbMovie>>() {}.getType();
        String json = gson.toJson(movieList, type);



        return json;
    }
}
