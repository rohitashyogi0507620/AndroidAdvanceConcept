package com.yogify.androidadvanceconcept

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.yogify.androidadvanceconcept.databinding.FragmentFirstBinding
import com.yogify.androidadvanceconcept.rxjava.RXJavactivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.RxJAva.setOnClickListener {
            startActivity(Intent(requireContext(), RXJavactivity::class.java))
        }

        binding.FlowChannel.setOnClickListener {
        }

        val myIntFlow: Flow<String> = flow { emit("sdsfdgdfgf") }

        GlobalScope.launch{

            myIntFlow.collect { Log.d("DATA", it) }
        }



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}