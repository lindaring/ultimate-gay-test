package za.co.lindaring.gay.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.lindaring.gay.exception.DatabaseException;
import za.co.lindaring.gay.model.GetUserResponse;
import za.co.lindaring.gay.prop.MessageProperties;
import za.co.lindaring.gay.repo.UserRepo;
import za.co.lindaring.gay.repo.model.User;

import java.util.List;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private MessageProperties messages;

    /**
     * Save user results.
     * @param user the user details.
     * @return the user id.
     * @throws DatabaseException if the insertion fails.
     */
    public long saveUserScore(User user) throws DatabaseException {
        long userId = userRepo.addUserAndScore(user);
        if (userId < 1) {
            log.error("{} :: saveUserScore({}) :: No rows inserted.", getClass(), user);
            throw new DatabaseException(messages.getUpdateFailedMsg());
        }
        log.debug("{} saved successfully.", user);
        return userId;
    }

    /**
     * Get a specific user.
     * @param id the user id.
     * @return the user, otherwise return null.
     */
    public GetUserResponse getUser(int id) {
        List<User> userList = userRepo.findUser(id);
        log.debug("{} saved successfully.", userList.size());
        User user = userList.stream().findFirst().orElse(null);

        if (user == null)
            return null;

        return GetUserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .score(user.getScore())
                .visited(user.getVisited().toLocalDateTime().toLocalDate())
                .build();
    }
}
