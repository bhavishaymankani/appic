package com.bhavishaymankani.machinetestappic.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bhavishaymankani.machinetestappic.activities.base.BaseActivity
import com.bhavishaymankani.machinetestappic.listeners.OnItemClickListener
import com.bhavishaymankani.machinetestappic.utils.Constants.ACCOUNT_NUMBER
import com.bhavishaymankani.machinetestappic.utils.Constants.BRAND_NAME
import com.bhavishaymankani.machinetestappic.utils.Constants.DATA_KEY
import com.bhavishaymankani.machinetestappic.adapter.Adapter
import com.bhavishaymankani.machinetestappic.databinding.ActivitySelectBinding
import com.bhavishaymankani.machinetestappic.utils.Constants
import com.bhavishaymankani.machinetestappic.viewmodel.DataViewModel

class SelectActivity : BaseActivity(), OnItemClickListener {
    private lateinit var binding: ActivitySelectBinding
    private lateinit var viewModel: DataViewModel
    private lateinit var adapter: Adapter
    private lateinit var list: MutableList<String>

    private var sharedPreferences: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "Select"
        viewModel = ViewModelProvider(this)[DataViewModel::class.java]
        list = mutableListOf<String>()
        adapter = Adapter(this)
        sharedPreferences = getSharedPreferences(DATA_KEY, Context.MODE_PRIVATE)

        observe()
        viewModel.getData()
    }


    private fun observe() {

        Log.d("xyz", intent.extras?.get(Constants.POSITION).toString())

        viewModel.data.observe(this) {
            val data = it.filterData!![0]?.hierarchy

            when (intent.extras?.get(Constants.POSITION)) {

                0 -> {
                    data?.forEach { item ->
                        list.add(item?.accountNumber.toString())
                        adapter.setList(list)
                    }
                }
                1 -> {
                    data?.forEach { item ->
                        if (item?.accountNumber == sharedPreferences?.all?.get(ACCOUNT_NUMBER)?.toString()) {

                            item?.brandNameList?.forEach { brandNameListItem ->
                                list.add(brandNameListItem?.brandName.toString())
                                adapter.setList(list)
                            }
                        }

                    }
                }
                2 -> {
                    data?.forEach { item ->
                        if (item?.accountNumber == sharedPreferences?.all?.get(ACCOUNT_NUMBER)?.toString()) {
                            item?.brandNameList?.forEach { brandNameListItem ->
                                if (brandNameListItem?.brandName == sharedPreferences?.all?.get(
                                        BRAND_NAME)?.toString()) {
                                    brandNameListItem?.locationNameList?.forEach { locationNameListItem ->
                                        list.add(locationNameListItem?.locationName.toString())
                                        adapter.setList(list)
                                    }
                                }

                            }
                        }
                    }
                }
            }

            adapter.setList(list)
            binding.selectRv.create(adapter as RecyclerView.Adapter<RecyclerView.ViewHolder>)

        }
    }

    override fun onItemClick(position: Int) {

        Intent(this, MainActivity::class.java).apply {
              if (intent.hasExtra(Constants.ACCOUNT_NUMBER) && intent.extras?.get(Constants.POSITION) == 1) {
                putExtra(Constants.BRAND_NAME, list[position])
                sharedPreferences?.edit()?.apply {
                    this?.putString(Constants.BRAND_NAME, list[position])
                    apply()
                }
            } else if (intent.hasExtra(Constants.BRAND_NAME) && intent.extras?.get(Constants.POSITION) == 2){
                putExtra(Constants.LOCATION, list[position])
                sharedPreferences?.edit()?.apply {
                    this?.putString(Constants.LOCATION, list[position])
                    apply()
                }
            }
            else {
                putExtra(Constants.ACCOUNT_NUMBER, list[position])
                sharedPreferences?.edit()?.apply {
                    this?.putString(Constants.ACCOUNT_NUMBER, list[position])
                    apply()
                }
            }
            startActivity(this)
            finish()
        }
    }
}