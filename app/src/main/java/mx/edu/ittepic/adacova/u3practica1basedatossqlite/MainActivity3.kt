package mx.edu.ittepic.adacova.u3practica1basedatossqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import mx.edu.ittepic.adacova.u3practica1basedatossqlite.databinding.ActivityMain3Binding

class MainActivity3 : AppCompatActivity() {
    lateinit var binding: ActivityMain3Binding
    var idSubdepto = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        idSubdepto = this.intent.extras!!.getString("idsubdepto")!!
        //nuestro alumno
        val subdepartamento = Subdepartamento(this).mostrarSubdepartamento(idSubdepto)

        //recuperamos
        binding.idSubdepto.setText(subdepartamento.idSubdepto)
        binding.idEdificio.setText(subdepartamento.idEdificio)

        binding.actualizar.setOnClickListener{
            val subdepartamento = Subdepartamento(this)
            subdepartamento.idSubdepto = binding.idSubdepto.text.toString()
            subdepartamento.idEdificio = binding.idEdificio.text.toString()
            subdepartamento.piso= binding.piso.text.toString()
            subdepartamento.idArea= binding.idArea.text.toString()

            val respuesta = subdepartamento.actualizar()
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
