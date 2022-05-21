package mx.edu.ittepic.adacova.u3practica1basedatossqlite

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteException
import androidx.core.content.ContentProviderCompat.requireContext
import mx.edu.ittepic.adacova.u3practica1basedatossqlite.fragments.HomeFragment
import java.lang.reflect.Array.getInt

class Mapeo_Empresas(este: Context) {

    //por cada tabla una clase
    //variables iguales a la tabla
    var idArea = ""
    var descripcion = ""
    var division = ""
    var cantidad_empleados = ""
    var este = este
    var err = ""
    //no pertence a la tabla


    //un curso es un resultado, es las tuplas o renglones del query, no se usa un cursor grande y global, porque se da de baja de memoria
    //sqlite esta dentro de la dist android o ios

    fun insertar(): Boolean {
        var baseDatos = BaseDatos(este, "empresa", null, 1)
        err = "" //limpiamos
        try {
            val tabla = baseDatos.writableDatabase
            var datos = ContentValues()
            datos.put("IDAREA", idArea)
            datos.put("DESCRIPCION", descripcion)
            datos.put("DIVISION", division)
            datos.put(" CANTIDAD_EMPLEADOS", cantidad_empleados)

            var resultado = tabla.insert("MAPEO_EMPRESAS", null, datos)

            //saber si es -1L si tiene algo
            if (resultado == -1L) {
                return false
            }

        } catch (err: SQLiteException) {
            this.err =
                err.message!! //si no es empty se disparo el err del catch y se llama desde el main
            return false;
        } finally {
            baseDatos.close()
        }
        return true
    }

    fun mostrarTodos(): ArrayList<Mapeo_Empresas> {
        //hacemos consulta y obtenemoso todos los datos
        var baseDatos = BaseDatos(este, "empresa", null, 1)
        var arreglo = ArrayList<Mapeo_Empresas>()
        err = ""
        try {
            var tabla = baseDatos.readableDatabase
           // var SQL_SELECT = "SELECT * FROM MAPEO_EMPRESAS WHERE IDAREA=?"
            var SQL_SELECT = "SELECT * FROM MAPEO_EMPRESAS"


            var cursor =
                tabla.rawQuery(SQL_SELECT, null, ) //solo lleva el cursor porque no hicimos where
            if (cursor.moveToFirst()) {
                do {
                    val empresa = Mapeo_Empresas(este)
                    empresa.idArea = cursor.getString(0)
                    empresa.descripcion = cursor.getString(1)
                    empresa.division = cursor.getString(2)
                    empresa.cantidad_empleados = cursor.getString(3)
                    arreglo.add(empresa)

                } while (cursor.moveToNext())
            }

        } catch (err: SQLiteException) {
            this.err = err.message!!

        } finally {
            baseDatos.close()
        }
        return arreglo

    }

    fun mostrarEmpresa(idAreaBuscar: String): Mapeo_Empresas {
        //CONSULTA POR DESCRIPCION Y DIVISION
        //hacemos consulta y obtenemoso todos los datos
        var baseDatos = BaseDatos(este, "empresa", null, 1)
        var arreglo = ArrayList<Mapeo_Empresas>()
        var encontrado=idAreaBuscar
        val empresa = Mapeo_Empresas(este)
        //var filtro = filtro
        err = ""
        var SQL_SELECT = ""
                try{

                    var tabla = baseDatos.readableDatabase


        var cursor = tabla.rawQuery(SQL_SELECT, null)//solo lleva el cursor porque no hicimos where
        if (cursor.moveToFirst()) {
            do {
                val empresa = Mapeo_Empresas(este)
                empresa.idArea = cursor.getString(0)
                empresa.descripcion = cursor.getString(1)
                empresa.division = cursor.getString(2)
                empresa.cantidad_empleados = cursor.getString(3)
                arreglo.add(empresa)
            }while(cursor.moveToNext())

        }

    }catch(err: SQLiteException) {
        this.err = err.message!!

    }
    finally {
        baseDatos.close()
    }
    return empresa

}

        fun actualizar():Boolean{
            var baseDatos = BaseDatos(este,"empresa",null,1)
            err=""
            try{
                var tabla = baseDatos.writableDatabase
                var datosActualizados= ContentValues()

                datosActualizados.put("IDAREA",idArea)
                datosActualizados.put("DESCRIPCION",idArea)
                datosActualizados.put("DIVISION",idArea)
                datosActualizados.put("CANTIDAD_EMPLEADOS",idArea)

                val resultado=tabla.update("MAPEO_EMPRESAS",datosActualizados,"IDAREA=?",
                    arrayOf(idArea)
                )

                //regresamos un id de la tupla insertada
                if(resultado==0){
                    return false
                }

            }catch(err: SQLiteException){
                this.err=err.message!!
                return false
            }
            finally{
                baseDatos.close()
            }
            return true
        }

        //eliminar se parametriza pero lo puede llamar el objeto directamente, solo se usa el campo el numeroo de control
        fun eliminar(idAreaEliminar:String):Boolean {
            var baseDatos = BaseDatos(este,"empresa",null,1)
            err=""
            try{
                var tabla = baseDatos.writableDatabase
                val resultado = tabla.delete("MAPEO_EMPRESAS","IDAREAL=?",arrayOf(idAreaEliminar))


                //regresamos un id de la tupla insertada
                if(resultado==0){
                    return false
                }

            }catch(err: SQLiteException){
                this.err=err.message!!
                return false
            }
            finally{
                baseDatos.close()
            }
            return false
        }

        //MODO 2 DE ELIMINAR
        fun eliminar():Boolean {
            var baseDatos = BaseDatos(este,"empresa",null,1)
            err=""
            try{
                var tabla = baseDatos.writableDatabase
                val resultado = tabla.delete("MAPEO_EMPRESAS","IDAREA=?",
                    arrayOf((idArea).toString())
                )

                //se consgtruye objeto alumno y despues invocas eliminar


                //regresamos un id de la tupla insertada
                if(resultado==0){
                    return false
                }

            }catch(err: SQLiteException){
                this.err=err.message!!
                return false
            }
            finally{
                baseDatos.close()
            }
            return false
        }
}


/*
*     fun insertar():Boolean{
        var baseDatos = BaseDatos(este,"escuela",null,1)
        * err=""
        try{

        }catch(err: SQLiteException){

        }
        finally{
            baseDatos.close()
        }


}*/
