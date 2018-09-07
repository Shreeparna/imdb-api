package factory;

import lib.DataSourceType;
import repos.api.ImdbExternalDaoApi;
import repos.dataSource.DataSource;
import repos.impl.ImdbMoviesStoreImpl;

public class MovieDataSourceFactory {
    private volatile static MovieDataSourceFactory movieDataSourceFactoryInstance =null;
    private void MovieFactory(){}

    DataSource dataSource;

    public static MovieDataSourceFactory getInstance(){
        if(movieDataSourceFactoryInstance == null){
            synchronized (MovieDataSourceFactory.class){
                if(movieDataSourceFactoryInstance == null){
                    movieDataSourceFactoryInstance = new MovieDataSourceFactory();
                }
            }
        }
        return movieDataSourceFactoryInstance;
    }
    public DataSource getDataSource(DataSourceType source){
        switch(source){
            case DB: {
                dataSource = new ImdbMoviesStoreImpl();
                break;
            }
            case API: dataSource = new ImdbExternalDaoApi();
        }
        return dataSource;
    }
}
