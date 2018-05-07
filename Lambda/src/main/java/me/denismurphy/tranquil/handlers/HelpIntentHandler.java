package me.denismurphy.tranquil.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;

import java.util.Optional;

public class HelpIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(Predicates.intentName("AMAZON.HelpIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText = "You can play the following sounds with Tranquil";
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("Tranquil - Help", speechText)
                .withReprompt(speechText)
                .build();
    }
}