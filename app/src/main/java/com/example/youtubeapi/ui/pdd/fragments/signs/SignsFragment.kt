package com.example.youtubeapi.ui.pdd.fragments.signs

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.youtubeapi.R

class SignsFragment : Fragment() {

    companion object {
        fun newInstance() = SignsFragment()
    }

    private lateinit var viewModel: SignsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.signs_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SignsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}