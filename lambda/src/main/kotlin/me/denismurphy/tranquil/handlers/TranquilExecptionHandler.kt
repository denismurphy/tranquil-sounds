package me.denismurphy.tranquil.handlers

import com.amazon.ask.dispatcher.exception.ExceptionHandler
import com.amazon.ask.dispatcher.request.handler.HandlerInput
import com.amazon.ask.exception.AskSdkException
import com.amazon.ask.model.Response
import org.slf4j.LoggerFactory.getLogger
import java.util.*

class TranquilExecptionHandler : ExceptionHandler {

    override fun canHandle(input: HandlerInput, throwable: Throwable): Boolean {
        return throwable is AskSdkException
    }

    override fun handle(input: HandlerInput, throwable: Throwable): Optional<Response> {
        val request = input.requestEnvelope.request
        LOGGER.error("Request: $request")
        LOGGER.error("Stacktrace", throwable)
        return input.responseBuilder
                .withShouldEndSession(true)
                .build()
    }

    companion object {
        private val LOGGER = getLogger(TranquilExecptionHandler::class.java)
    }
}