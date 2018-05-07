package me.denismurphy.tranquil.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;

import java.util.Optional;

public class FallbackIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(Predicates.intentName("AMAZON.FallbackIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText = "Sorry can't do that yet, Tranquil can play the following sounds";
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("Tranquil", speechText)
                .withReprompt(speechText)
                .build();
    }
}