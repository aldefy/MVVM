package android.tech.mvvm.feature.list.di.module

import android.arch.lifecycle.ViewModel
import android.tech.mvvm.di.scope.ViewModelKey
import android.tech.mvvm.feature.list.NotesActivity
import android.tech.mvvm.feature.list.NotesViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class NotesModule {

    @Binds
    @IntoMap
    @ViewModelKey(NotesViewModel::class)
    abstract fun bindNotesViewModel(viewModel: NotesViewModel): ViewModel

    @ContributesAndroidInjector
    abstract fun contributeNotesActivity(): NotesActivity
}
