package me.denismurphy.tranquil.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;

import java.util.Optional;

public class ListSoundsIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {

        return input.matches(Predicates.intentName("ListSounds"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText = "<speak>\n" +
                "    <prosody volume=\"x-loud\">The following genres are supported</prosody>\n" +
                "    <break time=\"1s\"/>\n" +
                "Celtic Music\n" +
                "<break time=\"200ms\"/>\n" +
                "Relaxing Sounds\n" +
                "<break time=\"200ms\"/>\n" +
                "Crackling Fire\n" +
                "<break time=\"200ms\"/>\n" +
                "Harp Music\n" +
                "<break time=\"200ms\"/>\n" +
                "Meditation Music\n" +
                "<break time=\"200ms\"/>\n" +
                "Bird song\n" +
                "<break time=\"200ms\"/>\n" +
                "Raining\n" +
                "<break time=\"200ms\"/>\n" +
                "<emphasis level=\"strong\">Space!</emphasis> \n" +
                "<break time=\"200ms\"/>\n" +
                "Whales Sounds\n" +
                "<break time=\"200ms\"/>\n" +
                "Chimes\n" +
                "<break time=\"200ms\"/>\n" +
                "Snowstorm\n" +
                "<break time=\"200ms\"/>\n" +
                "Piano\n" +
                "</speak>";
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("Tranquil - List Music", "The following genres are supported :\n" +
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
                        "•   Piano")
                .withReprompt(speechText)
                .build();
    }
}