package com.keepsolid.ksinternshiphomework.database;

public class BookDBSchema {
    public static final class BookTable{
        public static final String NAME = "books";

        public static final class cols{
            public static final String ID = "id";
            public static final String TITLE = "title";
            public static final String AUTHORS = "authors";
            public static final String DESCRIPTION = "description";
            public static final String URL = "url";
            public static final String THUMBNAIL = "thumbnail";
        }
    }
}
