package me.denismurphy.tranquil.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;

import java.util.Optional;

public class StopIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(Predicates.intentName("AMAZON.StopIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        input.getAttributesManager().getPersistentAttributes().remove("url");
        input.getAttributesManager().getPersistentAttributes().remove("offset");
        input.getAttributesManager().savePersistentAttributes();

        return input.getResponseBuilder()
                .addAudioPlayerStopDirective()
                .withShouldEndSession(true)
                .build();
    }
}