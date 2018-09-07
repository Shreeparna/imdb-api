package repos.impl;

import model.ImdbMovie;
import repos.dao.ImdbMovieStoreDao;
import resources.MySqlConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ImdbMoviesStoreImpl implements ImdbMovieStoreDao {
    @Override
    public List<ImdbMovie> getMoviesByName(String name) throws Exception {
        Connection con = MySqlConnector.getConnection().con;
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM imdb_api.imdb_movie_store where movie_title like '%" + name + "%'");
        List<ImdbMovie> imdbMovies = new ArrayList<>();
        while(resultSet.next()){
            imdbMovies.add(getImdbMovie(resultSet));
        }
        return imdbMovies;
    }

    private ImdbMovie getImdbMovie(ResultSet resultSet) throws Exception {
        ImdbMovie imdbMovie = new ImdbMovie();
        imdbMovie.setId(resultSet.getInt("id"));
        imdbMovie.setMovie_title(resultSet.getString("movie_title"));
        imdbMovie.setYear(resultSet.getInt("year"));
        imdbMovie.setReleased(getDateFromString(resultSet.getString("released")));
        imdbMovie.setRuntime(resultSet.getString("runtime"));
        imdbMovie.setGenre(resultSet.getString("genre"));
        imdbMovie.setDirector(resultSet.getString("director"));
        imdbMovie.setLanguage(resultSet.getString("language"));
        imdbMovie.setCountry(resultSet.getString("country"));
        imdbMovie.setImdbRating(resultSet.getDouble("imdbRating"));
        imdbMovie.setImdbId(resultSet.getString("imdbId"));
        imdbMovie.setType(resultSet.getString("type"));
        imdbMovie.setProduction(resultSet.getString("production"));
        return imdbMovie;
    }

    private Date getDateFromString(String released) throws ParseException {
        DateFormat format = new SimpleDateFormat("dd MMM yyyy");
        return format.parse(released);
    }

    @Override
    public void getMovies(String name, int year) {

    }
}
