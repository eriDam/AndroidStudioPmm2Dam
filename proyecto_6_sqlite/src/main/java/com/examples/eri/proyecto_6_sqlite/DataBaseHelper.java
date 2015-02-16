package com.examples.eri.proyecto_6_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by erika_000 on 16/02/2015.
 */

/*Creamos esta clase propia extendida de SQLiteOpenHelper, es una clase especifica, un ayudante para
* manejar las BDS dentro de SQLite, debemos sobreescribir tanto oncreate como onUpgrade,
 * estas deben estar definidads como minimo*/
public class DataBaseHelper extends SQLiteOpenHelper {

    //Datos de la tabla - Defino Atributos
    //Ponemos el nombre de la Base de datos
    final private static String NAME = "artistas_db";
    //Indicamos el nombre de la única tabla que tendremos
    final static String TABLE_NAME = "artistas";

    /*Indicamos las columnas, el _id, se define de esta forma, por que para
     *posteriores lecturas y adaptadores (adapters), se necesita q este este campo,
     *no se le puede quitar el guión*/
    final static String ID = "_id";
    final static String ARTIST_NAME = "nombre";

    //Comandos
    /**Dentro de un array d Strings, almaceno las columnas*/
    final static String[] columns = { ID, ARTIST_NAME };
    final private static String CREATE_CMD =

            "CREATE TABLE "+ TABLE_NAME +" (" + ID
                    + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + ARTIST_NAME + " TEXT NOT NULL)";


    final private static Integer VERSION = 1;
    final private Context mContext;


    //Constructor
    public DataBaseHelper(Context context) {
        super(context, NAME, null, VERSION);
        this.mContext = context;
    }

    //Creación de la base de datos, el onCreate lo utilizaremos tanto para generar la tabla
    //como para crear/rellenar los campos dentro de la misma, para ello utilizaremos la clase ContentValues
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Creamos la base de datos
        Log.i(this.getClass().toString(), "Tabla ARTISTAS creada");
        //Generamos la tabla, CREATE_CMD es un string que contiene la sentencia SQL para la creación de la tabla
        db.execSQL(CREATE_CMD);
        /*Rellenamos la tabla,  utilizamos la Clase ContentValues, que permite de una forma fácil
        * con el método put indicamos el campo o campos y el valor y con insert, le idnicamos la tabla */
        ContentValues values = new ContentValues();

        values.put(DataBaseHelper.ARTIST_NAME, "Vetusta Morla");
        db.insert(DataBaseHelper.TABLE_NAME, null, values);
        values.clear();

        values.put(DataBaseHelper.ARTIST_NAME, "ColdPlay");
        db.insert(DataBaseHelper.TABLE_NAME, null, values);
        values.clear();

        values.put(DataBaseHelper.ARTIST_NAME, "All India Radio");
        db.insert(DataBaseHelper.TABLE_NAME, null, values);
        Log.i(this.getClass().toString(), "Datos insertados");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //Método accesible desde fuera para la  lectura de la Base de datos
    //cursor es una clase de List, new String [] {} es por si queremos pasar mas parametros, en este caso no
    public Cursor readArtistas(SQLiteDatabase db) {
        return db.query(TABLE_NAME,
                columns,null,new String [] {}, null,null,
                null);

    }
}
