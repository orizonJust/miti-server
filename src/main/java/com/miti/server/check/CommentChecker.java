package com.miti.server.check;

import com.miti.server.model.entity.User;

public class CommentChecker {
    public boolean commentChecker(String comment, User commentator) {
        if ((comment != null && !comment.isEmpty()) &&
                commentator != null)
            return true;
        return false;
    }
}