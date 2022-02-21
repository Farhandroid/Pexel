/*
 * Created by Farhan Tanvir on 12/5/21, 5:08 PM
 * Last modified 12/5/21, 5:06 PM
 * Contact : farhantanvir65@gmail.com
 */


package com.farhan.tanvir.pexels.data
import androidx.recyclerview.widget.DiffUtil
import com.farhan.tanvir.pexels.data.model.Photo


class PhotoDiffUtil(
    private val oldList: ArrayList<Photo>,
    private val newList: ArrayList<Photo>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
                && oldList[oldItemPosition].width == newList[newItemPosition].width
                && oldList[oldItemPosition].height == newList[newItemPosition].height
                && oldList[oldItemPosition].url == newList[newItemPosition].url
                && oldList[oldItemPosition].photographer == newList[newItemPosition].photographer
                && oldList[oldItemPosition].photographer_url == newList[newItemPosition].photographer_url
                && oldList[oldItemPosition].photographer_id == newList[newItemPosition].photographer_id
                && oldList[oldItemPosition].avg_color == newList[newItemPosition].avg_color
                && oldList[oldItemPosition].src == newList[newItemPosition].src
                && oldList[oldItemPosition].liked == newList[newItemPosition].liked
    }
}