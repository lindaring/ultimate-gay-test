package za.co.lindaring.gay.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.lindaring.gay.exception.TechnicalException;
import za.co.lindaring.gay.model.AnswerRequest;
import za.co.lindaring.gay.model.GeneralResponse;
import za.co.lindaring.gay.model.PostAnwersRequest;
import za.co.lindaring.gay.repo.AnswerRepo;
import za.co.lindaring.gay.repo.model.Answer;
import za.co.lindaring.gay.repo.model.User;
import za.co.lindaring.gay.utils.GeneralUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@Service
public class AnswerService {

    @Autowired
    private UserService userService;

    @Autowired
    private AnswerRepo answerRepo;

    public GeneralResponse submitAnswers(HttpServletRequest httpServletRequest, PostAnwersRequest answersRequest)
            throws TechnicalException {
        try {
            String ip = GeneralUtils.getClientIp(httpServletRequest);
            String userAgent = GeneralUtils.getUserAgent(httpServletRequest);

            int score = calculateScore(answersRequest.getAnswerList());
            long userId = userService.saveUserScore(new User(0, answersRequest.getName(), ip, userAgent, score, null));

            return GeneralResponse.builder()
                    .success(true)
                    .id(userId)
                    .build();

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new TechnicalException();
        }
    }

    private int calculateScore(List<AnswerRequest> answerList) {
            List<Answer> result = answerRepo.findAnswers(answerList);
            int total = result.stream().mapToInt(Answer::getPoint).sum();
            log.debug("{} :: calculateScore() :: {}", getClass(), total);
            return total;
    }

}
