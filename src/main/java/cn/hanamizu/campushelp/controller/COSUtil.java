package cn.hanamizu.campushelp.controller;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class COSUtil {

    public static PutObjectResult uploadFile(MultipartFile multipartFile, String bucketName, String key) throws IOException {
        // 填写自己的COS信息
        COSCredentials cred = new BasicCOSCredentials("AKIDxkMkupW1HJ6jSxDFBtMlwtc6MN9h0EDy", "Zu0sVu4zPjLlxwhFZfZFhutyqoaaMtBE");
        ClientConfig clientConfig = new ClientConfig(new Region("ap-shanghai"));
        COSClient cosClient = new COSClient(cred, clientConfig);

        // 转存文件到本地（也可以选择不转存，直接使用multipartFile的InputStream上传）
        File localFile = File.createTempFile("cos", multipartFile.getOriginalFilename());
        multipartFile.transferTo(localFile);

        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, localFile);
        PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);

        // 上传完毕后删除临时文件
        localFile.delete();

        return putObjectResult;
    }
}

