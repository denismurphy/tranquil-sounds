package me.denismurphy.tranquil.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;

import java.util.Optional;

public class PauseIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(Predicates.intentName("AMAZON.PauseIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        input.getAttributesManager().getPersistentAttributes().put("offset", input.getRequestEnvelope().getContext().getAudioPlayer().getOffsetInMilliseconds());
        input.getAttributesManager().savePersistentAttributes();

        return input.getResponseBuilder()
                .addAudioPlayerStopDirective()
                .build();
    }
}