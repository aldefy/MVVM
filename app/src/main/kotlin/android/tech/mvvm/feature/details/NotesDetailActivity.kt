package android.tech.mvvm.feature.details

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.tech.mvvm.R.id.note
import android.tech.mvvm.helpers.ViewModelFactory
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_detail.*
import javax.inject.Inject

class NotesDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: NotesDetailsViewModel
    @Inject lateinit var viewModelFactory: ViewModelFactory


    companion object {
        fun newIntent(context: Context, noteId: Long): Intent {
            return Intent(context, NotesDetailActivity::class.java).putExtra("noteId", note)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(NotesDetailsViewModel::class.java)
        viewModel.selectedNote.observe(
                this, Observer { note ->
            run {
                if (note != null) {
                    tvNoteDesc.text = note.noteDesc
                    tvNoteId.text = note.noteId.toString()
                    tvNoteTitle.text = note.noteTitle
                }
            }
        })
    }
}
