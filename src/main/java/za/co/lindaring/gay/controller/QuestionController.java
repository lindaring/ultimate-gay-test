package za.co.lindaring.gay.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.lindaring.gay.model.Answer;
import za.co.lindaring.gay.model.Question;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value="/ultimate-gay-test/v1/question")
public class QuestionController {

    @GetMapping(value="/")
    @ApiOperation(notes="Get list of questions", value="Get list of questions")
    public ResponseEntity<Question> getDefinition() {
        Answer answer = new Answer(0, "Answer1", "directory1");
        List<Answer> answerList = new ArrayList<>();
        answerList.add(answer);
        Question question = new Question(6, "Question1", "directory1", answerList);
        return ResponseEntity.ok(question);
    }

}
