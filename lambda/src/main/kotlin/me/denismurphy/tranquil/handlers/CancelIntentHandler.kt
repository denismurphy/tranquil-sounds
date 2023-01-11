package me.denismurphy.tranquil.handlers

import com.amazon.ask.dispatcher.request.handler.HandlerInput
import com.amazon.ask.dispatcher.request.handler.RequestHandler
import com.amazon.ask.model.Response
import com.amazon.ask.request.Predicates
import java.util.*

class CancelIntentHandler : RequestHandler {

    override fun canHandle(input: HandlerInput): Boolean {
        return input.matches(Predicates.intentName("AMAZON.CancelIntent"))
    }

    override fun handle(input: HandlerInput): Optional<Response> {
        input.attributesManager.persistentAttributes.remove("url")
        input.attributesManager.persistentAttributes.remove("offset")
        input.attributesManager.savePersistentAttributes()
        return input
                .responseBuilder
                .addAudioPlayerStopDirective()
                .withShouldEndSession(true)
                .build()
    }
}