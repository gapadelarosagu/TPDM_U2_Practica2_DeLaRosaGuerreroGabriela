package mx.edu.ittepic.tpdm_u2_practica2_delarosaguerrerogabriela;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

public class Seguro {
    private BD_Aseguradora base;
    private int idseguro;
    private String descripcion;
    private String fecha;
    private String tipo;
    private String telefono;
    protected String error;

    public Seguro(Activity activity){base = new BD_Aseguradora(activity, "aseguradora",null,1); }

    public Seguro(int idseguro, String des, String fecha, String tipo,String telefono){
        this.idseguro = idseguro;
        this.descripcion= des;
        this.fecha = fecha;
        this.tipo = tipo;
        this.telefono=telefono;
    }

    public boolean insertar(Seguro seguro) {
        try {
            SQLiteDatabase transaccionInsertar = base.getWritableDatabase();
            ContentValues datos = new ContentValues();
            datos.put("DESCRIPCION", seguro.getDescripcion());
            datos.put("FECHA", seguro.getFecha());
            datos.put("TIPO", seguro.getTipo());
            datos.put("TELEFONO",seguro.getTelefono());

            long resultado = transaccionInsertar.insert("SEGURO", "IDSEGURO", datos);
            transaccionInsertar.close();
            if (resultado < 0) {
                return false; //no se pudo hacer el insert
            }
        } catch (SQLiteException e) {
            error = e.getMessage();
            return false;
        }
        return true;
    }

    public boolean eliminar(Seguro seguro) {
        try {
            SQLiteDatabase transaccionEliminar = base.getWritableDatabase();
            long resultado = transaccionEliminar.delete("SEGURO", "IDSEGURO=?", new String[]{"" + seguro.getIdseguro()});
            transaccionEliminar.close();

            if (resultado == 0) {
                return false;//no se elimino nada
            }
        } catch (SQLiteException e) {
            error = e.getMessage();
            return false;
        }
        return true;
    }

    public boolean modificar(Seguro seguro){
        try{
            SQLiteDatabase transaccionModificar = base.getWritableDatabase();
            ContentValues datos = new ContentValues();
            datos.put("DESCRIPCION", seguro.getDescripcion());
            datos.put("FECHA", seguro.getFecha());
            datos.put("TIPO", seguro.getTipo());
            datos.put("TELEFONO",seguro.getTelefono());
            String dato[]={seguro.getIdseguro()+""};
            long resultado = transaccionModificar.update("SEGURO",datos,"IDSEGURO=?",dato);
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

    public Seguro[] consultar(){
        try{
            SQLiteDatabase db=base.getReadableDatabase();
            Cursor c=db.rawQuery("SELECT * FROM SEGURO",null);
            if (c.moveToFirst()) {
                Seguro[] seguros = new Seguro[c.getCount()];
                int pos = 0;
                do {
                    seguros[pos] = new Seguro(c.getInt(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4));
                    pos++;
                } while (c.moveToNext());
                return seguros;
            }
        }catch(SQLiteException e){
            e.printStackTrace();
            return null;
        }
        return null;
    }


    public int getIdseguro() {
        return idseguro;
    }

    public void setIdseguro(int idseguro) {
        this.idseguro = idseguro;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
