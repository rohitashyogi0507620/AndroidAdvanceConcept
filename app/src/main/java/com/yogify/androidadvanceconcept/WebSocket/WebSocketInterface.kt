package com.yogify.androidadvanceconcept.WebSocket

import com.tinder.scarlet.WebSocket
import com.tinder.scarlet.ws.Receive
import com.tinder.scarlet.ws.Send
import io.reactivex.Flowable

interface WebSocketInterface {

    @Receive
    fun observeConnection(): Flowable<WebSocket.Event>

    @Send
    fun sendMessage(param: String)

}