package cn.hanamizu.campushelp.service.impl;

import cn.hanamizu.campushelp.entity.Notice;
import cn.hanamizu.campushelp.mapper.NoticeMapper;
import cn.hanamizu.campushelp.service.NoticeService;
import cn.hanamizu.campushelp.utils.tools.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private NoticeMapper noticeMapper;

    /**
     * 查询通知公告公告
     *
     * @param noticeId 通知公告公告主键
     * @return 通知公告公告
     */
    @Override
    public Notice selectNoticeByNoticeId(Integer noticeId) {
        return noticeMapper.selectNoticeByNoticeId(noticeId);
    }

    /**
     * 查询通知公告公告列表
     *
     * @param notice 通知公告公告
     * @return 通知公告公告
     */
    @Override
    public List<Notice> selectNoticeList(Notice notice) {
        return noticeMapper.selectNoticeList(notice);
    }

    /**
     * 新增通知公告公告
     *
     * @param notice 通知公告公告
     * @return 结果
     */
    @Override
    public int insertNotice(Notice notice) {
        notice.setCreateTime(DateUtil.getNowDate());
        return noticeMapper.insertNotice(notice);
    }

    /**
     * 修改通知公告公告
     *
     * @param notice 通知公告公告
     * @return 结果
     */
    @Override
    public int updateNotice(Notice notice) {
        return noticeMapper.updateNotice(notice);
    }

    /**
     * 批量删除通知公告公告
     *
     * @param noticeIds 需要删除的通知公告公告主键
     * @return 结果
     */
    @Override
    public int deleteNoticeByNoticeIds(Integer[] noticeIds) {
        return noticeMapper.deleteNoticeByNoticeIds(noticeIds);
    }

    /**
     * 删除通知公告公告信息
     *
     * @param noticeId 通知公告公告主键
     * @return 结果
     */
    @Override
    public int deleteNoticeByNoticeId(Integer noticeId) {
        return noticeMapper.deleteNoticeByNoticeId(noticeId);
    }
}

