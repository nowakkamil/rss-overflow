package com.rssoverflow.common.models;

import com.tickaroo.tikxml.annotation.Path;
import com.tickaroo.tikxml.annotation.PropertyElement;
import com.tickaroo.tikxml.annotation.Xml;
import com.tickaroo.tikxml.converter.htmlescape.HtmlEscapeStringConverter;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Xml
public class Entry {

    @PropertyElement(converter = HtmlEscapeStringConverter.class)
    String title;

    @Path("author")
    @PropertyElement(name = "name")
    String author;

    @PropertyElement
    Date published;

    @PropertyElement
    Date updated;

    @PropertyElement(converter = HtmlEscapeStringConverter.class)
    String summary;
}
