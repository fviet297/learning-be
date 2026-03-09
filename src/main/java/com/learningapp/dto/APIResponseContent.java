package com.learningapp.dto;

import com.learningapp.dto.request.FlashcardRequest;
import com.learningapp.dto.request.QuizRequest;

import java.util.List;
import java.util.StringJoiner;

public record APIResponseContent(
        List<FlashcardRequest> flashcardRequestList,
        List<QuizRequest> quizRequestList
) {

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(", ", "APIResponseContent{", "}");

        sj.add("flashcardRequestList=");
        if (flashcardRequestList == null || flashcardRequestList.isEmpty()) {
            sj.add("[]");
        } else {
            StringJoiner flashcardSj = new StringJoiner("; ", "[", "]");
            for (FlashcardRequest f : flashcardRequestList) {
                flashcardSj.add(flashcardToString(f));
            }
            sj.add(flashcardSj.toString());
        }

        sj.add("quizRequestList=");
        if (quizRequestList == null || quizRequestList.isEmpty()) {
            sj.add("[]");
        } else {
            StringJoiner quizSj = new StringJoiner("; ", "[", "]");
            for (QuizRequest q : quizRequestList) {
                quizSj.add(quizToString(q));
            }
            sj.add(quizSj.toString());
        }

        return sj.toString();
    }

    private String flashcardToString(FlashcardRequest f) {
        if (f == null) return "null";

        return new StringJoiner(", ", "FlashcardRequest{", "}")
                .add("id='" + f.getId() + "'")
                .add("question='" + truncate(f.getQuestion()) + "'")
                .add("answer='" + truncate(f.getAnswer()) + "'")
                .add("status=" + f.getStatus())
                .toString();
    }

    private String quizToString(QuizRequest q) {
        if (q == null) return "null";

        return new StringJoiner(", ", "QuizRequest{", "}")
                .add("id='" + q.getId() + "'")
                .add("question='" + truncate(q.getQuestion()) + "'")
                .add("options=" + formatOptions(q.getOptions()))
                .add("correctAnswer=" + q.getCorrectAnswer())
                .add("studyModuleId='" + q.getStudyModuleId() + "'")
                .toString();
    }

    private String truncate(String s) {
        if (s == null) return "null";
        return s.length() > 50 ? s.substring(0, 47) + "..." : s;
    }

    private String formatOptions(List<String> options) {
        if (options == null) return "null";
        if (options.isEmpty()) return "[]";
        StringJoiner sj = new StringJoiner(", ", "[", "]");
        for (int i = 0; i < options.size(); i++) {
            String opt = options.get(i);
            sj.add((i + 1) + ": '" + truncate(opt) + "'");
        }
        return sj.toString();
    }
}
