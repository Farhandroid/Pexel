/*
 * Created by Farhan Tanvir on 12/5/21, 5:08 PM
 * Last modified 12/5/21, 5:06 PM
 * Contact : farhantanvir65@gmail.com
 */

package com.farhan.tanvir.pexels.data

import com.farhan.tanvir.pexels.data.model.APIResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageApiService {
    @GET("v1/search")
    suspend fun getSearchedImage(
        @Query("query")
        searchQuery:String,
        @Query("per_page")
        page:Int
    ): Response<APIResponse>
}