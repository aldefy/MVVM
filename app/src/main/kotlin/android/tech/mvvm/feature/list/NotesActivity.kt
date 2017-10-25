package android.tech.mvvm.feature.list

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.tech.mvvm.R
import android.tech.mvvm.feature.details.NotesDetailActivity
import android.tech.mvvm.feature.list.bindings.NotesRVAdapter
import android.tech.mvvm.helpers.RVItemClickListener
import android.tech.mvvm.helpers.ViewModelFactory
import android.tech.mvvm.helpers.getDrawableCompat
import android.view.View
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_notes.*
import javax.inject.Inject

class NotesActivity : AppCompatActivity(), RVItemClickListener {


    @Inject lateinit var viewModelFactory: ViewModelFactory
    private lateinit var notesAdapter: NotesRVAdapter
    private val viewModel by lazy { ViewModelProviders.of(this@NotesActivity, viewModelFactory).get(NotesViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)
        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.title = "Notes"
            it.setHomeAsUpIndicator(getDrawableCompat(R.drawable.ic_launcher_foreground))
            it.setHomeButtonEnabled(true)
            it.setDisplayHomeAsUpEnabled(false)
        }
        init()
    }

    private fun init() {
        fab.setOnClickListener { viewModel.addNote() }
        initRecyclerView()
        viewModel.notesList.observe(
                this, Observer { notes ->
            run {
                if (notes != null) {
                    notesAdapter.updateNoteList(notes)
                }
            }
        }
        )
    }

    private fun initRecyclerView() {
        rvNotes.setHasFixedSize(true)

        val recyclerViewLayoutManager = LinearLayoutManager(this)
        rvNotes.layoutManager = recyclerViewLayoutManager

        notesAdapter = NotesRVAdapter(ArrayList(), this@NotesActivity)
        rvNotes.adapter = notesAdapter
    }

    override fun onItemClick(v: View, position: Int) {
        startActivity(NotesDetailActivity.newIntent(this@NotesActivity, viewModel.notesList.value!![position].noteId))
    }

}
