package za.co.lindaring.gay.repo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class User {
    private int id;
    private String name;
    private String ip;
    private String userAgent;
    private int score;
    private Timestamp visited;
}
