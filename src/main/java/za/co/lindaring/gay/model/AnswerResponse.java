package za.co.lindaring.gay.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AnswerResponse {
    private int answerId;
    private String answerDesc;
    private String answerPic;
}
