package mx.jzsc.adminproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QueryDocumentSnapshot

class AdaptadorEmpresas(var arrEmpresas: ArrayList<EmpresaData>) :
    RecyclerView.Adapter<AdaptadorEmpresas.EmpresaHolder>()

{
    //listener es un evento que escuche eventos del adaptador
    var listener: RenglonListener? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmpresaHolder {
        val vistaRenglon = LayoutInflater.from(parent.context)
            .inflate(R.layout.empresa_renlgon, parent, false)

        return EmpresaHolder(vistaRenglon) //una caja con un renglón
    }

    override fun onBindViewHolder(holder: EmpresaHolder, position: Int) {

        holder.set(arrEmpresas[position])
        //listener
        val renglon = holder.itemView.findViewById<LinearLayout>(R.id.layoutREmpresa) //linear layout
        val imagen = holder.itemView.findViewById<ImageView>(R.id.ivEmpresa)
        renglon.setOnClickListener {
            println("Click sobre: ${arrEmpresas[position]}")
            listener?.clickRenglon(position)  //solo si listener no es null --> ?
        }
        imagen.setOnClickListener {
            println("Click sobre: ${arrEmpresas[position]}")
            listener?.clickRenglon(position)  //solo si listener no es null --> ?
        }
    }

    override fun getItemCount(): Int {
        return arrEmpresas.size
    }

    fun actualizarEmpresas(lista: List<EmpresaData>?) {  //parámetro? es NULLABLE

        arrEmpresas.clear()              //Manejo de memoria, limpiar la memoria que no se usa
        if (lista != null){
            arrEmpresas.addAll(lista)
        }
        notifyDataSetChanged()      //avisar al adaptador que RECARGUE los datos

    }

    class EmpresaHolder (view: View): RecyclerView.ViewHolder(view){

        //Nombre empresa
        private val tvNombreEmpresa = view.findViewById<TextView>(R.id.etNombreEmpresa)  //Todos los ID son "R.id"


        //con esta función se vana poblar los datos de cada una de las cajas(renglones)del recicler view
        fun set(empresa: EmpresaData){
            val nombre = empresa.Nombre
            println(nombre)
            println("Nombre    $nombre")
            tvNombreEmpresa.text = nombre


        }

    }


}
