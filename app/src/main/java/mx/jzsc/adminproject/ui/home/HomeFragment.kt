package mx.jzsc.adminproject.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import mx.jzsc.adminproject.AdaptadorEmpresas
import mx.jzsc.adminproject.Empresa
import mx.jzsc.adminproject.RegistrarEmpresa
import mx.jzsc.adminproject.RenglonListener
import mx.jzsc.adminproject.databinding.ActivityMainBinding
import mx.jzsc.adminproject.databinding.FragmentHomeBinding

class HomeFragment : Fragment(), RenglonListener {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null


    private val adapatadorEmpresas = AdaptadorEmpresas(arrayListOf())

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        configurarLista()
        configurarEventos()
        registrarObservadores()

    }

    private fun configurarLista() {
        binding.rvEmpresas.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapatadorEmpresas
        }

        adapatadorEmpresas.listener= this
    }

    private fun registrarObservadores() {
        homeViewModel.arrEmpresas.observe(viewLifecycleOwner){lista ->
            adapatadorEmpresas.actualizarEmpresas(lista)
            binding.pgempresas.visibility = View.GONE
        }
    }

    private fun configurarEventos() {
        homeViewModel.getData()
        binding.btnAgregarEmpresa.setOnClickListener {
            val intentRegistrarEmpresa = Intent(context, RegistrarEmpresa::class.java)
            startActivity(intentRegistrarEmpresa)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun clickRenglon(position: Int) {
        val empresaSeleccionada = adapatadorEmpresas.arrEmpresas[position]
        val intentSucces = Intent(context, Empresa::class.java)
        intentSucces.putExtra("Nombre" , empresaSeleccionada.Nombre)
        intentSucces.putExtra("reqs" , empresaSeleccionada.reqs)
        intentSucces.putExtra("salario" , empresaSeleccionada.salario)
        startActivity(intentSucces)
    }
}