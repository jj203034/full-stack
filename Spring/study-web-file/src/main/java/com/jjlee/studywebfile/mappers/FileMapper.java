package com.jjlee.studywebfile.mappers;

import com.jjlee.studywebfile.entities.FileEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileMapper {
    int insertFile(FileEntity file);

}
