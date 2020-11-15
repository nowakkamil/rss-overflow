package com.example.android.common.models;

import com.tickaroo.tikxml.annotation.Path;
import com.tickaroo.tikxml.annotation.PropertyElement;
import com.tickaroo.tikxml.annotation.Xml;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Xml
public class Entry {

    @PropertyElement
    String title;

    @Path("author")
    @PropertyElement(name = "name")
    String author;

    @PropertyElement
    String published;

    @PropertyElement
    String updated;

    @PropertyElement
    String summary;
}
