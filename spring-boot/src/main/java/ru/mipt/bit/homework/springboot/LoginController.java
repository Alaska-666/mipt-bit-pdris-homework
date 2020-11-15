package ru.mipt.bit.homework.springboot;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;

@Controller
public class LoginController {
    UserStorage userStorage = new UserStorage();
    LogStorage logStorage = new LogStorage();
    private String loginUsername = "";
    private String registrationUsername = "";
    private String message = "";

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(ModelMap model) {
        model.addAttribute("username", registrationUsername);
        model.addAttribute("user", new User());
        registrationUsername = "";
        return "registration";
    }

    @RequestMapping(value = "/audit", method = RequestMethod.GET)
    public String audit(ModelMap model) {
        model.addAttribute("logs", logStorage.getLogs());
        return "audit";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String submitRegistration(@Valid @ModelAttribute("user")User user, ModelMap model) {
        if (user.getUsername().equalsIgnoreCase("admin") || userStorage.isUserExist(user)) {
            registrationUsername = user.getUsername();
            String action = "";
            if (user.getUsername().equalsIgnoreCase("admin")) {
                action = "registered with the name 'admin'";
            } else {
                action = "registered with the occupied name";
            }
            logStorage.add(user, action, "FAIL");
            return registration(model);
        }
        else {
            userStorage.add(user);
            message = "registration";
            loginUsername = user.getUsername();
            registrationUsername = "";
            logStorage.add(user, "registration", "OK");
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String login(ModelMap model) {
        model.addAttribute("user", new User());
        model.addAttribute("username", loginUsername);
        model.addAttribute("message", message);
        loginUsername = "";
        message = "";
        return "login";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String submitLogin(@Valid @ModelAttribute("user")User user, ModelMap model) {
        loginUsername = user.getUsername();
        if (!userStorage.isUserExist(user)) {
            message = "notExist";
            logStorage.add(user, "attempt to authorize an unregistered user", "FAIL");
        }
        else if (!userStorage.isPasswordCorrect(user)) {
            message = "incorrectPassword";
            logStorage.add(user, "login attempt with invalid password", "FAIL");
        }
        else {
            logStorage.add(user, "authorization", "OK");
            model.addAttribute("username", loginUsername);
            message = "";
            loginUsername = "";
            return "pageAfterLogin";
        }
        return login(model);
    }
}
