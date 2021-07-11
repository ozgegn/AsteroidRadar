package com.ozgegn.asteroidradar.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ozgegn.asteroidradar.R
import com.ozgegn.asteroidradar.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private val detailViewModel by lazy {
        ViewModelProvider(this).get(
            DetailViewModel::class.java
        )
    }

    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)

        binding.detailViewModel = detailViewModel
        binding.lifecycleOwner = this

        val asteroid = DetailFragmentArgs.fromBundle(requireArguments()).selectedAsteroid

        binding.asteroid = asteroid
        binding.detailViewModel = detailViewModel

        detailViewModel.infoClicked.observe(viewLifecycleOwner, {
            if (it) {
                displayAstronomicalUnitExplanationDialog()
            }
        })

        return binding.root
    }

    private fun displayAstronomicalUnitExplanationDialog() {
        AlertDialog.Builder(requireContext())
            .setMessage(R.string.astronomica_unit_explanation).setPositiveButton(
                R.string.astronomical_unit_explanation_posivite_button
            ) { dialog, _ -> dialog?.dismiss() }.show()
    }
}