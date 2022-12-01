package com.softylines.workshop.feature_pictures.UI

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softylines.workshop.core.Resource
import com.softylines.workshop.feature_pictures.domain.repository.PictureRepository
import com.softylines.workshop.feature_pictures.domain.use_case.GetPicturesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PictureListViewModel @Inject constructor(
    private val repository: PictureRepository,
    private val getPicturesUseCase: GetPicturesUseCase,
) : ViewModel() {
    private val _state = mutableStateOf(PictureListState())
    val state: State<PictureListState> = _state

    init {
        getPictures()
    }

    fun onEvent(event: PictureListEvent) {
        when (event) {
            is PictureListEvent.OnDeletePicture -> {
                viewModelScope.launch {
                    repository.deletePicture(event.picture)
                }
            }

            is PictureListEvent.OnAddPicture -> {
                viewModelScope.launch {
                    repository.addPicture(event.picture)
                }
            }
        }
    }

    private fun getPictures() {
        viewModelScope.launch {
            getPicturesUseCase.invoke(true).collect { result ->
                when (result) {

                    is Resource.Success -> {
                        Log.e("ResourceUnsplash", "Success")
                        _state.value = PictureListState(
                            pictures = result.data ?: emptyList(),
                        )
                    }

                    is Resource.Error -> {
                        Log.e("ResourceUnsplash", result.message ?: "An unexpected error occur")
                        _state.value = PictureListState(
                            error = result.message ?: "An unexpected error occur ",
                        )
                    }

                    is Resource.Loading -> {
                        Log.e("ResourceUnsplash", "Loading")
                        _state.value = PictureListState(
                            isLoading = true,
                        )
                    }
                }
            }
        }
    }
}