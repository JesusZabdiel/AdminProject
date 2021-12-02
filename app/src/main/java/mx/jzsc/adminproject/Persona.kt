package mx.jzsc.adminproject

import android.content.Intent
import android.graphics.drawable.Icon
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mx.jzsc.adminproject.databinding.ActivityPersonaBinding


class Persona : AppCompatActivity() {

    private lateinit var binding: ActivityPersonaBinding
    lateinit var objetoIntent : Intent
    lateinit var curp: String
    lateinit var curso: String
    lateinit var nombre: String
    lateinit var p1: String
    lateinit var p2: String
    lateinit var p3: String
    lateinit var p4: String
    lateinit var p5: String




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        objetoIntent = intent

        binding = ActivityPersonaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configurarEventos()

    }

    private fun configurarEventos() {
        nombre = objetoIntent.getStringExtra("Nombre").toString()
        curp = objetoIntent.getStringExtra("Curp").toString()
        curso = objetoIntent.getStringExtra("Curso").toString()

        p1 = objetoIntent.getStringExtra("p1").toString()
        p2 = objetoIntent.getStringExtra("p2").toString()
        p3 = objetoIntent.getStringExtra("p3").toString()
        p4 = objetoIntent.getStringExtra("p4").toString()
        p5 = objetoIntent.getStringExtra("p5").toString()

        binding.tvNOmbret.text = nombre
        binding.tvCurp.text = curp
        binding.tvCurso.text = curso

        if (p1.equals("false")) {
            binding.im1.setImageResource(R.drawable.tache)
        }else {
            binding.im1.setImageResource(R.drawable.yes)

        }

        if (p2.equals("false")) {
            binding.im2.setImageResource(R.drawable.tache)
        }else {
            binding.im2.setImageResource(R.drawable.yes)

        }

        if (p3.equals("false")) {
            binding.im3.setImageResource(R.drawable.tache)
        }else {
            binding.im3.setImageResource(R.drawable.yes)

        }

        if (p4.equals("false")) {
            binding.im4.setImageResource(R.drawable.tache)
        }else {
            binding.im4.setImageResource(R.drawable.yes)

        }

        if (p1.equals("false")) {
            binding.im5.setImageResource(R.drawable.tache)
        }else {
            binding.im5.setImageResource(R.drawable.yes)

        }


    }


    }

