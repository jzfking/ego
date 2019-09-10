package com.dj.ego.io;

import com.dj.ego.common.GlobalException;
import com.dj.ego.common.service.ServiceStatus;
import com.dj.ego.config.ResourceProperties;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @author 戴俊明
 * @className FileControl
 * @description 文件上传和下载
 * @date 2019/8/22 20:36
 **/
@Api("文件上传和下载")
@RestController
@RequestMapping("/file")
public class FileControl {

    @Autowired
    FileUtil fileUtil;
    @Autowired
    ResourceProperties resourceProperties;

    @PostMapping
    public ResponseEntity upload(@RequestParam MultipartFile file) {
        fileUtil.save(file, resourceProperties.getDynamicFilesPath());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping(value = "/{fileId}")
    public ResponseEntity delete(@PathVariable String fileId) {
        File file = new File(resourceProperties.getDynamicFilesPath() + fileId);
        if (!file.exists()) {
            throw GlobalException.builder().httpStatus(HttpStatus.NOT_FOUND)
                    .serviceStatus(ServiceStatus.file_can_not_find).build();
        } else {
            fileUtil.delete(file);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping(value = "/{fileName}/{fileId}")
    public void download(@PathVariable String fileName, @PathVariable String fileId, HttpServletRequest request, HttpServletResponse response) {

        File file = new File(resourceProperties.getDynamicFilesPath() + fileId);
        if (!file.exists()) {
            throw GlobalException.builder().httpStatus(HttpStatus.NOT_FOUND)
                    .serviceStatus(ServiceStatus.file_can_not_find).build();
        } else {
            response.reset();
            response.setContentType("application/octet-stream");
            response.setCharacterEncoding("UTF-8");
            response.addHeader("Content-Disposition",
                    "attachment;filename*=UTF-8''" + URLEncoder.encode(fileName, StandardCharsets.UTF_8));
            response.addHeader("Content-Length", "" + file.length());

            response.setHeader("Accept-Ranges", "bytes");
            long downloadSize = file.length();
            long fromPos = 0, toPos = 0;

            String range = request.getHeader("Range");
            if (null == range) {
                response.setHeader("Content-Length", downloadSize + "");
            } else {
                // 若客户端传来Range，说明之前下载了一部分，设置206状态(SC_PARTIAL_CONTENT)
                response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
                String bytes = range.replaceAll("bytes=", "");
                String[] ary = bytes.split("-");
                fromPos = Long.parseLong(ary[0]);
                //Range字段的值有两个
                final int rangeArgs = 2;
                if (ary.length == rangeArgs) {
                    toPos = Long.parseLong(ary[1]);
                }
                int size;
                if (toPos > fromPos) {
                    size = (int) (toPos - fromPos);
                } else {
                    size = (int) (downloadSize - fromPos);
                }
                response.setHeader("Content-Length", size + "");
                downloadSize = size;
            }

            RandomAccessFile in = null;
            ServletOutputStream out = null;
            try {
                in = new RandomAccessFile(file, "rw");
                // 设置下载起始位置
                if (fromPos > 0) {
                    in.seek(fromPos);
                }
                // 缓冲区大小
                int bufLen = (int) (downloadSize < 2048 ? downloadSize : 2048);
                byte[] buffer = new byte[bufLen];
                int num;
                int count = 0;
                // 当前写到客户端的大小
                out = response.getOutputStream();
                while ((num = in.read(buffer)) != -1) {
                    out.write(buffer, 0, num);
                    count += num;
                    //处理最后一段，计算不满缓冲区的大小
                    if (downloadSize - count < bufLen) {
                        bufLen = (int) (downloadSize - count);
                        if (bufLen == 0) {
                            break;
                        }
                        buffer = new byte[bufLen];
                    }
                }
                response.flushBuffer();
            } catch (IOException ignored) {
            } finally {
                try {
                    if (in != null) {
                        in.close();
                    }
                    if (out != null) {
                        out.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
