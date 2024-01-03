package com.devrezaur.main.controller;

import com.devrezaur.main.service.*;
import org.springframework.ui.Model;

public class BaseController {

    protected void addWarning(Model m) {
        m.addAttribute("warning", "so sorry");
    }

}
