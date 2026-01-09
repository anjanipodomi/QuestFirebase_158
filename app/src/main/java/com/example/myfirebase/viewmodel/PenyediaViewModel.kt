package com.example.myfirebase.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.lifecycle.viewmodel.initializer
import com.example.myfirebase.repositori.AplikasiDataSiswa
import com.example.myfirebase.viewmodel.HomeViewModel
import com.example.myfirebase.viewmodel.EntryViewModel


fun CreationExtras.aplikasiDataSiswa(): AplikasiDataSiswa =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as
            AplikasiDataSiswa)

object PenyediaViewModel {

    val Factory = viewModelFactory {

        initializer {
            HomeViewModel(
                aplikasiDataSiswa().container.repositorySiswa
            )
        }

        initializer {
            EntryViewModel(
                aplikasiDataSiswa().container.repositorySiswa
            )
        }
    }
}
