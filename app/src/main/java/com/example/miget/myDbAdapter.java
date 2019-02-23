package com.example.miget;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class myDbAdapter {
    myDbHelper myhelper;
    public myDbAdapter(Context context)
    {
        myhelper = new myDbHelper(context);
    }

    public long insertData(String name, String pass, String email)
    {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.UID,myDbHelper.NAME,myDbHelper.MyPASSWORD,myDbHelper.EMAIL};
        String[] values = {name};
        Cursor cursor =dbb.query(myDbHelper.TABLE_NAME,columns,myDbHelper.NAME + " = ?",values,null,null,null);
        long id;
        if (cursor.moveToNext()) {
            id = 0;
        } else {
            ContentValues contentValues = new ContentValues();
            contentValues.put(myDbHelper.NAME, name);
            contentValues.put(myDbHelper.MyPASSWORD, pass);
            contentValues.put(myDbHelper.EMAIL, email);
            id = dbb.insert(myDbHelper.TABLE_NAME, null , contentValues);
        }
        return id;
    }

    /*public String getData()
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.UID,myDbHelper.NAME,myDbHelper.MyPASSWORD};
        Cursor cursor =db.query(myDbHelper.TABLE_NAME,columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext())
        {
            int cid =cursor.getInt(cursor.getColumnIndex(myDbHelper.UID));
            String name =cursor.getString(cursor.getColumnIndex(myDbHelper.NAME));
            String  password =cursor.getString(cursor.getColumnIndex(myDbHelper.MyPASSWORD));
            buffer.append(cid+ "   " + name + "   " + password +" \n");
        }
        return buffer.toString();
    }*/

    public String[] getLogin(String username, String password)
    {
        SQLiteDatabase db = myhelper.getReadableDatabase();
        String[] columns = {myDbHelper.UID,myDbHelper.NAME,myDbHelper.MyPASSWORD,myDbHelper.EMAIL};
        String[] values = {username};
        Cursor cursor =db.query(myDbHelper.TABLE_NAME,columns,myDbHelper.NAME + " = ?",values,null,null,null);
        String pass, email;
        String[] ret = new String[4];
        if (cursor.moveToNext()) {
            pass = cursor.getString(cursor.getColumnIndex(myDbHelper.MyPASSWORD));
            if (pass.equals(password)){
                email = cursor.getString(cursor.getColumnIndex(myDbHelper.EMAIL));
                ret[0] = "Success";
                ret[1] = username;
                ret[2] = password;
                ret[3] = email;
            } else {
                ret[0] = "Wrong password";
            }
        } else {
            ret[0] = "Username not found";
        }
        return ret;
        /*SQLiteDatabase db=myhelper.getReadableDatabase();
        String[] columns = {myDbHelper.UID,myDbHelper.NAME,myDbHelper.MyPASSWORD};
        Cursor cursor =db.query(myDbHelper.TABLE_NAME,columns,null,null,null,null,null);
        String a, b;
        b = "not found";
        if (cursor.moveToNext()) {
            do {
                a = cursor.getString(1);

                if (a.equals(username)) {
                    b = cursor.getString(2);
                    break;
                }
            } while (cursor.moveToNext());

        }
        return b;*/
    }

    /*public  int delete(String uname)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] whereArgs ={uname};

        int count =db.delete(myDbHelper.TABLE_NAME ,myDbHelper.NAME+" = ?",whereArgs);
        return  count;
    }*/

    /*public int updateName(String oldName , String newName)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.NAME,newName);
        String[] whereArgs= {oldName};
        int count =db.update(myDbHelper.TABLE_NAME,contentValues, myDbHelper.NAME+" = ?",whereArgs );
        return count;
    }*/

    static class myDbHelper extends SQLiteOpenHelper
    {
        private static final String DATABASE_NAME = "myDatabase";    // Database Name
        private static final String TABLE_NAME = "myTable";   // Table Name
        private static final int DATABASE_Version = 1;    // Database Version
        private static final String UID="_id";     // Column I (Primary Key)
        private static final String NAME = "Name";    //Column II
        private static final String MyPASSWORD= "Password";    // Column III
        private static final String EMAIL= "Email";    // Column IV
        private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+
                " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+NAME+" VARCHAR(255), "+ MyPASSWORD+" VARCHAR(225), " + EMAIL + " VARCHAR(225));";
        private static final String DROP_TABLE ="DROP TABLE IF EXISTS "+TABLE_NAME;
        private Context context;

        public myDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context=context;
        }

        public void onCreate(SQLiteDatabase db) {

            try {
                db.execSQL(CREATE_TABLE);
            } catch (Exception e) {
                Message.message(context,""+e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                Message.message(context,"OnUpgrade");
                db.execSQL(DROP_TABLE);
                onCreate(db);
            }catch (Exception e) {
                Message.message(context,""+e);
            }
        }
    }
}
