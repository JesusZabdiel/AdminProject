package mx.jzsc.adminproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mx.jzsc.adminproject.databinding.ActivityEmpresaBinding
import mx.jzsc.adminproject.databinding.FragmentHomeBinding

class Empresa : AppCompatActivity() {


    lateinit var binding: ActivityEmpresaBinding
    lateinit var objetoIntent : Intent
    lateinit var nombre: String
    lateinit var reqs: String
    lateinit var salario: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        objetoIntent = intent

        binding = ActivityEmpresaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configurarEventos()

    }

    private fun configurarEventos() {
        nombre = objetoIntent.getStringExtra("Nombre").toString()
        reqs = objetoIntent.getStringExtra("reqs").toString()
        salario = objetoIntent.getStringExtra("salario").toString()
        binding.tvEmpresaSe.text =nombre
        binding.multiTextReqsEmpresa.setText(reqs)
        binding.tvSalario.text = salario
    }
}