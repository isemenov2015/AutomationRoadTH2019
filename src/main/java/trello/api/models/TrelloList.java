package trello.api.models;

public class TrelloList {
    public String id;
    public String name;
    public boolean closed;
    public String idBoard;

    @Override
    public String toString() {
        return "TrelloList{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", closed=" + closed +
                ", idBoard='" + idBoard + '\'' +
                '}';
    }
}
