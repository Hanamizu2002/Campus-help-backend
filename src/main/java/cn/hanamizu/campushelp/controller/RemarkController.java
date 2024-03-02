package cn.hanamizu.campushelp.controller;

import cn.hanamizu.campushelp.entity.Remark;
import cn.hanamizu.campushelp.service.ConfigService;
import cn.hanamizu.campushelp.service.RemarkService;
import cn.hanamizu.campushelp.utils.http.AjaxResult;
import cn.hanamizu.campushelp.utils.pages.TableDataInfo;
import cn.hanamizu.campushelp.utils.tools.PocketMoney;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student/remark")
public class RemarkController extends BaseController {
    @Autowired
    private RemarkService remarkService;
    @Autowired
    private ConfigService configService;
    @Autowired
    private PocketMoney money;

    /**
     * 查询remark列表
     */
    @GetMapping("/list")
    public TableDataInfo list(Remark remark) {
        startPage();
        List<Remark> list = remarkService.selectRemarkList(remark);
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
    public AjaxResult add(@RequestBody Remark remark) {
        if (remarkService.insertRemark(remark) > 1) {
            money.transfer("coin=coin+", Double.parseDouble(configService.getValueByKey("CoinBack")), remark.getPublish().getStudentId());
            return toAjax(true);
        }
        return toAjax(false);
    }

    /**
     * 修改remark
     */
    @PutMapping
    public AjaxResult edit(@RequestBody Remark remark) {
        return toAjax(remarkService.updateRemark(remark));
    }

    /**
     * 删除remark
     */
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(remarkService.deleteRemarkByIds(ids));
    }
}
