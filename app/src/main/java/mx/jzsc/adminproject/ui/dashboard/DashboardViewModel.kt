package mx.jzsc.adminproject.ui.dashboard

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import mx.jzsc.adminproject.EmpresaData
import mx.jzsc.adminproject.ProgramaData

class DashboardViewModel : ViewModel() {

    val db = Firebase.firestore
    val arrProgramas = MutableLiveData<List<ProgramaData>>()
    val array = arrayListOf<ProgramaData>()


    fun getData(){
        db.collection("programas")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val elementos = document["Elementos"].toString()
                    val nombre = document["Nombre"].toString()
                    val objetivo = document["Objetivo"].toString()
                    val plan = document["Planeacion"].toString()
                    val prer = document["Prerequisitos"].toString()
                    val programa = ProgramaData(elementos, nombre, objetivo, plan, prer)
                    array.add(programa)
                    println("Nombre " + nombre)
                    Log.d(ContentValues.TAG, "${document.id} => ${document.data}")
                }
                arrProgramas.value = array
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents.", exception)
            }
    }
}