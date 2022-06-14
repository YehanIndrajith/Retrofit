package com.example.restapiretrofit203

import android.annotation.SuppressLint
import android.os.Bundle
import android.telecom.Call
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restapiretrofit203.api.Post
import com.example.restapiretrofit203.api.RecyclerViewCustomAdapter


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
        //creating apiInterface instance
        val apiInterface = RetrofitAPI.create().getPosts()
        //enqueueing apiInterface
        apiInterface.enqueue( object : Callback<List<Post>> {
            @SuppressLint("CheckResult")
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                // getting the recyclerview by its id
                val recyclerview = binding.recyclerview
                // this creates a vertical layout Manager
                recyclerview.layoutManager = LinearLayoutManager(parentFragment?.context)
                //if response body isn't null use it as postList and create the adapter
                //pass the adapter into recycler view
                if(response.body() != null){
                    val adapter = RecyclerViewCustomAdapter(response.body()!!)
                    recyclerview.adapter = adapter

                    adapter.clickEvent.subscribe {
                        val id = response.body()!![it.toInt()-1].id
                        val userId = response.body()!![it.toInt()-1].userId
                        val title = response.body()!![it.toInt()-1].title
                        val body = response.body()!![it.toInt()-1].body

                        setFragmentResult("requestKey", bundleOf("id" to id,"userId" to userId,"title" to title,"body" to body))
                        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
                    }
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Log.i("First Fragment","DB Error")
            }
        })





    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}