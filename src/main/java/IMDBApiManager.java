import apis.ImdbApi;
import repos.dao.ImdbMovieStoreDao;
import repos.impl.ImdbMoviesStoreImpl;
import services.ImdbService;

public class IMDBApiManager {
    public static void main(String[] args) {
        ImdbMovieStoreDao imdbMovieStoreDao = new ImdbMoviesStoreImpl();
        ImdbService imdbService = new ImdbService(imdbMovieStoreDao);
        ImdbApi imdbApi = new ImdbApi(imdbService);
        imdbApi.getUserDetails();
        imdbApi.getImdbMoviesByDate();
        imdbApi.getImdbMoviesByName();
        imdbApi.getImdbMoviesByMultipleParams();
    }
}
