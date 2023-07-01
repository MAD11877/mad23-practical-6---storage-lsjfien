package sg.edu.np.mad.madpractical;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;


public class MyDBHandler extends SQLiteOpenHelper {
    String title = "MyDBHandler";

    public static int DATABASE_VERSION = 1;
    public static String DATABASE_NAME = "Users.db";
    public static String USERS = "Users";
    public static String COLUMN_NAME = "Name";
    public static String COLUMN_DESCRIPTION = "Description";
    public static String COLUMN_ID = "Id";
    public static String COLUMN_FOLLOWED = "Followed";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        Log.v(title, "On Create Database");
        String CREATE_USER_TABLE = "CREATE TABLE " + USERS + "(" +
                COLUMN_NAME + " TEXT," +
                COLUMN_DESCRIPTION + " TEXT," +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_FOLLOWED + " TEXT)";
        Log.i(title, CREATE_USER_TABLE);
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + USERS);
        onCreate(db);
    }

    public void addUser(User userData){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, userData.getName());
        values.put(COLUMN_DESCRIPTION, userData.getDescription());
        values.put(COLUMN_FOLLOWED, String.valueOf(userData.isFollowed()));

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(USERS, null, values);
        db.close();
        Log.i(title, "Inserted/Created User " + values);
    }

    public ArrayList<User> getUsers(){
        String query = "SELECT * FROM " + USERS;
        Log.i(title, "Query: " + query);
        User queryResult = new User();
        ArrayList<User> userList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            queryResult.setName((cursor.getString(0)));
            queryResult.setDescription((cursor.getString(1)));
            queryResult.setId(cursor.getInt(2));
            queryResult.setFollowed(Boolean.parseBoolean(cursor.getString(3)));
            userList.add(queryResult);
            while (cursor.moveToNext()){
                User user = new User();
                user.setName((cursor.getString(0)));
                user.setDescription((cursor.getString(1)));
                user.setId(cursor.getInt(2));
                user.setFollowed(Boolean.parseBoolean(cursor.getString(3)));
                userList.add(user);
            }
            cursor.close();
        }
        else{
            Log.w(title, "NO DATA");
            userList = null;
        }
        db.close();
        return userList;
    }

    public void updateUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME, user.getName());
        values.put(COLUMN_DESCRIPTION, user.getDescription());
        values.put(COLUMN_FOLLOWED, String.valueOf(user.isFollowed()));
        db.update(USERS, values, COLUMN_ID + "=?", new String[]{String.valueOf(user.getId())});
        Log.i(title, "Updated User");
        db.close();
    }
}