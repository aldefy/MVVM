package android.tech.mvvm.feature.list.bindings

import android.support.v7.widget.RecyclerView
import android.tech.mvvm.R
import android.tech.mvvm.data.db.entities.Note
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import timber.log.Timber

class NotesRVAdapter(private val notes: MutableList<Note>?) : RecyclerView.Adapter<NotesRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val commentText = LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_rv_item_note, parent, false) as TextView
        return ViewHolder(commentText)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = notes!![position]
        holder.noteTextView.text = note.noteTitle
    }

    override fun getItemCount(): Int {
        return notes?.size ?: 0
    }

    fun updateNoteList(newNotes: List<Note>) {
        Timber.d("Got new notes " + newNotes.size)
        this.notes!!.clear()
        this.notes.addAll(newNotes)
        notifyDataSetChanged()
    }

    /**
     * View holder for shopping list items of this adapter
     */
    class ViewHolder(var noteTextView: TextView) : RecyclerView.ViewHolder(noteTextView)
}
