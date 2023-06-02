package com.yogify.androidadvanceconcept.WebSocket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tinder.scarlet.Scarlet
import com.yogify.androidadvanceconcept.databinding.ActivityWebSocketBinding
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

@AndroidEntryPoint
class WebSocketActivity : AppCompatActivity() {
    lateinit var binding: ActivityWebSocketBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityWebSocketBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val scarletInstance = Scarlet.Builder()
//            .webSocketFactory(okHttpClient.newWebSocketFactory("wss://ws-feed.gdax.com"))
//            .addMessageAdapterFactory(MoshiMessageAdapter.Factory())
//            .addStreamAdapterFactory(RxJava2CallAdapterFactory())
//            .build()
//
//        val gdaxService = scarletInstance.create<WebSocketInterface>()
//
//
//        val BITCOIN_TICKER_SUBSCRIBE_MESSAGE = Subscribe(
//            productIds = listOf("BTC-USD"),
//            channels = listOf("ticker")
//        )
//
//        gdaxService.observeWebSocketEvent()
//            .filter { it is WebSocket.Event.OnConnectionOpened<*> }
//            .subscribe({
//                gdaxService.sendSubscribe(BITCOIN_TICKER_SUBSCRIBE_MESSAGE)
//            })
//
//        gdaxService.observeTicker()
//            .subscribe({ ticker ->
//                Log.d("Bitcoin price is ${ticker.price} at ${ticker.time}")
//            })


    }
}