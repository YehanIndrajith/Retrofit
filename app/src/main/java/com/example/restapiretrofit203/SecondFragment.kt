package com.example.restapiretrofit203

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFragmentResultListener("requestKey"){_,bundle->
            val id = bundle.getInt("id")
            (activity as AppCompatActivity).supportActionBar?.title = "Post $id Info"
            val userId = bundle.getInt("userId")
            val title = bundle.getString("title")
            val body = bundle.getString("body")

            binding.textViewIdContent.text = id.toString()
            binding.textViewUserIdContent.text = userId.toString()
            binding.textViewTitleContent.text = title
            binding.textViewBodyContent.text = body
            setFragmentResult("requestKey_2", bundleOf("id" to id))
        }

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_ThirdFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
