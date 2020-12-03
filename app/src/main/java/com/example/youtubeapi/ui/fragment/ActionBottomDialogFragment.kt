package com.example.youtubeapi.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.recyclerview.widget.LinearSnapHelper
import com.example.youtubeapi.R
import com.example.youtubeapi.data.models.DetailVideo
import com.example.youtubeapi.interfa.OnItemClickListener
import com.example.youtubeapi.ui.fragment.adapter.AdapterDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_action_bottom_dialog.*

class ActionBottomDialogFragment(private val listt:MutableList<DetailVideo>) : BottomSheetDialogFragment(),View.OnClickListener,
    OnItemClickListener {
    private var mListener: ItemClickListener? = null
    private lateinit var adapter: AdapterDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
@Nullable
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_action_bottom_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDialogAdapter()
//        textView.setOnClickListener(this)
//        textView2.setOnClickListener(this)
//        textView3.setOnClickListener(this)
//        textView4.setOnClickListener(this)
    }
    fun setDialogAdapter() {
        adapter =
            AdapterDialog(this)
        recyclerView_dialog_fragment.adapter = adapter
        adapter.videoDialog(item = listt)
        val snap = LinearSnapHelper()
        snap.attachToRecyclerView(recyclerView)
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
        val TAG = "ActionBottomDialog"
        fun newInstance(listDialogg: MutableList<DetailVideo>): ActionBottomDialogFragment? {
            return ActionBottomDialogFragment(listDialogg)
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

    override fun itemClick(position: Int) {
        TODO("Not yet implemented")
    }

    override fun itemClick(model: DetailVideo) {
        TODO("Not yet implemented")
    }
    fun intentDialog(){

    }
}