package mx.edu.ittepic.adacova.u3practica1basedatossqlite

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteException

class Subdepartamento (este: Context){

    //por cada tabla una clase
    //variables iguales a la tabla
    var idSubdepto=""
    var idEdificio=""
    var piso=""
    var idArea =""
    var este = este
    var err=""
    //no pertence a la tabla



    //un curso es un resultado, es las tuplas o renglones del query, no se usa un cursor grande y global, porque se da de baja de memoria
    //sqlite esta dentro de la dist android o ios

    fun insertar():Boolean{
        var baseDatos = BaseDatos(este,"subdepartamento",null,1)
        err="" //limpiamos
        try{
            val tabla = baseDatos.writableDatabase
            var datos = ContentValues()
            datos.put("IDSUBDEPTO",idSubdepto)
            datos.put("IDEDIFICIO",idEdificio)
            datos.put("PISO",piso)
            datos.put("IDAREA",idArea)

            var resultado = tabla.insert("SUBDEPARTAMENTO",null,datos)

            //saber si es -1L si tiene algo
            if(resultado==-1L)
            {
                return false
            }

        }catch(err: SQLiteException){
            this.err=err.message!! //si no es empty se disparo el err del catch y se llama desde el main
            return false;
        }
        finally{
            baseDatos.close()
        }
        return true
    }

    fun mostrarTodos():ArrayList<Subdepartamento>{
        //hacemos consulta y obtenemoso todos los datos
        var baseDatos = BaseDatos(este,"subdepartamento",null,1)
        var arreglo = ArrayList<Subdepartamento>()
        err=""
        try{
            var tabla=baseDatos.readableDatabase
            var SQL_SELECT="SELECT * FROM SUBDEPARTAMENTO"


            var cursor=tabla.rawQuery(SQL_SELECT,null) //solo lleva el cursor porque no hicimos where
            if(cursor.moveToFirst())
            {
                do{
                    val subdepartamento = Subdepartamento(este)
                    subdepartamento.idSubdepto=cursor.getString(0)
                    subdepartamento.idEdificio=cursor.getString(1)
                    subdepartamento.piso=cursor.getString(2)
                    arreglo.add(subdepartamento)

                }while(cursor.moveToNext())
            }

        }catch(err: SQLiteException){
            this.err=err.message!!

        }
        finally{
            baseDatos.close()
        }
        return arreglo

    }

    fun mostrarSubdepartamento(idEdificioBuscar:String):Subdepartamento{
        //hacemos consulta y obtenemoso todos los datos
        var baseDatos = BaseDatos(este,"subdepartamento",null,1)
        var arreglo = ArrayList<Subdepartamento>()
        val subdepartamento = Subdepartamento(este)
        err=""
        try{
            var tabla=baseDatos.readableDatabase
            var SQL_SELECT="SELECT * FROM SUBDEPARTAMENTO WHERE IDEDIFICIO=? "


            var cursor=tabla.rawQuery(SQL_SELECT,arrayOf(idEdificioBuscar) )//solo lleva el cursor porque no hicimos where
            if(cursor.moveToFirst())
            {

                val subdepartamento = Subdepartamento(este)
                subdepartamento.idSubdepto=cursor.getString(0)
                subdepartamento.idEdificio=cursor.getString(1)
                subdepartamento.piso=cursor.getString(2)
                subdepartamento.idArea=cursor.getString(3)

            }

        }catch(err: SQLiteException){
            this.err=err.message!!

        }
        finally{
            baseDatos.close()
        }
        return subdepartamento

    }

    fun actualizar():Boolean{
        var baseDatos = BaseDatos(este,"subdepartamento",null,1)
        err=""
        try{
            var tabla = baseDatos.writableDatabase
            var datosActualizados= ContentValues()
            datosActualizados.put("IDEDIFICIO",idEdificio)
            datosActualizados.put("IDSUBDEPTO",idSubdepto)
            datosActualizados.put("PISO",piso)
            datosActualizados.put("IDAREA",idArea)

            val resultado=tabla.update("SUBDEPARTAMENTO",datosActualizados,"IDSUBDEPTO=?",arrayOf(idSubdepto))

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
    fun eliminar(idSubdeptoEliminar:String):Boolean {
        var baseDatos = BaseDatos(este,"subdepartamento",null,1)
        err=""
        try{
            var tabla = baseDatos.writableDatabase
            val respuesta = tabla.delete("SUBDEPARTAMENTO","IDSUBDEPTO=?",arrayOf(idSubdeptoEliminar))


            //regresamos un id de la tupla insertada
            if(respuesta==0){
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
        var baseDatos = BaseDatos(este,"subdepartamento",null,1)
        err=""
        try{
            var tabla = baseDatos.writableDatabase
            val respuesta = tabla.delete("SUBDEPARTAMENTO","IDSUBDEPTO=?",arrayOf(idSubdepto))

            //se consgtruye objeto alumno y despues invocas eliminar


            //regresamos un id de la tupla insertada
            if(respuesta==0){
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