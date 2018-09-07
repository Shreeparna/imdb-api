package services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.ImdbMovie;
import repos.ImdbExternalDaoApi;
import repos.dao.ImdbMovieStoreDao;

import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ImdbService {
    private ImdbMovieStoreDao imdbMovieStoreDao;
    private ImdbExternalDaoApi imdbExternalDaoApi;
    public ImdbService(ImdbMovieStoreDao imdbMovieStoreDao) {
        this.imdbMovieStoreDao = imdbMovieStoreDao;
        this.imdbExternalDaoApi = new ImdbExternalDaoApi();
    }
    public  String getMovieList(Map<String,String> params) throws Exception {
        boolean hasNameParam = params.containsKey("name");
        boolean hasDateParam = params.containsKey("date");
        List<ImdbMovie> movieList = null;

        if(hasNameParam) {
            movieList = imdbMovieStoreDao.getMoviesByName(params.get("name"));
        }
        if(movieList.size()==0){
            movieList = imdbExternalDaoApi.getMoviesByName(params.get("name"));
        }

        Gson gson = new Gson();
        Type type = new TypeToken<List<ImdbMovie>>() {}.getType();
        String json = gson.toJson(movieList, type);
        return json;
    }
}
