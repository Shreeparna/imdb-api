import routes.ImdbRoutes;
import repos.dao.ImdbMovieStoreDao;
import repos.impl.ImdbMoviesStoreImpl;
import services.ImdbService;

public class IMDBApiManager {
    public static void main(String[] args) {
        ImdbMovieStoreDao imdbMovieStoreDao = new ImdbMoviesStoreImpl();
        ImdbService imdbService = new ImdbService();
        ImdbRoutes imdbRoutes = new ImdbRoutes(imdbService);
        imdbRoutes.getUserDetails();
        imdbRoutes.getImdbMoviesByDate();
        imdbRoutes.getImdbMoviesByName();
        imdbRoutes.getImdbMoviesByMultipleParams();
    }
}


//http://localhost:4567/getMovies?name=IT&date=12-12-12