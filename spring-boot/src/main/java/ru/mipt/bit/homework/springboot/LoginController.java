package ru.mipt.bit.homework.springboot;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class LoginController {
    UserStorage userStorage = new UserStorage();
    LogStorage logStorage = new LogStorage();

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(ModelMap model) {
        addAttributes(model, new User(), "", "");
        return "registration";
    }

    @RequestMapping(value = "/audit", method = RequestMethod.GET)
    public String audit(ModelMap model) {
        model.addAttribute("logs", logStorage.getLogs());
        return "audit";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String submitRegistration(@Valid @ModelAttribute("user")User user, ModelMap model, RedirectAttributes redirectAttributes) {
        String username = user.getUsername();
        if (user.getUsername().equalsIgnoreCase("admin") || userStorage.isUserExist(user)) {
            String action = "";
            if (user.getUsername().equalsIgnoreCase("admin")) {
                action = "registered with the name 'admin'";
            } else {
                action = "registered with the occupied name";
            }
            logStorage.add(user, action, "FAIL");
            addAttributes(model, new User(), username, "");
            return "registration";
        }
        else {
            userStorage.add(user);
            addAttributes(redirectAttributes, new User(), username, "registration");
            logStorage.add(user, "registration", "OK");
            return "redirect:/";
        }
    }

    private void addAttributes(ModelMap model, User user, String username, String message) {
        model.addAttribute("user", user);
        model.addAttribute("username", username);
        model.addAttribute("message", message);
    }

    private void addAttributes(RedirectAttributes redirectAttributes, User user, String username, String message) {
        redirectAttributes.addFlashAttribute("user", user);
        redirectAttributes.addFlashAttribute("username", username);
        redirectAttributes.addFlashAttribute("message", message);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String login(ModelMap model) {
        if (model.isEmpty()) {
            addAttributes(model, new User(), "", "");
        } else {
            addAttributes(model, new User(), (String) model.getAttribute("username"), (String) model.getAttribute("message"));
        }
        return "login";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String submitLogin(@Valid @ModelAttribute("user")User user, ModelMap model) {
        String username = user.getUsername();
        String message = "";
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
            model.addAttribute("username", username);
            return "pageAfterLogin";
        }
        addAttributes(model, user, username, message);
        return "login";
    }
}
