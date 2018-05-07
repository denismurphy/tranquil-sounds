package me.denismurphy.tranquil.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.amazon.ask.model.interfaces.audioplayer.PlayBehavior;
import com.amazon.ask.request.Predicates;
import com.amazon.ask.model.IntentRequest;

import java.util.Optional;

public class PlaySoundHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(Predicates.intentName("PlaySound"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {

        String url = "";

        IntentRequest intentRequest = (IntentRequest) input.getRequestEnvelope().getRequest();

        getUrl: for (Slot slot : intentRequest.getIntent().getSlots().values()) {

            if (slot.getValue() == null) {
                return input.getResponseBuilder().build();
            }

            url = getAudio(slot.getValue().toLowerCase());
            break getUrl;
        }

        String token = java.util.UUID.randomUUID().toString();
        input.getAttributesManager().getPersistentAttributes().put("url", url);
        input.getAttributesManager().savePersistentAttributes();

        return input.getResponseBuilder()
                .addAudioPlayerPlayDirective(PlayBehavior.REPLACE_ALL, 0L, null, token, url)
                .build();
    }

    private static String getAudio(String slotValue) {
        switch (slotValue) {
            case "irish":
            case "celtic":
                return "https://s3-eu-west-1.amazonaws.com/tranquil-sounds/Celtic.mp3";
            case "calming":
            case "calm":
            case "relaxing":
                return "https://s3-eu-west-1.amazonaws.com/tranquil-sounds/Calm.mp3";
            case "crackling":
            case "crackling fire":
            case "fire":
            case "fireplace":
                return "https://s3-eu-west-1.amazonaws.com/tranquil-sounds/Fire.mp3";
            case "harp":
                return "https://s3-eu-west-1.amazonaws.com/tranquil-sounds/Harp.mp3";
            case "meditation":
            case "yoga":
                return "https://s3-eu-west-1.amazonaws.com/tranquil-sounds/Meditation.mp3";
            case "birds song":
            case "birds singing":
            case "forest":
            case "nature":
                return "https://s3-eu-west-1.amazonaws.com/tranquil-sounds/Nature.mp3";
            case "raining":
            case "rain":
                return "https://s3-eu-west-1.amazonaws.com/tranquil-sounds/Rain.mp3";
            case "space":
            case "interstellar":
            case "galaxy":
            case "universe":
                return "https://s3-eu-west-1.amazonaws.com/tranquil-sounds/Space.mp3";
            case "whale":
            case "whales":
            case "underwater":
            case "deep underwater":
            case "deep sea":
            case "sea":
                return "https://s3-eu-west-1.amazonaws.com/tranquil-sounds/Whale.mp3";
            case "chimes":
            case "wind chimes":
               return "https://s3-eu-west-1.amazonaws.com/tranquil-sounds/Chimes.mp3";
            case "snowstorm":
            case "blizzard":
            case "arctic":
                return "https://s3-eu-west-1.amazonaws.com/tranquil-sounds/Snowstorm.mp3";
            case "piano":
                return "https://s3-eu-west-1.amazonaws.com/tranquil-sounds/Piano.mp3";

            //By default it go to random if not listed or someone say 'random' or 'shuffle'
            case "random":
            case "shuffle":
            default:
                return getRandom();
        }
    }

    private static String getRandom() {
        int random = getRandomInt(1, 12);
        switch (random) {
            case 1:
                return "https://s3-eu-west-1.amazonaws.com/tranquil-sounds/Celtic.mp3";
            case 2:
                return "https://s3-eu-west-1.amazonaws.com/tranquil-sounds/Calm.mp3";
            case 3:
                return "https://s3-eu-west-1.amazonaws.com/tranquil-sounds/Fire.mp3";
            case 4:
                return "https://s3-eu-west-1.amazonaws.com/tranquil-sounds/Harp.mp3";
            default:
            case 5:
                return "https://s3-eu-west-1.amazonaws.com/tranquil-sounds/Meditation.mp3";
            case 6:
                return "https://s3-eu-west-1.amazonaws.com/tranquil-sounds/Nature.mp3";
            case 7:
                return "https://s3-eu-west-1.amazonaws.com/tranquil-sounds/Rain.mp3";
            case 8:
                return "https://s3-eu-west-1.amazonaws.com/tranquil-sounds/Space.mp3";
            case 9:
                return "https://s3-eu-west-1.amazonaws.com/tranquil-sounds/Chimes.mp3";
            case 10:
                return "https://s3-eu-west-1.amazonaws.com/tranquil-sounds/Snowstorm.mp3";
            case 11:
                return "https://s3-eu-west-1.amazonaws.com/tranquil-sounds/Whale.mp3";
            case 12:
                return "https://s3-eu-west-1.amazonaws.com/tranquil-sounds/Piano.mp3";
        }
    }

    private static int getRandomInt(int maximum, int minimum) {
        return ((int) (Math.random() * (maximum - minimum))) + minimum;
    }

}