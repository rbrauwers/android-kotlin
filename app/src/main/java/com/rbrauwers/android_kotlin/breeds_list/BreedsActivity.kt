package com.rbrauwers.android_kotlin.breeds_list

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.rbrauwers.android_kotlin.R
import com.rbrauwers.android_kotlin.data.BreedsRepository
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_breeds.*
import kotlinx.android.synthetic.main.cell_breed.view.*
import kotlinx.android.synthetic.main.cell_main_option.view.*
import javax.inject.Inject
import android.support.v4.view.animation.FastOutLinearInInterpolator
import android.support.v4.view.animation.LinearOutSlowInInterpolator
import android.view.Gravity
import com.rbrauwers.android_kotlin.utils.log
import com.transitionseverywhere.*
import com.transitionseverywhere.extra.Scale
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter
import android.view.animation.AnimationUtils
import android.support.v4.view.ViewCompat
import android.opengl.ETC1.getHeight
import android.widget.Toast
import com.rbrauwers.android_kotlin.extension.hideToTop
import com.rbrauwers.android_kotlin.extension.showFromTop
import kotlinx.android.synthetic.main.cell_main_option.*


class BreedsActivity : AppCompatActivity(), BreedsContract.View {

    @Inject
    lateinit var repository: BreedsRepository

    private lateinit var presenter: BreedsPresenter
    private var adapter: BreedsAdapter
    private var breedClickListener: BreedClickListener

    init {
        breedClickListener = object: BreedClickListener {
            override fun onBreedClick(breed: String) {
                presenter.openBreedDetails(breed)
                showTopView(breed)
            }
        }
        adapter = BreedsAdapter(breedClickListener)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AndroidInjection.inject(this)
        setContentView(R.layout.activity_breeds)
        setTitle(R.string.dog_breeds)

        breedsRecyclerView.layoutManager = LinearLayoutManager(this)
        breedsRecyclerView.adapter = adapter

        presenter = BreedsPresenter(repository, this)
        breedsTopTextView.visibility = View.INVISIBLE
    }

    override fun onStart() {
        super.onStart()
        presenter.subscribe()
    }

    override fun onStop() {
        super.onStop()
        presenter.unsubscribe()
    }

    override fun showLoadingView() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoadingView() {
        progressBar.visibility = View.INVISIBLE
    }

    override fun showError() {
        Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
    }

    fun showTopView(breed: String) {
        breedsTopTextView.text = breed
        breedsTopTextView.showFromTop()
        breedsTopTextView.postDelayed(Runnable { breedsTopTextView.hideToTop() }, 5000)
    }

    override fun showBreeds(breeds: List<String>) {
        adapter.setData(breeds)
    }

    class BreedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }

    class BreedsAdapter(var breedClickListener: BreedClickListener) : RecyclerView.Adapter<BreedViewHolder>() {

        private var breeds: List<String>? = null

        fun setData(breeds: List<String>) {
            this.breeds = breeds
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BreedViewHolder {
            val view = LayoutInflater.from(parent?.context).inflate(R.layout.cell_breed, parent, false)
            val vh = BreedViewHolder(view)
            vh.itemView?.setOnClickListener {
                breedClickListener.onBreedClick(breeds!![vh.adapterPosition])
            }
            return vh
        }

        override fun onBindViewHolder(holder: BreedViewHolder?, position: Int) {
            holder?.itemView?.breedTextView?.text = breeds!![position]
        }

        override fun getItemCount(): Int {
            return breeds?.size ?: 0
        }

    }

    interface BreedClickListener {
        fun onBreedClick(breed: String)
    }
}
