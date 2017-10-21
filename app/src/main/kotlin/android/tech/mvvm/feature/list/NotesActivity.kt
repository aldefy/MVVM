package android.tech.mvvm.feature.list

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.tech.mvvm.R
import android.tech.mvvm.domain.SyncNoteLifecycleObserver
import android.tech.mvvm.feature.list.bindings.NotesRVAdapter
import android.tech.mvvm.helpers.getDrawableCompat
import android.tech.mvvm.presentation.NotesViewModel
import android.tech.mvvm.presentation.NotesViewModelFactory
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_notes.*
import java.util.Date
import javax.inject.Inject
import kotlin.collections.ArrayList

class NotesActivity : AppCompatActivity(), LifecycleOwner {
    @Inject
    lateinit var viewModelFactory: NotesViewModelFactory

    @Inject
    lateinit var syncNoteLifecycleObserver: SyncNoteLifecycleObserver
    private lateinit var viewModel: NotesViewModel
    private var registry = LifecycleRegistry(this)
    private lateinit var notesAdapter: NotesRVAdapter

    override fun getLifecycle(): Lifecycle {
        return registry
    }

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
        fab.setOnClickListener { viewModel.addNote("Test note" + Date().toString(), Date().toString()) }
        initRecyclerView()
        lifecycle.addObserver(syncNoteLifecycleObserver)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(NotesViewModel::class.java)
        viewModel.notes().observe(
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

        notesAdapter = NotesRVAdapter(ArrayList())
        rvNotes.adapter = notesAdapter
    }
}
