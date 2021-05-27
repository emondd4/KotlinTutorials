package com.emon.chipskotlin


import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import com.bdtask.limarket.ModelClass.SearchProductModel.SearchProductModel
import com.emon.chipskotlin.SearchProductModel.SearchProductCategory
import com.emon.chipskotlin.Utils.ApiClient
import com.emon.chipskotlin.Utils.DataRequestInterface
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.gson.Gson
import dmax.dialog.SpotsDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    lateinit var dialog: android.app.AlertDialog
    private var dataRequestInterface: DataRequestInterface? = null
    lateinit var chipGroup: ChipGroup
    private val TAG = "Product Searching"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dataRequestInterface = ApiClient.getRetrofit(this)
            .create(DataRequestInterface::class.java)

        chipGroup = findViewById(R.id.chipGroup)

        chipGroup.setOnCheckedChangeListener{group,checkedId:Int ->
            // Get the checked chip instance from chip group
            val chip:Chip? = findViewById(checkedId)

            chip?.let {
                // Show the checked chip text on toast message
                Toast.makeText(this,"Checked ChipGroup",Toast.LENGTH_SHORT).show()
            }
        }

        getSearchProduct()
    }

    private fun getSearchProduct() {

        dialog = SpotsDialog.Builder().setContext(this)
            .setTheme(R.style.Custom)
            .setCancelable(false)
            .build()
            .apply {
                show()
            }

        val call: Call<SearchProductModel?>? = dataRequestInterface?.searchProductList(
            "shirt",
            this.resources?.getString(R.string.key)!!,
            "230",
            1,
            "",
            "",
            "asc"
        )

        call?.enqueue(object : Callback<SearchProductModel?> {
            override fun onResponse(call: Call<SearchProductModel?>, response: Response<SearchProductModel?>) {
                Log.wtf(TAG, Gson().toJson(response.body()))

                val categoryData: ArrayList<SearchProductCategory?> = response.body()?.category_list!!

                for (i in categoryData.indices){
                    //println(categoryData[i]?.category_name!!)
                    if (categoryData[i]?.category_name != null){
                        addChip(categoryData[i]?.category_name!!,chipGroup)
                    }
                }

                dialog.dismiss()
            }

            override fun onFailure(
                call: Call<SearchProductModel?>, t: Throwable
            ) {
                Log.wtf(TAG, "onFailure: SearchProduct Data: ", t)
            }
        })
    }

    private fun addChip(pItem: String, pChipGroup: ChipGroup) {
        val lChip = Chip(this)

        lChip.text = pItem
        lChip.setTextColor(resources.getColor(R.color.white))
        //lChip.chipBackgroundColor = AppCompatResources.getColorStateList(this,R.color.chip_bg)

        lChip.setOnClickListener {
            if (!lChip.isChecked){
                Toast.makeText(this,"Checked",Toast.LENGTH_SHORT).show()
                lChip.isChecked = true
                lChip.chipBackgroundColor = AppCompatResources.getColorStateList(this,R.color.chip_bg)
            }else{
                Toast.makeText(this,"Again Checked",Toast.LENGTH_SHORT).show()
                lChip.isChecked = false
                lChip.chipBackgroundColor = AppCompatResources.getColorStateList(this,R.color.chip_bg)
            }

        }
        pChipGroup.addView(lChip, pChipGroup.childCount - 1)
    }
}