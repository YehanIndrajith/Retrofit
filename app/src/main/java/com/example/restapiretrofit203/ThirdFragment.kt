package com.example.restapiretrofit203

import android.annotation.SuppressLint
import android.os.Bundle
import android.telecom.Call
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restapiretrofit203.api.Comments
import com.example.restapiretrofit203.api.RecyclerViewCustomAdapter2

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ThirdFragment : Fragment() {

    private var _binding: FragmentThirdBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentThirdBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //creating apiInterface instance
        val apiInterface = RetrofitAPI.create().getComments()
        //enqueueing apiInterface
        apiInterface.enqueue( object : Callback<List<Comments>> {
            @SuppressLint("CheckResult")
            override fun onResponse(call: Call<List<Comments>>, response: Response<List<Comments>>) {
                // getting the recyclerview by its id
                val recyclerview = binding.recyclerview
                // this creates a vertical layout Manager
                recyclerview.layoutManager = LinearLayoutManager(parentFragment?.context)
                //if response body isn't null use it as postList and create the adapter
                //pass the adapter into recycler view
                if(response.body() != null){
                    setFragmentResultListener("requestKey_2") { _, bundle ->
                        val id = bundle.getInt("id")
                        (activity as AppCompatActivity).supportActionBar?.title = "Post $id Comments"
                        val data = ArrayList<Comments>()
                        var index = 0
                        response.body()!!.forEach{
                            if(it.postId == id){
                                index++
                                Log.i("Third Fragment","${it.id} and ${it.postId} and $id")
                                //id of comment is over written by index (since user have no idea what id is)
                                data.add(Comments(postId = it.postId,index,name=it.name,email = it.email,body = it.body))
                            }
                        }
                        val adapter = RecyclerViewCustomAdapter2(data)
                        recyclerview.adapter = adapter

                    }
                }
            }

            override fun onFailure(call: Call<List<Comments>>, t: Throwable) {
                Log.i("Third Fragment","DB Error")
            }
        })



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

