/*
 * Created by Farhan Tanvir on 12/5/21, 5:08 PM
 * Last modified 12/5/21, 5:06 PM
 * Contact : farhantanvir65@gmail.com
 */


package com.farhan.tanvir.pexels.presentation.image_search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farhan.tanvir.pexels.data.ImageRepository
import com.farhan.tanvir.pexels.data.model.APIResponse
import com.farhan.tanvir.pexels.data.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageSearchViewModel @Inject constructor(private val imageRepository: ImageRepository) :
    ViewModel() {
    val searchedImageData: MutableLiveData<Resource<APIResponse>> = MutableLiveData()

    fun getSearchedImage(searchQuery: String, page: Int) = viewModelScope.launch(Dispatchers.IO) {
        searchedImageData.postValue(Resource.Loading())
        try {
            val apiResult = imageRepository.getSearchedImage(searchQuery, page)
            searchedImageData.postValue(apiResult)
        } catch (e: Exception) {
            searchedImageData.postValue(Resource.Error(e.message.toString()))
        }
    }
}