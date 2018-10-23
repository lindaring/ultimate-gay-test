package za.co.lindaring.gay.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class QuestionResponse {
    private int questionId;
    private String questionDesc;
    private String questionPic;
    private List<AnswerResponse> answersList;
}
