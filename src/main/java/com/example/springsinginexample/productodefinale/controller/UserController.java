package com.example.springsinginexample.productodefinale.controller;

import com.example.springsinginexample.productodefinale.model.Post;
import com.example.springsinginexample.productodefinale.model.User;
import com.example.springsinginexample.productodefinale.service.PostInterface;
import com.example.springsinginexample.productodefinale.service.PostService;
import com.example.springsinginexample.productodefinale.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.Id;
import javax.validation.Valid;
import java.util.List;


@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @RequestMapping(value= {"/", "/login"}, method=RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView model = new ModelAndView();

        model.setViewName("user/login");
        return model;
    }

    @RequestMapping(value= {"/signup"}, method=RequestMethod.GET)
    public ModelAndView signup() {
        ModelAndView model = new ModelAndView();
        User user = new User();
        model.addObject("user", user);
        model.setViewName("user/signup");

        return model;
    }

    @RequestMapping(value= {"/signup"}, method=RequestMethod.POST)
    public ModelAndView createUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView model = new ModelAndView();
        User userExists = userService.findUserByEmail(user.getEmail());
        if(userExists != null) {
            bindingResult.rejectValue("email", "error.user", "This email already exists!");
        }
        if(bindingResult.hasErrors()) {
            model.setViewName("user/signup");
        } else {
            userService.saveUser(user);
            model.addObject("msg", "User has been registered successfully!");
            model.addObject("user", new User());
            model.setViewName("user/login");
        }
        return model;
    }

    @RequestMapping(value= {"/homes"}, method=RequestMethod.GET)
    public ModelAndView homes() {
        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        model.addObject("userName", user.getFirstname() + " " + user.getLastname());
        List<Post> postList = postService.listAll();
        model.addObject("postList", postList);
        model.setViewName("home/home");
        return model;
    }

    @RequestMapping(value= {"/newpost"}, method=RequestMethod.GET)
    public ModelAndView newpost() {
        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        model.addObject("useremail", user.getEmail());
        System.out.println("user email: " + user.getEmail());
        Post post = new Post();
        model.addObject("post", post);
        model.setViewName("home/newpost");
        return model;
    }

    @RequestMapping(value= "/save", method=RequestMethod.POST)
    public String savePost(@ModelAttribute("post") Post post){
    postService.save(post);
    return "redirect:/homes";
    }

    @RequestMapping(value= {"/access_denied"}, method=RequestMethod.GET)
    public ModelAndView accessDenied() {
        ModelAndView model = new ModelAndView();
        model.setViewName("errors/access_denied");
        return model;
    }

    @RequestMapping(value= "/edit/{id}", method=RequestMethod.GET)
    public ModelAndView showEditPostForm(@PathVariable(name = "id") Long id){
        ModelAndView mav = new ModelAndView("editpost");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        mav.addObject("useremail", user.getEmail());
        Post post = postService.get(id);
        mav.addObject("post", post);
        mav.setViewName("home/editpost");
        postService.delete(id);
        return mav;
    }

    @RequestMapping("/delete/{id}")
    public String deletepost(@PathVariable(name = "id") Long id ) {
        postService.delete(id);
        return "redirect:/homes";
    }
}
