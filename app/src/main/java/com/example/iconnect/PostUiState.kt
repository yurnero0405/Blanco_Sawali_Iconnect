package com.example.iconnect

data class PostUiState(
    val isLoading: Boolean = false,
    val post: Post? = null,
    val error: String? = null
)