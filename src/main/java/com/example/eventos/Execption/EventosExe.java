package com.example.eventos.Execption;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class EventosExe extends RuntimeException{
    public ProblemDetail tProblemDetail()
    {
        var pb = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        return pb;
    }
}
