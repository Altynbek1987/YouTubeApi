package com.example.youtubeapi.ui.pdd.fragments.pdd

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.youtubeapi.R

class PddFragment : Fragment() {


    companion object {
        fun newInstance() = PddFragment()
    }

    private lateinit var viewModel: PddViewModel



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.pdd_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PddViewModel::class.java)
        // TODO: Use the ViewModel
    }

}