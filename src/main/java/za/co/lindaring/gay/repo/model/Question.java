package za.co.lindaring.gay.repo.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Question {
    private Integer questionId;
    private String questionDesc;
    private String questionBackground;
    private Integer answerId;
    private String answerDesc;
    private String answerBackground;
}
