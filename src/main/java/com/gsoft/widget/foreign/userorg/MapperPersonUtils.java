package com.gsoft.widget.foreign.userorg;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gsoft.cos3.util.Assert;


@Service
public class MapperPersonUtils {

    @Resource
    private UserForeignService personFeign;
    
    public Map<Long,PersonnelDto> mapperAllPersonList(){
        //这里映射不应该只是从当前登录人机构开始，应该就是拿到该维度下面的所有人
        Map<Long,PersonnelDto> map=new HashMap<>();
        List<PersonnelDto> allPersonByDimension = personFeign.getAllPerson();
        if(Assert.isEmpty(allPersonByDimension)){
            return map;
        }
        //list转map
        map = allPersonByDimension.stream().collect(Collectors.toMap(PersonnelDto::getId, personnelDto -> personnelDto));
        return map;
    }

}
