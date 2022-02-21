/*
 * Created by Farhan Tanvir on 12/5/21, 5:08 PM
 * Last modified 12/5/21, 5:06 PM
 * Contact : farhantanvir65@gmail.com
 */

package com.farhan.tanvir.pexels.data.model
import com.google.gson.annotations.SerializedName

data class Photo(
    @SerializedName("id") val id : Int,
    @SerializedName("width") val width : Int,
    @SerializedName("height") val height : Int,
    @SerializedName("url") val url : String,
    @SerializedName("photographer") val photographer : String,
    @SerializedName("photographer_url") val photographer_url : String,
    @SerializedName("photographer_id") val photographer_id : Int,
    @SerializedName("avg_color") val avg_color : String,
    @SerializedName("src") val src : Src,
    @SerializedName("liked") val liked : Boolean
)