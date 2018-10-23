package za.co.lindaring.gay.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.lindaring.gay.exception.DatabaseException;
import za.co.lindaring.gay.model.GeneralResponse;
import za.co.lindaring.gay.model.PostAnwersRequest;
import za.co.lindaring.gay.repo.model.User;
import za.co.lindaring.gay.utils.GeneralUtils;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Service
public class AnswerService {

    @Autowired
    private UserService userService;

    public GeneralResponse submitAnwers(HttpServletRequest httpServletRequest, PostAnwersRequest anwersRequest)
            throws DatabaseException {
        String ip = GeneralUtils.getClientIp(httpServletRequest);
        String userAgent = GeneralUtils.getUserAgent(httpServletRequest);

        userService.saveUserScore(new User(0, anwersRequest.getName(), ip, userAgent, 10));

        return GeneralResponse.builder()
                .success(true)
                .build();
    }

}
