package com.bimabagaskhoro.myapplicatio.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.bimabagaskhoro.myapplicatio.R
import com.bimabagaskhoro.myapplicatio.data.Resource
import com.bimabagaskhoro.myapplicatio.databinding.FragmentDashboardBinding
import com.bimabagaskhoro.myapplicatio.ui.adapter.AlphabetAdapter
import com.bimabagaskhoro.myapplicatio.ui.detail.DetailItemFragment
import com.bimabagaskhoro.myapplicatio.ui.vm.AlphabetViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment() {
    private val viewModel: AlphabetViewModel by viewModels()
    private lateinit var adapter: AlphabetAdapter

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val reqActivity = requireActivity() as AppCompatActivity
        reqActivity.title = getString(R.string.title_dashboard)
        reqActivity.setSupportActionBar(binding.toolbar)

        adapter = AlphabetAdapter()
        initViewModel()
    }

    private fun initViewModel() {
        viewModel.getAlphabet().observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    binding.progressbar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    adapter.setData(it.data!!)
                    binding.apply {
                        progressbar.visibility = View.GONE
                        rvAlphabet.adapter = adapter
                        rvAlphabet.layoutManager = GridLayoutManager(context, 2)
                        rvAlphabet.setHasFixedSize(true)
                        adapter.onItemClick = {
                            val bundle = Bundle().apply { putParcelable(DetailItemFragment.EXTRA_DATA, it) }
                            findNavController().navigate(R.id.action_navigation_dashboard_to_detailItemFragment, bundle)
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}