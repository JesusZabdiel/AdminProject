package mx.jzsc.adminproject.ui.notifications

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import mx.jzsc.adminproject.EmpresaData
import mx.jzsc.adminproject.PersonaData

class NotificationsViewModel : ViewModel() {

    val db = Firebase.firestore
    val arrEmpresas = MutableLiveData<List<PersonaData>>()
    val array = arrayListOf<PersonaData>()

    fun getData(){
        db.collection("personas")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val curp = document["Curp"].toString()
                    val curso = document["Curso"].toString()
                    val nombre = document["Nombre"].toString()
                    val p1 = document["p1"].toString()
                    val p2 = document["p2"].toString()
                    val p3 = document["p3"].toString()
                    val p4 = document["p4"].toString()
                    val p5 = document["p5"].toString()

                    val persona = PersonaData(curp,curso,nombre, p1, p2, p3, p4,p5)
                    array.add(persona)
                    println("Nombre " + nombre)
                    Log.d(ContentValues.TAG, "${document.id} => ${document.data}")
                }
                arrEmpresas.value = array
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents.", exception)
            }
    }
}