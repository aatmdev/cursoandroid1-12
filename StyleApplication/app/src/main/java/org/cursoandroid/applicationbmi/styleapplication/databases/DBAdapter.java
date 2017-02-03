package org.cursoandroid.applicationbmi.styleapplication.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.cursoandroid.applicationbmi.styleapplication.models.PointDTO;

import java.util.List;

import static org.cursoandroid.applicationbmi.styleapplication.utilities.Constantes.COLS;
import static org.cursoandroid.applicationbmi.styleapplication.utilities.Constantes.DB_NAME;
import static org.cursoandroid.applicationbmi.styleapplication.utilities.Constantes.DB_VERSION;
import static org.cursoandroid.applicationbmi.styleapplication.utilities.Constantes.TABLE_NAME;
import static org.cursoandroid.applicationbmi.styleapplication.utilities.Constantes._ADDRESS;
import static org.cursoandroid.applicationbmi.styleapplication.utilities.Constantes._ID;
import static org.cursoandroid.applicationbmi.styleapplication.utilities.Constantes._NAME;
import static org.cursoandroid.applicationbmi.styleapplication.utilities.Constantes._PK;

/**
 * Created by aatovarma on 2/02/2017.
 */

public class DBAdapter extends SQLiteOpenHelper {

    private SQLiteDatabase _db;

    public DBAdapter(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this._db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table " + TABLE_NAME + " (" +
                    _ID + " integer primary key autoincrement, " +
                    _PK + " integer, " +
                    _NAME + " text, " +
                    _ADDRESS + " text "
                + " ); ";
        db.execSQL(query);
    }

    public void insert(List<PointDTO> puntos){
        for(PointDTO punto : puntos){
            ContentValues cv = new ContentValues();
            cv.put(_PK, punto.getId());
            cv.put(_NAME, punto.getNombre());
            cv.put(_ADDRESS, punto.getDireccion());
            _db.insert(TABLE_NAME, null, cv);
        }
    }

    public void update(PointDTO punto, int id){
        ContentValues cv = new ContentValues();
        cv.put(_PK, punto.getId());
        cv.put(_NAME, punto.getNombre());
        cv.put(_ADDRESS, punto.getDireccion());
        _db.update(TABLE_NAME, cv, _ID + " = " + id, null);

    }

    public void delete(int id){
        _db.delete(TABLE_NAME, _ID + " = " + id, null);
    }

    public Cursor getAll(){
        return _db.query(TABLE_NAME, COLS, null, null, null, null, null);
    }

    public Cursor get(int id){
        return _db.query(TABLE_NAME, COLS, _ID + " = " + id, null, null, null, null);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
