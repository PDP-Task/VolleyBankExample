package com.samsdk.volleyexample.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.Gson
import com.samsdk.volleyexample.R
import com.samsdk.volleyexample.adapter.MyAdapter
import com.samsdk.volleyexample.databinding.ActivityMainBinding
import com.samsdk.volleyexample.mode.Course
import com.samsdk.volleyexample.networking.VolleyHandler
import com.samsdk.volleyexample.networking.VolleyHttp

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var volleyAdapter: MyAdapter
    private val posters = ArrayList<Course>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()

    }

    private fun initViews() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        apiPosterList()
        refreshAdapter(posters)

        volleyAdapter.onClickItem = {
            showBottomSheet(it)
        }
    }

    private fun apiPosterList() {
        VolleyHttp.get(VolleyHttp.API_LIST_POST,
            VolleyHttp.paramsEmpty(),
            @SuppressLint("NotifyDataSetChanged")
            object : VolleyHandler {
                override fun onSuccess(response: String?) {
                    val posterArray = Gson().fromJson(response, Array<Course>::class.java)
                    posters.addAll(posterArray)
                    volleyAdapter.notifyDataSetChanged()
                }

                override fun onError(error: String?) {
                    Log.d("@@@", error!!)
                }
            })
    }

    private fun refreshAdapter(posters: ArrayList<Course>) {
        volleyAdapter = MyAdapter(posters)
        binding.recyclerView.adapter = volleyAdapter
    }

    private fun showBottomSheet(course: Course) {
        val view: View = layoutInflater.inflate(R.layout.bottom_sheet_layout, null)
        val dialog = BottomSheetDialog(this)
        dialog.setContentView(view)
        val textUs: TextView = view.findViewById(R.id.textUs)
        val textUz: TextView = view.findViewById(R.id.textUz)
        val textRate: TextView = view.findViewById(R.id.textRate)
        val textDiff: TextView = view.findViewById(R.id.textDiff)
        textUs.text = course.CcyNm_EN
        textUz.text = course.CcyNm_UZ
        textRate.text = course.Rate
        textDiff.text = course.Diff
        dialog.show()
    }
}