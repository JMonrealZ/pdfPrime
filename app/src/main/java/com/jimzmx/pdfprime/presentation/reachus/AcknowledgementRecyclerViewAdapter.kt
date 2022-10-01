package com.jimzmx.pdfprime.presentation.reachus

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jimzmx.pdfprime.R
import kotlinx.android.synthetic.main.list_item_acknowledgement.view.*

class AcknowledgementRecyclerViewAdapter(private var acknolds : MutableList<Acknowledgement>,
                                         private var clickListener: (Acknowledgement) -> Unit
) : RecyclerView.Adapter<AcknowledgementViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AcknowledgementViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.list_item_acknowledgement, parent, false)
        return AcknowledgementViewHolder(listItem,
            //{ackSelected : Acknowledgement -> onClickAckListener(ackSelected)})
            clickListener)
    }

    override fun onBindViewHolder(holder: AcknowledgementViewHolder, position: Int) {
        holder.bind(acknolds[position], clickListener)
    }

    override fun getItemCount(): Int {
        return acknolds.size
    }
}

class AcknowledgementViewHolder(
    val view : View,
    private val clickListener : (Acknowledgement)->Unit
) : RecyclerView.ViewHolder(view){
    fun bind( ack : Acknowledgement, clickListener: (Acknowledgement) -> Unit){
        view.apply {
            //Glide.with(this).load(ack.img).into(ivImageAck)
            ivImageAck.setImageResource(ack.img)
            tvNameAck.text = ack.name
            tvDescAck.text = ack.description
            setOnClickListener{ clickListener(ack)}
        }
    }
}