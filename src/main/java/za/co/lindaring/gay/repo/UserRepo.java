package za.co.lindaring.gay.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import za.co.lindaring.gay.prop.SqlProperties;
import za.co.lindaring.gay.repo.model.User;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class UserRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SqlProperties sqlProperties;

    public long addUserAndScore(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(sqlProperties.getInsertUserScore(), Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getName());
            statement.setString(2, user.getIp());
            statement.setString(3, user.getUserAgent());
            statement.setInt(4, user.getScore());
            return statement;
        }, keyHolder);

        return (long) keyHolder.getKey();
    }

    public List<User> findUser(int id) {
        return jdbcTemplate.query(
            sqlProperties.getUser(),
            (rs, rowNum) -> User.builder()
                    .id(rs.getInt("id"))
                    .name(rs.getString("name"))
                    .ip(rs.getString("ip"))
                    .userAgent(rs.getString("user_agent"))
                    .score(rs.getInt("score"))
                    .visited(rs.getTimestamp("visited"))
                    .build(),
                id
        );
    }

    public List<User> findAllUsers() {
        return jdbcTemplate.query(
                sqlProperties.getAllUsers(),
                (rs, rowNum) -> User.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .ip(rs.getString("ip"))
                        .userAgent(rs.getString("user_agent"))
                        .score(rs.getInt("score"))
                        .visited(rs.getTimestamp("visited"))
                        .build()
        );
    }

}
