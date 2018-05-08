package me.denismurphy.tranquil.handlers

import com.amazon.ask.dispatcher.request.handler.HandlerInput
import com.amazon.ask.dispatcher.request.handler.RequestHandler
import com.amazon.ask.model.Response
import com.amazon.ask.model.interfaces.audioplayer.PlayBehavior
import com.amazon.ask.request.Predicates

import java.math.BigDecimal
import java.util.Optional

class ResumeIntentHandler : RequestHandler {

    override fun canHandle(input: HandlerInput): Boolean {
        return input.matches(Predicates.intentName("AMAZON.ResumeIntent"))
    }

    override fun handle(input: HandlerInput): Optional<Response> {
        val offset = input.attributesManager.persistentAttributes["offset"] as BigDecimal
        val url = input.attributesManager.persistentAttributes["url"] as String
        input.attributesManager.persistentAttributes.remove("offset")
        input.attributesManager.savePersistentAttributes()
        val newToken = java.util.UUID.randomUUID().toString()
        return input.responseBuilder
                .addAudioPlayerPlayDirective(PlayBehavior.REPLACE_ALL, offset.longValueExact(), null, newToken, url)
                .build()
    }
}