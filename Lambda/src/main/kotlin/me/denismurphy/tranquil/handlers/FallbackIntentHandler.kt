package me.denismurphy.tranquil.handlers

import com.amazon.ask.dispatcher.request.handler.HandlerInput
import com.amazon.ask.dispatcher.request.handler.RequestHandler
import com.amazon.ask.model.Response
import com.amazon.ask.request.Predicates
import java.util.*

class FallbackIntentHandler : RequestHandler {

    override fun canHandle(input: HandlerInput): Boolean {
        return input.matches(Predicates.intentName("AMAZON.FallbackIntent"))
    }

    override fun handle(input: HandlerInput): Optional<Response> {
        val speechText = "Sorry can't do that yet, Tranquil can play the following sounds"
        return input.responseBuilder
                .withSpeech(speechText)
                .withSimpleCard("Tranquil", speechText)
                .withReprompt(speechText)
                .build()
    }
}