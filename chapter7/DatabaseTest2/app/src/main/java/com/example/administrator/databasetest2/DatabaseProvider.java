package com.example.administrator.databasetest2;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class DatabaseProvider extends ContentProvider {
    public DatabaseProvider() {
    }
    public static final int BOOK_DIR =0;
    public  static final int BOOK_ITEM=1;
    public  static final int CATEGORY_DIR=2;
    public  static final int CATEGORY_ITEM=3;
    public static final String AUTHORITY = "com.example.administrator.databasetest2.provider";
    // content://com.example.administrator.databasetest2.provider/book
    private static UriMatcher uriMatcher;
    private MyDatabaseHelper dbhelper;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY,"book",BOOK_DIR);
        uriMatcher.addURI(AUTHORITY,"book/#",BOOK_ITEM);
        uriMatcher.addURI(AUTHORITY,"category",CATEGORY_DIR);
        uriMatcher.addURI(AUTHORITY,"category/#",CATEGORY_ITEM);
    }
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
//        throw new UnsupportedOperationException("Not yet implemented");
        SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();
        int deleteRows = 0;
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
                deleteRows = sqLiteDatabase.delete("Book",selection,selectionArgs);
                break;
            case BOOK_ITEM:
                String bookId = uri.getPathSegments().get(1);
                deleteRows = sqLiteDatabase.delete("Book","id = ?",new String[]{bookId});
                break;
            case CATEGORY_DIR:
                deleteRows = sqLiteDatabase.delete("Category",selection,selectionArgs);
                break;
            case CATEGORY_ITEM:
                String categoryId = uri.getPathSegments().get(1);
                deleteRows = sqLiteDatabase.delete("Category","id = ? ",new String[]{categoryId});
                break;
                default:
                    break;


            }
            return deleteRows;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
//        throw new UnsupportedOperationException("Not yet implemented");
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.administrator.databasetest2.provider.book";
            case BOOK_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.administrator.databasetest2.provider.book";
            case CATEGORY_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.administrator.databasetest2.provider.category";
            case CATEGORY_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.administrator.databasetest2.provider.category";
                // content://com.example.administrator.databasetest2.provider/book
                default:
                    break;
        }
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        SQLiteDatabase sqLiteDatabase=dbhelper.getWritableDatabase();
        Uri uriReturn = null;
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
            case BOOK_ITEM:
                long newBookId = sqLiteDatabase.insert("Book", null, values);
               uriReturn = Uri.parse("content://" + AUTHORITY + "/book/" + newBookId);
                break;
            case CATEGORY_DIR:
            case CATEGORY_ITEM:
                long newCategoryId = sqLiteDatabase.insert("Category",null,values);
                uriReturn = Uri.parse("content://"+AUTHORITY+"/category/"+newCategoryId);
                break;
                default:
                    break;

        }
       // throw new UnsupportedOperationException("Not yet implemented");
        return uriReturn;
    }


//    @Override
//    public Uri insert(Uri uri, ContentValues values) {
//        // 添加数据
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        Uri uriReturn = null;
//        switch (uriMatcher.match(uri)) {
//            case BOOK_DIR:
//            case BOOK_ITEM:
//                long newBookId = db.insert("Book", null, values);
//                uriReturn = Uri.parse("content://" + AUTHORITY + "/book/" + newBookId);
//                break;
//            case CATEGORY_DIR:
//            case CATEGORY_ITEM:
//                long newCategoryId = db.insert("Category", null, values);
//                uriReturn = Uri.parse("content://" + AUTHORITY + "/category/" + newCategoryId);
//                break;
//            default:
//                break;
//        }
//        return uriReturn;
//    }



    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        dbhelper=new MyDatabaseHelper(getContext(),"BookStore2.db",null,2);

        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.
        SQLiteDatabase db=dbhelper.getReadableDatabase();
        Cursor cursor = null;
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
                cursor = db.query("Book",projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case BOOK_ITEM:
                String bookId = uri.getPathSegments().get(1);
                cursor = db.query("Book",projection,"id = ?",new String[]{bookId},null,null,sortOrder);
                break;
            case CATEGORY_DIR:
                cursor = db.query("Category", projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case CATEGORY_ITEM:
                String categoryId = uri.getPathSegments().get(1);
                cursor = db.query("Category", projection, "id = ?", new String[] { categoryId }, null, null, sortOrder);
                break;
            default:
                break;
        }
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();
        int updateRows = 0;
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
                updateRows = sqLiteDatabase.update("Book",values,selection,selectionArgs);
                break;
            case BOOK_ITEM:
                String bookId =uri.getPathSegments().get(1);
                updateRows = sqLiteDatabase.update("Book",values,"id = ?",new String[]{bookId});
                break;
            case CATEGORY_DIR:
                updateRows = sqLiteDatabase.update("Category",values,selection,selectionArgs);
                break;
            case CATEGORY_ITEM:
                String categoryId = uri.getPathSegments().get(1);
                updateRows = sqLiteDatabase.update("Category",values,"id = ?",new String[]{categoryId});
                default:
                    break;
        }
        //throw new UnsupportedOperationException("Not yet implemented");
        return updateRows;
    }
}
