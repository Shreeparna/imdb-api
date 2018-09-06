package repos;
import model.ImdbMovie;
import repos.dao.ImdbMovieStoreDao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class ImdbExternalDaoApi implements ImdbMovieStoreDao {
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
            response.toString();
            return null;
    }

    @Override
    public void getMovies(String name, int year) {

    }
}
