package za.co.lindaring.gay.repo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import za.co.lindaring.gay.model.AnswerRequest;
import za.co.lindaring.gay.prop.SqlProperties;
import za.co.lindaring.gay.repo.model.Answer;

import java.util.List;

@Slf4j
@Repository
public class AnswerRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SqlProperties sqlProperties;

    public List<Answer> findAnswers(List<AnswerRequest> answerList) {
        String sql = getAnswersSql(answerList);
        return jdbcTemplate.query(
                sql,
                (rs, rowNum) -> Answer.builder()
                        .id(rs.getInt("id"))
                        .answer(rs.getString("answer"))
                        .background(rs.getString("background"))
                        .point(rs.getInt("point"))
                        .questionId(rs.getInt("question_id"))
                        .build()
        );
    }

    private String getAnswersSql(List<AnswerRequest> answerList) {
        StringBuilder sql = new StringBuilder(sqlProperties.getAllAnswers());
        sql.append(" WHERE");
        for (int i = 0; i < answerList.size(); i++) {
            sql.append(" ID = '").append(answerList.get(i).getAnswerId()).append("'");
            if (i < (answerList.size() - 1))
                sql.append(" OR");
        }
        log.debug("{} :: getAnswersSql() :: SQL = {}", getClass(), sql.toString());
        return sql.toString();
    }

}
