package com.taotao.manager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taotao.fdfs.UploadUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

@Controller
@RequestMapping(value = "pic")
public class UploadController {

    /**
     *  用于json处理问题
     */
    @Autowired
    private ObjectMapper objectMapper;

    @Value("${UploadImageWebPath}")
    private String UploadImageWebPath;

    @Value("${TrackerServerPath}")
    private String TrackerServerPath;

    @Value("${FastDFSWebLink}")
    private String FastDFSWebLink;

    @ResponseBody
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(@RequestParam(value = "uploadFile") MultipartFile file, HttpSession session) throws IOException {
        String subfix = StringUtils.substringAfterLast(file.getOriginalFilename(), ".");
        String[] uploadinfos = UploadUtil.upload(TrackerServerPath, file.getBytes(), subfix);
        HashMap<String, Object> map = new HashMap<>();
        map.put("error", 0);
        map.put("url", FastDFSWebLink+uploadinfos[0]+"/"+uploadinfos[1]);
        map.put("height", 100);
        map.put("width", 100);
        String json = objectMapper.writeValueAsString(map);
        return json;
    }

    @ResponseBody
    @RequestMapping(value = "/uploadSpringMVC", method = RequestMethod.POST)
    public String uploadSpringMVC(@RequestParam(value = "uploadFile") MultipartFile file, HttpSession session) throws IOException {
        String realPath = session.getServletContext().getRealPath("upload");
        String filename = file.getOriginalFilename();
        String subfix = StringUtils.substringAfter(filename, ".");
        String storeFileName = UUID.randomUUID() + "." + subfix;
        file.transferTo(new File(realPath + "/" + storeFileName));
        String webLink = UploadImageWebPath + storeFileName;
        HashMap<String, Object> map = new HashMap<>();
        map.put("error", 0);
        map.put("url", webLink);
        map.put("height", 100);
        map.put("width", 100);
        String json = objectMapper.writeValueAsString(map);
        return json;
    }
}
