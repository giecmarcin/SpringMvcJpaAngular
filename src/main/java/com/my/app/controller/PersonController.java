package com.my.app.controller;

import com.my.app.model.Person;
import com.my.app.repository.PersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * Created by mgiec on 9/7/2016.
 */

@Controller
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonDao personDao;

    @RequestMapping("/form")
    public ModelAndView showAddPersonForm(){
        return new ModelAndView("person", "person", new Person());
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView submitPersonForm(@Valid @ModelAttribute("person") Person person, BindingResult result){
//        if(result.getSuppressedFields().length > 0){
//            return new ModelAndView("redirect:/person/form");
//        }else{
            personDao.savePerson(person);
            System.out.println(personDao);
            return  new ModelAndView("redirect:/person/all");
//        }
    }

    @RequestMapping(value = "/all")
    public String showAll(Model model){
        model.addAttribute("people", personDao.findAll());
        return "people";
    }
//    @InitBinder("person")
//    public void binderPerson(WebDataBinder binder){
//        binder.setAllowedFields("firstName, lastname"); //remove lastname
//    }
}
