package mx.jzsc.adminproject

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.*
import com.google.firebase.ktx.Firebase
import mx.jzsc.adminproject.databinding.ActivityRegistrarTalentoBinding

class RegistrarTalento : AppCompatActivity() {

    private lateinit var binding:ActivityRegistrarTalentoBinding
    private lateinit var db : FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrarTalentoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = Firebase.firestore
        configurarEventos()
    }

    private fun configurarEventos() {
        binding.btnAlta.setOnClickListener{
            crearNuevoTalento()
            val intentCompleto = Intent(this, MainActivity::class.java)
            startActivity(intentCompleto)
        }
    }

    private fun crearNuevoTalento() {
        val talento = hashMapOf(
            "Nombre" to binding.itNombreC.text.toString(),
            "Curp" to binding.itCurp.text.toString(),
            "Curso" to binding.itNombreCurso.text.toString(),
            "p1" to binding.check1.isChecked.toString(),
            "p2" to binding.check2.isChecked.toString(),
            "p3" to binding.check3.isChecked.toString(),
            "p4" to binding.check4.isChecked.toString(),
            "p5" to binding.check5.isChecked.toString()
        )

        // Add a new document with a generated ID
        db.collection("personas")
            .add(talento)
            .addOnSuccessListener { documentReference ->
                Log.d(ContentValues.TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(ContentValues.TAG, "Error adding document", e)
            }
    }
}