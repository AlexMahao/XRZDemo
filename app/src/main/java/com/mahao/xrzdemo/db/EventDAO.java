package com.mahao.xrzdemo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mdw on 2015/12/1.
 */
public class EventDAO extends SQLiteOpenHelper {

    private static EventDAO dao;

    public static EventDAO getInstance(Context context){
        if(dao==null){
            dao = new EventDAO(context);
        }
        return dao;
    }


    private  EventDAO(Context context) {
        super(context, "xrz.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table t_collect(_id integer primary key,id,json)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * 查询所有的收藏
     * @return
     */
    public List<String> queryAll(){
        List<String> result = new ArrayList<String>();
        SQLiteDatabase db = dao.getReadableDatabase();

        Cursor cursor = db.query("t_collect",new String[]{"json"},null,null,null,null,null);

        while(cursor.moveToNext()){
            String json = cursor.getString(0);
            result.add(json);
        }
        db.close();
        return result;
    }

    /**
     * 查询是否存在
     * @param id
     * @return
     */
    public boolean isExsit(int id){
        SQLiteDatabase db = dao.getReadableDatabase();

        Cursor cursor = db.query("t_collect", new String[]{"json"}, "id=" + id, null, null, null, null);

        if(cursor.moveToNext()){
            return  true;
        }


        return  false;

    }
    /**
     * 插入收藏
     * @param json
     * @return
     */
    public long insert(String json,int id){
        if(isExsit(id)){
            return  -1;
        }

        SQLiteDatabase db = dao.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("id",id);
        values.put("json",json);


        long result = db.insert("t_collect", null, values);

        db.close();
        return  result;
    }

    /**
     * 删除某一收藏
     * @param id
     * @return
     */
    public long delete(int id){
        SQLiteDatabase db = dao.getWritableDatabase();

        int result = db.delete("t_collect", "id=" + id, null);

        db.close();
        return result;
    }

}
