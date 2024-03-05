package cn.hanamizu.campushelp.service;

import cn.hanamizu.campushelp.entity.Notice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeService {
    /**
     * 查询通知公告公告
     *
     * @param noticeId 通知公告公告主键
     * @return 通知公告公告
     */
    Notice selectNoticeByNoticeId(Integer noticeId);

    /**
     * 查询通知公告公告列表
     *
     * @param notice 通知公告公告
     * @return 通知公告公告集合
     */
    List<Notice> selectNoticeList(Notice notice);

    /**
     * 新增通知公告公告
     *
     * @param notice 通知公告公告
     * @return 结果
     */
    int insertNotice(Notice notice);

    /**
     * 修改通知公告公告
     *
     * @param notice 通知公告公告
     * @return 结果
     */
    int updateNotice(Notice notice);

    /**
     * 批量删除通知公告公告
     *
     * @param noticeIds 需要删除的通知公告公告主键集合
     * @return 结果
     */
    int deleteNoticeByNoticeIds(Integer[] noticeIds);

    /**
     * 删除通知公告公告信息
     *
     * @param noticeId 通知公告公告主键
     * @return 结果
     */
    int deleteNoticeByNoticeId(Integer noticeId);
}
