package za.co.lindaring.gay.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class AnswerRequest {
    private int questionId;
    private int answerId;
}
