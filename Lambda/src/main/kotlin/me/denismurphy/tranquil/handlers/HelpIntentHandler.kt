package me.denismurphy.tranquil.handlers

import com.amazon.ask.dispatcher.request.handler.HandlerInput
import com.amazon.ask.dispatcher.request.handler.RequestHandler
import com.amazon.ask.model.Response
import com.amazon.ask.request.Predicates
import java.util.*

class HelpIntentHandler : RequestHandler {

    override fun canHandle(input: HandlerInput): Boolean {
        return input.matches(Predicates.intentName("AMAZON.HelpIntent"))
    }

    override fun handle(input: HandlerInput): Optional<Response> {
        val speechText = "You can play the following sounds with Tranquil"
        return input.responseBuilder
                .withSpeech(speechText)
                .withSimpleCard("Tranquil - Help", speechText)
                .withReprompt(speechText)
                .build()
    }
}