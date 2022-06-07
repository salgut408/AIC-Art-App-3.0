package com.example.android.aicartapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.aicartapp.repository.ArtRepository
import com.example.android.aicartapp.util.Constants.Companion.FIELD_TERMS
import com.example.android.aicartapp.util.Resource
import com.example.example.ArtResponse
import com.example.example.ArtworkObject
import kotlinx.coroutines.launch
import retrofit2.Response

class MainArtViewModel(
    val artRepository: ArtRepository
) : ViewModel() {

    val breakingArt: MutableLiveData<Resource<ArtResponse>> = MutableLiveData()
    var breakingArtPage = 1

    val searchArt: MutableLiveData<Resource<ArtResponse>> = MutableLiveData()
    var searchArtPage = 1

    init {
        getBreakingArt()
    }

    fun getBreakingArt() = viewModelScope.launch {
        breakingArt.postValue(Resource.Loading())
        val response = artRepository.getBreakingArt(FIELD_TERMS, breakingArtPage)
        breakingArt.postValue(handleBreakingArtResponse(response))

    }

    fun searchArt(searchQuery: String) = viewModelScope.launch {
        searchArt.postValue(Resource.Loading())
        val response = artRepository.searchNews(FIELD_TERMS, searchQuery, searchArtPage)
        searchArt.postValue(handleSearchNewsResponse(response))
    }

    private fun handleBreakingArtResponse(response: Response<ArtResponse>): Resource<ArtResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }


    private fun handleSearchNewsResponse(response: Response<ArtResponse>): Resource<ArtResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    fun saveArtwork(artwork: ArtworkObject) = viewModelScope.launch {
        artRepository.upsert(artwork)
    }

    fun getSavedArt() = artRepository.getSavedArt()

    fun deleteArt(artwork: ArtworkObject) = viewModelScope.launch {
        artRepository.deleteArtwork(artwork)
    }


}
































