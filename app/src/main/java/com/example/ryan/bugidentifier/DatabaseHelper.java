package com.example.ryan.bugidentifier;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    //Configurations for the helper
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

    private void countRows(){
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT COUNT(*) FROM "+TABLE_IMAGE;
        SQLiteStatement statement =db.compileStatement(sql);
        long count = statement.simpleQueryForLong();
        Log.e("ccount2",count+"");
    }

    public void insertImage(Drawable dbDrawable, String imageId, String insect) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(IMAGE_ID, imageId);

        //convert drawable to byte array
        Bitmap bitmap = ((BitmapDrawable)dbDrawable).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);

        values.put(IMAGE_JPEG, stream.toByteArray());

        values.put(INSECT, insect);

        db.beginTransaction();
        if(db.insert(TABLE_IMAGE, null, values) == -1){
            Log.e("DataBaseHelper","Error inserting element into Database.");
        } else {
            Log.e ("DatabaseHelper", "Successfully inserted item into Database.");
            db.setTransactionSuccessful();
        }
        db.endTransaction();
        db.close();
    }

    public void getAll(List<Item> items){
        SQLiteDatabase db = this.getReadableDatabase();
        //Cursor  cursor = db.query(TABLE_IMAGE, new String[] {COL_ID, IMAGE_ID, IMAGE_JPEG, INSECT}, null, null, null, null, null);
        String sqlQuery = "select * from " + TABLE_IMAGE;
        Cursor cursor = db.rawQuery(sqlQuery, null);
        try {
            if (cursor.moveToFirst() && cursor != null) {
                Item newItem;
                while (!cursor.isAfterLast()) {
                    int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(COL_ID)));
                    String imageId = cursor.getString(cursor.getColumnIndex(IMAGE_ID));
                    byte[] imageByteArray = cursor.getBlob(cursor.getColumnIndex(IMAGE_JPEG));
                    String insect = cursor.getString(cursor.getColumnIndex(INSECT));

                    //convert the byte data to a bitmap
                    Bitmap bitmap;
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    bitmap = BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.length, options);

                    //Instantiate new Item with above values.
                    newItem = new Item(id, insect, bitmap, imageId);

                    items.add(newItem);
                    cursor.moveToNext();
                }
            } else {
                //TODO: Give error statement, and handle.
            }
        } catch (Exception e){
            Log.e("DatabaseHelper", "Failed to load from DB. Exception: " + e.toString() );
        }
        cursor.close();
        db.close();
    }
}
