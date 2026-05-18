package com.refuge.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.refuge.data.session.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val sessionManager: SessionManager
) : ViewModel() {

    val userId = sessionManager.getUserId()
    val userName = sessionManager.getUserName()
    val userEmail = sessionManager.getUserEmail()

    fun logout(onDone: () -> Unit) {
        viewModelScope.launch {
            sessionManager.logout()
            onDone()
        }
    }
}