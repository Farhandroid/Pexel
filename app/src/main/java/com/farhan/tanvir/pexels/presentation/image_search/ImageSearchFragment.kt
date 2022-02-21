/*
 * Created by Farhan Tanvir on 12/5/21, 5:08 PM
 * Last modified 12/5/21, 5:06 PM
 * Contact : farhantanvir65@gmail.com
 */

package com.farhan.tanvir.pexels.presentation.image_search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.farhan.tanvir.pexels.R
import com.farhan.tanvir.pexels.data.model.Photo
import com.farhan.tanvir.pexels.data.util.Resource
import com.farhan.tanvir.pexels.databinding.FragmentImageSearchBinding
import com.farhan.tanvir.pexels.presentation.adapter.ImageAdapter

class ImageSearchFragment : Fragment() {

    private lateinit var viewModel: ImageSearchViewModel
    private lateinit var fragmentImageSearchBinding: FragmentImageSearchBinding
    private lateinit var imageAdapter: ImageAdapter
    private val itemPerPage = 40

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_image_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //ツールバーを表示
        (requireActivity() as AppCompatActivity).supportActionBar?.show()
        activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        fragmentImageSearchBinding = FragmentImageSearchBinding.bind(view)
        viewModel =
            ViewModelProvider(this)[ImageSearchViewModel::class.java]
        fragmentImageSearchBinding.searchET.doAfterTextChanged {
            it?.let {
                if (it.isNotEmpty()) {
                    getSearchResultData(it.toString())
                }
            }
        }
        fragmentImageSearchBinding.clearSearchIV.setOnClickListener {
            fragmentImageSearchBinding.searchET.text.clear()
        }
        viewModel.searchedImageData.observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Success -> {
                    response.data?.let { apiResponse ->
                        imageAdapter.setData(apiResponse.photos)
                    }
                }
                is Resource.Error -> {
                }

                is Resource.Loading -> {

                }
            }
        })
        initRecyclerView()
        initActions()
    }

    private fun getSearchResultData(searchQuery: String) {
        try {
            viewModel.getSearchedImage(searchQuery, itemPerPage)
        } catch (e: Exception) {
        }
    }

    private fun initRecyclerView() {
        imageAdapter = ImageAdapter()
        fragmentImageSearchBinding.imageRV.apply {
            adapter = imageAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

    private fun initActions() {
        imageAdapter.setOnItemClickListener(object : ImageAdapter.OnItemClickListener {
            override fun onItemClick(view: View, obj: Photo, position: Int) {
                (requireActivity() as AppCompatActivity).supportActionBar?.hide()
                val bundle = Bundle().apply {
                    putSerializable("selected_image", obj.src)
                }
                findNavController().navigate(
                    R.id.action_imageSearchFragment_to_imageViewerFragment,
                    bundle
                )
            }
        })
    }
}