package com.example.android.common.models;

import com.tickaroo.tikxml.annotation.Element;
import com.tickaroo.tikxml.annotation.PropertyElement;
import com.tickaroo.tikxml.annotation.Xml;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Xml
public class Feed {

    @PropertyElement
    String title;

    @PropertyElement
    String subtitle;

    @PropertyElement
    String updated;

    @Element
    List<Entry> entries;
}
