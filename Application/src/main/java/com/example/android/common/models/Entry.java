package com.example.android.common.models;

import lombok.Data;

import java.util.List;

@Data
public class Entry {
    String title;
    String description;
    String link;
    String author;
    String pubDate;
    List<String> categories;

    public Entry(
            String title,
            String description,
            String link,
            String author,
            String pubDate,
            List<String> categories) {
        this.title = title;
        this.description = description;
        this.link = link;
        this.author = author;
        this.pubDate = pubDate;
        this.categories = categories;
    }
}
