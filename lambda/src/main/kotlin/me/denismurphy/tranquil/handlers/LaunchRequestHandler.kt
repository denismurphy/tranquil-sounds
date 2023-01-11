package me.denismurphy.tranquil.handlers

import com.amazon.ask.dispatcher.request.handler.HandlerInput
import com.amazon.ask.dispatcher.request.handler.RequestHandler
import com.amazon.ask.model.LaunchRequest
import com.amazon.ask.model.Response
import com.amazon.ask.request.Predicates
import java.util.*

class LaunchRequestHandler : RequestHandler {

    override fun canHandle(input: HandlerInput): Boolean {
        return input.matches(Predicates.requestType(LaunchRequest::class.java))
    }

    override fun handle(input: HandlerInput): Optional<Response> {

        val speechText = "<speak>\n" +
                "Welcome to Tranquil Sounds"+
                "<break time=\"200ms\"/>\n" +
                "Play relaxing music from a variety of genres<break time=\"200ms\"/>\n" +
                "<prosody volume=\"x-loud\">What genre would you like to play?</prosody>\n" +
                "</speak>"

        return input.responseBuilder
                .withSpeech(speechText)
                .withSimpleCard("Welcome to Tranquil", "The following genres are supported :\n" +
                        "•   Irish\n" +
                        "•   Celtic\n" +
                        "•   Calming\n" +
                        "•   Relaxing\n" +
                        "•   Crackling\n" +
                        "•   Crackling Fire\n" +
                        "•   Fire\n" +
                        "•   Fireplace\n" +
                        "•   Harp\n" +
                        "•   Meditation\n" +
                        "•   Yoga\n" +
                        "•   Bird song\n" +
                        "•   Birds singing\n" +
                        "•   Forest\n" +
                        "•   Nature\n" +
                        "•   Raining\n" +
                        "•   Rain\n" +
                        "•   Space\n" +
                        "•   Interstellar\n" +
                        "•   Galaxy\n" +
                        "•   Universe\n" +
                        "•   Whale\n" +
                        "•   Whales\n" +
                        "•   Underwater\n" +
                        "•   Deep Underwater\n" +
                        "•   Deep Sea\n" +
                        "•   Sea\n" +
                        "•   Chimes\n" +
                        "•   Wind Chimes\n" +
                        "•   Snowstorm\n" +
                        "•   Blizzard\n" +
                        "•   Arctic\n" +
                        "•   Piano\n")
                .withReprompt("What genre would you like to play?")
                .build()
    }

}