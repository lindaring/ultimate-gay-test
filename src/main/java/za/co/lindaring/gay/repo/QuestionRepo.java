package za.co.lindaring.gay.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import za.co.lindaring.gay.prop.SqlProperties;
import za.co.lindaring.gay.repo.model.Question;

import java.util.List;

@Repository
public class QuestionRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SqlProperties sqlProperties;

    public List<Question> findQuestions(int limit) {
        String sql = String.format(sqlProperties.getQuestionsAndAnswers(), limit);
        return jdbcTemplate.query(
                sql,
                (rs, rowNum) -> Question.builder()
                                        .questionId(rs.getInt("question_id"))
                                        .questionDesc(rs.getString("question_desc"))
                                        .questionBackground(rs.getString("question_pic"))
                                        .answerId(rs.getInt("answer_id"))
                                        .answerDesc(rs.getString("answer_desc"))
                                        .answerBackground(rs.getString("answer_pic"))
                                        .build()
        );
    }

}
