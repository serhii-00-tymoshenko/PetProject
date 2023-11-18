package com.example.petproject.ui.bottom_sheet.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.petproject.R
import com.example.petproject.databinding.ItemBottomSheetUserBinding
import com.example.petproject.entities.BottomSheetUser

class BottomSheetAdapter(
    private val callback: (BottomSheetUser) -> Unit
) : ListAdapter<BottomSheetUser, BottomSheetAdapter.BottomSheetViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<BottomSheetUser>(){
            override fun areItemsTheSame(
                oldItem: BottomSheetUser,
                newItem: BottomSheetUser
            ) = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: BottomSheetUser,
                newItem: BottomSheetUser
            ) = oldItem == newItem
        }
    }

    inner class BottomSheetViewHolder(private val binding: ItemBottomSheetUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: BottomSheetUser) {
            if (adapterPosition != RecyclerView.NO_POSITION) {
                binding.root.setOnClickListener {
                    callback.invoke(user)
                }
            }
            binding.apply {
                bottomSheetUserPhoto.setImageResource(user.photo)
                bottomSheetUsername.text = user.username
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BottomSheetViewHolder {
        val binding = ItemBottomSheetUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BottomSheetViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BottomSheetViewHolder, position: Int) {
        val currentUser = getItem(position)
        holder.bind(currentUser)
    }
}