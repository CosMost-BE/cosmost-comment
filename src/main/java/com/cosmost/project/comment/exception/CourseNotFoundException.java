package com.cosmost.project.comment.exception;

public class CourseNotFoundException extends RuntimeException {
    private static final String MESSAGE = "코스ID가 존재하지 않습니다";

    public  CourseNotFoundException() {
        super(MESSAGE);
    }
}
