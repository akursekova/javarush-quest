package dev.akursekova.app;

import lombok.Builder;

@Builder
public class Answer {
    String text;
    Question nextQuestion;

    public String getText() {
        return text;
    }
}
