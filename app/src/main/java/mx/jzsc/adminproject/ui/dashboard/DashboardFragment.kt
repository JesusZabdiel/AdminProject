package mx.jzsc.adminproject.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import mx.jzsc.adminproject.*
import mx.jzsc.adminproject.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() , RenglonListener{

    private lateinit var dashboardViewModel: DashboardViewModel
    private var _binding: FragmentDashboardBinding? = null

    private val adaptadorProgramas = AdaptadorProgramas(arrayListOf())

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

         return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dashboardViewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)

        configurarLista()
        configurarEventos()
        registrarObservadores()

    }
    private fun configurarLista() {
        binding.rvProgramas.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adaptadorProgramas
        }

        adaptadorProgramas.listener= this
    }

    private fun registrarObservadores() {
        dashboardViewModel.arrProgramas.observe(viewLifecycleOwner){lista ->
            adaptadorProgramas.actualizarProgramas(lista)
            binding.pgProgramas.visibility = View.GONE
        }

    }

    private fun configurarEventos() {
        dashboardViewModel.getData()
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun clickRenglon(position: Int) {
        val programaSeleccionado = adaptadorProgramas.arrProgramas[position]
        val intentSucces = Intent(context, Programa::class.java)
        intentSucces.putExtra("Elementos" , programaSeleccionado.elementos)
        intentSucces.putExtra("Nombre" , programaSeleccionado.Nombre)
        intentSucces.putExtra("Objetivo" , programaSeleccionado.Objetivo)
        intentSucces.putExtra("Planeacion" , programaSeleccionado.Planeacion)
        intentSucces.putExtra("Prerequisitos" , programaSeleccionado.Prerequisitos)
        startActivity(intentSucces)
    }
}