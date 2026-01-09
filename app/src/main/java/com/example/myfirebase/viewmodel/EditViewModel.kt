package com.example.myfirebase.viewmodel

sealed interface StatusUIEdit {
    object Loading : StatusUIEdit
    object Success : StatusUIEdit
    object Error : StatusUIEdit
}

class EditViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositorySiswa: RepositorySiswa
) : ViewModel() {

    private val idSiswa: Long =
        savedStateHandle.get<String>(DestinasiEdit.itemIdArg)?.toLong()
            ?: error("idSiswa tidak ditemukan")

    var uiStateSiswa by mutableStateOf(UIStateSiswa())
        private set

    var statusUIEdit: StatusUIEdit by mutableStateOf(StatusUIEdit.Loading)
        private set

    init {
        getSiswa()
    }
}

