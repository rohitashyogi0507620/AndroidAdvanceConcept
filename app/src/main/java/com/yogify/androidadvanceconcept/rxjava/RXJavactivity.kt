package com.yogify.androidadvanceconcept.rxjava

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jakewharton.rxbinding4.view.clicks
import com.yogify.androidadvanceconcept.FlowWithMVVM.Product
import com.yogify.androidadvanceconcept.databinding.ActivityRxjavactivityBinding
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class RXJavactivity : AppCompatActivity() {
    // Observable , Oprater , Observer Three Main Component in rxjava

    private lateinit var binding: ActivityRxjavactivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRxjavactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // simpleObserver()
        createObservalbe()


        //Rx binding for perform clicks into observables

        binding.onClickRxjava.clicks().throttleFirst(1500, TimeUnit.MILLISECONDS).subscribe {
            Log.d("BUTTONCLICK", "Button click")
        }

        // subscribeOn is where task will perform and observeOn where data is observe
        RetrofitService.getInstance().getProduct().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe(object : Observer<List<Product>> {
                override fun onSubscribe(d: Disposable) {
                    binding.progressBar.visibility = View.VISIBLE
                }

                override fun onNext(product: List<Product>) {
                    Log.d("DATA", product.toString())
                }

                override fun onError(e: Throwable) {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(applicationContext, e.message.toString(), Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onComplete() {
                    binding.progressBar.visibility = View.GONE
                }

            })

    }

    private fun createObservalbe() {
        val observable = Observable.create<String> {
            it.onNext("Rohit")
            it.onNext("Seema")
            it.onNext("Ajay")
            it.onError(IllegalArgumentException("Error from Server"))
            it.onNext("TATA")
            it.onComplete()
        }


        observable.subscribe(object : Observer<String> {
            override fun onSubscribe(d: Disposable) {
                Log.d("DATA", "Subscribe")
            }

            override fun onNext(t: String) {
                Log.d("DATA", t)
            }

            override fun onError(e: Throwable) {
                Log.d("DATA", e.message.toString())
            }

            override fun onComplete() {
                Log.d("DATA", "Complete")
            }

        })
    }

    private fun simpleObserver() {

        // Basic Process

        var list = listOf<String>("Rohitahsh", "Pooja", "Seema")

        val obserable = Observable.fromIterable(list)

        obserable.subscribe(object : Observer<String> {
            override fun onSubscribe(d: Disposable) {
                Log.d("DATA", "Subscribe")
            }

            override fun onNext(t: String) {
                Log.d("DATA", t)
            }

            override fun onError(e: Throwable) {
                Log.d("DATA", e.message.toString())
            }

            override fun onComplete() {
                Log.d("DATA", "Complete")
            }

        })
    }
}