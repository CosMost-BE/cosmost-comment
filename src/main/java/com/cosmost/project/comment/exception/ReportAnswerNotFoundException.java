package com.cosmost.project.comment.exception;

public class ReportAnswerNotFoundException extends RuntimeException {

    private static final String MESSAGE = "신고답변이 존재하지 않습니다";

    public  ReportAnswerNotFoundException() {
        super(MESSAGE);
    }
}
