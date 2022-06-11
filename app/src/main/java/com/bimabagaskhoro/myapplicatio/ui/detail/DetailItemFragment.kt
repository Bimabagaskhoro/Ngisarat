package com.bimabagaskhoro.myapplicatio.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.bimabagaskhoro.myapplicatio.R
import com.bimabagaskhoro.myapplicatio.databinding.FragmentDetailItemBinding
import com.bimabagaskhoro.myapplicatio.domain.model.ItemAlphabet
import com.bimabagaskhoro.myapplicatio.ui.vm.AlphabetViewModel
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailItemFragment : Fragment() {

    private var _binding: FragmentDetailItemBinding? = null
    private val binding get() = _binding!!
    private lateinit var dataItem: ItemAlphabet

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDetailItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val reqActivity = requireActivity() as AppCompatActivity
        reqActivity.title = getString(R.string.detail)
        reqActivity.setSupportActionBar(binding.toolbar)

        if (arguments != null) {
            dataItem = arguments?.getParcelable(EXTRA_DATA)!!
            initView()
        }
    }

    private fun initView() {
        if (arguments != null){
            dataItem = arguments?.getParcelable(EXTRA_DATA)!!

            binding.apply {
                textView.text = dataItem.tittleAlphabet
                Glide.with(requireContext())
                    .load(EXTRA_LINK+dataItem.imageAlphabet)
                    .into(binding.imgDetail)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
        const val EXTRA_LINK = "http://172.20.10.11/bisindo-api-php/image_alphabet/"
    }
}