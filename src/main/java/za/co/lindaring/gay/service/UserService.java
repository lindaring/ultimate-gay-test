package za.co.lindaring.gay.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.lindaring.gay.exception.DatabaseException;
import za.co.lindaring.gay.prop.MessageProperties;
import za.co.lindaring.gay.repo.UserRepo;
import za.co.lindaring.gay.repo.model.User;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private MessageProperties messages;

    public void saveUserScore(User user) throws DatabaseException {
        try {
            int saved = userRepo.addUserAndScore(user);
            if (saved < 1) {
                log.error("{} :: saveUserScore({}) :: No rows inserted.", getClass(), user);
                throw new DatabaseException(messages.getUpdateFailedMsg());
            }
        } catch (Exception e) {
            log.error("{} :: saveUserScore({}) :: Database insert failed.", getClass(), user);
            throw new DatabaseException(messages.getUpdateFailedMsg());
        }
        log.debug("{} saved successfully.", user);
    }
}
