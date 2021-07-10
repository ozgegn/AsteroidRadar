package com.ozgegn.asteroidradar.ui.detail

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.ozgegn.asteroidradar.R
import com.ozgegn.asteroidradar.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private val detailViewModel by lazy {
        ViewModelProvider(this, DetailViewModelFactory(requireActivity().application)).get(
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

        val asteroidId = DetailFragmentArgs.fromBundle(requireArguments()).asteroidId

        detailViewModel.getAsteroid(asteroidId)

        detailViewModel.error.observe(viewLifecycleOwner, {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        })

        detailViewModel.infoClicked.observe(viewLifecycleOwner, {
            if (it) {
                AlertDialog.Builder(requireContext())
                    .setMessage(R.string.astronomica_unit_explanation).setPositiveButton(
                        R.string.astronomical_unit_explanation_posivite_button
                    ) { dialog, _ -> dialog?.dismiss() }.show()
            }
        })

        return binding.root
    }
}