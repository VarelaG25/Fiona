package com.refuge.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.refuge.data.session.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class SessionViewModel @Inject constructor(
    private val sessionManager: SessionManager
) : ViewModel() {

    val isLogged: Flow<Boolean> = sessionManager.isLogged()
}