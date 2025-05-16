package com.learningapp.constants;

public final class CoreConstants {

    public interface RESPONSE_STATUS {
        String SUCCESS = "Success";
        String ERROR = "Error";
    }

    public interface OBJECTS_NAME {
        String FLASHCARD = "Flashcard";
        String QUIZ = "Quiz";
        String QUIZ_RESULT = "Quiz Result";
        String STUDY_MODULE = "Study Module";
    }

    public interface MESSAGE_ERROR {
        String NOT_FOUND_ENTITY = "%s with ID %s not found or has been deleted";
    }
}
