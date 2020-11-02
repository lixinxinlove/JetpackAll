package com.lixinxinlove.all.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.lixinxinlove.all.R

private const val ARG_PARAM1 = "param1"

class CardFragment : Fragment() {
    private var param1: String? = null


    private lateinit var titleText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_card, container, false)
        titleText = view.findViewById(R.id.title_text)
        titleText.text = param1
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String) = CardFragment().apply {
            arguments = Bundle().apply { putString(ARG_PARAM1, param1) }
        }
    }
}