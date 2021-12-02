package mx.jzsc.adminproject.ui.notifications

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import mx.jzsc.adminproject.*
import mx.jzsc.adminproject.databinding.FragmentNotificationsBinding

class NotificationsFragment : Fragment(), RenglonListener {

    private lateinit var notificationsViewModel: NotificationsViewModel
    private var _binding: FragmentNotificationsBinding? = null
    private val adaptadorPeronas = AdaptadorPersonas(arrayListOf())

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        notificationsViewModel = ViewModelProvider(this).get(NotificationsViewModel::class.java)

        configurarLista()
        configurarEventos()
        registrarObservadores()

    }

    private fun configurarLista() {
        binding.rvPersonas.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adaptadorPeronas
        }

        adaptadorPeronas.listener= this
    }


    private fun registrarObservadores() {
        notificationsViewModel.arrEmpresas.observe(viewLifecycleOwner){lista ->
            adaptadorPeronas.actualizarPersonas(lista)
            binding.pgpersonas.visibility = View.GONE
        }
    }


    private fun configurarEventos() {
        notificationsViewModel.getData()
        binding.btnAddTalento.setOnClickListener{
            val intentAgregarTalento = Intent(context, RegistrarTalento::class.java)
            startActivity(intentAgregarTalento)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun clickRenglon(position: Int) {
        val personaSeleccionada = adaptadorPeronas.arrPersonas[position]
        val intentSucces = Intent(context, Persona::class.java)
        intentSucces.putExtra("Curp" , personaSeleccionada.Curp)
        intentSucces.putExtra("Nombre" , personaSeleccionada.Nombre)
        intentSucces.putExtra("Curso" , personaSeleccionada.Curso)
        intentSucces.putExtra("p1" , personaSeleccionada.p1)
        intentSucces.putExtra("p2" , personaSeleccionada.p2)
        intentSucces.putExtra("p3" , personaSeleccionada.p3)
        intentSucces.putExtra("p4" , personaSeleccionada.p4)
        intentSucces.putExtra("p5" , personaSeleccionada.p5)
        startActivity(intentSucces)
    }
}