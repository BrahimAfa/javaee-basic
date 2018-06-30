package com.mitrais.article.model;

import javax.persistence.*;

@Entity
@Table(name = "articles")
public class Article {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "title")
    protected String title;

    @Column(name = "content")
    protected String content;

    @OneToMany(mappedBy = "articleId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Comment comments;

    public Article(Long id, String title, String content, Comment comments) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.comments = comments;
    }

    public Article(String title, String content) {
        this.title = title;
        this.content = content;
        this.comments = comments;
    }

    public Article() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Comment getComments() {
        return comments;
    }

    public void setComments(Comment comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", comments=" + comments +
                '}';
    }
}
