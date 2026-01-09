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

fun getSiswa() {
    viewModelScope.launch {
        statusUIEdit = StatusUIEdit.Loading
        try {
            val siswa = repositorySiswa.getSatuSiswa(idSiswa)
            siswa?.let {
                uiStateSiswa = UIStateSiswa(
                    detailSiswa = DetailSiswa(
                        id = it.id,
                        nama = it.nama,
                        alamat = it.alamat,
                        telpon = it.telpon
                    ),
                    isEntryValid = true
                )
            }
            statusUIEdit = StatusUIEdit.Success
        } catch (e: Exception) {
            statusUIEdit = StatusUIEdit.Error
        }
    }
}

fun updateUiState(detailSiswa: DetailSiswa) {
    uiStateSiswa = UIStateSiswa(
        detailSiswa = detailSiswa,
        isEntryValid = validasiInput(detailSiswa)
    )
}

suspend fun updateSiswa() {
    try {
        repositorySiswa.editSatuSiswa(
            idSiswa,
            uiStateSiswa.detailSiswa.toSiswa()
        )
        statusUIEdit = StatusUIEdit.Success
    } catch (e: Exception) {
        statusUIEdit = StatusUIEdit.Error
    }
}