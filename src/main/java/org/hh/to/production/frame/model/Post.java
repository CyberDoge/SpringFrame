package org.hh.to.production.frame.model;


import javax.persistence.*;

@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private int id;

    @Column(name = "header")
    private String header;

    @Column(name = "posting_date")
    private long date;

    @Column(name = "post_text")
    private String text;

    public Post(String header, String text, long date) {
        this.header = header;
        this.text = text;
        this.date = date;
    }

    public Post() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public void setText(String text) {
        this.text = text;
    }
}
