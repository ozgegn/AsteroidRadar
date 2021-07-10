package com.ozgegn.asteroidradar.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.ozgegn.asteroidradar.Constants
import com.ozgegn.asteroidradar.R
import com.ozgegn.asteroidradar.databinding.FragmentMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainFragment : Fragment() {

    private val viewModel by lazy {
        ViewModelProvider(this, MainViewModelFactory(requireActivity().application)).get(
            MainViewModel::class.java
        )
    }

    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)

        binding.mainViewModel = viewModel
        binding.lifecycleOwner = this

        val adapter = AsteroidListAdapter(AsteroidClickListener {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToDetailFragment(it.id))
        })

        binding.listAsteroid.adapter = adapter
        binding.listAsteroid.addItemDecoration(DividerItemDecoration(requireContext(), VERTICAL))

        initData()

        return binding.root
    }

    private fun initData() {

        val calender = Calendar.getInstance()
        val dateFormat = SimpleDateFormat(Constants.API_QUERY_DATE_FORMAT, Locale.getDefault())
        val startDate = dateFormat.format(calender.time)
        calender.add(Calendar.DAY_OF_YEAR, 7)
        val endDate = dateFormat.format(calender.time)

        viewModel.getData(getString(R.string.nasa_api_key), startDate, endDate)

    }

}