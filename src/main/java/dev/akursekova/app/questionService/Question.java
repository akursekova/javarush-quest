package dev.akursekova.app.questionService;

import lombok.Builder;

import java.util.List;

@Builder
public class Question {
    String text;
    List<Answer> answers;
    boolean isWin;
    boolean isLoose;

    public String getText() {
        return text;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public boolean isWin() {
        return isWin;
    }

    public boolean isLoose() {
        return isLoose;
    }
}


