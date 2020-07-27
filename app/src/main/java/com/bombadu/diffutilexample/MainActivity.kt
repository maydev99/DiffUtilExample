package com.bombadu.diffutilexample

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var nameViewModel: NameViewModel
    private val adapter = NameAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()
        insertDataIntoDB()
        getNameList()
    }

    private fun insertDataIntoDB() {
        val myData = arrayListOf("Michael", "Cate", "Steve", "Gwen", "Alex", "Ellie")
        for (i in myData) {
            println(i)
            nameViewModel.insertName(Name(i))

        }
    }

    private fun getNameList() {
        nameViewModel.getAllNames().observe(this,
            Observer { list ->
                list?.let {
                    adapter.submitList(it)
                }
            })
    }

    private fun setupRecyclerView() {
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)
        recycler_view.adapter = adapter
        this.nameViewModel = ViewModelProvider(this).get((NameViewModel::class.java))

        nameViewModel.getAllNames().observe(this, Observer { allNames ->
            allNames?.let { adapter.submitList(it) }
        })

        ItemTouchHelper(object :
        ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                adapter.getNameAt(viewHolder.adapterPosition)
                    ?.let { nameViewModel.deleteName(it) }
                Toast.makeText(this@MainActivity,"Name Deleted", Toast.LENGTH_SHORT).show()
            }
        }).attachToRecyclerView(recycler_view)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.delete_all -> {
                nameViewModel.deleteAllNames()
            }
        }

        when (item.itemId) {
            R.id.get_data -> {
                insertDataIntoDB()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}