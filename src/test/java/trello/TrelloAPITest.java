package trello;

import org.testng.annotations.Test;
import trello.api.TrelloApi;
import trello.api.models.Card;
import trello.api.models.TrelloList;

import java.io.IOException;
import java.util.List;


public class TrelloAPITest {
    // eCbzw5OB

    @Test
    public void tryIt() throws IOException {
        TrelloApi trelloApi = new TrelloApi();
        //trelloApi.createCard("eCbzw5OB");
        List<TrelloList> trelloList = trelloApi.getBoardLists("ebIWPL9K");
        for (TrelloList tList : trelloList) {
            Card card = new Card();
            card.name = "Card: " + tList.name;
            card.desc = "Awesome card";
            trelloApi.createCard(tList.id);
            // System.out.println(tList.name);
        }
    }

}
