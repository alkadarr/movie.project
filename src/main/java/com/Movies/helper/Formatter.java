package com.Movies.helper;

import java.time.format.DateTimeFormatter;

public class Formatter {

    public static DateTimeFormatter dateTimeIndo(){
        return DateTimeFormatter.ofPattern("dd/MM/yyyy");
    }
}
