package eu.telecomnancy.codingweek.global;

public class Conversations {
    private int id;
    private String user1;
    private String user2;
    private String idMessages;

    public Conversations() {
    }

    public Conversations(int id, String user1, String user2, String idMessages) {
        this.id = id;
        this.user1 = user1;
        this.user2 = user2;
        this.idMessages = idMessages;
    }

    public int getId() {
        return id;
    }

    public String getOtherUser(String user) {
        if (user.equals(user1)) {
            return user2;
        } else {
            return user1;
        }
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser1() {
        return user1;
    }

    public void setUser1(String user1) {
        this.user1 = user1;
    }

    public String getUser2() {
        return user2;
    }

    public void setUser2(String user2) {
        this.user2 = user2;
    }

    public String getIdMessages() {
        return idMessages;
    }

    public void setIdMessages(String idMessages) {
        this.idMessages = idMessages;
    }

    @Override
    public String toString() {
        return "Conversations{" +
                "id=" + id +
                ", user1='" + user1 + '\'' +
                ", user2='" + user2 + '\'' +
                ", idMessages='" + idMessages + '\'' +
                '}';
    }
}
