package com.example.youtubeapi.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import com.example.youtubeapi.data.models.DetailVideo
import com.example.youtubeapi.data.network.connection.NetworkConnection
import com.example.youtubeapi.databinding.FragmentActionBottomDialogBinding
import com.example.youtubeapi.interfa.OnItemClickListener
import com.example.youtubeapi.ui.fragment.adapter.AdapterDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ActionBottomDialogFragment(private var onItemClickListener: OnItemClickListener) :
    BottomSheetDialogFragment(), View.OnClickListener {

    lateinit var binding: FragmentActionBottomDialogBinding

    private var mListener: ItemClickListener? = null
    private lateinit var adapter: AdapterDialog

    @Nullable
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentActionBottomDialogBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDialogAdapter()

        val networkConnection = NetworkConnection(requireContext())
        networkConnection.observe(this, Observer { isConnected ->
            if (isConnected){
                binding.layoutDisconnectFragment.visibility = View.GONE
                binding.layoutConnectFragment.visibility = View.VISIBLE
            }else{
                binding.layoutConnectFragment.visibility = View.GONE
                binding.layoutDisconnectFragment.visibility = View.VISIBLE
            }
        })
    }

    fun setDialogAdapter() {
        adapter =
            AdapterDialog(onItemClickListener)
        binding.recyclerViewDialogFragment.adapter = adapter
        detaillist?.let { adapter.videoDialog(item = it) }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ItemClickListener) {
            mListener = context as ItemClickListener
        } else {
            throw RuntimeException(
                "$context must implement ItemClickListener"
            )
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    companion object {
        var detaillist: MutableList<DetailVideo>? = null
        fun showFragment(
            detaillist: MutableList<DetailVideo>,
            supportFragmentManager: FragmentManager,
            onItemClickListener: OnItemClickListener
        ) {
            this.detaillist = detaillist
            val fragmentt = ActionBottomDialogFragment(onItemClickListener)
            fragmentt.show(supportFragmentManager, fragmentt.tag)
        }
    }

    override fun onClick(v: View?) {
        val tvSelected = view as TextView
        mListener!!.onItemClick(tvSelected.text.toString())
        dismiss()
    }

    interface ItemClickListener {
        fun onItemClick(item: String?)
    }
}