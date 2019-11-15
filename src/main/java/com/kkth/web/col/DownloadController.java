package com.kkth.web.col;

import com.kkth.framework.annotations.ApiOperation;
import com.kkth.framework.annotations.Resources;
import com.kkth.framework.controller.SuperController;

import com.kkth.framework.enums.AuthTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * 文件下载
 *
 * @author lichee
 */
@Slf4j
@RestController
@RequestMapping(value = "/download")
public class DownloadController extends SuperController {

    @Value("${kkth.file.location}")
    private String fileLocation;

    @Resources(AuthTypeEnum.LOGIN)
    @ApiOperation("下载文件")
    @GetMapping("{keyPath}/{fileName}")
    public void downloadFile(@PathVariable String keyPath,
        @PathVariable String fileName, HttpServletResponse response){

        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = new FileInputStream(new File(fileLocation + keyPath, fileName));
            outputStream = response.getOutputStream();
            response.setContentType("application/x-download");
            response.setCharacterEncoding("utf-8");
            String name = URLEncoder.encode(fileName, "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + name);
            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}