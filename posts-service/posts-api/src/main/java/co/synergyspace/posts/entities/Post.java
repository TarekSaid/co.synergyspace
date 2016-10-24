package co.synergyspace.posts.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by tarek on 23/09/16.
 */
public abstract class Post {

    protected Long id;
    protected Date date;
    protected String content;
    protected Post replyingTo;
    protected List<Post> replies;

    public Post() {
    }

    public Post(Long id) {
        this.id = id;
    }

    public Post(String content) {
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Post getReplyingTo() {
        return replyingTo;
    }

    public void setReplyingTo(Post replyingTo) {
        this.replyingTo = replyingTo;
    }

    public List<Post> getReplies() {
        return replies;
    }

    public void setReplies(List<Post> replies) {
        this.replies = replies;
    }

    public void addReply(Post reply) {
        if (replies == null) {
            replies = new ArrayList<>();
        }

        replies.add(reply);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Post post = (Post) o;

        return id != null ? id.equals(post.id) : post.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
