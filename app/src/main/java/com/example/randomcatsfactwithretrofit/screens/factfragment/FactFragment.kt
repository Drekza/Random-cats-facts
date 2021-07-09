package com.example.randomcatsfactwithretrofit.screens.factfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.randomcatsfactwithretrofit.R
import com.example.randomcatsfactwithretrofit.data.ApiRequests
import com.example.randomcatsfactwithretrofit.databinding.FragmentFactBinding
import com.example.randomcatsfactwithretrofit.utils.BASE_URL
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception


class FactFragment : Fragment() {

    private var _binding: FragmentFactBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFactBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        initialization()
    }

    private fun initialization() {

        getCurrentData()

        binding.layoutGenerateNewFact.setOnClickListener {
            getCurrentData()
        }
    }

    private fun getCurrentData() {
        binding.factTextView.visibility = View.INVISIBLE
        binding.factTimeStampTextView.visibility = View.INVISIBLE
        binding.progressBar.visibility = View.VISIBLE


        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiRequests::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            try{
                val response = api.getCatFact().awaitResponse()
                if(response.isSuccessful){
                    val data = response.body()

                    withContext(Dispatchers.Main){
                        binding.factTextView.visibility = View.VISIBLE
                        binding.factTimeStampTextView.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE

                        binding.factTextView.text = data?.text
                        binding.factTimeStampTextView.text = data?.createdAt
                    }
                }
            } catch (ex: Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}