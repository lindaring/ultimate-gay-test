package za.co.lindaring.gay.repo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class User {
    private int id;
    private String name;
    private String ip;
    private String userAgent;
    private int score;
}
