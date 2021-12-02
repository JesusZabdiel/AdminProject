package mx.jzsc.adminproject.ui.home

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import mx.jzsc.adminproject.EmpresaData

class HomeViewModel : ViewModel() {

    val db = Firebase.firestore
    val arrEmpresas = MutableLiveData<List<EmpresaData>>()
    val array = arrayListOf<EmpresaData>()

    fun getData(){
        db.collection("empresas")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val nombre = document["Nombre"].toString()
                    val reqs = document["reqs"].toString()
                    val salario = document["salario"].toString()
                    val empresa = EmpresaData(nombre, reqs, salario)
                    array.add(empresa)
                    println("Nombre " + nombre)
                    Log.d(TAG, "${document.id} => ${document.data}")
                }
                arrEmpresas.value = array
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }
    }

}