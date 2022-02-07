package com.emon.mvvmapicallfinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.emon.mvvmapicallfinal.Adapter.LayoutAdapter
import com.emon.mvvmapicallfinal.Retrofit.Status
import com.emon.mvvmapicallfinal.Utils.CustomDialog
import com.emon.mvvmapicallfinal.ViewModel.ActivityViewModel
import com.emon.mvvmapicallfinal.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    //create a view binding variable
    private lateinit var binding: ActivityMainBinding

    //create instance of activity viewmodel
    private lateinit var viewModel: ActivityViewModel

    private lateinit var layoutManager: GridLayoutManager
    private lateinit var layoutAdapter: LayoutAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //instantiate view binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //instantiate Custom Loader
        CustomDialog.init(this)

        //instantiate viewModel
        viewModel = ViewModelProvider(this)[ActivityViewModel::class.java]

        //Since flow run asynchronously, start listening on background thread
        lifecycleScope.launch {

            /**
             * Collect the data emitted in CommentApiState helper class,
             * It may contain error,success or loading state.
             * Update the UI according to the State received
             */
            viewModel.photosState.collect {

                //When state to check the state of received data
                when (it.status) {

                    //If its loading state then show the progress bar
                    Status.LOADING -> {
                        CustomDialog.show()
                    }
                    //If api call was a success , Update the Ui with data and make progress bar invisible
                    Status.SUCCESS -> {
                        CustomDialog.dismiss()
                        it.data?.let {
                            layoutManager = GridLayoutManager(this@MainActivity, 2)
                            layoutAdapter = LayoutAdapter(it)
                            binding.recyclerView.layoutManager = layoutManager
                            binding.recyclerView.adapter = layoutAdapter
                        }
                    }
                    //In case of error, show some data to user
                    else -> {
                        CustomDialog.dismiss()
                        Toast.makeText(this@MainActivity, "${it.message}", Toast.LENGTH_SHORT)
                            .show()
                    }
                }


            }

        }

    }
}