package com.jjlee.studywebfile.controllers;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "file")
public class FileController {
    @RequestMapping(value = "uploadForm", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView getUploadForm() {
        return new ModelAndView("file/uploadForm");
    }

    @RequestMapping(value = "uploadForm",
            method = RequestMethod.POST,
            produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView postUploadForm(@RequestParam(value = "file")MultipartFile file) {
        ModelAndView modelAndView = new ModelAndView("file/uploadForm");
        System.out.println("OriginalFilename : " + file.getOriginalFilename());
        System.out.println("ContentType : "+ file.getContentType());
        System.out.println("Size : " + file.getSize());
        return modelAndView;
    }

    @RequestMapping(value = "uploadXhr", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView getUploadXhr() {
        return null;
    }

}
