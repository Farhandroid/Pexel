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
import com.farhan.tanvir.pexels.data.util.Resource
import com.farhan.tanvir.pexels.data.model.APIResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ImageSearchViewModel() :
    ViewModel() {
    val searchedImageData: MutableLiveData<Resource<APIResponse>> = MutableLiveData()
    private val imageRepository= ImageRepository()

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