/*
 * Created by Farhan Tanvir on 12/5/21, 5:08 PM
 * Last modified 12/5/21, 5:06 PM
 * Contact : farhantanvir65@gmail.com
 */

package com.farhan.tanvir.pexels.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.farhan.tanvir.pexels.data.PhotoDiffUtil
import com.farhan.tanvir.pexels.data.model.Photo
import com.farhan.tanvir.pexels.databinding.ImageItemBinding


class ImageAdapter:
    RecyclerView.Adapter<ImageAdapter.ItemViewHolder>()  {

    private lateinit var itemClickListener: OnItemClickListener
    private var photoDetails = ArrayList<Photo>()

    interface OnItemClickListener {
        fun onItemClick(view: View, obj: Photo, position: Int)
    }

    fun setOnItemClickListener(mItemClickListener: OnItemClickListener) {
        this.itemClickListener = mItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder.from(
            parent
        )
    }

    override fun onBindViewHolder(viewHolder: ItemViewHolder, position: Int) {
        val item = photoDetails[position]
        viewHolder.bind(item, itemClickListener, position)
    }

    fun setData(newData: ArrayList<Photo>) {
        val diffUtil =
            PhotoDiffUtil(photoDetails, newData)
        val diffUtilResult = DiffUtil.calculateDiff(diffUtil)
        photoDetails = newData
        diffUtilResult.dispatchUpdatesTo(this)
    }

    override fun getItemCount(): Int {
        return photoDetails.size
    }


    class ItemViewHolder(private val binding: ImageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(photoDetails: Photo, itemClickListener: OnItemClickListener, position: Int) {
            binding.photoDetails = photoDetails
            binding.executePendingBindings()
            binding.apply {
                root.setOnClickListener { v: View ->
                    itemClickListener.onItemClick(
                        v,
                        photoDetails,
                        position
                    )
                }
            }
        }

        companion object {
            fun from(parent: ViewGroup): ItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ImageItemBinding.inflate(layoutInflater, parent, false)
                return ItemViewHolder(
                    binding
                )
            }
        }
    }
}