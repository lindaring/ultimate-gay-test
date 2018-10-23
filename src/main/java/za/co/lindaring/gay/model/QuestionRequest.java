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
public class QuestionRequest {
    private int questionId;
    private List<AnswerRequest> answersList;
}
