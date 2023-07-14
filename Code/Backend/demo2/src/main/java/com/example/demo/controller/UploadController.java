package com.example.demo.controller;

import com.example.demo.entity.Good;
import com.example.demo.entity.User;
import com.example.demo.mapper.GoodMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/Upload")
public class UploadController {


    @Value("${prop.upload-folder}")
    private String UPLOAD_FOLDER;
    @Value("${prop.backend}")
    private String backend;
    @Autowired
    private GoodMapper goodMapper;

    @RequestMapping("")
    public HashMap<String,Object> upload(@RequestParam(name = "file", required = false) MultipartFile[] files, @RequestParam(name = "user_id", required = false) String id,
                         @RequestParam(name = "name", required = false) String name, HttpServletRequest request) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        System.out.println("----------------上传照片----------------");
        System.out.println("IN FUNC" + id + name);
        HashMap<String,Object> map = new HashMap<String,Object>();
        List<String> list = new ArrayList<>();
        if (files == null || files.length == 0) {
            System.out.println(files.length);
            System.out.println("----No Files----");
            map.put("status","failure");
            return map;
        }
        else {
            System.out.println("Files: " + files.length);
            File savePathFile = new File(UPLOAD_FOLDER);
            if (!savePathFile.exists()) {
                savePathFile.mkdir();
            }
            int i = 0;
            Long time = System.currentTimeMillis();
            for(MultipartFile file:files)
            {
                String suffix = "jpg";
                String filename = id + "_" + name + "_" + String.valueOf(i) + "_"+ String.valueOf(time) + "." + suffix;
                try {
                        file.transferTo(new File(UPLOAD_FOLDER + filename));
                        System.out.println(UPLOAD_FOLDER + filename);
                        System.out.println("success");
                }catch (Exception e) {
                    System.out.println("--上传失败--");
                    map.put("status","failure");
                    return map;
                }
                i++;
                list.add(backend + filename);
            }
            map.put("status","success");
            map.put("data",list);
            return map;
        }
    }
}
