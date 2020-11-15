package com.example.android.common.models;

import com.tickaroo.tikxml.annotation.PropertyElement;
import com.tickaroo.tikxml.annotation.Xml;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Xml(name = "feed")
public class Feed {

    @PropertyElement
    String title;

    @PropertyElement
    String updated;
}
