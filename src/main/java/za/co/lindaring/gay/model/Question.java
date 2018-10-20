package za.co.lindaring.gay.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Question {
    private int id;
    private String desc;
    private String background;
    private List<Answer> answer;
}
