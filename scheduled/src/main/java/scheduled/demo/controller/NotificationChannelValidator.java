package scheduled.demo.controller;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import scheduled.demo.entity.notification.Channel;
import scheduled.demo.request.NotificationRequest;

@Component
public class NotificationChannelValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return NotificationRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        NotificationRequest request = (NotificationRequest) target;
        boolean existsBy = Channel.existsBy(request.channel());
        if (!existsBy) errors.rejectValue("channel", "This channel not exists");
    }
}