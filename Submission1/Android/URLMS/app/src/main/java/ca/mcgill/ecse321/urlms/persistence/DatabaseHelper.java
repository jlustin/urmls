package ca.mcgill.ecse321.urlms.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

import ca.mcgill.ecse321.urlms.model.StaffManager;
import ca.mcgill.ecse321.urlms.model.StaffMember;
import ca.mcgill.ecse321.urlms.model.URLMS;

import static android.R.attr.id;

/**
 * Created by ericvuong on 2017-10-15.
 */

public class DatabaseHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "staffManager.db";
    public static final String TABLE_NAME = "staff_table";

    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static URLMS urlms = new URLMS(0);

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
    }
@Override
    public void onCreate(SQLiteDatabase db){
    db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT)");
}

@Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
    db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
    onCreate(db);
}

    public boolean insertData(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result==-1){
            return false;
        }else return true;
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select * from " +TABLE_NAME, null);
        return result;
    }

    public boolean updateData(String id, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_2, name);
        db.update(TABLE_NAME, contentValues, "ID = ?", new String[] {id});
        return true;
    }
    public Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?", new String[] {id});
    }
    public void deleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();
            db.delete(TABLE_NAME, "1", null);
    }
    public List<StaffMember> load(Cursor cursor){
        StaffManager sm = this.urlms.getStaffManager();
        while(cursor.moveToNext()){
            if(cursor.getString(0)!=null){
                sm.addStaffMember(cursor.getString(1),Integer.parseInt(cursor.getString(0)));
            }}
        return sm.getStaffMembers();
    }
}
