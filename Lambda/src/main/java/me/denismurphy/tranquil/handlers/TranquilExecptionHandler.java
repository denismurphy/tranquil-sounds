package me.denismurphy.tranquil.handlers;

import com.amazon.ask.dispatcher.exception.ExceptionHandler;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import org.slf4j.Logger;
import com.amazon.ask.model.Request;
import com.amazon.ask.exception.AskSdkException;

import java.util.Optional;

import static org.slf4j.LoggerFactory.getLogger;

public class TranquilExecptionHandler implements ExceptionHandler {

    private static Logger LOGGER = getLogger(TranquilExecptionHandler.class);

    @Override
    public boolean canHandle(HandlerInput input, Throwable throwable) {
        return throwable instanceof AskSdkException;
    }

    @Override
    public Optional<Response> handle(HandlerInput input, Throwable throwable) {
        Request request = input.getRequestEnvelope().getRequest();
        LOGGER.error("Request: " + request.toString());
        LOGGER.error("Stacktrace", throwable);
        return input.getResponseBuilder()
                .withShouldEndSession(true)
                .build();
    }
}