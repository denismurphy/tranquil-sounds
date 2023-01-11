package me.denismurphy.tranquil;

import com.amazon.ask.Skill;
import com.amazon.ask.Skills;
import com.amazon.ask.SkillStreamHandler;
import me.denismurphy.tranquil.handlers.*;

public class TranquilStreamHandler extends SkillStreamHandler {

    private static Skill getSkill() {
        return Skills.standard()
                .addExceptionHandler(new TranquilExecptionHandler())
                .addRequestHandlers(new LaunchRequestHandler(),
                        new PlaySoundHandler(),
                        new CancelIntentHandler(),
                        new FallbackIntentHandler(),
                        new HelpIntentHandler(),
                        new PauseIntentHandler(),
                        new ResumeIntentHandler(),
                        new StopIntentHandler(),
                        new SessionEndedHandler(),
                        new ListSoundsIntentHandler())
                .withTableName("tranquil")
                .withAutoCreateTable(true)
                .build();
    }

    public TranquilStreamHandler() {
        super(getSkill());
    }

}