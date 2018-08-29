package com.gsoft.widget.foreign.file;

import com.gsoft.widget.foreign.FeignClientConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@FeignClient(value = "cos3-file-manager", configuration = FeignClientConfiguration.class)
public interface FileForeign {

    @RequestMapping("/file/getFileByRefId/{referenceId}")
    public byte[] getFilePathByRefId(@PathVariable("referenceId") String referenceId);


}
