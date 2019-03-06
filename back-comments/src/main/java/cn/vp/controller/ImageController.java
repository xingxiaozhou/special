package cn.vp.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 照片回显
 */
@Controller
public class ImageController {

    /**
     * 图片上传及回显
     * @param fileImage
     * @return
     */
    @RequestMapping("/imgPath.do")
    @ResponseBody
    public String imgPath(@RequestParam("fileImage") MultipartFile fileImage, HttpServletRequest request)  {
        String realPath = request.getSession().getServletContext().getRealPath("/images/");
        File file=new File(realPath);
        if (!file.exists()){
            file.mkdirs();
        }
        String originalFilename = fileImage.getOriginalFilename();
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(realPath+originalFilename);
            out.write(fileImage.getBytes());
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        String path="images/"+originalFilename;
        JSONObject json=new JSONObject();
        json.put("imagePath",path);
        return json.toJSONString();
    }



}
