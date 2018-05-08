package me.denismurphy.tranquil

import com.amazon.ask.Skill
import com.amazon.ask.SkillStreamHandler
import com.amazon.ask.Skills
import me.denismurphy.tranquil.handlers.*

class TranquilStreamHandler(var skill: Skill) : SkillStreamHandler(skill) {
    init {
        this.skill = Skills.standard()
                .addExceptionHandler(TranquilExecptionHandler())
                .addRequestHandlers(LaunchRequestHandler(),
                        PlaySoundHandler(),
                        CancelIntentHandler(),
                        FallbackIntentHandler(),
                        HelpIntentHandler(),
                        PauseIntentHandler(),
                        ResumeIntentHandler(),
                        StopIntentHandler(),
                        SessionEndedHandler(),
                        ListSoundsIntentHandler())
                .withTableName("tranquil")
                .withAutoCreateTable(true)
                .build()
    }
}