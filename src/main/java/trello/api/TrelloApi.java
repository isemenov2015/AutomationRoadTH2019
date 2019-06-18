package trello.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import okhttp3.*;
import trello.api.models.Card;
import trello.api.models.TrelloList;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TrelloApi {

    Gson gson = new Gson();
    public static final String KEY = "fd2860007ef5e8e7beec950749cfd3b0";
    public static final String TOKEN = "03d2abd499dddbd79717842b46aea80739fbed18dc70117af375fde98076acb2";

    OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS).build();

    public List<TrelloList> getBoardLists(String boardId) throws IOException {
        Request request = new Request.Builder()
                .url("https://api.trello.com/1/boards/" + boardId + "/lists?cards=none&fields=all&key=" + KEY + "&token=" + TOKEN)
                .build();
        String response = client.newCall(request).execute().body().string();
        Type type = new TypeToken<List<TrelloList>>(){}.getType();
        List<TrelloList> trelloLists = gson.fromJson(response, type);
        return trelloLists;
    }

    public void createCard(String listId, Card card) throws IOException {
        String json = gson.toJson(card);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        Request request = new Request.Builder()
                .url("https://api.trello.com/1/cards?idList="+listId+"&keepFromSource=all&key="+KEY+"&token="+TOKEN)
                .post(body)
                .build();
        String response = client.newCall(request).execute().body().string();
        System.out.println(response);
    }

    public void createList(String idBoard, TrelloList list) throws IOException {
        String json = gson.toJson(list);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        Request request = new Request.Builder()
                .url("https://api.trello.com/1/cards?idBoard="+idBoard+"&keepFromSource=all&key="+KEY+"&token="+TOKEN)
                .post(body)
                .build();
        String response = client.newCall(request).execute().body().string();
        System.out.println(response);
    }

    public String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public String get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}
