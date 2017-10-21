package android.tech.mvvm.di.module

import android.tech.mvvm.feature.list.NotesActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Binds all sub-components within the app.
 */
@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = arrayOf(NotesActivityModule::class))
    abstract fun bindNotesActivity(): NotesActivity

}
