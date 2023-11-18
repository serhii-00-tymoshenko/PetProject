package com.example.petproject.ui.bottom_sheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.petproject.R
import com.example.petproject.databinding.ItemBottomSheetBinding
import com.example.petproject.entities.BottomSheetUser
import com.example.petproject.ui.bottom_sheet.adapters.BottomSheetAdapter
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheet(
    private val callback: (BottomSheetUser) -> Unit
) : BottomSheetDialogFragment() {
    companion object {
        const val TAG = "BottomSheet"
    }

    private var _binding: ItemBottomSheetBinding? = null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ItemBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
    }

    private fun setupAdapter() {
        val bottomSheetAdapter = BottomSheetAdapter {
            user -> callback.invoke(user)
        }
        val recycler = binding.bottomSheetRecycler
        recycler.apply {
            adapter = bottomSheetAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
        bottomSheetAdapter.submitList(
            listOf(
                BottomSheetUser(R.drawable.ic_launcher_background, "gffh", 0),
                BottomSheetUser(R.drawable.ic_launcher_background, "gfftfh", 1)
            )
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}