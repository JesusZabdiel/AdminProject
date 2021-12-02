package mx.jzsc.adminproject

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import mx.jzsc.adminproject.databinding.ActivityCrearProgramaBinding


class CrearPrograma : AppCompatActivity() {

    private lateinit var binding: ActivityCrearProgramaBinding
    private lateinit var db : FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCrearProgramaBinding.inflate(layoutInflater)

        db = Firebase.firestore
        setContentView(binding.root)
        configurarEventos()
    }

    private fun configurarEventos() {
        binding.btnFinalizar.setOnClickListener{
            crearNuevoPrograma()
            val completoIntent = Intent(this, MainActivity::class.java)
            startActivity(completoIntent)
        }
    }

    private fun crearNuevoPrograma() {
        // Create a new user with a first and last name
        val programa = hashMapOf(
            "Nombre" to binding.inNombre.text.toString(),
            "Elementos" to binding.etElementos.text.toString(),
            "Objetivo" to binding.itObjetivo.text.toString(),
            "Planeacion" to binding.etPlaneacion.text.toString(),
            "Prerequisitos" to binding.etPrerequisitos.text.toString()
        )

        // Add a new document with a generated ID
        db.collection("programas")
            .add(programa)
            .addOnSuccessListener { documentReference ->
                Log.d(ContentValues.TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(ContentValues.TAG, "Error adding document", e)
            }
    }

}