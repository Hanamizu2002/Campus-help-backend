package cn.hanamizu.campushelp.controller;

import cn.hanamizu.campushelp.utils.http.AjaxResult;
import com.qcloud.cos.model.PutObjectResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/upload")
public class UploadController extends BaseController {

    @PostMapping
    public ResponseEntity<AjaxResult> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body(AjaxResult.error("文件不能为空"));
        }
        String bucketName = "cloud-1318358151";
        String key = "Picture/pic/" + System.currentTimeMillis() + "-" + file.getOriginalFilename();

        try {
            PutObjectResult result = COSUtil.uploadFile(file, bucketName, key);
            String fileUrl = "http://cloud.alsace.team/" + key + "?imageMogr2/format";
            return ResponseEntity.ok(AjaxResult.success("文件上传成功", fileUrl));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(AjaxResult.error("文件上传失败"));
        }
    }
}

