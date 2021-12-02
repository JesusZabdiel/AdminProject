package mx.jzsc.adminproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdaptadorProgramas(var arrProgramas: ArrayList<ProgramaData>) :
RecyclerView.Adapter<AdaptadorProgramas.ProgramaHolder>()


{

    var listener: RenglonListener? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProgramaHolder {
        val vistaRenglon = LayoutInflater.from(parent.context)
            .inflate(R.layout.programa_renglon, parent, false)

        return ProgramaHolder(vistaRenglon) //una caja con un renglón

    }

    override fun onBindViewHolder(holder: ProgramaHolder, position: Int) {
        holder.set(arrProgramas[position])

        //listener
        val renglon = holder.itemView.findViewById<LinearLayout>(R.id.layoutPrograma) //linear layout
        val imagen = holder.itemView.findViewById<ImageView>(R.id.imPrograma)
        renglon.setOnClickListener {
            println("Click sobre: ${arrProgramas[position]}")
            listener?.clickRenglon(position)  //solo si listener no es null --> ?
        }
        imagen.setOnClickListener {
            println("Click sobre: ${arrProgramas[position]}")
            listener?.clickRenglon(position)  //solo si listener no es null --> ?
        }
    }

    override fun getItemCount(): Int {
        return arrProgramas.size
    }

    fun actualizarProgramas(lista: List<ProgramaData>?) {  //parámetro? es NULLABLE

        arrProgramas.clear()              //Manejo de memoria, limpiar la memoria que no se usa
        if (lista != null){
            arrProgramas.addAll(lista)
        }
        notifyDataSetChanged()      //avisar al adaptador que RECARGUE los datos

    }


    class ProgramaHolder(view: View): RecyclerView.ViewHolder(view) {
        //Nombre pais
        private val tvNombrePrograma = view.findViewById<TextView>(R.id.tvNombreP)  //Todos los ID son "R.id"

        //con esta función se vana poblar los datos de cada una de las cajas(renglones)del recicler view
        fun set(programa: ProgramaData){
            tvNombrePrograma.text = programa.Nombre

        }
    }

}