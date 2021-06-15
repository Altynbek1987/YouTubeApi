package com.example.youtubeapi.ui.pdd.fragments.fines

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.youtubeapi.R

class FinesFragment : Fragment() {

    companion object {
        fun newInstance() = FinesFragment()
    }

    private lateinit var viewModel: FinesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fines_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FinesViewModel::class.java)
        // TODO: Use the ViewModel
    }

}