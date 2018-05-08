package me.denismurphy.tranquil.handlers

import com.amazon.ask.dispatcher.request.handler.HandlerInput
import com.amazon.ask.dispatcher.request.handler.RequestHandler
import com.amazon.ask.model.Response
import com.amazon.ask.request.Predicates
import java.util.*

class PauseIntentHandler : RequestHandler {

    override fun canHandle(input: HandlerInput): Boolean {
        return input.matches(Predicates.intentName("AMAZON.PauseIntent"))
    }

    override fun handle(input: HandlerInput): Optional<Response> {
        input.attributesManager.persistentAttributes["offset"] = input.requestEnvelope.context.audioPlayer.offsetInMilliseconds
        input.attributesManager.savePersistentAttributes()

        return input.responseBuilder
                .addAudioPlayerStopDirective()
                .build()
    }
}