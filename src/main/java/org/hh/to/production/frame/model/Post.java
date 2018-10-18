package org.hh.to.production.frame.model;


import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "post_id")
    private int id;
    @Column(name = "header")
    private String header;
    @Column(name = "preview_text")
    private String previewText;
    @Column(name = "preview_image")
    private String previewImage;
    @Column(name = "posting_date")
    private long date;
    @Column(name = "post_text")
    private String text;
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Comment> comments;

    public Post(int id, String header, String previewText, String previewImage, long date) {
        this.id = id;
        this.header = header;
        this.previewText = previewText;
        this.previewImage = previewImage;
        this.date = date;
    }

    public Post(String header, String previewText, String previewImage, String text, long date) {
        this.header = header;
        this.previewText = previewText;
        this.previewImage = previewImage;
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

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getPreviewText() {
        return previewText;
    }

    public void setPreviewText(String previewText) {
        this.previewText = previewText;
    }

    public String getPreviewImage() {
        return previewImage;
    }

    public void setPreviewImage(String previewImage) {
        this.previewImage = previewImage;
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

    public void setText(String text) {
        this.text = text;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }
}
