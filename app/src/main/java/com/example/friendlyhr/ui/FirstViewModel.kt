package com.example.friendlyhr.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.friendlyhr.data.FriendlyRepositoryImpl
import com.example.friendlyhr.data.model.Position
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface PositionUiState
{
    data class Success(val positions: List<Position>) : PositionUiState
    object Loading : PositionUiState
    data class Error(val error: Throwable) : PositionUiState
}

@HiltViewModel
class FirstViewModel @Inject constructor(private val friendlyRepositoryImpl: FriendlyRepositoryImpl) : ViewModel() {

    private val _positionUiState = MutableStateFlow<PositionUiState>(PositionUiState.Loading)
    val positionUiState: StateFlow<PositionUiState> = _positionUiState.asStateFlow()

    init {
        getAllPosition()
    }

    private fun getAllPosition() {
        viewModelScope.launch {
            friendlyRepositoryImpl.fetchData().onSuccess {response ->
                _positionUiState.value = PositionUiState.Success(response.positions)
            }.onFailure {e ->
                _positionUiState.value = PositionUiState.Error(e)
            }
        }
    }
}