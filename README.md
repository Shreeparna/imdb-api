# imdb-api


Setup:
DB System - Mysql

Create a DB - imdb_api
Create Table - imdb_movie_store
create table imdb_movie_store ( id int(100) primary key auto_increment, movie_title varchar(40), year year,released date, 
runtime varchar(50), genre varchar(100), director varchar(50), language varchar(50), country varchar(50), 
imdbRating float(10,5), imdbId varchar(300), type varchar(50), production varchar(200));

Rest Api Exposed - /getMovies
http://localhost:4567/getMovies?name=IT

Working for key - name
(Imdb api for date is not working.)

