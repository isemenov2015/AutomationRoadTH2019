package trello.api;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import trello.api.interceptors.TrelloAuthInterceptor;
import trello.api.services.BoardService;
import trello.api.services.ListService;

import java.util.concurrent.TimeUnit;

public class TrelloRestClient {
    public static final String HOME_TO_BASE_URL = "https://api.trello.com/1/";

    public BoardService boardsService;
    public ListService listService;

    public TrelloRestClient() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(new TrelloAuthInterceptor())
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(HOME_TO_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        boardsService = retrofit.create(BoardService.class);
        listService = retrofit.create(ListService.class);
    }
}
