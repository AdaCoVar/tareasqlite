package mx.edu.ittepic.adacova.u3practica1basedatossqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BaseDatos(
    context: Context,
    name:String?,
    factory: SQLiteDatabase.CursorFactory?,
    version:Int,
) : SQLiteOpenHelper(context,name,factory,version){
    //entre los parametros la version al cambiar se invoca el onupgrade
    //
    override fun onCreate(db: SQLiteDatabase
        //SQLITEDatabase sirve para hacer el CRUD de la base de datos
        //tiene los eventos de ejecucion, se construye la aplicacioln sql en la aplicacion,
        //hace el createtable, ios y android ya lo traen
    ) {
        /*Se invoca automaticamente tras la instalacion de eal app en el celular y construye
        la estructura de la BASE DE DATOS( tablas y relaciones) tienen prirmary key y foreign
         */
        db.execSQL("CREATE TABLE MAPEO_EMPRESAS ( IDAREA VARCHAR(20) PRIMARY KEY AUTOINCREMENT , DESCRIPCION VARCHAR(200), " +
                "DIVISION VARCHAR(50), CANTIDAD_EMPLEADOS VARCHAR(20))")//crea pero tambien sirven las istruccionees de sql

        //SI EN INSERT TENEMOS ERROR ES QUE TENEMOS ERROR EN LA TABLA
        db.execSQL("CREATE TABLE SUBDEPARTAMENTO ( IDSUBDEPTO VARCHAR(20) PRIMARY KEY AUTOINCREMENT ,IDEDIFICIO VARCHAR(20), IDAREA VARCHAR(20))")//crea pero tambien sirven las istruccionees de sql
        //, FOREIGN KEY (IDAREA) REFERENCES MAPEO_EMPRESAS (IDAREA))

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        /*Se invoca tras un cabio de version de la bd
        * en la version dde bd se usan numeros naturales, al haber diferencia, se dispara el onupgrade*/
    }
}