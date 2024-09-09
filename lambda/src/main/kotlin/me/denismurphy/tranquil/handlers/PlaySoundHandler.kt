package me.denismurphy.tranquil.handlers

import com.amazon.ask.dispatcher.request.handler.HandlerInput
import com.amazon.ask.dispatcher.request.handler.RequestHandler
import com.amazon.ask.model.IntentRequest
import com.amazon.ask.model.Response
import com.amazon.ask.model.interfaces.audioplayer.PlayBehavior
import com.amazon.ask.request.Predicates
import java.util.Optional
import java.util.UUID

class PlaySoundHandler : RequestHandler {

    private val random: String
        get() {
            val random = getRandomInt(1, 12)
            return when (random) {
                1 -> "https://s3-eu-west-1.amazonaws.com/tranquil-sounds/Celtic.mp3"
                2 -> "https://s3-eu-west-1.amazonaws.com/tranquil-sounds/Calm.mp3"
                3 -> "https://s3-eu-west-1.amazonaws.com/tranquil-sounds/Fire.mp3"
                4 -> "https://s3-eu-west-1.amazonaws.com/tranquil-sounds/Harp.mp3"
                5 -> "https://s3-eu-west-1.amazonaws.com/tranquil-sounds/Meditation.mp3"
                6 -> "https://s3-eu-west-1.amazonaws.com/tranquil-sounds/Nature.mp3"
                7 -> "https://s3-eu-west-1.amazonaws.com/tranquil-sounds/Rain.mp3"
                8 -> "https://s3-eu-west-1.amazonaws.com/tranquil-sounds/Space.mp3"
                9 -> "https://s3-eu-west-1.amazonaws.com/tranquil-sounds/Chimes.mp3"
                10 -> "https://s3-eu-west-1.amazonaws.com/tranquil-sounds/Snowstorm.mp3"
                11 -> "https://s3-eu-west-1.amazonaws.com/tranquil-sounds/Whale.mp3"
                12 -> "https://s3-eu-west-1.amazonaws.com/tranquil-sounds/Piano.mp3"
                else -> "https://s3-eu-west-1.amazonaws.com/tranquil-sounds/Meditation.mp3"
            }
        }

    override fun canHandle(input: HandlerInput): Boolean {
        return input.matches(Predicates.intentName("PlaySound"))
    }

    override fun handle(input: HandlerInput): Optional<Response> {

        var url = ""

        val intentRequest = input.requestEnvelope.request as IntentRequest

        getUrl@ for (slot in intentRequest.intent.slots.values) {

            if (slot.value == null) {
                return input.responseBuilder.build()
            }

            url = getAudio(slot.value.toLowerCase())
            break@getUrl
        }

        val token = UUID.randomUUID().toString()
        input.attributesManager.persistentAttributes["url"] = url
        input.attributesManager.savePersistentAttributes()

        return input.responseBuilder
                .addAudioPlayerPlayDirective(PlayBehavior.REPLACE_ALL, 0L, null, token, url)
                .build()
    }

    private fun getAudio(slotValue: String): String {
        when (slotValue) {
            "irish",
            "celtic" ->
                return "https://s3-eu-west-1.amazonaws.com/tranquil-sounds/Celtic.mp3"
            "calming",
            "calm",
            "relaxing" ->
                return "https://s3-eu-west-1.amazonaws.com/tranquil-sounds/Calm.mp3"
            "crackling",
            "crackling fire",
            "fire",
            "fireplace" ->
                return "https://s3-eu-west-1.amazonaws.com/tranquil-sounds/Fire.mp3"
            "harp" ->
                return "https://s3-eu-west-1.amazonaws.com/tranquil-sounds/Harp.mp3"
            "meditation",
            "yoga" ->
                return "https://s3-eu-west-1.amazonaws.com/tranquil-sounds/Meditation.mp3"
            "birds song",
            "birds singing",
            "forest",
            "nature" ->
                return "https://s3-eu-west-1.amazonaws.com/tranquil-sounds/Nature.mp3"
            "raining",
            "rain" ->
                return "https://s3-eu-west-1.amazonaws.com/tranquil-sounds/Rain.mp3"
            "space",
            "interstellar",
            "galaxy",
            "universe" ->
                return "https://s3-eu-west-1.amazonaws.com/tranquil-sounds/Space.mp3"
            "whale",
            "whales",
            "underwater",
            "deep underwater",
            "deep sea",
            "sea" ->
                return "https://s3-eu-west-1.amazonaws.com/tranquil-sounds/Whale.mp3"
            "chimes",
            "wind chimes" ->
                return "https://s3-eu-west-1.amazonaws.com/tranquil-sounds/Chimes.mp3"
            "snowstorm",
            "blizzard",
            "arctic" ->
                return "https://s3-eu-west-1.amazonaws.com/tranquil-sounds/Snowstorm.mp3"
            "piano" ->
                return "https://s3-eu-west-1.amazonaws.com/tranquil-sounds/Piano.mp3"

        //By default, it goes to random if not listed or someone say 'random' or 'shuffle'
            "random",
            "shuffle" ->
                return random
            else ->
                return random
        }
    }

    private fun getRandomInt(maximum: Int, minimum: Int): Int {
        return (Math.random() * (maximum - minimum)).toInt() + minimum
    }

}