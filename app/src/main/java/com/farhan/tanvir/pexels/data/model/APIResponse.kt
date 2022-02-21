/*
 * Created by Farhan Tanvir on 12/5/21, 5:08 PM
 * Last modified 12/5/21, 5:06 PM
 * Contact : farhantanvir65@gmail.com
 */


package com.farhan.tanvir.pexels.data.model
import com.google.gson.annotations.SerializedName

data class APIResponse(
    @SerializedName("total_results") val total_results: Int,
    @SerializedName("page") val page: Int,
    @SerializedName("per_page") val per_page: Int,
    @SerializedName("photos") val photos: ArrayList<Photo>,
    @SerializedName("next_page") val next_page: String
)