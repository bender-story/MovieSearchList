package com.rahul.network

import com.google.gson.Gson
import com.rahul.model.MovieDetailsResult
import com.rahul.model.MovieSearchResult
import io.reactivex.Observable
import retrofit2.mock.BehaviorDelegate

/**
 * Mock service Interface
 *
 */
class MockServiceImpl(private val delegate: BehaviorDelegate<ServiceInterface>) : ServiceInterface {
    override fun fetchMovieList(
        movieName: String,
        pageNo: Int,
        apiKey: String
    ): Observable<List<MovieSearchResult>> {
        var json:String= getMovieListMockJSON()

        val mockResponse= Gson().fromJson(json, Array<MovieSearchResult>::class.java).toList()
        return delegate.returningResponse(mockResponse).fetchMovieList(movieName,pageNo = pageNo)
    }

    override fun fetchMovieDetails(
        movieID: String,
        apiKey: String
    ): Observable<List<MovieDetailsResult>> {
        var json:String= getMovieDetailsJson()

        val mockResponse= Gson().fromJson(json, Array<MovieSearchResult>::class.java).toList()
        return delegate.returningResponse(mockResponse).fetchMovieDetails(movieID)
    }

    private fun getMovieDetailsJson():String{
        return "{\n" +
                "    \"Title\": \"Batman Beyond: Return of the Joker\",\n" +
                "    \"Year\": \"2000\",\n" +
                "    \"Rated\": \"PG-13\",\n" +
                "    \"Released\": \"12 Dec 2000\",\n" +
                "    \"Runtime\": \"76 min\",\n" +
                "    \"Genre\": \"Animation, Action, Crime, Sci-Fi, Thriller\",\n" +
                "    \"Director\": \"Curt Geda\",\n" +
                "    \"Writer\": \"Bob Kane (character created by: Batman), Paul Dini (story), Glen Murakami (story), Bruce Timm (story), Paul Dini (screenplay)\",\n" +
                "    \"Actors\": \"Will Friedle, Kevin Conroy, Mark Hamill, Angie Harmon\",\n" +
                "    \"Plot\": \"The Joker is back with a vengeance, and Gotham's newest Dark Knight needs answers as he stands alone to face Gotham's most infamous Clown Prince of Crime.\",\n" +
                "    \"Language\": \"English\",\n" +
                "    \"Country\": \"USA\",\n" +
                "    \"Awards\": \"3 wins & 5 nominations.\",\n" +
                "    \"Poster\": \"https://m.media-amazon.com/images/M/MV5BNmRmODEwNzctYzU1MS00ZDQ1LWI2NWMtZWFkNTQwNDg1ZDFiXkEyXkFqcGdeQXVyNTI4MjkwNjA@._V1_SX300.jpg\",\n" +
                "    \"Ratings\": [\n" +
                "        {\n" +
                "            \"Source\": \"Internet Movie Database\",\n" +
                "            \"Value\": \"7.8/10\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Source\": \"Rotten Tomatoes\",\n" +
                "            \"Value\": \"90%\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"Metascore\": \"N/A\",\n" +
                "    \"imdbRating\": \"7.8\",\n" +
                "    \"imdbVotes\": \"22,355\",\n" +
                "    \"imdbID\": \"tt0233298\",\n" +
                "    \"Type\": \"movie\",\n" +
                "    \"DVD\": \"N/A\",\n" +
                "    \"BoxOffice\": \"N/A\",\n" +
                "    \"Production\": \"N/A\",\n" +
                "    \"Website\": \"N/A\",\n" +
                "    \"Response\": \"True\"\n" +
                "}"
    }


