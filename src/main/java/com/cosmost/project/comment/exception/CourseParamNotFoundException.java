package com.cosmost.project.comment.exception;

import java.util.NoSuchElementException;

public class CourseParamNotFoundException extends NoSuchElementException {

    private static final String MESSAGE = "입력값된 쿼리값이 옳바르지 않습니다.";

    public  CourseParamNotFoundException() {
        super(MESSAGE);
    }
}
