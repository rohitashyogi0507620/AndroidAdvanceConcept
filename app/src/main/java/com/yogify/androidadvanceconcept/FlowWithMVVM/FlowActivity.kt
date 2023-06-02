package com.yogify.androidadvanceconcept.FlowWithMVVM

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.yogify.androidadvanceconcept.databinding.ActivityFlowBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FlowActivity : AppCompatActivity() {

    lateinit var binding: ActivityFlowBinding

    private val mainViewModel: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFlowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel.productResponse.observe(this) {
            when (it) {
                is NetworkResult.Loading -> {
                    binding.progressBar.isVisible = it.isLoading
                }

                is NetworkResult.Failure -> {
                    Toast.makeText(this, it.errorMessage, Toast.LENGTH_SHORT).show()
                    binding.progressBar.isVisible = false
                    binding.textView.setText( it.errorMessage)

                }

                is NetworkResult.Success -> {
                    binding.textView.setText(it.data.toString())
                    binding.progressBar.isVisible = false
                }
            }
        }

    }
}