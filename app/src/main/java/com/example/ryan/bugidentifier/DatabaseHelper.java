package com.example.ryan.bugidentifier;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.io.ByteArrayOutputStream;

/**
 * Created by Randula.
 * Website: https://acadgild.com/blog/save-retrieve-image-sqlite-android/
 * Github: https://github.com/hiteshbpatel/Android_Blog_Projects/tree/master/Android-SQLite-master
 *
 * Modified by:Michael R. Roark
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private final String TAG = "DatabaseHelperClass";
    private static final int databaseVersion = 1;
    private static final String databaseName = "dbTest";
    private static final String TABLE_IMAGE = "ImageTable";

    // Image Table Columns names
    private static final String COL_ID = "col_id";
    private static final String IMAGE_ID = "image_id";
    private static final String IMAGE_JPEG = "image_jpeg";
    private static final String INSECT = "insect"; //What insect is this?

    public DatabaseHelper(Context context) {
        super(context, databaseName, null, databaseVersion);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_IMAGE_TABLE = "CREATE TABLE " + TABLE_IMAGE + "("
                + COL_ID + " INTEGER PRIMARY KEY ,"
                + IMAGE_ID + " TEXT,"
                + IMAGE_JPEG + " TEXT,"
                + INSECT + " TEXT )";
        sqLiteDatabase.execSQL(CREATE_IMAGE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Drop older table if existed
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_IMAGE);
        onCreate(sqLiteDatabase);
    }

    public void insetImage(Drawable dbDrawable, String imageId, String insect) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(IMAGE_ID, imageId);
        Bitmap bitmap = ((BitmapDrawable)dbDrawable).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        values.put(IMAGE_JPEG, stream.toByteArray());
        values.put(INSECT, insect);
        db.insert(TABLE_IMAGE, null, values);
        db.close();
    }

    public ImageHelper getImage(String imageId) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor2 = db.query(TABLE_IMAGE,
                new String[] {COL_ID, IMAGE_ID, IMAGE_JPEG},IMAGE_ID
                        +" LIKE '"+imageId+"%'", null, null, null, null);
        ImageHelper imageHelper = new ImageHelper();

        if (cursor2.moveToFirst()) {
            do {
                imageHelper.setImageId(cursor2.getString(1));
                imageHelper.setImageByteArray(cursor2.getBlob(2));
                imageHelper.setInsect(cursor2.getString(4));
            } while (cursor2.moveToNext());
        }

        cursor2.close();
        db.close();
        return imageHelper;
    }

}
