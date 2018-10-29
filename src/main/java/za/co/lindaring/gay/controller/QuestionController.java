package za.co.lindaring.gay.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping(value="/{from}/{to}")
    @ApiOperation(notes="Get list of questions", value="Get list of questions")
    public ResponseEntity<GetQuestionsReponse> getDefinition(
            @ApiParam(value="Pagination start point", defaultValue = "0") @PathVariable int from,
            @ApiParam(value="Pagination end point", defaultValue = "100") @PathVariable int to)
            throws QuestionNotFoundException, TechnicalException {
        return ResponseEntity.ok(questionService.getQuestions(from, to));
    }

    @GetMapping(value="/")
    @ApiOperation(notes="Get all of questions", value="Get all list of questions")
    public ResponseEntity<GetQuestionsReponse> getAllDefinitions()
            throws QuestionNotFoundException, TechnicalException {
        return ResponseEntity.ok(questionService.getQuestions(-1, -1));
    }

}
