package br.unigran.banco_dados;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import br.unigran.lista_compra.Produto;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(@Nullable Context context) {
        super(context, "BancoListaCompra", null, 5);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
           "create table produto(" +
           "id integer primary key autoincrement," +
           "nome varchar(150)," +
           "marca varchar(150)," +
           "quantidade integer);"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(
            "create table produto(" +
            "id integer primary key autoincrement," +
            "nome varchar(150)," +
            "marca varchar(150)," +
            "quantidade integer);"
        );
    }
}
