package com.example.android.common.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Entry {
    String title;
    String description;
    String link;
    String author;
    String pubDate;
    List<String> categories;
}
