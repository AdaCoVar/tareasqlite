package mx.edu.ittepic.adacova.u3practica1basedatossqlite.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import mx.edu.ittepic.adacova.u3practica1basedatossqlite.*
import mx.edu.ittepic.adacova.u3practica1basedatossqlite.databinding.FragmentHomeBinding
import mx.edu.ittepic.adacova.u3practica1basedatossqlite.databinding.FragmentOption2Binding

class Option2Fragment : Fragment() {

        var _binding: FragmentOption2Binding? = null
        val binding get()= _binding!!
        val listaIDs = ArrayList<String>()
        //val adapter = ArrayAdapter(requireContext(),)
        //val appContext = requireContext()!!.applicationContext

        //para cuando volvemos
        override fun onResume() {
            super.onResume()
            mostrarDatosEnListView()
        }

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            _binding = FragmentOption2Binding.inflate(inflater,container,false)
            val view = binding.root
            mostrarDatosEnListView()


            binding.insertar.setOnClickListener {
                var subdepartamento = Subdepartamento(requireContext()) //ALUMNO ES CLASE CONTROLADOR = ADMON DATOS

                subdepartamento.idSubdepto = binding.idSubdepto.text.toString()//insertamos
                subdepartamento.idEdificio= binding.idEdificio.text.toString()
                subdepartamento.piso = binding.piso.text.toString()
                subdepartamento.idArea = binding.idArea.text.toString()//insertamos


                val resultado =
                    subdepartamento.insertar() //INVOCA INSERTAR, LA INSECION ES ABSTRACTA, VE EL METODO QUE SE INVOCA PERO NO SABE COMO FUNCIONA
                if (resultado) {
                    Toast.makeText(requireContext(), "SE INSERTO CON EXITO", Toast.LENGTH_SHORT)
                        .show()
                    // AlertDialog.Builder(requireContext()).setTitle("a").setMessage("${empresa.idArea.toString()}")

                    mostrarDatosEnListView()

                    binding.idSubdepto.setText("")
                    binding.idEdificio.setText("")
                    binding.piso.setText("")
                    binding.idArea.setText("")

                } else {
                    AlertDialog.Builder(requireContext())
                        .setTitle("Atencion")
                        .setMessage("Error al insertar")
                        .show()
                }
                //LA CLASE MAIN ACTIVITY ES VISTA, INTERFAZ GRAFICA
            }
            return view
        }

        private fun mostrarDatosEnListView(){
            var listaSubdepartamento= Subdepartamento(requireContext()).mostrarTodos()
            var idSubdepto=ArrayList<String>() //QUIEROO GUARDAR SOLO EL NOMBRE
            (0..listaSubdepartamento.size - 1).forEach {
                val al = listaSubdepartamento.get(it)
                listaIDs.add(al.idSubdepto.toString())
                idSubdepto.add(al.idSubdepto.toString()) //DIGO QUE SOLO QUIERO NOMBRE
            }
            binding.lista.adapter = ArrayAdapter(requireContext(),
                android.R.layout.simple_list_item_1, idSubdepto)
            //  listaEmpresas)

            binding.lista.setOnItemClickListener { adapterView, view, indice, l ->

                val idSubdeptoLista = listaIDs.get(indice)
                val subdepartamento = Subdepartamento(requireContext()).mostrarSubdepartamento(idSubdeptoLista)
                AlertDialog.Builder(requireContext())
                    .setTitle("ATENCION")
                    .setMessage("Que deseas hacer con ${subdepartamento.idSubdepto},\n del edificio: ${subdepartamento.idEdificio}?")
                    .setNegativeButton("Eliminar"){d,i,->
                        subdepartamento.eliminar()
                        mostrarDatosEnListView()
                    }
                    .setPositiveButton("Actualizar"){d,i->
                        var otraVentana = Intent(getActivity(), MainActivity3::class.java)
                        otraVentana.putExtra("idSubdepto",subdepartamento.idSubdepto)
                        getActivity()?.startActivity(otraVentana)
                        // startActivity(otraVentana)
                    }
                    .setNeutralButton("Cerrar"){d,i->}
                    .show()
            }
        }




    }