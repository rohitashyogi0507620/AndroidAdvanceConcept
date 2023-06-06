package com.yogify.androidadvanceconcept.FlowWithMVVM

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import com.yogify.androidadvanceconcept.databinding.ActivityFlowBinding
import com.yogify.androidadvanceconcept.utils.PreferencesKeys.AGE
import com.yogify.androidadvanceconcept.utils.PreferencesKeys.APPLICATION_PREFERENCES
import com.yogify.androidadvanceconcept.utils.PreferencesKeys.NAME
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class FlowActivity : AppCompatActivity() {

    lateinit var binding: ActivityFlowBinding

    private val mainViewModel: MainViewModel by viewModels()

    private val dataStore by preferencesDataStore(name = APPLICATION_PREFERENCES)

    var  count  =0

    lateinit var userNameFlow: Flow<String>
    lateinit var  userAgeFlow: Flow<Int>

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


        binding.btnStoredata.setOnClickListener {
            count++
            lifecycleScope.launch(Dispatchers.IO) {
                storeUserInfo(10, "Rohitash Yogi ${count}")
                Log.d("DATA", "Data Stored")
            }
        }

        binding.btnFetcdata.setOnClickListener {

        }

        userAgeFlow= dataStore.data.map { preferences ->
            preferences[AGE] ?: 0
        }

        userNameFlow = dataStore.data.map { preferences ->
            preferences[NAME] ?: ""
        }

        lifecycleScope.launch(Dispatchers.Main){
            userNameFlow.collect {
                binding.textView.setText(it)

            }
        }




    }

    suspend fun storeUserInfo(age: Int, name: String) {
        dataStore.edit { preferences ->
            preferences[AGE] = age
            preferences[NAME] = name
        }
    }
}