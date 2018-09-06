package repos.dao;

import model.ImdbMovie;

import java.sql.SQLException;
import java.util.List;

public interface ImdbMovieStoreDao {
    public List<ImdbMovie> getMoviesByName(String name) throws SQLException;
    public void getMovies(String name, int year);
}