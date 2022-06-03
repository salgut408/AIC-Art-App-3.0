package com.example.android.aicartapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.aicartapp.repository.ArtRepository

class MainArtViewModelProviderFactory(
    val artRepository: ArtRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainArtViewModel(artRepository) as T
    }
}