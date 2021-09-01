package com.poly.service.impl;

import com.poly.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;

@Service
public class UploadServiceImpl implements UploadService {

    @Autowired
    private ServletContext app;

    @Override
    public File save(MultipartFile file, String folder) {
        File dir = new File(app.getRealPath("/assets/" + folder));
//        File dir = new File("/assets/" + folder);
        if(!dir.exists()){
            dir.mkdirs();
        }
        // save image
        String s = System.currentTimeMillis() + file.getOriginalFilename();
        String name = Integer.toHexString(s.hashCode()) + s.substring(s.lastIndexOf("."));
        try{
            File savedFile = new File(dir,name);
            file.transferTo(savedFile);
            System.out.println(savedFile.getAbsolutePath());
            return savedFile;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
}
