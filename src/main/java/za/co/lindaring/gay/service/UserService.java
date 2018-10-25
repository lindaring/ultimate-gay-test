package za.co.lindaring.gay.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.lindaring.gay.exception.TechnicalException;
import za.co.lindaring.gay.exception.UserNotFoundException;
import za.co.lindaring.gay.model.GetAllUsersResponse;
import za.co.lindaring.gay.model.GetUserResponse;
import za.co.lindaring.gay.repo.UserRepo;
import za.co.lindaring.gay.repo.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserService {

    private final String ALL_STARS = "ALL_STARS";

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CacheService cacheService;

    /**
     * Save user results.
     * @param user the user details.
     * @return the user id.
     * @throws TechnicalException if the insertion fails.
     */
    long saveUserScore(User user) throws TechnicalException {
        long userId = userRepo.addUserAndScore(user);
        if (userId < 1) {
            log.error("{} :: saveUserScore({}) :: No rows inserted.", getClass(), user);
            throw new TechnicalException();
        }
        log.debug("{} saved successfully.", user);
        return userId;
    }

    /**
     * Get a specific user.
     * @param id the user id.
     * @return the user, otherwise return null.
     * @throws UserNotFoundException if user not found in database.
     * @throws TechnicalException for any other failures.
     */
    public GetUserResponse getUser(int id) throws TechnicalException, UserNotFoundException {
        try {
            List<User> userList = userRepo.findUser(id);
            log.debug("{} user retrieved array size.", userList.size());
            User user = userList.stream().findFirst().orElse(null);

            if (user == null)
                throw new UserNotFoundException();

            return GetUserResponse.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .score(user.getScore())
                    .visited(user.getVisited().toLocalDateTime().toLocalDate())
                    .build();

        } catch (UserNotFoundException e)  {
            throw e;

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new TechnicalException();
        }
    }

    /**
     * Get all users.
     * @return the users.
     * @throws UserNotFoundException if users not found in database.
     * @throws TechnicalException for any other failures.
     */
    public GetAllUsersResponse getAllUsers() throws TechnicalException, UserNotFoundException {
        try {
            Optional<GetAllUsersResponse> responseOptional = cacheService.getUsers(ALL_STARS);
            if (responseOptional.isPresent()) {
                log.debug("Users retrieved from cache.");
                return responseOptional.get();
            }

            List<User> userList = userRepo.findAllUsers();
            log.debug("{} users retrieved array size.", userList.size());

            if (userList.isEmpty())
                throw new UserNotFoundException();

            List<GetUserResponse> userResponseList = new ArrayList<>();
            userList.forEach(x ->
                userResponseList.add(
                    GetUserResponse.builder()
                            .id(x.getId())
                            .name(x.getName())
                            .score(x.getScore())
                            .visited(x.getVisited().toLocalDateTime().toLocalDate())
                            .build())
            );

            GetAllUsersResponse response = new GetAllUsersResponse(userResponseList);
            cacheService.putUsers(ALL_STARS, response);
            return response;

        } catch (UserNotFoundException e)  {
            throw e;

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new TechnicalException();
        }
    }
}
