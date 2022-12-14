package dev.akursekova.quest.subjects;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
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


