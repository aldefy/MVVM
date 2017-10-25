package android.tech.mvvm.di.module

import android.tech.mvvm.feature.details.NotesDetailActivity
import android.tech.mvvm.feature.list.NotesActivity
import android.tech.mvvm.feature.list.di.module.NotesModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Binds all sub-components within the app.
 */
@Module
abstract class UIBuilder {
    @ContributesAndroidInjector(modules = arrayOf(NotesModule::class))
    abstract fun contributeNotesActivity(): NotesActivity

    @ContributesAndroidInjector()
    abstract fun contributeNoteDetailsActivity(): NotesDetailActivity
}
