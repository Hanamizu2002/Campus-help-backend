package cn.hanamizu.campushelp.service;

import cn.hanamizu.campushelp.entity.Remark;

import java.util.List;

public interface RemarkService {
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
     * 批量删除remark
     *
     * @param ids 需要删除的remark主键集合
     * @return 结果
     */
    int deleteRemarkByIds(Long[] ids);

    /**
     * 删除remark信息
     *
     * @param id remark主键
     * @return 结果
     */
    int deleteRemarkById(Long id);
}
