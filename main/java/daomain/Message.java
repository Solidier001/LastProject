package daomain;

import java.util.Date;

public class Message {
    private Integer id;
    private String form;
    private String message;
    private String name;
    private String to;
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id='" + id + '\'' +
                ", form='" + form + '\'' +
                ", message='" + message + '\'' +
                ", name='" + name + '\'' +
                ", to='" + to + '\'' +
                ", date=" + date +
                '}';
    }
}
