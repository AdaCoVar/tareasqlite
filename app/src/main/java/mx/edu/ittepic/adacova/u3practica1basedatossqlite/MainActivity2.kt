package mx.edu.ittepic.adacova.u3practica1basedatossqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import mx.edu.ittepic.adacova.u3practica1basedatossqlite.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    lateinit var binding: ActivityMain2Binding
    var idArea =""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        idArea = this.intent.extras!!.getString("idArea")!!
        //nuestro alumno
        val empresa = Mapeo_Empresas(this).mostrarEmpresa(idArea)


        //recuperamos
        binding.idArea.setText(empresa.idArea)
        binding.descripcion.setText(empresa.descripcion)

        binding.actualizar.setOnClickListener{
            val empresa = Mapeo_Empresas(this)
            empresa.idArea = binding.idArea.text.toString()
            empresa.descripcion = binding.descripcion.text.toString()
            empresa.division= binding.division.text.toString()
            empresa.cantidad_empleados= binding.cantidadempleados.text.toString()

            val respuesta = empresa.actualizar()
            if(respuesta){
                Toast.makeText(this,"SE ACTUALIZO", Toast.LENGTH_SHORT)
                    .show()
            }else{
                AlertDialog.Builder(this)
                    .setTitle("Atencion")
                    .setMessage("No se pudo actualizar")
                    .show()
            }
        }

        binding.regresar.setOnClickListener{
            finish()
        }

    }
}
