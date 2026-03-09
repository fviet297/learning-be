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
                 Dựa trên nội dung văn bản cung cấp, hãy giúp tôi tạo 10 flashcards.
                 Trả về kết quả tuân thủ nghiêm ngặt định dạng JSON sau:
                 [
                     {
                         "question": "Câu hỏi",
                         "answer": "Câu trả lời"
                      }
                 ]
                 Không giải thích thêm, chỉ trả về JSON bằng Tiếng Việt.
                 """;

        String QUIZ_COMMAND = """
                 Dựa trên nội dung văn bản cung cấp, hãy giúp tôi tạo 10 câu hỏi trắc nghiệm (quiz).
                 Mỗi câu hỏi phải có 4 lựa chọn (options). 
                 Trả về kết quả tuân thủ nghiêm ngặt định dạng JSON sau:
                 [
                     {
                         "question": "Nội dung câu hỏi?",
                         "options": ["Lựa chọn 1", "Lựa chọn 2", "Lựa chọn 3", "Lựa chọn 4"],
                         "correctAnswer": 0 // Chỉ số của câu trả lời đúng (0-3)
                     }
                 ]
                 Lưu ý: "options" là một mảng chuỗi (JSON Array). Không giải thích thêm, chỉ trả về JSON bằng Tiếng Việt.
                 """;

        String COMBINED_COMMAND = """
            Nội dung: {command}
            Dựa trên nội dung trên, hãy tạo chính xác 10 flashcards và 10 câu hỏi trắc nghiệm.
            Yêu cầu:
            - Ngôn ngữ: Tiếng Việt.
            - Định dạng: JSON nguyên bản.
            - "options" trong quiz phải là mảng của 4 chuỗi.
            
            Trả về kết quả tuân thủ nghiêm ngặt cấu trúc sau:
            {format}
            """;

    }
    }
