package dev.akursekova.app.subjects;

import lombok.Builder;

@Builder
public class Answer {
    String text;
    Question nextQuestion;

    public String getText() {
        return text;
    }

    public Question getNextQuestion() {
        return nextQuestion;
    }
}
