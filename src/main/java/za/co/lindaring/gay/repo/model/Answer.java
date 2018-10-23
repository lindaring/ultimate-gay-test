package za.co.lindaring.gay.repo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class Answer {
    private int id;
    private String answer;
    private String background;
    private int point;
    private int questionId;
}
