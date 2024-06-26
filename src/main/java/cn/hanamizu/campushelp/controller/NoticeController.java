package cn.hanamizu.campushelp.controller;

import cn.hanamizu.campushelp.entity.Notice;
import cn.hanamizu.campushelp.service.NoticeService;
import cn.hanamizu.campushelp.utils.http.AjaxResult;
import cn.hanamizu.campushelp.utils.pages.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 通知Controller
 */
@RestController
@RequestMapping("/notice/advise")
public class NoticeController extends BaseController {
    @Autowired
    private NoticeService noticeService;

    /**
     * 查询通知公告公告列表
     */
    @GetMapping("/list")
    public TableDataInfo list(Notice notice) {
        startPage();
        List<Notice> list = noticeService.selectNoticeList(notice);
        return getDataTable(list);
    }


    /**
     * 获取通知公告公告详细信息
     */
    @GetMapping(value = "/{noticeId}")
    public AjaxResult getInfo(@PathVariable("noticeId") Integer noticeId) {
        return AjaxResult.success(noticeService.selectNoticeByNoticeId(noticeId));
    }

    /**
     * 新增通知公告公告
     */
    @PostMapping
    public AjaxResult add(@RequestBody Notice notice) {

        return toAjax(noticeService.insertNotice(notice));
    }

    /**
     * 修改通知公告公告
     */
    @PutMapping
    public AjaxResult edit(@RequestBody Notice notice) {
        return toAjax(noticeService.updateNotice(notice));
    }

    /**
     * 删除通知公告公告
     */
    @DeleteMapping("/{noticeIds}")
    public AjaxResult remove(@PathVariable Integer[] noticeIds) {
        return toAjax(noticeService.deleteNoticeByNoticeIds(noticeIds));
    }
}