    private fun getMovieListMockJSON():String{
        return "{\n" +
                "    \"Search\": [\n" +
                "        {\n" +
                "            \"Title\": \"Batman Beyond: Return of the Joker\",\n" +
                "            \"Year\": \"2000\",\n" +
                "            \"imdbID\": \"tt0233298\",\n" +
                "            \"Type\": \"movie\",\n" +
                "            \"Poster\": \"https://m.media-amazon.com/images/M/MV5BNmRmODEwNzctYzU1MS00ZDQ1LWI2NWMtZWFkNTQwNDg1ZDFiXkEyXkFqcGdeQXVyNTI4MjkwNjA@._V1_SX300.jpg\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Title\": \"Son of Batman\",\n" +
                "            \"Year\": \"2014\",\n" +
                "            \"imdbID\": \"tt3139072\",\n" +
                "            \"Type\": \"movie\",\n" +
                "            \"Poster\": \"https://m.media-amazon.com/images/M/MV5BYjdkZWFhNzctYmNhNy00NGM5LTg0Y2YtZWM4NmU2MWQ3ODVkXkEyXkFqcGdeQXVyNTA0OTU0OTQ@._V1_SX300.jpg\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Title\": \"Superman/Batman: Public Enemies\",\n" +
                "            \"Year\": \"2009\",\n" +
                "            \"imdbID\": \"tt1398941\",\n" +
                "            \"Type\": \"movie\",\n" +
                "            \"Poster\": \"https://m.media-amazon.com/images/M/MV5BZDc5NTFiMzgtZWJiOS00N2M1LWJmOGYtZmNjMzFhMzcxZjRiXkEyXkFqcGdeQXVyNTAyODkwOQ@@._V1_SX300.jpg\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Title\": \"Batman: Arkham Asylum\",\n" +
                "            \"Year\": \"2009\",\n" +
                "            \"imdbID\": \"tt1282022\",\n" +
                "            \"Type\": \"game\",\n" +
                "            \"Poster\": \"https://m.media-amazon.com/images/M/MV5BMWE1MGI0ZmItNzU2My00Mzk5LThkNTMtMmFiMjRiZDNlNzkwXkEyXkFqcGdeQXVyNjgyODQ1Mzk@._V1_SX300.jpg\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Title\": \"Batman: Bad Blood\",\n" +
                "            \"Year\": \"2016\",\n" +
                "            \"imdbID\": \"tt4870838\",\n" +
                "            \"Type\": \"movie\",\n" +
                "            \"Poster\": \"https://m.media-amazon.com/images/M/MV5BZWZiZmZhYmQtYjVkZi00MWIzLWEzM2MtYzhkNjliNzc2MTMwL2ltYWdlL2ltYWdlXkEyXkFqcGdeQXVyNTAyODkwOQ@@._V1_SX300.jpg\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Title\": \"Batman vs. Robin\",\n" +
                "            \"Year\": \"2015\",\n" +
                "            \"imdbID\": \"tt4324274\",\n" +
                "            \"Type\": \"movie\",\n" +
                "            \"Poster\": \"https://m.media-amazon.com/images/M/MV5BMjI0ODY2MDE5Nl5BMl5BanBnXkFtZTgwMTk0NTcyNTE@._V1_SX300.jpg\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Title\": \"Batman\",\n" +
                "            \"Year\": \"1966–1968\",\n" +
                "            \"imdbID\": \"tt0059968\",\n" +
                "            \"Type\": \"series\",\n" +
                "            \"Poster\": \"https://m.media-amazon.com/images/M/MV5BMTkzNDY5NTg5MF5BMl5BanBnXkFtZTgwNzI4NzM1MjE@._V1_SX300.jpg\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Title\": \"Batman Ninja\",\n" +
                "            \"Year\": \"2018\",\n" +
                "            \"imdbID\": \"tt7451284\",\n" +
                "            \"Type\": \"movie\",\n" +
                "            \"Poster\": \"https://m.media-amazon.com/images/M/MV5BYmFhYzZhYzgtZjZiYS00NWEwLWFhYTUtN2UxM2FmYzdhNDUyXkEyXkFqcGdeQXVyNDk2Nzc1Mg@@._V1_SX300.jpg\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Title\": \"Batman & Mr. Freeze: SubZero\",\n" +
                "            \"Year\": \"1998\",\n" +
                "            \"imdbID\": \"tt0143127\",\n" +
                "            \"Type\": \"movie\",\n" +
                "            \"Poster\": \"https://m.media-amazon.com/images/M/MV5BOTQ0NmUzMzAtODk5My00MzYwLThlYWEtY2NkOGNhODg5ZmY1XkEyXkFqcGdeQXVyNjExODE1MDc@._V1_SX300.jpg\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Title\": \"The New Batman Adventures\",\n" +
                "            \"Year\": \"1997–1999\",\n" +
                "            \"imdbID\": \"tt0118266\",\n" +
                "            \"Type\": \"series\",\n" +
                "            \"Poster\": \"https://m.media-amazon.com/images/M/MV5BN2Q5MWY3YTUtNTQ0Ny00MDNlLWJiMmYtNDkwZTg5ZDFiMTFkXkEyXkFqcGdeQXVyNjExODE1MDc@._V1_SX300.jpg\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"totalResults\": \"381\",\n" +
                "    \"Response\": \"True\"\n" +
                "}"
    }

}