package com.example.iconnect

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PostViewModel : ViewModel() {

    private val _uiState =
        MutableStateFlow(PostUiState())

    val uiState: StateFlow<PostUiState> =
        _uiState.asStateFlow()

    init {
        getPost(1)
    }

    fun getPost(postId: Int) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true,
                    error = null
                )
            }

            try {
                val result =
                    RetrofitInstance.api.getPost(postId)

                _uiState.update {
                    it.copy(
                        isLoading = false,
                        post = result,
                        error = null
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        post = null,
                        error = "Unable to retrieve data. Please check your connection or try another Post ID."
                    )
                }
            }
        }
    }
}