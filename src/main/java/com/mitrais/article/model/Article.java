package com.mitrais.article.model;

import javax.persistence.*;

@Entity
@Table(name = "articles")
public class Article {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.TABLE)
    protected Long id;

    @Column(name = "title")
    protected String title;

    @Column(name = "content")
    protected String Content;

    public Article(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        Content = content;
    }

    public Article(String title, String content) {
        this.title = title;
        Content = content;
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
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
