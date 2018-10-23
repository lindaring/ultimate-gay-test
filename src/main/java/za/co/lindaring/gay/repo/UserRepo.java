package za.co.lindaring.gay.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import za.co.lindaring.gay.prop.SqlProperties;
import za.co.lindaring.gay.repo.model.User;

@Repository
public class UserRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SqlProperties sqlProperties;

    public int addUserAndScore(User user) {
        return jdbcTemplate.update(sqlProperties.getInsertUserScore(),
                user.getName(),
                user.getIp(),
                user.getUserAgent(),
                user.getScore()
        );
    }

}
