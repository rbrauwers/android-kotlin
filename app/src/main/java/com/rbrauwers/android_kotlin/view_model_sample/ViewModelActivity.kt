package com.rbrauwers.android_kotlin.view_model_sample

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import com.google.gson.Gson
import com.rbrauwers.android_kotlin.R
import com.rbrauwers.android_kotlin.breeds_list.BreedsActivity
import com.rbrauwers.android_kotlin.extension.startActivityWithClass
import com.rbrauwers.android_kotlin.main_options.MainActivity
import com.rbrauwers.android_kotlin.maps.MapsActivity
import com.rbrauwers.android_kotlin.utils.log
import com.rbrauwers.android_kotlin.view_model.MyViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_view_model_sample.*
import kotlinx.android.synthetic.main.cell_breed.view.*
import kotlinx.android.synthetic.main.cell_main_option.view.*
import javax.inject.Inject

/**
 * Created by rodrigobrauwers on 27/03/18.
 */
class ViewModelActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MyViewModel::class.java)

        viewModel.items.observe(this, Observer {
            items ->
            log{ "observer called" }
            log { Gson().toJson(items) }
            recyclerView.adapter.notifyDataSetChanged()
        })

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = Adapter(this, viewModel)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_view_model, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            R.id.option_add -> {
                log { "Add" }
                viewModel.addRandom()
            }
            R.id.option_clear -> {
                log {"Clear"}
            }
        }

        return super.onOptionsItemSelected(item)
    }

    inner class Adapter(private val context: Context, private val myViewModel: MyViewModel) : RecyclerView.Adapter<BreedsActivity.BreedViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BreedsActivity.BreedViewHolder {
            val view = LayoutInflater.from(context).inflate(R.layout.cell_breed, parent, false)
            return BreedsActivity.BreedViewHolder(view)
        }

        override fun onBindViewHolder(holder: BreedsActivity.BreedViewHolder?, position: Int) {
            holder?.itemView?.breedTextView?.text = viewModel.item(position)
        }

        override fun getItemCount(): Int {
            return viewModel.size()
        }

    }
}

