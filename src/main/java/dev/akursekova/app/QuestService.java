package dev.akursekova.app;

import java.util.List;

public final class QuestService {
        private static QuestService questServiceInstance;
        protected Question init() {
                Question q1 = Question.builder()
                        .text("Your lies have been exposed. You have lost.")
                        .isLoose(true)
                        .build();

                Question q2 = Question.builder()
                        .text("You have not negotiated. You have lost.")
                        .isLoose(true)
                        .build();

                Question q3 = Question.builder()
                        .text("You have rejected the challenge. You have lost.")
                        .isLoose(true)
                        .build();

                Question q4 = Question.builder()
                        .text("You have been returned home. You won.")
                        .isWin(true)
                        .build();

                Question q5 = Question.builder()
                        .text("You went up to the bridge. Who are you?")
                        .answers(List.of(
                                Answer.builder()
                                        .text("Tell the truth")
                                        .nextQuestion(q4)
                                        .build(),
                                Answer.builder()
                                        .text("Tell a lie")
                                        .nextQuestion(q1)
                                        .build()
                        ))
                        .build();

                Question q6 = Question.builder()
                        .text("You've accepted the challenge. Will you come over the captain and will go up the bridge to him?")
                        .answers(List.of(
                                Answer.builder()
                                        .text("Accept to go up to the bridge")
                                        .nextQuestion(q5)
                                        .build(),
                                Answer.builder()
                                        .text("Reject to go up to the bridge")
                                        .nextQuestion(q2)
                                        .build()
                        ))
                        .build();

                Question q7 = Question.builder()
                        .text("You've lost your memory. Accept the UFO challenge?")
                        .answers(List.of(
                                Answer.builder()
                                        .text("Accept challenge")
                                        .nextQuestion(q6)
                                        .build(),
                                Answer.builder()
                                        .text("Reject challenge")
                                        .nextQuestion(q3)
                                        .build()
                        ))
                        .build();
                return q7;
        }



        public static QuestService getInstance() {
                if (questServiceInstance == null){
                        questServiceInstance = new QuestService();
                }

                return questServiceInstance;
        }
}
