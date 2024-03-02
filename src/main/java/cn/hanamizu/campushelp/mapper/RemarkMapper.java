package cn.hanamizu.campushelp.mapper;

import cn.hanamizu.campushelp.entity.Remark;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RemarkMapper extends BaseMapper<Remark> {
    /**
     * 查询remark
     *
     * @param id remark主键
     * @return remark
     */
    Remark selectRemarkById(Long id);

    /**
     * 查询remark列表
     *
     * @param Remark remark
     * @return remark集合
     */
    List<Remark> selectRemarkList(Remark Remark);

    /**
     * 新增remark
     *
     * @param Remark remark
     * @return 结果
     */
    int insertRemark(Remark Remark);

    /**
     * 修改remark
     *
     * @param Remark remark
     * @return 结果
     */
    int updateRemark(Remark Remark);

    /**
     * 删除remark
     *
     * @param id remark主键
     * @return 结果
     */
    int deleteRemarkById(Long id);

    /**
     * 批量删除remark
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteRemarkByIds(Long[] ids);
}

