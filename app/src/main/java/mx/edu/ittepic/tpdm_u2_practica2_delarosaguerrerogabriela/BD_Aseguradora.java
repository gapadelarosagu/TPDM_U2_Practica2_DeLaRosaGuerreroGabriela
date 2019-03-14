package mx.edu.ittepic.tpdm_u2_practica2_delarosaguerrerogabriela;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BD_Aseguradora extends SQLiteOpenHelper {
    public BD_Aseguradora(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context,name,factory,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE PROPIETARIO(TELEFONO VARCHAR(20) PRIMARY KEY NOT NULL, NOMBRE VARCHAR(200), DOMICILIO VARCHAR(200),FECHA VARCHAR(200))");

        db.execSQL("CREATE TABLE SEGURO(IDSEGURO INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, DESCRIPCION VARCHAR(200)," +
                "FECHA VARCHAR(200),TIPO VARCHAR(200),TELEFONO VARCHAR(200),FOREIGN KEY (TELEFONO) REFERENCES PROPIETARIO(TELEFONO))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
