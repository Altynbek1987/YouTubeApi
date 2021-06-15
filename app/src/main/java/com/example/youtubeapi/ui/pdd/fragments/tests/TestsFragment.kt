package com.example.youtubeapi.ui.pdd.fragments.tests

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.youtubeapi.R

class TestsFragment : Fragment() {

    companion object {
        fun newInstance() = TestsFragment()
    }

    private lateinit var viewModel: TestsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.tests_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TestsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}