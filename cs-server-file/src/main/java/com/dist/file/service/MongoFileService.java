package com.dist.file.service;

import com.dist.base.exception.ResourceGetException;
import com.dist.base.utils.FileUtil;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author yujx
 * @date 2019/03/19 10:03
 */
@Slf4j
@Service
public class MongoFileService {

    @Autowired
    private GridFsTemplate gridFsTemplate;  // 存取文件信息

    @Autowired
    private GridFSBucket gridFSBucket;  // 存取文件块


    /**
     * 从mongo中下载文件
     *
     * @param mongoId
     * @param localTempPath 本地文件路径
     */
    public void downloadFileById(String mongoId, String localTempPath) {

        // 创建目录
        FileUtil.createDirs(localTempPath);

        InputStream inputStream = this.getInputStreamById(mongoId);

        //将文件保存到服务器物理路径上
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(new File(localTempPath));
            IOUtils.copy(inputStream, fileOutputStream);
            log.info("文件{}下载成功，本地文件路径为：{}", mongoId, localTempPath);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // 检测文件的合法性，不合法删除
        if (!FileUtil.checkFile(localTempPath)) {
            throw new ResourceGetException("文件下载失败");
        }

    }


    // TODO: 2019/3/27 为啥return了inputStream，不能关gridFSDownloadStream

    /**
     * 根据文件id获取文件流
     *
     * @param fileId
     * @return
     */
    private InputStream getInputStreamById(String fileId) {
        //文件对象
        GridFSFile gridFSFile = gridFsTemplate.findOne(Query.query(Criteria.where("_id").is(fileId)));

        //打开下载流
        GridFSDownloadStream gridFSDownloadStream = gridFSBucket.openDownloadStream(gridFSFile.getObjectId());

        //定义GridFsResource
        GridFsResource gridFsResource = new GridFsResource(gridFSFile, gridFSDownloadStream);
        InputStream inputStream = null;

        try {
            inputStream = gridFsResource.getInputStream();
            return inputStream;
        } catch (IOException e) {
            e.printStackTrace();
            throw new ResourceGetException("文件流获取失败");
        } finally {
            // 这里不是断掉引用，是真的把流关了，从而return的流也用不了了
//            if (inputStream != null) {
//                try {
//                    inputStream.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }

//            // 关闭下载流
//            gridFSDownloadStream.close();
        }
    }
}
