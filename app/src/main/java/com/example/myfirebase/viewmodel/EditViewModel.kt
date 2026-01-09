package com.example.myfirebase.viewmodel

sealed interface StatusUIEdit {
    object Loading : StatusUIEdit
    object Success : StatusUIEdit
    object Error : StatusUIEdit
}
