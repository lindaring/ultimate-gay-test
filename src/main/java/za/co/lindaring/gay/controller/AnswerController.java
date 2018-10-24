package za.co.lindaring.gay.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.lindaring.gay.exception.TechnicalException;
import za.co.lindaring.gay.model.GeneralResponse;
import za.co.lindaring.gay.model.PostAnwersRequest;
import za.co.lindaring.gay.service.AnswerService;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping(value="/ultimate-gay-test/v1/answer")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @PostMapping(value="/")
    @ApiOperation(notes="Submit answers", value="Submit answers")
    public ResponseEntity<GeneralResponse> getDefinition(HttpServletRequest httpServletRequest,
                                                         @RequestBody PostAnwersRequest answersRequest)
            throws TechnicalException {
        return ResponseEntity.ok(answerService.submitAnswers(httpServletRequest, answersRequest));
    }

}
