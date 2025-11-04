package org.qsheker.weatherapplication.api;

import jakarta.validation.Valid;
import org.qsheker.weatherapplication.domain.ObserverType;
import org.qsheker.weatherapplication.domain.db.User;
import org.qsheker.weatherapplication.domain.service.MessageSenderService;
import org.qsheker.weatherapplication.domain.service.UserService;
import org.qsheker.weatherapplication.web.dto.PhoneRequestDto;
import org.qsheker.weatherapplication.web.dto.ResponseDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/message")
public class MessageController {
    private final UserService userService;
    private final MessageSenderService messageSenderService;

    public MessageController(UserService userService, MessageSenderService messageSenderService) {
        this.userService = userService;
        this.messageSenderService = messageSenderService;
    }
//    TODO postmapping create new user with phone with also strategy(City monitor or Extreme weather) and Post mapping for sending message

    @PostMapping("/register")
    public ResponseDto register(@Valid @RequestBody PhoneRequestDto phoneRequestDto,
                                @RequestParam(value = "observer", defaultValue = "EXTREME_WEATHER_ALERT") ObserverType observerType){
        User user = User.builder()
                .cityName(phoneRequestDto.getCityName())
                .phoneNumber(phoneRequestDto.getPhoneNumber())
                .observerType(observerType)
                .build();
        userService.save(user);
         ResponseDto responseDto = new ResponseDto();
         responseDto.setMessage("saved user!");
         return responseDto;
    }

    @PostMapping("/send")
    public ResponseDto sendMessage(@RequestParam(value = "observer", defaultValue = "EXTREME_WEATHER_ALERT") ObserverType observerType){
        messageSenderService.send(observerType);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage("messages send!");
        return responseDto;
    }

}

