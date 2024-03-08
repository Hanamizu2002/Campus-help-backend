package cn.hanamizu.campushelp.mapper;

import cn.hanamizu.campushelp.entity.TaskType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TaskTypeMapper extends BaseMapper<TaskType> {
    List<TaskType> listActiveTaskTypes();
}
