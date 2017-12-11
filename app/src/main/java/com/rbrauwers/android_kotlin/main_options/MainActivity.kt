package com.rbrauwers.android_kotlin.main_options

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rbrauwers.android_kotlin.R
import com.rbrauwers.android_kotlin.helper.SharedPreferencesHelper
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.cell_main_option.view.*
import javax.inject.Inject
import android.telephony.TelephonyManager
import android.util.Log
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.android.schedulers.AndroidSchedulers
import android.os.Build
import com.rbrauwers.android_kotlin.breeds_list.BreedsActivity
import com.rbrauwers.android_kotlin.extension.startActivityWithClass


class MainActivity : AppCompatActivity() {

    @Inject
    protected lateinit var sharedPreferencesHelper: SharedPreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AndroidInjection.inject(this)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = MainOptionsAdapter(this)
    }

    private fun requestPhoneNumberPermission() {
        val rxPermissions = RxPermissions(this)
        rxPermissions.request(Manifest.permission.READ_PHONE_STATE, Manifest.permission.CAMERA)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe({ granted -> showPhoneNumber() })
    }

    @SuppressLint("MissingPermission", "HardwareIds")
    private fun showPhoneNumber() {
        val tMgr = applicationContext.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        val phoneNumber = tMgr.line1Number
        Log.d("xxxx", "phone number")
        Log.d("", "phone number: $phoneNumber")

        val builder: AlertDialog.Builder
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert)
        }
        else {
            builder = AlertDialog.Builder(this)
        }
        builder.setTitle("Phone number")
            .setMessage(if (phoneNumber == null) "NULL" else phoneNumber)
            .show()
    }

    class MainOptionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    inner class MainOptionsAdapter(private val context: Context) : RecyclerView.Adapter<MainOptionViewHolder>() {

        val data = arrayOf("Retrofit + RxKotlin", "Get SIM Number")

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MainOptionViewHolder {
            val view = LayoutInflater.from(context).inflate(R.layout.cell_main_option, parent, false)
            return MainOptionViewHolder(view)
        }

        override fun onBindViewHolder(holder: MainOptionViewHolder?, position: Int) {
            holder?.itemView?.mainOptionTextView?.text = data[position]
            holder?.itemView?.setOnClickListener {
                when(position) {
                    0 -> startActivityWithClass(BreedsActivity::class.java)
                    1 -> requestPhoneNumberPermission()
                }
            }
        }

        override fun getItemCount(): Int {
            return data.size
        }

    }

}
