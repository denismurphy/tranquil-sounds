package me.denismurphy.tranquil.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.interfaces.audioplayer.PlayBehavior;
import com.amazon.ask.request.Predicates;

import java.math.BigDecimal;
import java.util.Optional;

public class ResumeIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(Predicates.intentName("AMAZON.ResumeIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        BigDecimal offset = (BigDecimal) input.getAttributesManager().getPersistentAttributes().get("offset");
        String url = (String) input.getAttributesManager().getPersistentAttributes().get("url");
        input.getAttributesManager().getPersistentAttributes().remove("offset");
        input.getAttributesManager().savePersistentAttributes();
        String newToken = java.util.UUID.randomUUID().toString();
        return input.getResponseBuilder()
                .addAudioPlayerPlayDirective(PlayBehavior.REPLACE_ALL, offset.longValueExact(), null, newToken, url)
                .build();
    }
}