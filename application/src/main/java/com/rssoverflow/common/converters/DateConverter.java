package com.rssoverflow.common.converters;

import com.tickaroo.tikxml.TypeConverter;

import org.apache.commons.lang3.time.FastDateFormat;

import java.util.Date;

public class DateConverter implements TypeConverter<Date> {

    public final String DATE_FORMAT_PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    private FastDateFormat fastDateFormat = FastDateFormat.getInstance(DATE_FORMAT_PATTERN);

    @Override
    public Date read(String value) throws Exception {
        return fastDateFormat.parse(value);
    }

    @Override
    public String write(Date value) throws Exception {
        return fastDateFormat.format(value);
    }
}
