package za.co.lindaring.gay.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class GetUserResponse {
    private int id;
    private String name;
    private int score;
    private LocalDate visited;
}
