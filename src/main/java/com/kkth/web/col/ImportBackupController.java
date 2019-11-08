package com.kkth.web.col;

import com.kkth.framework.annotations.ApiOperation;
import com.kkth.framework.annotations.Resources;
import com.kkth.framework.controller.SuperController;
import com.kkth.framework.enums.AuthTypeEnum;
import com.kkth.framework.enums.ErrorCodeEnum;
import com.kkth.framework.file.FileUpload;
import com.kkth.framework.responses.ApiResponses;
import com.kkth.framework.utils.ApiAssert;
import com.kkth.web.cpt.xls.GeolandscapeExcel;
import com.kkth.web.model.entity.Geolandscape;
import com.kkth.web.model.vo.FileVo;
import com.kkth.web.service.GeolandscapeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

/**
 * xls 导入
 *
 * @author lichee
 */
@Slf4j
@RestController
@MultipartConfig
@RequestMapping(value = "/importBackup")
public class ImportBackupController extends SuperController {

    @Autowired
    private FileUpload fileUpload;

    @Value("${kkth.file.url}")
    private String fileUrl;

    @Value("${kkth.file.location}")
    private String fileLocation;

    @Autowired
    private GeolandscapeService geolandscapeService;

    @Autowired
    private GeolandscapeExcel geolandscapeExcel;

    @Resources(AuthTypeEnum.LOGIN)
    @ApiOperation("导入xls")
    @PostMapping(value = "/xls")
    public ApiResponses<FileVo> file(HttpServletRequest servletRequest, String keyPath,
        @RequestParam(value="file",required=false)  MultipartFile file) {

        ApiAssert.notNull(ErrorCodeEnum.BAD_REQUEST, keyPath);
        FileVo fileVo = null;
        if (!file.isEmpty()) {
            fileVo = invokeFileUpload(keyPath, file);
            importFile(keyPath, fileVo);
        } else {
            ApiAssert.failure(ErrorCodeEnum.BAD_REQUEST);
        }
        return success(fileVo);
    }

    private void importFile(String keyPath, FileVo fileVo) {

        File file = new File(fileLocation + keyPath, fileVo.getName());
        if(keyPath.equals("geolandscape")){
            List<Geolandscape> list = geolandscapeExcel.readFile(file);
            geolandscapeService.saveBatch(list);
        }
    }

    private FileVo invokeFileUpload(String keyPath, MultipartFile file) {

        String filename = fileUpload.fileUpload(fileLocation + keyPath, file);
        FileVo fileVo = new FileVo();
        fileVo.setPath(fileUrl + keyPath + "/" + filename);
        fileVo.setName(filename);
        fileVo.setOriginalName(file.getOriginalFilename());
        fileVo.setSize(file.getSize());
        return fileVo;
    }

}