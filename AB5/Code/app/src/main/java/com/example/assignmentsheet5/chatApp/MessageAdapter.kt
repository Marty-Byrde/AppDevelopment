package com.example.assignmentsheet5.chatApp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.assignmentsheet5.R
import java.text.SimpleDateFormat


class MessageAdapter (private val messages: Array<Message>) : RecyclerView.Adapter<MessageAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View
        if (viewType == 0) {
            // Incoming message
            view = LayoutInflater.from(parent.context).inflate(R.layout.message_incomming, parent, false)
        } else {
            // Outgoing message
            view = LayoutInflater.from(parent.context).inflate(R.layout.message_outgoing, parent, false)
        }
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val message: Message = messages[position]
        holder.txtAuthor.text = message.author
        holder.txtMessage.text = message.text
        holder.txtDate.text = SimpleDateFormat("HH:mm").format(message.date)
    }

    override fun getItemCount(): Int {
        return messages.size
    }

    override fun getItemViewType(position: Int): Int {
        val message: Message = messages[position]
        return if (message.type === MessageType.IN) 0 else 1
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var txtAuthor: TextView
        var txtMessage: TextView
        var txtDate: TextView

        init {
            txtAuthor = view.findViewById<View>(R.id.txtAuthor) as TextView
            txtMessage = view.findViewById<View>(R.id.txtMessage) as TextView
            txtDate = view.findViewById<View>(R.id.txtDate) as TextView
        }
    }

}