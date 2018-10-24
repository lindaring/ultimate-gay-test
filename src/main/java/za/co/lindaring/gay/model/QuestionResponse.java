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
    private int id;
    private String desc;
    private String pic;
    private List<AnswerResponse> answersList;
}
