package trello;

import org.testng.annotations.Test;
import trello.api.TrelloApi;

import java.io.IOException;


public class TrelloAPITest {
    // eCbzw5OB

    @Test
    public void tryIt() throws IOException {
        TrelloApi trelloApi = new TrelloApi();
        trelloApi.createCard("eCbzw5OB");
    }

}
