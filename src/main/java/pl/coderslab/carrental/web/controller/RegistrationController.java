package pl.coderslab.carrental.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.coderslab.carrental.persistence.model.User;
import pl.coderslab.carrental.service.UserService;
import pl.coderslab.carrental.web.dto.UserDto;
import pl.coderslab.carrental.captcha.ICaptchaService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class RegistrationController {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private ICaptchaService captchaService;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView registration() {
        ModelAndView modelAndView = new ModelAndView();
        UserDto user = new UserDto();
        modelAndView.addObject("userDto", user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }


    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid final UserDto accountDto, BindingResult bindingResult, final HttpServletRequest request) {

        final String response = request.getParameter("g-recaptcha-response");
        ModelAndView modelAndView = new ModelAndView();
        System.out.println(accountDto.toString());
        try {
            captchaService.processResponse(response);
        } catch (NullPointerException e) {
            modelAndView.setViewName("registration");
        }


        User userExists = userService.findUserByEmail(accountDto.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "Niestety już występuje użytkownik o takim adresie e-mail");
        }
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult);
            modelAndView.setViewName("registration");
        } else {
            userService.createUserAccount(accountDto);
            modelAndView.addObject("successMessage", "Rejestracja użytkownika zakończona sukcesem");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("successRegister");
            LOGGER.debug("Registering user account with information: {}", accountDto);
        }
        return modelAndView;
    }
}
