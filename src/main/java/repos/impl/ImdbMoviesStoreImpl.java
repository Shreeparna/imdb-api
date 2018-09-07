package repos.impl;

import lib.Params;
import model.ImdbMovie;
import repos.dao.ImdbMovieStoreDao;
import repos.dataSource.DataSource;
import resources.MySqlConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ImdbMoviesStoreImpl implements ImdbMovieStoreDao, DataSource {
    Connection con=null;
    public ImdbMoviesStoreImpl(){
        con = MySqlConnector.getConnection().con;
    }

    @Override
    public List<ImdbMovie> getMoviesByName(String name) throws Exception {
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

    private Date getDateFromString(String released) throws ParseException{
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.parse(released);
    }

    @Override
    public void getMovies(String name, int year) {

    }

    @Override
    public void setMovies(List<ImdbMovie> movieList) throws Exception{
        String query = "Insert into imdb_api.imdb_movie_store (movie_title, year, released, runtime, genre, director, language, country, " +
                " imdbRating, imdbId, type, production) values";
        String values="";

        if(movieList.size()==1){
            values +=getValuesString(movieList.get(0));
        }
        else {
            for (ImdbMovie movie : movieList) {
                if (values == "") {
                    values += getValuesString(movie);
                } else {
                    values += ", " + getValuesString(movie);
                }
            }

            values = "("+values+")";

        }
        query=query+values+";";
        PreparedStatement preparedStmt = con.prepareStatement(query);
        preparedStmt.execute();
    }

    @Override
    public List<ImdbMovie> getMovies(Map<Params,String> paramValueList) throws Exception {
        List<ImdbMovie> movieList=null;

        if(paramValueList.containsKey(Params.NAME) && paramValueList.size()==1){
            movieList = getMoviesByName(paramValueList.get(Params.NAME));
        }
        return movieList;
    }

    public String getValuesString(ImdbMovie movie) throws Exception{
        String values="";
        DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
        Date date = (Date)formatter.parse(movie.getReleased().toString());
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
//        String formatedDate = cal.get(Calendar.DATE) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.YEAR);
        String formatedDate = cal.get(Calendar.YEAR) +"-"+(cal.get(Calendar.MONTH) + 1)+"-"+cal.get(Calendar.DATE);

        values+= "('"+movie.getMovie_title()+"',"+movie.getYear()+",'"+formatedDate+"','"+movie.getRuntime()+"','"+movie.getGenre()+"','"+
                movie.getDirector()+"','"+movie.getLanguage()+"','"+movie.getCountry()+"',"+movie.getImdbRating()+",'"+movie.getImdbId()+"','"+
                movie.getType()+"','"+movie.getProduction()+"')";
        return values;
    }
}
