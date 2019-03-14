package mx.edu.ittepic.tpdm_u2_practica2_delarosaguerrerogabriela;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import android.widget.Toast;

public class Propietario {
    private BD_Aseguradora base;
    private String telefono;
    private String nombre;
    private String domicilio;
    private String fecha;
    protected String error;

    public Propietario(Activity activity){base = new BD_Aseguradora(activity, "aseguradora",null,1); }

    public Propietario(String tel, String nombre, String dom, String fecha){
        this.telefono = tel;
        this.nombre = nombre;
        this.domicilio = dom;
        this.fecha = fecha;
    }

    public boolean insertar(Propietario propietario) {
        try {
            SQLiteDatabase transaccionInsertar = base.getWritableDatabase();
            ContentValues datos = new ContentValues();
            datos.put("TELEFONO", propietario.getTelefono());
            datos.put("NOMBRE", propietario.getNombre());
            datos.put("DOMICILIO", propietario.getDomicilio());
            datos.put("FECHA", propietario.getFecha());

            long resultado = transaccionInsertar.insert("PROPIETARIO", null, datos);
            transaccionInsertar.close();
            if (resultado == -1) {
                return false; //no se pudo hacer el insert
            }
        } catch (SQLiteException e) {
            error = e.getMessage();
            Log.e("Error SQL",e.getMessage());
            return false;
        }
        return true;
    }

    public boolean eliminar(Propietario propietario) {
        try {
            SQLiteDatabase transaccionEliminar = base.getWritableDatabase();
            long resultado = transaccionEliminar.delete("PROPIETARIO", "TELEFONO=?", new String[]{"" + propietario.getTelefono()});
            transaccionEliminar.close();

            if (resultado < 0) {
                return false;//no se elimino nada
            }
        } catch (SQLiteException e) {
            error = e.getMessage();
            return false;
        }
        return true;
    }

    public boolean modificar(Propietario propietario){
        try{
            SQLiteDatabase transaccionModificar = base.getWritableDatabase();
            ContentValues datos = new ContentValues();
            datos.put("TELEFONO", propietario.getTelefono());
            datos.put("NOMBRE", propietario.getNombre());
            datos.put("DOMICILIO", propietario.getDomicilio());
            datos.put("FECHA", propietario.getFecha());
            String dato[]={propietario.getTelefono()+""};
            long resultado = transaccionModificar.update("PROPIETARIO",datos,"TELEFONO=?",dato);
            transaccionModificar.close();
            if(resultado<0){
                return false; //no se actualizo nada
            }
        }catch(SQLiteException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Propietario[] consultar(){
        try{
            SQLiteDatabase db=base.getReadableDatabase();
            Cursor c=db.rawQuery("SELECT * FROM PROPIETARIO",null);
            if (c.moveToFirst()) {
                Propietario[] propietarios = new Propietario[c.getCount()];
                int pos = 0;
                do {
                    propietarios[pos] = new Propietario(c.getString(0),c.getString(1),c.getString(2),c.getString(3));
                    pos++;
                } while (c.moveToNext());
                return propietarios;
            }
        }catch(SQLiteException e){
            e.printStackTrace();
            return null;
        }
        return null;
    }
    public Propietario consultar(String dato){
        try{
            SQLiteDatabase db=base.getReadableDatabase();
            Cursor c=db.rawQuery("SELECT * FROM PROPIETARIO WHERE TELEFONO=?",new String[]{dato.toString()});
            if (c.moveToFirst()) {
                Propietario propietario = new Propietario(c.getString(0),c.getString(1),c.getString(2),c.getString(3));
                return propietario;
            }
        }catch(SQLiteException e){
            e.printStackTrace();
            return null;
        }
        return null;
    }


    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
