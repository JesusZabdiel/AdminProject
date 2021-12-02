package mx.jzsc.adminproject

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import mx.jzsc.adminproject.databinding.ActivityRegistrarEmpresaBinding
import androidx.annotation.NonNull

import com.google.android.gms.tasks.OnFailureListener

import com.google.firebase.firestore.DocumentReference

import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class RegistrarEmpresa : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrarEmpresaBinding
    private lateinit var db :FirebaseFirestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegistrarEmpresaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = Firebase.firestore

        configurarEventos()

    }

    private fun configurarEventos() {
        binding.btnRegistrar.setOnClickListener{
            crearUnaNuevaEmpresa()
            val intentCrearPrograma = Intent(this, CrearPrograma::class.java )
            startActivity(intentCrearPrograma)
        }
    }

    private fun crearUnaNuevaEmpresa() {
        // Create a new user with a first and last name
        val empresa = hashMapOf(
            "Nombre" to binding.itNombreEmpresa.text.toString(),
            "reqs" to binding.etReqs.text.toString(),
            "salario" to binding.tiSalario.text.toString()
        )

        // Add a new document with a generated ID
        db.collection("empresas")
            .add(empresa)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }
}