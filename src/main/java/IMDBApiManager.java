import apis.ImdbApi;

public class IMDBApiManager {
    public static void main(String[] args) {
        ImdbApi.getUserDetails();
        ImdbApi.getImdbMoviesByDate();
        ImdbApi.getImdbMoviesByName();
        ImdbApi.getImdbMoviesByMultipleParams();
    }
}
