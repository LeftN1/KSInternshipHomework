package com.keepsolid.ksinternshiphomework.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.keepsolid.ksinternshiphomework.database.BookDBSchema.BookTable;
import com.keepsolid.ksinternshiphomework.models.BookItem;
import com.keepsolid.ksinternshiphomework.models.VolumeInfo;

public class MyCursor extends CursorWrapper {

    public MyCursor(Cursor cursor) {
        super(cursor);
    }

    public BookItem getBook(){
        String id = getString(getColumnIndex(BookTable.cols.ID));
        String title = getString(getColumnIndex(BookTable.cols.TITLE));
        String authors = getString(getColumnIndex(BookTable.cols.AUTHORS));
        String description = getString(getColumnIndex(BookTable.cols.DESCRIPTION));
        String url = getString(getColumnIndex(BookTable.cols.URL));
        String thumbnail = getString(getColumnIndex(BookTable.cols.THUMBNAIL));

        VolumeInfo volumeInfo = new VolumeInfo(title, authors, description, url, thumbnail);
        BookItem bookItem = new BookItem(id, volumeInfo);

        return bookItem;
    }
}
