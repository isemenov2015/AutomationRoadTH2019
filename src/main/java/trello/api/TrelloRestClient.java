package trello.api;

import okhttp3.OkHttpClient;
import trello.api.services.BoardService;

public class TrelloRestClient {
    public static final String HOME_TO_BASE_URL = "https://api.trello.com/1/";

    public BoardService boardsService;

    public TrelloRestClient() {
        OkHttpClient client = new OkHttpClient();


        //boardsService = retrofit.
    }
}
