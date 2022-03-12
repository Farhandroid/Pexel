/*
 * Created by Farhan Tanvir on 12/5/21, 5:08 PM
 * Last modified 12/5/21, 5:06 PM
 * Contact : farhantanvir65@gmail.com
 */

package com.farhan.tanvir.pexels.data

import com.farhan.tanvir.pexels.data.model.APIResponse
import com.farhan.tanvir.pexels.data.util.Resource
import retrofit2.Response
import javax.inject.Inject

class ImageRepository @Inject constructor(private val imageApiService: ImageApiService) {
    suspend fun getSearchedImage(
        searchQuery: String,
        page: Int,
    ): Resource<APIResponse> {
        return responseToResource(imageApiService.getSearchedImage(searchQuery, page))
    }

    private fun responseToResource(response: Response<APIResponse>): Resource<APIResponse> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }
}