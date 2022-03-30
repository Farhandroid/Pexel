/*
 * Created by Farhan Tanvir on 12/5/21, 5:08 PM
 * Last modified 12/5/21, 5:06 PM
 * Contact : farhantanvir65@gmail.com
 */


package com.farhan.tanvir.pexels.presentation.image_viewer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.farhan.tanvir.pexels.R
import com.farhan.tanvir.pexels.databinding.FragmentImageSearchBinding
import com.farhan.tanvir.pexels.databinding.FragmentImageViewerBinding

class ImageViewerFragment : Fragment() {

    private var _binding: FragmentImageViewerBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args : ImageViewerFragmentArgs by navArgs()
        val imageSrc = args.selectedImage
        binding.imageSrc=imageSrc

        binding.backImageView.setOnClickListener{
            findNavController().popBackStack()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //ツールバーを非表示
        activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        // Inflate the layout for this fragment
        _binding = FragmentImageViewerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}