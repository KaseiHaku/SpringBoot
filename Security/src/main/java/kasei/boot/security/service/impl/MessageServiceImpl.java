package kasei.boot.security.service.impl;

import kasei.boot.security.service.MessageService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
public class MessageServiceImpl implements MessageService {


    @PreAuthorize("authenticated")
    @Override
    public String getMessage() {

        Authentication authentication = SecurityContextHolder.getContext()
            .getAuthentication();
        return "hello " + authentication;
    }
}
