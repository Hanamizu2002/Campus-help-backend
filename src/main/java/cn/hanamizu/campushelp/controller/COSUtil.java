package cn.hanamizu.campushelp.controller;

import cn.hanamizu.campushelp.common.config.COSConfig;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Component
public class COSUtil {

    private final COSConfig cosConfig;

    @Autowired
    public COSUtil(COSConfig cosConfig) {
        this.cosConfig = cosConfig;
    }

    public PutObjectResult uploadFile(MultipartFile multipartFile, String bucketName, String key) throws IOException {
        COSCredentials cred = new BasicCOSCredentials(cosConfig.getSecretId(), cosConfig.getSecretKey());
        ClientConfig clientConfig = new ClientConfig(new Region(cosConfig.getRegion()));
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
