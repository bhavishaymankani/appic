package com.bhavishaymankani.machinetestappic.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.RecyclerView
import com.bhavishaymankani.machinetestappic.activities.base.BaseActivity
import com.bhavishaymankani.machinetestappic.listeners.OnItemClickListener
import com.bhavishaymankani.machinetestappic.R
import com.bhavishaymankani.machinetestappic.utils.Constants.ACCOUNT_NUMBER
import com.bhavishaymankani.machinetestappic.utils.Constants.BRAND_NAME
import com.bhavishaymankani.machinetestappic.utils.Constants.LOCATION
import com.bhavishaymankani.machinetestappic.utils.Constants.POSITION
import com.bhavishaymankani.machinetestappic.adapter.Adapter
import com.bhavishaymankani.machinetestappic.databinding.ActivityMainBinding
import com.bhavishaymankani.machinetestappic.utils.Constants

class MainActivity : BaseActivity(), OnItemClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: Adapter
    private lateinit var list: MutableList<String>
    private var sharedPreferences: SharedPreferences? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        list = mutableListOf("Select Account Number", "Select Brand", "Select Location")
        adapter = Adapter(this).apply { setList(list) }
        sharedPreferences = getSharedPreferences(Constants.DATA_KEY, Context.MODE_PRIVATE)



        binding.recyclerview.create(adapter as RecyclerView.Adapter<RecyclerView.ViewHolder>)

        receive()
    }



    private fun receive() {
        if (sharedPreferences?.contains(ACCOUNT_NUMBER) == true) {
            list[0] = sharedPreferences?.all?.get(ACCOUNT_NUMBER)?.toString()!!
        }
        if (sharedPreferences?.contains(BRAND_NAME) == true) {
            list[1] = sharedPreferences?.all?.get(BRAND_NAME)?.toString()!!
        }
        if (sharedPreferences?.contains(LOCATION) == true) {
            list[2] = sharedPreferences?.all?.get(LOCATION)?.toString()!!
        }
        adapter.setList(list)
    }

    override fun onItemClick(position: Int) {
        Intent(this, SelectActivity::class.java).apply {
            if (position == 0){
                putExtra(POSITION, position)
            }
            if (position == 1){
                putExtra(POSITION, position)
                putExtra(ACCOUNT_NUMBER, list[position])
            }
            if (position == 2){
                putExtra(POSITION, position)
                putExtra(ACCOUNT_NUMBER, list[position])
                putExtra(BRAND_NAME, list[position])
            }
            startActivity(this)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.clear_data -> {
                sharedPreferences?.edit()?.clear()?.apply()
            }
        }

        return true
    }



}


