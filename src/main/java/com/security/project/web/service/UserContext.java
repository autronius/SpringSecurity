package com.security.project.web.service;

import com.security.project.web.service.domain.CustomUser;

public interface UserContext {
    CustomUser getCurrentUser();
    void setCurrentUser(CustomUser user);
}