package org.hh.to.production.frame.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private int id;

    @Transient
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    @JsonIgnore
    private Post post;


    //todo change user_id to object user
    @Column(name = "user_id")
    @JsonIgnore
    private int userId;

    @Column(name = "text")
    private String text;

    @Column(name = "date")
    private long date;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "comment_up",
            joinColumns = @JoinColumn(name = "comment_id",
                    referencedColumnName = "comment_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id",
                    referencedColumnName = "user_id"))
    private Set<User> votedUp;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "comment_down",
            joinColumns = @JoinColumn(name = "comment_id",
                    referencedColumnName = "comment_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id",
                    referencedColumnName = "user_id"))
    private Set<User> votedDown;

    public Comment(Post post, int userId, String text, long date) {
        this.post = post;
        this.userId = userId;
        this.text = text;
        this.date = date;
    }

    public Comment() {
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Set<User> getVotedDown() {
        return votedDown;
    }

    public void setVotedDown(Set<User> votedDown) {
        this.votedDown = votedDown;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<User> getVotedUp() {
        return votedUp;
    }

    public void setVotedUp(Set<User> votedUp) {
        this.votedUp = votedUp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    @Transient
    public int getVoices() {
        if (getVotedUp()==null || getVotedDown() == null) return 0;
        return getVotedUp().size() - getVotedDown().size();
    }

}
