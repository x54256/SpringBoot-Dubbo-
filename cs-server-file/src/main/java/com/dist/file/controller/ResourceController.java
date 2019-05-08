package com.dist.file.controller;

import com.dist.base.common.BaseController;
import com.dist.base.constant.GlobalConstant;
import com.dist.base.exception.IllegalParameterException;
import com.dist.base.response.Result;
import com.dist.base.response.ResultUtil;
import com.dist.base.utils.ContextPathUtil;
import com.dist.base.utils.FileUtil;
import com.dist.base.utils.office.OfficeToPdfUtil;
import com.dist.file.report.IReport;
import com.dist.file.report.ReportContainer;
import com.dist.file.service.MongoFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 资源文件的控制器
 *
 * @author yujx
 * @date 2019/03/19 09:47
 */
@RestController
@RequestMapping("/rest/public/resource")
@Api(tags = "ResourceController", description = "资源文件")
public class ResourceController extends BaseController {

    @Value("${local_temp_path}")
    private String localTempPath;

    @Value("${report_save_file_dir}")
    private String saveFileDir;

    @Autowired
    private MongoFileService mongoFileService;

    @Autowired
    private ReportContainer reportContainer;

    @GetMapping("/v1/file/preview/{mongoId}")
    @ApiOperation(value = "根据mongoId下载到本地，返回预览的url", httpMethod = "GET")
    public Result filePreviewByMongoId(
            @ApiParam(value = "mongo上的文件id", required = true) @PathVariable(value = "mongoId") String mongoId,
            @ApiParam(value = "文件名", required = true) @RequestParam(value = "fileName") String fileName,
            @ApiParam(value = "文件后缀（不带.）", required = true) @RequestParam(value = "fileSuffix") String fileSuffix
    ) {

        String url;
        String baseURL = ContextPathUtil.getBaseURL(request);
        String filePath = ContextPathUtil.getContextPath(localTempPath, request) + File.separator + mongoId + File.separator + fileName + "." + fileSuffix;

        if (!new File(filePath).exists()) {
            // 根据文件id下载文件
            mongoFileService.downloadFileById(mongoId, filePath);
        }

        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.getAndIncrement();

        AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<Integer>(0, 1);

        // 根据文件后缀判断是否需要转成pdf
        if (GlobalConstant.NEED_2_CONVERT_PDF.contains(fileSuffix.toLowerCase())) {

            String pdfFilePath = FileUtil.replaceFileSuffix(filePath, "pdf");

            if (!new File(pdfFilePath).exists()) {
                if (!OfficeToPdfUtil.convert2PDF(filePath, pdfFilePath)) {
                    throw new RuntimeException("文件:" + filePath + "转换pdf失败");
                }
            }

            url = baseURL + localTempPath + "/" + mongoId + "/" + fileName + ".pdf";
        } else {
            url = baseURL + localTempPath + "/" + mongoId + "/" + fileName + "." +fileSuffix;
        }

        return ResultUtil.success((Object) url);
    }


    @PostMapping("/v1/report/generate")
    @ApiOperation(value = "生成审查报告，返回下载地址和预览地址", httpMethod = "POST")
    public Result generateReport(
            @ApiParam(value = "生成报告所需参数，json对象") @RequestBody Map<String, Object> map
    ) {

        Integer module = Integer.valueOf(String.valueOf(map.get("module")));

        Map<Integer, IReport> reportMap = reportContainer.getReportMap();

        if (reportMap == null) {
            return ResultUtil.error("系统内部错误，审查报告的map构建失败");
        }

        if (reportMap.containsKey(module)) {
            String tomcatPath = ContextPathUtil.getContextPath(saveFileDir, request);

            // 生成报告返回word文件全路径
            String wordFullPath = reportMap.get(module).generate(map, tomcatPath);
            // 获取文件名
            String fileName = wordFullPath.substring(wordFullPath.lastIndexOf(File.separator) + 1, wordFullPath.length());
            // 生成word文件的下载路径
            String wordDownloadUrl = ContextPathUtil.getBaseURL(request) + saveFileDir + "/" + fileName;

            // 获取pdf文件本地存储路径
            String pdfFullPath = FileUtil.replaceFileSuffix(wordFullPath, "pdf");
            if (!new File(pdfFullPath).exists()) {
                if (!OfficeToPdfUtil.convert2PDF(wordFullPath, pdfFullPath)) {
                    throw new RuntimeException("文件:" + wordFullPath + "转换pdf失败");
                }
            }
            // 生成pdf文件的下载路径
            String pdfDownloadUrl = FileUtil.replaceFileSuffix(wordDownloadUrl, "pdf");

            Map<String, String> downloadMap = new HashMap<>();

            downloadMap.put("wordDownloadUrl", wordDownloadUrl);
            downloadMap.put("pdfDownloadUrl", pdfDownloadUrl);
            return ResultUtil.success(downloadMap);
        }

        throw new IllegalParameterException("传入的module有误，请检查");
    }


//    @ApiOperation(value = "报告预览", httpMethod = "GET")
//    @GetMapping("/v1/report/preview/{fileName}")
//    public Result preview(
//            @ApiParam(value = "报告文件名称(不带后缀)", required = true) @PathVariable(value = "fileName") String fileName) {
//
//        String basePath = ContextPathUtil.getContextPath(saveFileDir, request) + File.separator + fileName;
//
//        String docPath = basePath + ".doc";
//        String pdfPath = basePath + ".pdf";
//
//        if (!new File(docPath).exists()) {
//            throw new ResourceGetException("文件不存在，请先生成审查报告");
//        }
//
//        if (!new File(pdfPath).exists()) {
//            if (!OfficeToPdfUtil.convert2PDF(docPath, pdfPath)) {
//                throw new RuntimeException("文件:" + docPath + "转换pdf失败");
//            }
//        }
//
//        String url = ContextPathUtil.getBaseURL(request) + saveFileDir + "/" + fileName + ".pdf";
//        return ResultUtil.success((Object) url);
//    }


}
