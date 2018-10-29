package za.co.lindaring.gay.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import za.co.lindaring.gay.exception.QuestionNotFoundException;
import za.co.lindaring.gay.exception.TechnicalException;
import za.co.lindaring.gay.model.AnswerResponse;
import za.co.lindaring.gay.model.GetQuestionsReponse;
import za.co.lindaring.gay.model.QuestionResponse;
import za.co.lindaring.gay.repo.QuestionRepo;
import za.co.lindaring.gay.repo.model.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class QuestionService {

    private static final String ALL_QUESTIONS = "ALL_QUESTIONS";

    @Autowired
    private QuestionRepo questionRepo;

    @Autowired
    private CacheService cacheService;

    /**
     * Get a list of questions and related answers.
     *
     * @param from the start point.
     * @param to the to point.
     * @return list of questions.
     */
    public GetQuestionsReponse getQuestions(int from, int to) throws QuestionNotFoundException, TechnicalException {
        try {
            Optional<GetQuestionsReponse> responseOptional = cacheService.getQuestions(getKey(from, to));
            if (responseOptional.isPresent()) {
                log.debug("Questions retrieved from cache.");
                return responseOptional.get();
            }

            List<Question> questionAnswerList = questionRepo.findQuestions(from, to);
            log.debug("getQuestion :: questionList :: size={}", questionAnswerList.size());

            if (questionAnswerList.isEmpty())
                throw new QuestionNotFoundException();

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

            GetQuestionsReponse response = new GetQuestionsReponse(questionResponseList);
            cacheService.putQuestions(getKey(from, to), response);
            log.debug("getQuestion :: mapped question={}", questionResponseList);
            return response;

        } catch (QuestionNotFoundException e) {
            throw e;

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new TechnicalException();
        }
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

    private String getKey(int from, int to) {
        //Cache question from a range. If different range supplied, get fresh list.
        return ALL_QUESTIONS + "_" + from + "_" + to;
    }

}
