package com.ozgegn.asteroidradar.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ozgegn.asteroidradar.R
import com.ozgegn.asteroidradar.data.Asteroid
import com.ozgegn.asteroidradar.databinding.RowAsteroidListBinding

class AsteroidListAdapter(
    val clickListener: AsteroidClickListener
) :
    ListAdapter<Asteroid, AsteroidListAdapter.AsteroidListViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AsteroidListViewHolder {
        return AsteroidListViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: AsteroidListViewHolder, position: Int) {
        holder.onBindView(getItem(position), clickListener)
    }

    class AsteroidListViewHolder(private val binding: RowAsteroidListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBindView(asteroid: Asteroid, clickListener: AsteroidClickListener) {
            binding.asteroid = asteroid
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): AsteroidListViewHolder {
                val binding = DataBindingUtil.inflate<RowAsteroidListBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.row_asteroid_list,
                    parent,
                    false
                )
                return AsteroidListViewHolder(binding)
            }
        }

    }

    companion object DiffCallback : DiffUtil.ItemCallback<Asteroid>() {
        override fun areItemsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
            return oldItem == newItem
        }
    }
}

class AsteroidClickListener(val clickListener: (asteroid: Asteroid) -> Unit) {
    fun onClick(asteroid: Asteroid) = clickListener(asteroid)
}