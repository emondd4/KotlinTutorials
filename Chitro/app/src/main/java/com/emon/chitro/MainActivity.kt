package com.emon.chitro

import android.Manifest
import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.emon.chitro.Adapter.PhotoAdapter
import com.emon.chitro.ModelClass.PhotosModelClass
import com.emon.chitro.ModelClass.TotalResponse
import com.emon.chitro.Retrofit.DataRequestInterface
import com.fivemin.chief.nonetworklibrary.networkBroadcast.NoNet
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {

    private var fm: FragmentManager? = null
    private var mNoNet: NoNet? = null

    lateinit var searchText: EditText
    lateinit var orderSpinner: Spinner
    lateinit var categoriesSpinner: Spinner
    lateinit var search_btn: Button
    lateinit var next_btn: FloatingActionButton
    lateinit var recyclerView: RecyclerView

    private var text_searchText: String = " "
    private var text_order: String = " "
    private var text_categories: String = " "
    private var text_page: String = " "
    private var pageNo: Int = 1

    private var photoAdapter: PhotoAdapter? = null

    private val TAG = "Response on wtf"

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchText = findViewById(R.id.searchText)
        orderSpinner = findViewById(R.id.order)
        categoriesSpinner = findViewById(R.id.categories)
        search_btn = findViewById(R.id.search_btn)
        next_btn = findViewById(R.id.next_btn)

        recyclerView = findViewById(R.id.recycler)
        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity,LinearLayout.VERTICAL,false)

        //check internet connection
        fm = supportFragmentManager
        mNoNet = NoNet()
        mNoNet!!.initNoNet(this, fm)


        //set spinner data
        val categoryAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
            this@MainActivity,
            R.layout.spinner_item,
            resources.getStringArray(R.array.category)
        )

        val orderAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
            this@MainActivity,
            R.layout.spinner_item,
            resources.getStringArray(R.array.order)
        )

        categoriesSpinner.adapter = categoryAdapter
        orderSpinner.adapter = orderAdapter

        onClick()

        permission()

    }

    private fun onClick() {

        //category spinner listener
//        categoriesSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onNothingSelected(parent: AdapterView<*>?) {
//
//            }
//
//            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                if (parent != null) {
//                    Toast.makeText(this@MainActivity, ""+parent.selectedItem, Toast.LENGTH_SHORT).show()
//                }
//            }
//        }

        //order spinner listener
//        orderSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
//            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long
//            ) {
//                if (parent != null) {
//                    Toast.makeText(this@MainActivity, ""+parent.selectedItem, Toast.LENGTH_SHORT).show()
//                }
//            }
//
//            override fun onNothingSelected(parent: AdapterView<*>?) {
//
//            }
//        }

        //SearchButton spinner listener
        search_btn.setOnClickListener {
            fetchData()
        }

        next_btn.setOnClickListener {
            pageNo += pageNo
            fetchData()
        }

    }

    private fun fetchData() {

        text_searchText = searchText.text.toString()
        text_categories = categoriesSpinner.selectedItem.toString().toLowerCase(Locale.ROOT)
        text_order = orderSpinner.selectedItem.toString().toLowerCase(Locale.ROOT)
        text_page = pageNo.toString()


        val retrofit = Retrofit.Builder()
                .baseUrl("https://pixabay.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val dataRequestInterface: DataRequestInterface = retrofit.create(DataRequestInterface::class.java)
        val params = HashMap<String, String>()
        params["key"] =  "20443593-541b1c5272a51222795377377"
        params["q"] = text_searchText
        params["image_type"] = "all"
        params["orientation"] = "all"
        params["category"] = text_categories
        params["order"] = text_order
        params["page"] = text_page

        val call: Call<TotalResponse?>? = dataRequestInterface.getPosts(params)

        call?.enqueue(object : Callback<TotalResponse?> {
            override fun onResponse(call: Call<TotalResponse?>, response: Response<TotalResponse?>) {

                Log.wtf(TAG, Gson().toJson(response.body()))



                val photoList: ArrayList<PhotosModelClass?>? = response.body()?.hits
                photoAdapter = PhotoAdapter(photoList, this@MainActivity)
                recyclerView.adapter = photoAdapter

            }

            override fun onFailure(call: Call<TotalResponse?>, t: Throwable) {
                val myDialog = ProgressDialog(this@MainActivity)
                myDialog.setMessage(t.message)
                myDialog.setCancelable(false)
                myDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Dismiss") { dialog, which ->
                    myDialog.dismiss()
                }
                myDialog.show()
            }
        })

    }

    private fun permission() {
        Dexter.withContext(this)
                .withPermissions(
                    Manifest.permission.INTERNET,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ).withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                    if (report.areAllPermissionsGranted()) {

                    } else {
                        permission()
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    p0: MutableList<com.karumi.dexter.listener.PermissionRequest>?,
                    p1: PermissionToken?
                ) {
                    p1?.continuePermissionRequest()
                }
            }).check()
    }

    override fun onPause() {
        mNoNet!!.unRegisterNoNet();
        super.onPause()
    }

    override fun onResume() {
        mNoNet!!.RegisterNoNet()
        super.onResume()
    }
}
