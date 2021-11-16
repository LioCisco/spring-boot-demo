package com.eddie.testspring.service.serviceImpl;

import com.eddie.testspring.service.IQiNiuService;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.File;

/**
 * @author Eddie.Liao
 * @description: TODO
 * @date 2021/11/16 12:39 下午
 */
@Service
@Slf4j
public class QiNiuServiceImpl implements IQiNiuService , InitializingBean {

    private  final UploadManager uploadManager;

    private final Auth auth;

    @Value("${qiniu.bucket}")
    private String bucket;


    private StringMap putPolicy;

    @Autowired
    public QiNiuServiceImpl(UploadManager uploadManager,Auth auth){
        this.uploadManager = uploadManager;
        this.auth = auth;
    }


    @Override
    public Response uploadFile(File file) throws QiniuException {
        Response response = this.uploadManager.put(file,file.getName(),getUploadToken());
        int retry = 0;
        while(response.needRetry() && retry < 3){
            response = this.uploadManager.put(file,file.getName(),getUploadToken());
            retry++ ;
        }
        return response;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.putPolicy = new StringMap();
        putPolicy.put("returnBody","{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\",\"width\":$(imageInfo.width), \"height\":${imageInfo.height}}");
    }

    private String getUploadToken(){
        return this.auth.uploadToken(bucket,null,3600,putPolicy);
    }
}
