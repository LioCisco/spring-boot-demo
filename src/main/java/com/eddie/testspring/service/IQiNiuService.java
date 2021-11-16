package com.eddie.testspring.service;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;

import java.io.File;

/**
 * @author Eddie.Liao
 * @description: TODO
 * @date 2021/11/16 12:38 下午
 */
public interface IQiNiuService {

    Response uploadFile(File file) throws QiniuException;
}
