package za.co.lindaring.gay.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import za.co.lindaring.gay.model.AnswerResponse;
import za.co.lindaring.gay.model.GetQuestionsReponse;
import za.co.lindaring.gay.model.QuestionResponse;
import za.co.lindaring.gay.repo.QuestionRepo;
import za.co.lindaring.gay.repo.model.Question;

import java.util.*;

@Slf4j
@Service
public class QuestionService {

    @Autowired
    private QuestionRepo questionRepo;

    /**
     * Get a list of questions and related answers.
     *
     * @param limit max questions that must be returned.
     * @return list of questions.
     */
    public GetQuestionsReponse getQuestions(int limit) {
        List<Question> questionAnswerList = questionRepo.findQuestions(limit);
        log.debug("getQuestion :: questionList :: size={}", questionAnswerList.size());

        if (questionAnswerList.isEmpty())
            return new GetQuestionsReponse(Collections.singletonList(new QuestionResponse()));

        List<QuestionResponse> questionResponseList = new ArrayList<>();

        questionAnswerList.forEach(x -> {
            log.debug("getQuestion :: question={}", x);
            Optional<QuestionResponse> questionResponseOptional = getItemFromList(questionResponseList, x.getQuestionId());
            AnswerResponse answerResponse = initAnswerResponse(x);

            if (answerResponse != null && questionResponseOptional.isPresent()) {
                addAnswerToList(questionResponseOptional.get(), answerResponse);
            } else {
                addQuestionToList(questionResponseList, x, answerResponse);
            }
        });

        log.debug("getQuestion :: mapped question={}", questionResponseList);
        return new GetQuestionsReponse(questionResponseList);
    }

    private AnswerResponse initAnswerResponse(Question question) {
        if (!StringUtils.isEmpty(question.getAnswerDesc())) {
            log.debug("initAnswerResponse :: {} No answer found.", question);
            return AnswerResponse.builder()
                    .id(question.getAnswerId())
                    .desc(question.getAnswerDesc())
                    .pic(question.getAnswerBackground())
                    .build();
        }
        return null;
    }

    private Optional<QuestionResponse> getItemFromList(List<QuestionResponse> questionResponseList, int elementId) {
        return questionResponseList.stream().filter(x -> x.getId() == elementId).findFirst();
    }

    private void addAnswerToList(QuestionResponse questionResponse, AnswerResponse answerResponse) {
        log.debug("addAnswerToList :: Found in list. Adding answer {}...", questionResponse.getAnswersList());
        questionResponse.getAnswersList().add(answerResponse);
    }

    private void addQuestionToList(List<QuestionResponse> questionResponseList, Question question, AnswerResponse answerResponse) {
        log.debug("addQuestionToList :: {} not found in list. Creating new...", question);
        List<AnswerResponse> answerResponseList = new ArrayList<>();
        answerResponseList.add(answerResponse);

        QuestionResponse questionResponse = QuestionResponse.builder()
                .id(question.getQuestionId())
                .desc(question.getQuestionDesc())
                .pic(question.getQuestionBackground())
                .answersList(answerResponse != null ? answerResponseList : null)
                .build();

        questionResponseList.add(questionResponse);
    }

}
