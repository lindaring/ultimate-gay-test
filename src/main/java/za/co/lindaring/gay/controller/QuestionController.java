package za.co.lindaring.gay.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.lindaring.gay.exception.QuestionNotFoundException;
import za.co.lindaring.gay.exception.TechnicalException;
import za.co.lindaring.gay.model.GetQuestionsReponse;
import za.co.lindaring.gay.service.QuestionService;

@CrossOrigin
@RestController
@RequestMapping(value="/ultimate-gay-test/v1/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping(value="/")
    @ApiOperation(notes="Get list of questions", value="Get list of questions")
    public ResponseEntity<GetQuestionsReponse> getDefinition()
            throws QuestionNotFoundException, TechnicalException {
        return ResponseEntity.ok(questionService.getQuestions(10));
    }

}
