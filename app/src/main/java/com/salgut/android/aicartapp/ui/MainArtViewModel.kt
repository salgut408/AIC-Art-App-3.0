package com.salgut.android.aicartapp.ui

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.media.Image
import android.net.ConnectivityManager
import android.net.ConnectivityManager.*
import android.net.NetworkCapabilities.*
import android.os.Build
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.request.RequestOptions
import com.salgut.android.aicartapp.ArtApplication
import com.salgut.android.aicartapp.databinding.ActivityArtBinding
import com.salgut.android.aicartapp.models.ArtResponse
import com.salgut.android.aicartapp.models.ArtworkObject
import com.salgut.android.aicartapp.repository.ArtRepository
import com.salgut.android.aicartapp.util.Constants.Companion.FIELD_TERMS
import com.salgut.android.aicartapp.util.Resource

import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class MainArtViewModel(
    app: Application,
    val artRepository: ArtRepository,

    ) : AndroidViewModel(app) {

    val breakingArt: MutableLiveData<Resource<ArtResponse>> = MutableLiveData()
    var breakingArtPage = 1
    var breakingArtResponse: ArtResponse? = null

    val searchArt: MutableLiveData<Resource<ArtResponse>> = MutableLiveData()
    var searchArtPage = 1
    var searchArtResponse: ArtResponse? = null


    init {
        getBreakingArt()

    }

    fun getBreakingArt() = viewModelScope.launch {
        safeBreakingArtCall()


    }

    fun searchArt(searchQuery: String) = viewModelScope.launch {
        searchArt.postValue(Resource.Loading())
        val response = artRepository.searchArt(FIELD_TERMS, searchQuery, searchArtPage)
        searchArt.postValue(handleSearchArtResponse(response))
    }

    private suspend fun handleBreakingArtResponse(response: Response<ArtResponse>): Resource<ArtResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                breakingArtPage++
                if (breakingArtResponse == null) {
                    breakingArtResponse = resultResponse
                } else {
                    val oldArtworks = breakingArtResponse?.artworkObject
                    val newArtworks = resultResponse.artworkObject
                    oldArtworks?.addAll(newArtworks)
                    artRepository.insertAllCache(newArtworks)

                }
                return Resource.Success(breakingArtResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }


    private fun handleSearchArtResponse(response: Response<ArtResponse>): Resource<ArtResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                searchArtPage++
                if (searchArtResponse == null) {

                    searchArtResponse = resultResponse
                } else {
                    val oldArtworks = searchArtResponse?.artworkObject
                    val newArtworks = resultResponse.artworkObject
                    val index = oldArtworks?.size
                    oldArtworks?.addAll(newArtworks)
                }
                return Resource.Success(searchArtResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleNewSearchArtResponse(response: Response<ArtResponse>): Resource<ArtResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                searchArtPage++
                if (searchArtResponse == null) {

                    searchArtResponse = resultResponse
                } else {
                    val oldArtworks = searchArtResponse?.artworkObject
                    val newArtworks = resultResponse.artworkObject
                    oldArtworks?.clear()
                    oldArtworks?.addAll(newArtworks)
                }
                return Resource.Success(searchArtResponse ?: resultResponse)
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

    fun createPaletteSync(bitmap: Bitmap): Palette = Palette.from(bitmap).generate()

    @RequiresApi(Build.VERSION_CODES.O)
    fun calcDominantColor (drawable: Drawable) {
        viewModelScope.launch {
            val bmp = (drawable as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888, true)

            Palette.from(bmp).generate() {palette->
                palette?.dominantSwatch?.rgb?.toInt()
            }
        }

    }















    private suspend fun safeBreakingArtCall() {
        searchArt.postValue(Resource.Loading())
        try {
            if (hasInternetConnection()) {
                val response = artRepository.getBreakingArt(FIELD_TERMS, breakingArtPage)
                breakingArt.postValue(handleBreakingArtResponse(response))
            } else {
                searchArt.postValue(Resource.Error("No connection"))
            }

        } catch (t: Throwable) {
            when (t) {
                is IOException -> breakingArt.postValue(Resource.Error("Network  error"))
                else -> breakingArt.postValue(Resource.Error("Conversion error"))
            }
        }
    }

    private suspend fun safeSearchArtCall(searchQuery: String) {
        searchArt.postValue(Resource.Loading())
        try {
            if (hasInternetConnection()) {
                val response = artRepository.searchArt(FIELD_TERMS, searchQuery, searchArtPage)
                searchArt.postValue(handleSearchArtResponse(response))
            } else {
                searchArt.postValue(Resource.Error("No connection"))
            }

        } catch (t: Throwable) {
            when (t) {
                is IOException -> searchArt.postValue(Resource.Error("Network  error"))
                else -> searchArt.postValue(Resource.Error("Conversion error"))
            }
        }
    }


    @SuppressLint("MissingPermission")
    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<ArtApplication>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val capabilities =
                connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            return when {
                capabilities.hasTransport(TRANSPORT_WIFI) -> true
                capabilities.hasTransport(TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.activeNetworkInfo?.run {
                return when (type) {
                    TYPE_WIFI -> return true
                    TYPE_MOBILE -> true
                    TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }
        return false
    }


}
































