package com.example.project3;


//imports

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.content.ContentValues;
import android.database.sqlite.SQLiteOpenHelper;

//create database and store CRUD operations
public abstract class Database extends SQLiteOpenHelper {

//declare string variables for table parameters
    public static final int dbVersion = 1;
    private static final String USER_DATABASE = "USER_DATABASE";
    public static final String TABLE_1 = "USER_TABLE";
    public static final String COLUMN_1 = "USERNAME";
    public static final String COLUMN_2 = "PASSWORD";
    public static final int db2Version = 1;
    public static final String TABLE_2 = "ITEM_TABLE";
    public static final String TABLE_2_COLUMN_1 = "ITEM";
    public static final String TABLE_2_COLUMN_2 = "AMOUNT";

//table create statements
    private static final String CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS " +
        TABLE_1 + " (" + COLUMN_1 +"VARCHAR NOT NULL,"+ COLUMN_2 + "VARCHAR NOT NULL" + ");";

    private static final String CREATE_ITEM_TABLE = "CREATE TABLE IF NOT EXISTS " +
        TABLE_2 + " (" + TABLE_2_COLUMN_1 +"VARCHAR NOT NULL,"+ TABLE_2_COLUMN_2 + "INT NOT NULL" + ");";
    public Database( Context context) {
        super(context, USER_DATABASE, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        CreateUserTable(db);
        CreateItemTable(db);
    }

    /**
     * Called when the database needs to be upgraded. The implementation
     * should use this method to drop tables, add tables, or do anything else it
     * needs to upgrade to the new schema version.
     *
     * <p>
     * The SQLite ALTER TABLE documentation can be found
     * <a href="http://sqlite.org/lang_altertable.html">here</a>. If you add new columns
     * you can use ALTER TABLE to insert them into a live table. If you rename or remove columns
     * you can use ALTER TABLE to rename the old table, then create the new table and then
     * populate the new table with the contents of the old table.
     * </p><p>
     * This method executes within a transaction.  If an exception is thrown, all changes
     * will automatically be rolled back.
     * </p>
     * <p>
     * <em>Important:</em> You should NOT modify an existing migration step from version X to X+1
     * once a build has been released containing that migration step.  If a migration step has an
     * error and it runs on a device, the step will NOT re-run itself in the future if a fix is made
     * to the migration step.</p>
     * <p>For example, suppose a migration step renames a database column from {@code foo} to
     * {@code bar} when the name should have been {@code baz}.  If that migration step is released
     * in a build and runs on a user's device, the column will be renamed to {@code bar}.  If the
     * developer subsequently edits this same migration step to change the name to {@code baz} as
     * intended, the user devices which have already run this step will still have the name
     * {@code bar}.  Instead, a NEW migration step should be created to correct the error and rename
     * {@code bar} to {@code baz}, ensuring the error is corrected on devices which have already run
     * the migration step with the error.</p>
     *
     * @param db         The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public static void CreateUserTable(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
    }
    public static void CreateItemTable(SQLiteDatabase db) {
        db.execSQL(CREATE_ITEM_TABLE);
    }
//Create Operation
    public void addUser(User user) {
        ContentValues values = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();
        values.put(COLUMN_1,user.getUser_name());
        values.put(COLUMN_2, user.getUser_password());
        db.insert(TABLE_1, null,values);
}
//Read Operation
    public User getUser (String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_1,
                new String[] {COLUMN_1,COLUMN_2}, COLUMN_1,new String[] {COLUMN_1 + " =" + username},null,null,null,null);
        cursor.moveToFirst();
        User user = new User(cursor.getString(0),cursor.getString(1));
        return user;
}
//crud operations for item table
    public void addItem (Item item) {
        ContentValues values = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();
        values.put(TABLE_2_COLUMN_1,item.itemName);
        values.put(TABLE_2_COLUMN_2, item.itemAmount);
        db.insert(TABLE_2, null,values);
    }
    //que ry all items in table
    public void getItem () {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_2,new String [] {TABLE_2_COLUMN_1, TABLE_2_COLUMN_2},"SELECT * ",null,null,null,null);
    }
    //update item in table
    public void updateItem (Item item) {
        ContentValues values = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();
        values.put(TABLE_2_COLUMN_1,item.itemName);
        values.put(TABLE_2_COLUMN_2, item.itemAmount);
        db.update(TABLE_2,values,"WHERE " + TABLE_2_COLUMN_2 + "= ?",new String[]{item.itemName});
    }
    //delete item in table
    public void deleteItem(Item item) {
        ContentValues values = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_2,"WHERE" + TABLE_2_COLUMN_2 + "= ?", new String[] {item.itemName});
    }
}
