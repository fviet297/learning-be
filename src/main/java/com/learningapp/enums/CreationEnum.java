package com.learningapp.enums;

import com.learningapp.constants.Constants;

public enum CreationEnum {

    FLASHCARD("FLASHCARD", Constants.OPEN_ROUTER.FLASHCARD_COMMAND),
    QUIZZ("QUIZZ", Constants.OPEN_ROUTER.QUIZ_COMMAND);

    private String type;
    private String command;

    CreationEnum(String type, String command) {
        this.type = type;
        this.command = command;
    }

    public String getType() {
        return type;
    }

    public String getCommand() {
        return command;
    }
}
