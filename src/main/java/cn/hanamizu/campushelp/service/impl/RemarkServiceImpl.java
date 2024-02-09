package cn.hanamizu.campushelp.service.impl;

import cn.hanamizu.campushelp.entity.Remark;
import cn.hanamizu.campushelp.mapper.RemarkMapper;
import cn.hanamizu.campushelp.service.RemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RemarkServiceImpl implements RemarkService {
    @Autowired
    private RemarkMapper remarkMapper;

    /**
     * 查询remark
     *
     * @param id remark主键
     * @return remark
     */
    @Override
    public Remark selectRemarkById(Long id) {
        return remarkMapper.selectRemarkById(id);
    }

    /**
     * 查询remark列表
     *
     * @param Remark remark
     * @return remark
     */
    @Override
    public List<Remark> selectRemarkList(Remark Remark) {
        return remarkMapper.selectRemarkList(Remark);
    }

    /**
     * 新增remark
     *
     * @param Remark remark
     * @return 结果
     */
    @Override
    public int insertRemark(Remark Remark) {
        return remarkMapper.insertRemark(Remark);
    }

    /**
     * 修改remark
     *
     * @param Remark remark
     * @return 结果
     */
    @Override
    public int updateRemark(Remark Remark) {
        return remarkMapper.updateRemark(Remark);
    }

    /**
     * 批量删除remark
     *
     * @param ids 需要删除的remark主键
     * @return 结果
     */
    @Override
    public int deleteRemarkByIds(Long[] ids) {
        return remarkMapper.deleteRemarkByIds(ids);
    }

    /**
     * 删除remark信息
     *
     * @param id remark主键
     * @return 结果
     */
    @Override
    public int deleteRemarkById(Long id) {
        return remarkMapper.deleteRemarkById(id);
    }
}

