package com.jjlee.studywebfile.controllers;

import com.jjlee.studywebfile.entities.FileEntity;
import com.jjlee.studywebfile.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequestMapping(value = "file")
public class FileController {
    private final FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @RequestMapping(value = "uploadForm", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView getUploadForm() {
        return new ModelAndView("file/uploadForm");
    }

    @RequestMapping(value = "uploadForm",
            method = RequestMethod.POST,
            produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView postUploadForm(HttpServletRequest request,
                                       @RequestParam(value = "file")MultipartFile file) throws IOException {
        ModelAndView modelAndView = new ModelAndView("file/uploadForm");
        boolean result = this.fileService.putFile(request, file);
        modelAndView.addObject("result", result);
        return modelAndView;
    }

    @RequestMapping(value = "uploadMultipleForm",
        method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView getUploadMultipleForm() {
        return new ModelAndView("file/uploadMultipleForm");
    }

    @RequestMapping(value = "uploadMultipleForm",
            method = RequestMethod.POST, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView postUploadMultipleForm(HttpServletRequest request,
                                               @RequestParam(value = "file")MultipartFile[] files) throws IOException {
        ModelAndView modelAndView = new ModelAndView("file/uploadMultipleForm");
        boolean result = this.fileService.putMultipleFile(request, files);
        modelAndView.addObject("result", result);
        return modelAndView;
    }

    @RequestMapping(value = "uploadXhr", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView getUploadXhr() {
        return null;
    }

}
