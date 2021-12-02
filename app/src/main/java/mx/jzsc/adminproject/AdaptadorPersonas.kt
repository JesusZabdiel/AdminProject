package mx.jzsc.adminproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdaptadorPersonas(var arrPersonas: ArrayList<PersonaData>) :
RecyclerView.Adapter<AdaptadorPersonas.PersonaHolder>()

{

    var listener: RenglonListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonaHolder {
        val vistaRenglon = LayoutInflater.from(parent.context)
            .inflate(R.layout.persona_renglon, parent, false)

        return AdaptadorPersonas.PersonaHolder(vistaRenglon) //una caja con un renglón
    }

    override fun onBindViewHolder(holder: PersonaHolder, position: Int) {
        holder.set(arrPersonas[position])
        //listener
        val renglon = holder.itemView.findViewById<LinearLayout>(R.id.layoutPersona) //linear layout
        val imagen = holder.itemView.findViewById<ImageView>(R.id.ivPersona)
        renglon.setOnClickListener {
            println("Click sobre: ${arrPersonas[position]}")
            listener?.clickRenglon(position)  //solo si listener no es null --> ?
        }
        imagen.setOnClickListener {
            println("Click sobre: ${arrPersonas[position]}")
            listener?.clickRenglon(position)  //solo si listener no es null --> ?
        }
    }

    override fun getItemCount(): Int {
        return arrPersonas.size
    }

    fun actualizarPersonas(lista: List<PersonaData>?) {  //parámetro? es NULLABLE

        arrPersonas.clear()              //Manejo de memoria, limpiar la memoria que no se usa
        if (lista != null){
            arrPersonas.addAll(lista)
        }
        notifyDataSetChanged()      //avisar al adaptador que RECARGUE los datos

    }
    class PersonaHolder (view: View): RecyclerView.ViewHolder(view){

        //Nombre empresa
        private val tvNombrePersona = view.findViewById<TextView>(R.id.tvpersona)  //Todos los ID son "R.id"


        //con esta función se vana poblar los datos de cada una de las cajas(renglones)del recicler view
        fun set(persona: PersonaData){
            val nombre = persona.Nombre
            println(nombre)
            println("Nombre    $nombre")
            tvNombrePersona.text = nombre

        }
    }

}