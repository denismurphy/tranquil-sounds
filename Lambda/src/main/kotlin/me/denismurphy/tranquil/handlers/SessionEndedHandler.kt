package me.denismurphy.tranquil.handlers

import com.amazon.ask.dispatcher.request.handler.HandlerInput
import com.amazon.ask.dispatcher.request.handler.RequestHandler
import com.amazon.ask.model.Response
import com.amazon.ask.model.SessionEndedRequest

import java.util.Optional

import com.amazon.ask.request.Predicates.requestType

class SessionEndedHandler : RequestHandler {

    override fun canHandle(input: HandlerInput): Boolean {
        return input.matches(requestType(SessionEndedRequest::class.java))
    }

    override fun handle(input: HandlerInput): Optional<Response> {
        input.attributesManager.persistentAttributes.remove("url")
        input.attributesManager.persistentAttributes.remove("offset")
        input.attributesManager.savePersistentAttributes()
        return input.responseBuilder.build()
    }

}