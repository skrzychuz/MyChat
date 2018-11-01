import java.io.Serializable;

public class MyMessage implements Serializable {
    int id;
    String text;

    public MyMessage() {
    }

    public MyMessage(int id, String text) {
        this.id = id;
        this.text = text;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
