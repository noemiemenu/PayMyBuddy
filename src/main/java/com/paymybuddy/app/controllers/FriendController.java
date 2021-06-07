package com.paymybuddy.app.controllers;


import com.paymybuddy.app.exceptions.FriendAlreadyLinkedException;
import com.paymybuddy.app.exceptions.UserNotFoundException;
import com.paymybuddy.app.forms.AddFriendForm;
import com.paymybuddy.app.models.User;
import com.paymybuddy.app.services.interfaces.AuthenticationService;
import com.paymybuddy.app.services.interfaces.FriendService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * The type Friend controller.
 */
@AllArgsConstructor
@Controller
public class FriendController {
    private final FriendService friendService;
    private final AuthenticationService authenticationService;

    /**
     * Show add friend page string.
     *
     * @param model   the model
     * @param request the request
     * @return the string
     */
    @GetMapping(value = "/friends")
    public String showAddFriendPage(Model model, HttpServletRequest request) {
        User user = authenticationService.getCurrentLoggedUser(request);
        model.addAttribute("friends", user.getFriends());
        return "friends";
    }


    /**
     * Add friend string.
     *
     * @param addFriendForm      the add friend form
     * @param request            the request
     * @param redirectAttributes the redirect attributes
     * @return  redirect to the friends Page
     */
    @GetMapping("/friend/new")
    public String addFriend(AddFriendForm addFriendForm, HttpServletRequest request, RedirectAttributes redirectAttributes){
        User user = authenticationService.getCurrentLoggedUser(request);

        try {
            friendService.addFriend(addFriendForm, user);
        } catch (UserNotFoundException | FriendAlreadyLinkedException e) {
            redirectAttributes.addAttribute("error", e.getMessage());
        }
        return"redirect:/friends";
    }
}
