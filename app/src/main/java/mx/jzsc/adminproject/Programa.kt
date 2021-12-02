package mx.jzsc.adminproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mx.jzsc.adminproject.databinding.ActivityEmpresaBinding
import mx.jzsc.adminproject.databinding.ActivityProgramaBinding

class Programa : AppCompatActivity() {

    lateinit var binding: ActivityProgramaBinding
    lateinit var objetoIntent : Intent
    lateinit var nombre: String
    lateinit var elementos: String
    lateinit var objetivo: String
    lateinit var planeacion: String
    lateinit var prerer: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        objetoIntent = intent
        binding = ActivityProgramaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configurarEventos()

    }

    private fun configurarEventos() {
        nombre = objetoIntent.getStringExtra("Nombre").toString()
        elementos = objetoIntent.getStringExtra("Elementos").toString()
        objetivo = objetoIntent.getStringExtra("Objetivo").toString()
        prerer = objetoIntent.getStringExtra("Prerequisitos").toString()
        planeacion = objetoIntent.getStringExtra("Planeacion").toString()

        binding.tvNombrePrograma.text = nombre
        binding.multiLineProgE.setText(elementos)
        binding.multiLineObjetivo.setText(objetivo)
        binding.multiLineObjetivo3.setText(planeacion)
        binding.multilinePrer.setText(prerer)
    }
}