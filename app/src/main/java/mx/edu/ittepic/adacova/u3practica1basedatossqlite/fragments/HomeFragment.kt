package mx.edu.ittepic.adacova.u3practica1basedatossqlite.fragments

import android.R
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter

import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import mx.edu.ittepic.adacova.u3practica1basedatossqlite.MainActivity2
import mx.edu.ittepic.adacova.u3practica1basedatossqlite.Mapeo_Empresas
import mx.edu.ittepic.adacova.u3practica1basedatossqlite.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
     var _binding: FragmentHomeBinding? = null
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
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        val view = binding.root
        mostrarDatosEnListView()


            binding.insertar.setOnClickListener {
                var empresa = Mapeo_Empresas(requireContext()) //ALUMNO ES CLASE CONTROLADOR = ADMON DATOS

                empresa.idArea = binding.idArea.text.toString()//insertamos
                empresa.descripcion = binding.descripcion.text.toString()
                empresa.division = binding.division.text.toString()
                empresa.cantidad_empleados = binding.cantidadempleados.text.toString()


                val resultado =
                    empresa.insertar() //INVOCA INSERTAR, LA INSECION ES ABSTRACTA, VE EL METODO QUE SE INVOCA PERO NO SABE COMO FUNCIONA
                if (resultado) {
                    Toast.makeText(requireContext(), "SE INSERTO CON EXITO", Toast.LENGTH_SHORT)
                        .show()
                  // AlertDialog.Builder(requireContext()).setTitle("a").setMessage("${empresa.idArea.toString()}")

                    mostrarDatosEnListView()

                    binding.idArea.setText("")
                    binding.descripcion.setText("")
                    binding.division.setText("")
                    binding.cantidadempleados.setText("")

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
        var listaEmpresas=Mapeo_Empresas(requireContext()).mostrarTodos()
        var idArea=ArrayList<String>() //QUIEROO GUARDAR SOLO EL NOMBRE
        (0..listaEmpresas.size - 1).forEach {
            val al = listaEmpresas.get(it)
            listaIDs.add(al.idArea.toString())
            idArea.add(al.idArea.toString()) //DIGO QUE SOLO QUIERO NOMBRE
        }
        binding.lista.adapter = ArrayAdapter<String>(requireContext(),
            R.layout.simple_list_item_1, idArea)
            //  listaEmpresas)

            binding.lista.setOnItemClickListener { adapterView, view, indice, l ->

                val idAreaLista = listaIDs.get(indice)
                val empresa = Mapeo_Empresas(requireContext()).mostrarEmpresa(idAreaLista)
                AlertDialog.Builder(requireContext())
                    .setTitle("ATENCION")
                    .setMessage("Que deseas hacer con ${empresa.idArea},\n de division: ${empresa.division}?")
                    .setNegativeButton("Eliminar"){d,i,->
                        empresa.eliminar()
                        mostrarDatosEnListView()
                    }
                    .setPositiveButton("Actualizar"){d,i->
                        var otraVentana = Intent(getActivity(), MainActivity2::class.java)
                        otraVentana.putExtra("idArea",empresa.idArea)
                        getActivity()?.startActivity(otraVentana)
                        // startActivity(otraVentana)
                    }
                    .setNeutralButton("Cerrar"){d,i->}
                    .show()
            }
    }




}