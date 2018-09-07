package repos.dataSource;

import lib.Params;
import model.ImdbMovie;
import java.util.List;
import java.util.Map;

public interface DataSource {
    public List<ImdbMovie> getMovies(Map<Params,String> paramValueList) throws Exception;
}
