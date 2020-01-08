package org.degerhardes.B8DC.controller;

import org.degerhardes.B8DC.service.DCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class SiteController {
    DCService dcService;

    @Autowired
    public SiteController(DCService dcService) {
        this.dcService = dcService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView showConvert() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("main");
        return modelAndView;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView getConvert(@ModelAttribute("convert") String inc){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("main");
        return modelAndView;
    }
}
