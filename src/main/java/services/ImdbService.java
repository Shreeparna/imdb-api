package services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import factory.MovieDataSourceFactory;
import lib.DataSourceType;
import lib.Params;
import model.ImdbMovie;
import repos.dao.ImdbMovieStoreDao;
import repos.dataSource.DataSource;
import repos.impl.ImdbMoviesStoreImpl;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImdbService {
    MovieDataSourceFactory movieDataSourceFactoryInstance;
    ImdbMovieStoreDao imdbMovieStoreDao;
    public ImdbService(){
        movieDataSourceFactoryInstance = MovieDataSourceFactory.getInstance();
        imdbMovieStoreDao = new ImdbMoviesStoreImpl();
    }
    public  String getMovieList(Map<String,String> params) throws Exception {
        Params param=null;
        String value="name";
        boolean hasNameParam = false;
        boolean hasDateParam = false;
        Map<Params,String> paramValueList = new HashMap<>();

        List<ImdbMovie> movieList = null;
        DataSource dataSource=null;


        if(params.containsKey("name")){
            param = Params.NAME;
            hasNameParam=true;
            value = params.get("name");
            paramValueList.put(Params.NAME,params.get("name"));
        }
        if(params.containsKey("date")){
            param = Params.DATE;
            hasDateParam = true;
            value = params.get("date");
            paramValueList.put(Params.DATE,params.get("date"));
        }

        dataSource = movieDataSourceFactoryInstance.getDataSource(DataSourceType.DB);
        movieList = dataSource.getMovies(paramValueList);
        if(movieList.size()==0){
            dataSource = movieDataSourceFactoryInstance.getDataSource(DataSourceType.API);
            movieList = dataSource.getMovies(paramValueList);
            imdbMovieStoreDao.setMovies(movieList);
        }

        Gson gson = new Gson();
        Type type = new TypeToken<List<ImdbMovie>>() {}.getType();
        String json = gson.toJson(movieList, type);
        return json;
    }
}
