package com.wordle.wordlebackend.dtos;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateGameRequest {
    private String playerToken;
}
