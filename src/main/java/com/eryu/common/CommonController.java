package com.eryu.common;

import com.eryu.common.utils.QiniuUploadUtil;
import com.eryu.dto.SimpleResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 *
 * 图片上传的统一性接口
 *
 * Created by troubleMan on 2017/7/31.
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/file")
public class CommonController {

    /**
     *
     * 上传图片到七牛
     * @param file 图片
     * @param response 返回七牛的图片加载地址
     * @return
     */
    @RequestMapping(value="/uploadImg", method= RequestMethod.POST)
    @ResponseBody
    public SimpleResponse uploadImg(@RequestParam("file-fr")MultipartFile file, HttpServletResponse response){
             String key = "";
            try {
                key = QiniuUploadUtil.uploadToQiniu(file);
                 } catch (Exception e) {
                return SimpleResponse.builder().data(e.getMessage()).build();
                }
             response.setContentType("text/html;charset=UTF-8");
            //解决跨域名访问问题
             response.setHeader("Access-Control-Allow-Origin", "*");
            return SimpleResponse.builder().data(key).build();
    }

}
