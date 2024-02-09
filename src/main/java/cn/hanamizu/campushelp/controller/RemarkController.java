package cn.hanamizu.campushelp.controller;

import cn.hanamizu.campushelp.entity.Remark;
import cn.hanamizu.campushelp.service.RemarkService;
import cn.hanamizu.campushelp.utils.http.AjaxResult;
import cn.hanamizu.campushelp.utils.pages.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student/remark")
public class RemarkController extends BaseController {
    @Autowired
    private RemarkService remarkService;

    /**
     * 查询remark列表
     */
    @GetMapping("/list")
    public TableDataInfo list(Remark Remark) {
        startPage();
        List<Remark> list = remarkService.selectRemarkList(Remark);
        return getDataTable(list);
    }

    /**
     * 获取remark详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(remarkService.selectRemarkById(id));
    }

    /**
     * 新增remark
     */
    @PostMapping
    public AjaxResult add(@RequestBody Remark Remark) {
        return toAjax(remarkService.insertRemark(Remark));
    }

    /**
     * 修改remark
     */
    @PutMapping
    public AjaxResult edit(@RequestBody Remark Remark) {
        return toAjax(remarkService.updateRemark(Remark));
    }

    /**
     * 删除remark
     */
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(remarkService.deleteRemarkByIds(ids));
    }
}
