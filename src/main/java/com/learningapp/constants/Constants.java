package com.learningapp.constants;

public final class Constants {

    public interface RESPONSE_STATUS {
        String SUCCESS = "Success";
        String ERROR = "Error";
    }

    public interface MESSAGE_ERROR {
        String NOT_FOUND_ENTITY = "%s with ID %s not found or has been deleted";
        String NO_DATA = "The list of %s has no data";
    }

    public interface MESSAGE_AUTH {
        String NOT_FOUND = "User not found";
        String USER_EXISTS = "Username already exists";
        String INVALID_CREDENTIALS = "Invalid credentials";
    }

    public interface QUERY {
        String PAGE = "page";
        String SIZE = "size";
        String SORT = "sort";
        String PAGE_DEFAULT = "0";
        String SIZE_DEFAULT = "10";
        String UPDATE_ORDER = "updatedAt";
    }

    public interface OPEN_ROUTER {
        String FLASHCARD_COMMAND = """
                 Based on the content of the text above, please help me return 20 flashcards in 
                the following structure be low example: 
                [
                    {
                        "question": "Question",
                        "answer": "Answer"
                     }
                     ,
                                         {
                        "question": "Question",
                        "answer": "Answer"
                     }
                ]
                ; no yapping; response with json format
                """;

        String QUIZ_COMMAND = """
                 Based on the content of the text above, please help me please help me return 20 quizzes in the following structure,correct answer is random:
                [
                    {
                        "question": "Questions ?",
                        "options": "[\\"options1\\",\\"options2\\",\\"options3\\",\\"options4\\"]",
                        "correctAnswer": 0 //1,2,3\s
                    },
                                        {
                        "question": "Questions ?",
                        "options": "[\\"options1\\",\\"options2\\",\\"options3\\",\\"options4\\"]",
                        "correctAnswer": 0 //1,2,3\s
                    }
                ]
                
                 ;Noticed "options" is String not Object; no yapping;only vietnamese; response with json format"
                """;
        String USER_ROLE = "user";
    }

}
