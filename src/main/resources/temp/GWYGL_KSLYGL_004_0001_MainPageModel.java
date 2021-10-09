package com.insigma.siis.local.pagemodel.GWYGL.GWYGL_KSLYGL.#{packName};


import cn.hutool.core.util.StrUtil;
import com.insigma.odin.framework.AppException;
import com.insigma.odin.framework.persistence.HBSession;
import com.insigma.odin.framework.persistence.HBUtil;
import com.insigma.odin.framework.radow.PageModel;
import com.insigma.odin.framework.radow.RadowException;
import com.insigma.odin.framework.radow.annotation.*;
import com.insigma.odin.framework.radow.element.ElementType;
import com.insigma.odin.framework.radow.event.EventMessageType;
import com.insigma.odin.framework.radow.event.EventRtnType;
import com.insigma.odin.framework.radow.event.NextEventValue;
import com.insigma.odin.framework.sys.SysfunctionManager;
import com.insigma.odin.framework.util.SysUtil;
import com.insigma.siis.local.business.GWYGL.GWYGL_KSLYGL.GWYGL_KSLYGL_004_0001_BS;
import com.insigma.siis.local.business.GWYGL.GWYGL_PUBLIC.GWYGL_PUBLIC_001_0001.AreaBS;
import com.insigma.siis.local.business.GWYGL.GWYGL_PUBLIC.GWYGL_PUBLIC_002_0001.BusinessModelBS;
import com.insigma.siis.local.business.GWYGL.GWYGL_PUBLIC.GWYGL_PUBLIC_003_0003.GWYGL_PUBLIC_003_0003_BS;
import com.insigma.siis.local.business.GWYGL.GWYGL_PUBLIC.GWYGL_PUBLIC_004_0001.OpLogBS;
import com.insigma.siis.local.business.entity.M01;
import com.insigma.siis.local.business.entity.Y25;
import com.insigma.siis.local.business.entity.Y26;
import com.insigma.siis.local.business.publicServantManage.ExportAsposeBS;
import com.insigma.siis.local.business.utils.SevenZipUtil;
import com.insigma.siis.local.dto.QUERYDTO;
import com.insigma.siis.local.dto.Y25DTO;
import com.insigma.siis.local.dto.enums.M01Enum;
import com.insigma.siis.local.dto.enums.M02Enum;
import com.insigma.siis.local.epsoft.config.AppConfig;
import com.insigma.siis.local.epsoft.util.LogUtil;
import com.insigma.siis.local.util.FilePathStoreUtil;
import com.insigma.siis.local.util.JsonUtil;
import org.apache.commons.lang.StringUtils;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;


public class GWYGL_KSLYGL_004_0001_MainPageModel extends PageModel {

    private final GWYGL_KSLYGL_004_0001_BS bs1 = new GWYGL_KSLYGL_004_0001_BS();
    private final BusinessModelBS bs2 = new BusinessModelBS();
    private final GWYGL_PUBLIC_003_0003_BS public_003_0003_bs = new GWYGL_PUBLIC_003_0003_BS();
    private final LogUtil applog = new LogUtil();


    /**
     *===============================================
     * 方法名称: 页面初始化方法
     * 方法创建日期:2021/5/8
     * 方法创建人员:zyy
     * 方法最后修改日期:2021/5/8
     * 方法最后修改人员:zyy
     * 方法功能描述:
     * 设计参考文档:
     * 结果结构详述:
     *
     * @throws
     *===============================================
     */
    @Override
    public int doInit() throws RadowException {
        this.setNextEventName("initX");
        return EventRtnType.NORMAL_SUCCESS;
    }

    /**
     *===============================================
     * 方法名称: 页面所需要的数据初始化
     * 方法创建日期:2021/5/8
     * 方法创建人员:zyy
     * 方法最后修改日期:2021/5/8
     * 方法最后修改人员:zyy
     * 方法功能描述:
     * 设计参考文档:
     * 结果结构详述:
     *
     * @throws
     *===============================================
     */
    @PageEvent("initX")
    public int initX(){
        try {
            String personA0000=this.getPageElement("personA0000").getValue();
            QUERYDTO querydto = new QUERYDTO(M01Enum.M01_0111);
            copyObjValueToElement(querydto, this);
            this.getPageElement("y2505").setValue(SysfunctionManager.getCurrentSysfunction().getParam2());
//            String sql = bs2.getBusinesStatuSqlNew(M01Enum.M01_0111);
            String endLink = bs1.getEndLink();
//            this.getExecuteSG().addExecuteCode("loadSelectStore(\"m0213_select\",\"" + sql + "\")");
            String m0213 = bs2.getModelState(M01Enum.M01_0111.getM0113(), "");
            this.getPageElement("m0213").setValue(m0213);
            M01 m01 = bs2.getM01ByM0113(M01Enum.M01_0111.getM0113());
            this.getPageElement("endLink").setValue(endLink);
            this.getPageElement("m0100").setValue(m01.getM0100());
            getPageElement("judgmentState").setValue("drafts");
            this.setNextEventName("numberOfProcesses");
//            updateProcessNum();


            if(!personA0000.equals("")){
                this.setNextEventName("openNew");
            }else{
                this.setNextEventName("add");
            }
        } catch (AppException|RadowException e) {
            e.printStackTrace();
        }
        return EventRtnType.NORMAL_SUCCESS;
    }


    /**
     * 查询流程数
     * @return
     * @throws RadowException
     * @throws AppException
     */
    @PageEvent("numberOfProcesses")
    public int numberOfProcesses() throws RadowException, AppException {
        HBSession session = HBUtil.getHBSession();
        List<Object> countList = bs1.getProcesses(AreaBS.getUserSsjbbm());
        //设置流程的总数
        this.getPageElement("processingData").setValue(countList.get(2).toString());
        this.getPageElement("numberOfSettlements").setValue(countList.get(1).toString());
        this.getPageElement("numberOfDrafts").setValue(countList.get(0).toString());
        this.getExecuteSG().addExecuteCode("numberYW()");
        return EventRtnType.NORMAL_SUCCESS;
    }


    /**
     *===============================================
     * 方法名称: 页面grid列表数据查询
     * 方法创建日期:2021/5/8
     * 方法创建人员:zyy
     * 方法最后修改日期:2021/5/8
     * 方法最后修改人员:zyy
     * 方法功能描述:
     * 设计参考文档:
     * 结果结构详述:
     *
     * @throws
     *===============================================
     */
    @PageEvent("grid.dogridquery")
    @NoRequiredValidate
    public int doGridQuery(int start, int limit) throws RadowException, AppException {
//        QUERYDTO querydto = new QUERYDTO();
//        this.copyElementsValueToObj(querydto, this);
////        String m0213_select = this.getPageElement("m0213_select").getValue();
//        String m0213_select = this.getPageElement("judgmentState").getValue();
//        querydto.setB0111(AreaBS.getUserSsjbbm());
//        if(!querydto.getY0181_sb1().isEmpty()){
//            querydto.setY0181_sb1(querydto.getY0181_sb1().replaceAll("-", ""));
//        }
//        if(!querydto.getY0181_sb2().isEmpty()){
//            querydto.setY0181_sb2(querydto.getY0181_sb2().replaceAll("-", ""));
//        }

        Map<String, Object> map = new HashMap<String, Object>();
        String y2998 = "";
        String judgmentState = getPageElement("judgmentState").getValue();
        if ("inProcess".equals(judgmentState)){
            map.put("state1"," AND(y0118 LIKE '0%' OR y0118 LIKE '1%' OR y0118 LIKE '2%' OR y0118 LIKE '3%')and y2598!='00'");
        }else if ("completed".equals(judgmentState)){
            y2998 ="99";
        }else if ("drafts".equals(judgmentState)){
            y2998 ="00";
        }

        map.put("y2503", "");
        map.put("askName",getPageElement("y2502").getValue());

        map = bs2.getY01BussinessStatus(map,y2998);
        map.put("b0111", AreaBS.getUserSsjbbm());
        map.put("isaudit", SysfunctionManager.getCurrentSysfunction().getParam1());
        map.put("userid",AreaBS.getUserid());

//        updateProcessNum();
        this.setNextEventName("numberOfProcesses");

        try {
            long startTime = System.currentTimeMillis();
            this.pageQuery(bs1.getMainGridStr(map), "SQL", start, limit);
            applog.createLog("Y25", "A01", AreaBS.getUserid(), SysUtil.getCacheCurrentUser().getName(), "考录录用申报列表查询", null);
            long endTime = System.currentTimeMillis();
            System.out.println("sql ==== "+bs1.getMainGridStr(map));
            System.out.println("start === "+start +",   limit === "+limit);
            System.out.println("执行查询的时间花费====="+(endTime-startTime));
        } catch (AppException | SQLException e) {
            e.printStackTrace();
        }
        return EventRtnType.SPE_SUCCESS;
    }


    /**
     *===============================================
     * 方法名称: 报送前校验
     * 方法创建日期:2021/5/8
     * 方法创建人员:zyy
     * 方法最后修改日期:2021/5/8
     * 方法最后修改人员:zyy
     * 方法功能描述:
     * 设计参考文档:
     * 结果结构详述:
     *
     * @throws
     *===============================================
     */
    @PageEvent("startLast")
    public int startLast(String y2500){
        HBSession sess = HBUtil.getHBSession();
        Y25 y25 = (Y25) sess.get(Y25.class, y2500);
        try {
            String m0213 = bs2.getModelState(M01Enum.M01_0111.getM0113(), y2500);
            String endLink = bs1.getEndLink();
            String check = bs1.check(y2500, m0213,endLink);
            List<Y26> y26List = bs1.getY26ListByY2500(y2500);
            if (y26List.size() < 1) {
                this.getExecuteSG().addExecuteCode("window.fn.hMessage({ type:\"info\",txt:\"请添加人员后再进行送审!\"})");
                return EventRtnType.FAILD;
            }
            if (!"0".equals(check)) {
                this.getExecuteSG().addExecuteCode("window.fn.hMessage({ type:\"warn\",txt:\""+check+"人信息未录入,请补录后再送审!\"})");
                return EventRtnType.FAILD;
            }
            if("1".equals(AppConfig.IS_CLASSIFIED_SYSTEM)){
                if (StringUtils.isEmpty(y25.getY2597())) {
                    this.setMainMessage("公务员考录录用密级等级不能为空");
                    return EventRtnType.FAILD;
                }
            }
            if ("1".equals(AppConfig.OFD_OPEN)) {
                for (Y26 y26 : y26List) {
                    String haveofd = bs2.isHaveOFD(y26.getY2500(), y26.getY2600());
                    if ("0".equals(haveofd)) {
                        this.getExecuteSG().addExecuteCode("window.fn.hMessage({ type:\"warn\",txt:\"还存在人员未盖章!\"})");
                        return EventRtnType.FAILD;
                    }
                }
            }
        } catch (AppException e) {
            e.printStackTrace();
        }
        if("1".equals(AppConfig.IS_CLASSIFIED_SYSTEM)){
            if(StringUtils.isNotEmpty(y25.getY2597())) {
                String mj = y25.getY2597().equals("3")?"秘密":"内部";
                this.getExecuteSG().addExecuteCode("hint('" + mj + "','start','" + y2500 + "')");
            }else{
                this.setMainMessage("当前业务密级为空，请编辑后再发送！","warn");
                return EventRtnType.FAILD;
            }
        }else{
            this.addNextEvent(NextEventValue.YES, "start",y2500);
            this.addNextEvent(NextEventValue.CANNEL, "cancelEvent");
            this.setMessageType(EventMessageType.CONFIRM);
            this.setMainMessage("确定要发送该请示吗？发送后无法修改！");
        }
        return EventRtnType.NORMAL_SUCCESS;
    }


    /**
     *===============================================
     * 方法名称: 业务报送
     * 方法创建日期:2021/5/8
     * 方法创建人员:zyy
     * 方法最后修改日期:2021/5/8
     * 方法最后修改人员:zyy
     * 方法功能描述:
     * 设计参考文档:
     * 结果结构详述:
     *
     * @throws
     *===============================================
     */
    @PageEvent("start")
    @Transaction
    @NoRequiredValidate
    public int startEvent(String y0000) {
        try {
            //预警
            String b0111 = AreaBS.getUserSsjbbm();
            String m0213 = bs2.getModelState(M01Enum.M01_0111.getM0113(), y0000);
            Y25DTO y25dto = bs1.getY25(y0000, false);
            bs2.updateY01For00(y0000,M01Enum.M01_0111, "00", y25dto.getY2502(),"#{packName}");
            bs2.startFlow(y0000, "", M01Enum.M01_0111, m0213, y25dto.getY2502(), "考录录用流程发起");
            y25dto = bs1.stratY25(y0000, m0213);
            String msg = new BusinessModelBS().getFlowYesTip(y0000);
            applog.createLog("Y25", "A01", AreaBS.getUserid(), SysUtil.getCacheCurrentUser().getName(), "考录录用申报流程发送请示", null);
            //执行送审申报
            this.getExecuteSG().addExecuteCode("window.fn.hMessage({ type:\"success\",txt:\"发送成功！\"})");
            //刷新左侧人员数量,附件数量,已填写数据为0
            this.getExecuteSG().addExecuteCode("ydySetPersonNum($('#m0100').val(),'0')");
            this.getExecuteSG().addExecuteCode("ydySetFileNum($('#m0100').val(),'0')");
            this.getExecuteSG().addExecuteCode("ydySetWritePersonNum($('#m0100').val(),'0')");
        } catch (AppException | SQLException e) {
            e.printStackTrace();
            this.getExecuteSG().addExecuteCode("window.fn.hMessage({ type:\"warn\",txt:\""+e.getMessage()+"\"})");
        } catch (RadowException e) {
            this.getExecuteSG().addExecuteCode("window.fn.hMessage({ type:\"warn\",txt:\"发送失败!\"})");//提示用户发送失败
            e.printStackTrace();
        }
        this.setNextEventName("grid.dogridquery");
        this.setNextEventName("numberOfProcesses");
        return EventRtnType.NORMAL_SUCCESS;
    }


    /**
     *===============================================
     * 方法名称: 页面报送
     * 方法创建日期:2021/5/8
     * 方法创建人员:zyy
     * 方法最后修改日期:2021/5/8
     * 方法最后修改人员:zyy
     * 方法功能描述:
     * 设计参考文档:
     * 结果结构详述:
     *
     * @throws
     *===============================================
     */
    @PageEvent("start2")
    @Transaction
    @NoRequiredValidate
    public int start2Event(String y0000) {
        try {
            String m0213 = bs2.getModelState(M01Enum.M01_0111.getM0113(), y0000);
            String endLink = bs1.getEndLink();
            String check = bs1.check(y0000, m0213,endLink);
            if (!"0".equals(check)) {
                this.getExecuteSG().addExecuteCode("window.fn.hMessage({ type:\"warn\",txt:\"依然存在"+ check +"人信息未录入\"})");
                return EventRtnType.FAILD;
            }
            bs2.updateFlowYes(y0000, m0213, "GWYGL_KSLYGL_004_0001_3002");
            Y25DTO y25dto = bs1.stratY25(y0000, m0213);
            applog.createLog("Y25", "A01", AreaBS.getUserid(), SysUtil.getCacheCurrentUser().getName(), "考录录用申报流程发送请示", null);
            //执行送审申报
            this.getExecuteSG().addExecuteCode("window.fn.hMessage({ type:\"success\",txt:\"已将【"+ y25dto.getY2502() +"】送审成功\"})");
        } catch (AppException | SQLException e) {
            e.printStackTrace();
            this.getExecuteSG().addExecuteCode("window.fn.hMessage({ type:\"warn\",txt:\""+e.getMessage()+"\"})");
        } catch (RadowException e) {
            e.printStackTrace();
        }
        this.setNextEventName("grid.dogridquery");
        return EventRtnType.NORMAL_SUCCESS;
    }



    /**
     *===============================================
     * 方法名称: 跳转人员查看页面
     * 方法创建日期:2021/5/8
     * 方法创建人员:zyy
     * 方法最后修改日期:2021/5/8
     * 方法最后修改人员:zyy
     * 方法功能描述:
     * 设计参考文档:
     * 结果结构详述:
     *
     * @throws
     *===============================================
     */
    @PageEvent("view")
    public int viewEvent(String y2500) throws RadowException {
        this.setRadow_parent_data(y2500);
        this.getExecuteSG().addExecuteCode("moveAddnone()");
        this.openWindowWithMax("lookWindow", "pages.GWYGL.GWYGL_KSLYGL.#{packName}.GWYGL_KSLYGL_004_0001_MainLook");
        return EventRtnType.NORMAL_SUCCESS;
    }

    /**
     *===============================================
     * 方法名称: 调用页面grid查询方法
     * 方法创建日期:2021/5/8
     * 方法创建人员:zyy
     * 方法最后修改日期:2021/5/8
     * 方法最后修改人员:zyy
     * 方法功能描述:
     * 设计参考文档:
     * 结果结构详述:
     *
     * @throws
     *===============================================
     */
    @PageEvent("query")
    @NoRequiredValidate
    @AutoNoMask
    public int queryEvent() {
        this.setNextEventName("grid.dogridquery");
        return EventRtnType.NORMAL_SUCCESS;
    }


    /**
     *===============================================
     * 方法名称: 重置按钮
     * 方法创建日期:2021/5/8
     * 方法创建人员:zyy
     * 方法最后修改日期:2021/5/8
     * 方法最后修改人员:zyy
     * 方法功能描述:
     * 设计参考文档:
     * 结果结构详述:
     *
     * @throws
     *===============================================
     */
    @PageEvent("reset")
    @NoRequiredValidate
    @AutoNoMask
    public int resetEvent() throws RadowException {
        try {
            QUERYDTO querydto = new QUERYDTO(M01Enum.M01_0111);
            querydto.setM0213_select("-1");
            copyObjValueToElement(querydto, this);
            this.getPageElement("y2505").setValue(SysfunctionManager.getCurrentSysfunction().getParam2());
            this.setNextEventName("grid.dogridquery");
        } catch (AppException e) {
            e.printStackTrace();
        }
        return EventRtnType.NORMAL_SUCCESS;
    }

    /**
     *===============================================
     * 方法名称: 跳转新增编辑页面
     * 方法创建日期:2021/5/8
     * 方法创建人员:zyy
     * 方法最后修改日期:2021/5/8
     * 方法最后修改人员:zyy
     * 方法功能描述:
     * 设计参考文档:
     * 结果结构详述:
     *
     * @throws
     *===============================================
     */
    @PageEvent("added")
    @NoRequiredValidate
    @AutoNoMask
    public int addedEvent(String y2500) throws RadowException, AppException {

        String y2505 = this.getPageElement("y2505").getValue();
        String personA0000 = getPageElement("personA0000").getValue();
        String msgid = this.getPageElement("msgid").getValue();
        String str="";
        if (!("").equals(personA0000)){
            str= y2500+","+y2505+","+SysfunctionManager.getCurrentSysfunction().getParam2()+"," + personA0000+","+msgid;
        }else
        {
            str= y2500+","+y2505+","+SysfunctionManager.getCurrentSysfunction().getParam2();
        }

        if(!"".equals(msgid)){
            setRadow_parent_data("msgid,"+msgid);
        }else{
            setRadow_parent_data(str);
        }

        this.getExecuteSG().addExecuteCode("moveAddnone()");
//        this.setRadow_parent_data(y2500 + "," + y2505);
        this.openWindowWithMax("addedWindow",
                "pages.GWYGL.GWYGL_KSLYGL.#{packName}.GWYGL_KSLYGL_004_0001_MainAdd");
        return EventRtnType.NORMAL_SUCCESS;
    }


    /**
     *===============================================
     * 方法名称: 删除前询问
     * 方法创建日期:2021/5/8
     * 方法创建人员:zyy
     * 方法最后修改日期:2021/5/8
     * 方法最后修改人员:zyy
     * 方法功能描述:
     * 设计参考文档:
     * 结果结构详述:
     *
     * @throws
     *===============================================
     */
    @PageEvent("deleteLast")
    public int deleteLast(String y2500){
        if(StrUtil.isEmpty(y2500)){
            this.getExecuteSG().addExecuteCode("window.fn.hMessage({ type:\"warn\",txt:\"请至少选择一条记录！\"})");//提醒删除失败
            return EventRtnType.NORMAL_SUCCESS;
        }
        this.addNextEvent(NextEventValue.YES, "delete",y2500);
        this.setMessageType(EventMessageType.CONFIRM);
        this.setMainMessage("确定删除当前所选批次信息？");
        return EventRtnType.NORMAL_SUCCESS;
    }

    /**
     *===============================================
     * 方法名称: 删除批次信息
     * 方法创建日期:2021/5/8
     * 方法创建人员:zyy
     * 方法最后修改日期:2021/5/8
     * 方法最后修改人员:zyy
     * 方法功能描述:
     * 设计参考文档:
     * 结果结构详述:
     *
     * @throws
     *===============================================
     */
    @PageEvent("delete")
    @NoRequiredValidate
    @AutoNoMask
    @Transaction
    public int deleteEvent(String y2500s) {
        try {
            if(StrUtil.isEmpty(y2500s)){
                this.getExecuteSG().addExecuteCode("window.fn.hMessage({ type:\"warn\",txt:\"请至少选择一条记录！\"})");//提醒删除失败
                return EventRtnType.NORMAL_SUCCESS;
            }
            bs1.deleteProcessInfo(y2500s);
            this.setNextEventName("numberOfProcesses");
            this.getExecuteSG().addExecuteCode("window.fn.hMessage({ type:\"success\",txt:\"删除成功!\"})");
            applog.createLog("Y25", "A01", AreaBS.getUserid(), SysUtil.getCacheCurrentUser().getName(), "考录录用申报批次删除", null);
        } catch (Exception e) {
            this.getExecuteSG().addExecuteCode("window.fn.hMessage({ type:\"warn\",txt:\"删除失败\"})");//提醒删除失败
            e.printStackTrace();
        }
        this.createPageElement("grid", ElementType.GRID, false).reload();//执行删除操作
        return EventRtnType.NORMAL_SUCCESS;
    }



    /**
     *===============================================
     * 方法名称: 附件上传方法
     * 方法创建日期:2021/5/8
     * 方法创建人员:zyy
     * 方法最后修改日期:2021/5/8
     * 方法最后修改人员:zyy
     * 方法功能描述:
     * 设计参考文档:
     * 结果结构详述:
     *
     * @throws
     *===============================================
     */
    @PageEvent("toAttachment")
    public int toAttachment(String y0000) throws RadowException {
        try {
            HashMap map = new HashMap();
            map.put("y0000", y0000);  //业务唯一流水号
            map.put("m0113", M01Enum.M01_0111.getM0113()); //模型编码
            map.put("m0409", "1");//附件级别代码(1:业务主体 2:业务明细)
            String rowdow_parent_data = JsonUtil.object2JsonStr(map);
            this.setRadow_parent_data(rowdow_parent_data);
            this.openWindowWithMax("attachmentWin", "pages.GWYGL.GWYGL_PUBLIC.GWYGL_PUBLIC_002_0002.GWYGL_PUBLIC_002_0002_Main");
            return EventRtnType.NORMAL_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return EventRtnType.FAILD;
        }
    }



    /**
     *===============================================
     * 方法名称: 跳转审批页面
     * 方法创建日期:2021/5/8
     * 方法创建人员:zyy
     * 方法最后修改日期:2021/5/8
     * 方法最后修改人员:zyy
     * 方法功能描述:
     * 设计参考文档:
     * 结果结构详述:
     *
     * @throws
     *===============================================
     */
    @PageEvent("toAudit")
    public int toAudit(String y0000) throws RadowException, AppException {
        this.setRadow_parent_data(y0000 + "," + SysfunctionManager.getCurrentSysfunction().getParam2());
        this.openWindowWithMax("auditWin", "pages.GWYGL.GWYGL_KSLYGL.#{packName}.GWYGL_KSLYGL_004_0001_MainEditAdvise");
        return EventRtnType.NORMAL_SUCCESS;
    }


    /**
     *===============================================
     * 方法名称: 审批前置校验
     * 方法创建日期:2021/5/8
     * 方法创建人员:zyy
     * 方法最后修改日期:2021/5/8
     * 方法最后修改人员:zyy
     * 方法功能描述:
     * 设计参考文档:
     * 结果结构详述:
     *
     * @throws
     *===============================================
     */
    @PageEvent("pass")
    @NoRequiredValidate
    public int passEvent(String y0000) throws RadowException {
        try {
            String msg = new BusinessModelBS().getFlowYesTip(y0000);
            this.setMessageType(EventMessageType.CONFIRM);
            this.setMainMessage(msg);
            this.addNextEvent(NextEventValue.YES, "passOk", y0000);
            return EventRtnType.NORMAL_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return EventRtnType.FAILD;
        }
    }



    /**
     *===============================================
     * 方法名称: 业务报送
     * 方法创建日期:2021/5/8
     * 方法创建人员:zyy
     * 方法最后修改日期:2021/5/8
     * 方法最后修改人员:zyy
     * 方法功能描述:
     * 设计参考文档:
     * 结果结构详述:
     *
     * @throws
     *===============================================
     */
    @PageEvent("passOk")
    @NoRequiredValidate
    @Transaction
    public int passOkEvent(String y0000) {
        try {
            Y25DTO y25dto = new Y25DTO();
            y25dto.setY2500(y0000);
            //当前环节
            String m0213 = bs2.getModelState(M01Enum.M01_0111.getM0113(), y0000);
            String endLink = bs1.getEndLink();
            y25dto.setY2598(m0213);
            String check = bs1.check(y0000, m0213,endLink);
            if (!"0".equals(check)) {
                this.getExecuteSG().addExecuteCode("window.fn.hMessage({ type:\"info\",txt:\"依然存在"+check+"】人的意见未录入\"})");
                return EventRtnType.FAILD;
            }
            String y0117 = bs2.updateFlowYes(y0000, m0213,"GWYGL_KSLYGL_004", "考录录用审批通过");
            bs1.updateStatus(y25dto, y0117);
            if(endLink == m0213){
                applog.createLog("Y25", "A01", AreaBS.getUserid(), SysUtil.getCacheCurrentUser().getName(), "考录录用申报流程审批完成", null);
                this.getExecuteSG().addExecuteCode("window.fn.hMessage({ type:\"success\",txt:\"审批完成!\"})");
            }else{
                applog.createLog("Y25", "A01", AreaBS.getUserid(), SysUtil.getCacheCurrentUser().getName(), "考录录用申报流程审核完成", null);
                this.getExecuteSG().addExecuteCode("window.fn.hMessage({ type:\"success\",txt:\"审核完成!\"})");
            }
            this.setNextEventName("grid.dogridquery");
            return EventRtnType.NORMAL_SUCCESS;
        } catch (AppException | SQLException e) {
            e.printStackTrace();
            this.getExecuteSG().addExecuteCode("window.fn.hMessage({ type:\"warn\",txt:\""+e.getMessage()+"\"})");
            return EventRtnType.FAILD;
        }
    }


    /**
     *===============================================
     * 方法名称: 审批不通过
     * 方法创建日期:2021/5/8
     * 方法创建人员:zyy
     * 方法最后修改日期:2021/5/8
     * 方法最后修改人员:zyy
     * 方法功能描述:
     * 设计参考文档:
     * 结果结构详述:
     *
     * @throws
     *===============================================
     */
    @PageEvent("nopass")
    @NoRequiredValidate
    public int nopassEvent(String y0000) throws RadowException {
        try {
            String m0213 = bs2.getModelState(M01Enum.M01_0111.getM0113(), y0000);
            String endLink = bs1.getEndLink();
            String check = bs1.check(y0000, m0213,endLink);
            if (!"0".equals(check)) {
                this.getExecuteSG().addExecuteCode("window.fn.hMessage({ type:\"info\",txt:\"依然存在"+check+"人的意见未录入!\"})");
                return EventRtnType.FAILD;
            }
            HashMap map = new HashMap();
            map.put("y0000", y0000);
            map.put("m0113", m0213);

            String rowdow_parent_data = JsonUtil.object2JsonStr(map);
            this.setRadow_parent_data(rowdow_parent_data);
            this.openWindowWithMax("noPassOpinionWin",
                    "pages.GWYGL.GWYGL_PUBLIC.GWYGL_PUBLIC_003_0001.GWYGL_PUBLIC_003_0001_NoPassOpinion");
        } catch (AppException e1) {
            e1.printStackTrace();
        }

        return EventRtnType.NORMAL_SUCCESS;
    }



    /**
     *===============================================
     * 方法名称: 回调方法
     * 方法创建日期:2021/5/8
     * 方法创建人员:zyy
     * 方法最后修改日期:2021/5/8
     * 方法最后修改人员:zyy
     * 方法功能描述:
     * 设计参考文档:
     * 结果结构详述:
     *
     * @throws
     *===============================================
     */
    @PageEvent("returnInfo")
    @NoRequiredValidate
    public int returnInfo(String y0000) throws RadowException {
        try {
            Y25DTO y25dto = new Y25DTO();
            y25dto.setY2500(y0000);
            y25dto.setY2598(M02Enum.M02_00.getM0213());
            //环节不通过代码
            bs1.updateStatus(y25dto, "0");
            this.setNextEventName("grid.dogridquery");
            return EventRtnType.NORMAL_SUCCESS;
        } catch (AppException e) {
            e.printStackTrace();
            return EventRtnType.FAILD;
        }
    }


    /**
     *===============================================
     * 方法名称: 页面grid列表刷新方法
     * 方法创建日期:2021/5/8
     * 方法创建人员:zyy
     * 方法最后修改日期:2021/5/8
     * 方法最后修改人员:zyy
     * 方法功能描述:
     * 设计参考文档:
     * 结果结构详述:
     *
     * @throws
     *===============================================
     */
    @PageEvent("rl")
    @NoRequiredValidate
    public int rl(String y2200) throws RadowException {
        this.createPageElement("grid", ElementType.GRID, false).reload();
        return EventRtnType.NORMAL_SUCCESS;
    }



    /**
     *===============================================
     * 方法名称: 流程复现
     * 方法创建日期:2021/5/8
     * 方法创建人员:zyy
     * 方法最后修改日期:2021/5/8
     * 方法最后修改人员:zyy
     * 方法功能描述:
     * 设计参考文档:
     * 结果结构详述:
     *
     * @throws
     *===============================================
     */
    @PageEvent("WorkRecurren")
    public int WorkRecurren(String y0000) throws RadowException {
        this.setRadow_parent_data(y0000);
        this.openWindowWithMax("WorkRecurrenWin", "pages.GWYGL.GWYGL_PUBLIC.GWYGL_PUBLIC_002_0003.GWYGL_PUBLIC_002_0003_WorkRecurrent");
        return EventRtnType.NORMAL_SUCCESS;
    }



    /**
     *===============================================
     * 方法名称: 页面重置按钮
     * 方法创建日期:2021/5/8
     * 方法创建人员:zyy
     * 方法最后修改日期:2021/5/8
     * 方法最后修改人员:zyy
     * 方法功能描述:
     * 设计参考文档:
     * 结果结构详述:
     *
     * @throws
     *===============================================
     */
//    @PageEvent("resetBtn.onclick")
//    @AutoNoMask
//    @NoRequiredValidate
//    public int refresh() throws RadowException {
//        this.getPageElement("y0181_sb1").setValue("");
//        this.getPageElement("y0181_sb2").setValue("");
//        this.getPageElement("sbname").setValue("");
//        this.getPageElement("m0213_select").setValue("");
//        return EventRtnType.NORMAL_SUCCESS;
//    }


    /**
     *===============================================
     * 方法名称: 页面跳转新增/编辑页面逻辑
     * 方法创建日期:2021/5/8
     * 方法创建人员:zyy
     * 方法最后修改日期:2021/5/8
     * 方法最后修改人员:zyy
     * 方法功能描述:
     * 设计参考文档:
     * 结果结构详述:
     *
     * @throws
     *===============================================
     */
    @PageEvent("add")
    public int add() throws RadowException, AppException {
        String signType = this.getPageElement("signType").getValue();
        String msgid = this.getPageElement("msgid").getValue();
        if(signType!=null&&"1".equals(signType)) {
            //先判断该类型业务，是否有 “业务录入”状态。如果有，进行提示；如果没有，则直接弹出新界面
            Map<String, Object> map = new HashMap<String, Object>();
            String y2998 = "";
            String judgmentState = getPageElement("judgmentState").getValue();
            if ("inProcess".equals(judgmentState)){
                map.put("state1"," AND(y0118 LIKE '0%' OR y0118 LIKE '1%' OR y0118 LIKE '2%' OR y0118 LIKE '3%')and y2598!='00'");
            }else if ("completed".equals(judgmentState)){
                y2998 ="99";
            }else if ("drafts".equals(judgmentState)){
                y2998 ="00";
            }

            map.put("y2503", "");
            map.put("askName",getPageElement("y2502").getValue());

            map = bs2.getY01BussinessStatus(map,y2998);
            map.put("b0111", AreaBS.getUserSsjbbm());
            map.put("isaudit", SysfunctionManager.getCurrentSysfunction().getParam1());
            map.put("userid",AreaBS.getUserid());
            List list = HBUtil.getHBSession().createSQLQuery(bs1.getMainGridStr(map)).list();
            if(list!=null&&list.size()>0) {
                this.setNextEventName("openOld");
            }else {
                this.setNextEventName("openNew");
            }
        }else if(signType != null && "2".equals(signType)){
            String y0000 = getRadow_parent_data().split(",")[0];
            this.setNextEventName("view",y0000);
        }
        return EventRtnType.NORMAL_SUCCESS;
    }


    /*
     *===============================================
     * 方法名称: 默认查询草稿状态业务
     * 方法创建日期:2021/5/8
     * 方法创建人员:zyy
     * 方法最后修改日期:2021/5/8
     * 方法最后修改人员:zyy
     * 方法功能描述:
     * 设计参考文档:
     * 结果结构详述:
     *
     * @throws
     *===============================================
     */
    @PageEvent("openOld")
    public int openOld() throws RadowException, AppException {
        this.getPageElement("judgmentState").setValue("drafts");
//		this.getPageElement("m0213_select").setValue("00");
        this.setNextEventName("grid.dogridquery");
        return EventRtnType.NORMAL_SUCCESS;
    }

    /*
     *===============================================
     * 方法名称: 跳转新增页面
     * 方法创建日期:2021/5/8
     * 方法创建人员:zyy
     * 方法最后修改日期:2021/5/8
     * 方法最后修改人员:zyy
     * 方法功能描述:
     * 设计参考文档:
     * 结果结构详述:
     *
     * @throws
     *===============================================
     */
    @PageEvent("openNew")
    public int openNew() throws RadowException, AppException {
//        this.getPageElement("m0213_select").setValue("00");
//        this.getPageElement("judgmentState").setValue("00");
        String personA0000 = this.getPageElement("personA0000").getValue();

        this.setNextEventName("added",personA0000);
        return EventRtnType.NORMAL_SUCCESS;
    }

    @PageEvent("recallLast")
    public int recallLast(String Y0000) throws AppException {
        this.addNextEvent(NextEventValue.YES, "recall",Y0000);
        this.addNextEvent(NextEventValue.CANNEL,"cannelEvent");
        this.setMessageType(EventMessageType.CONFIRM);
        this.setMainMessage("确定要撤回该请示吗?");
        return EventRtnType.NORMAL_SUCCESS;
    }

    /*
     *===============================================
     * 方法名称: 撤回方法
     * 方法创建日期:2021/5/8
     * 方法创建人员:zyy
     * 方法最后修改日期:2021/5/8
     * 方法最后修改人员:zyy
     * 方法功能描述:
     * 设计参考文档:
     * 结果结构详述:
     *
     * @throws
     *===============================================
     */
    @PageEvent("recall")
    @Transaction
    @OpLog
    public int recall(String Y0000) throws RadowException, AppException {
        String m0113 = bs2.getBussM0113(Y0000);
        System.out.println("当前业务类型："+m0113);
        String m0213 = bs2.getModelState(M01Enum.M01_0111.getM0113(),"");
        System.out.println("当前用户环节："+m0213);
        String m023buss = bs2.getBussM0213(Y0000);
        System.out.println("当前业务流程环节："+m023buss);
        boolean end = bs2.getProcessEnd(Y0000);
        //判断流程是否结束
        if(end){
            this.getExecuteSG().addExecuteCode("window.fn.hMessage({ type:\"info\",txt:\"当前业务流程已经结束无法撤回\"})");
        }else{
            if(!m0213.equals(m023buss)){
                if(bs2.isMainToDo(Y0000,m023buss)){
                    //删除Y01流程数据
                    bs2.updateFlowRecall(Y0000,"GWYGL_KSLYGL_004_0001撤回",m023buss);
                    bs2.updateY01FlowRecallStatus(Y0000,m023buss);
                    //更新业务主表数据
                    M01 m01= bs2.getM01ByM0113(m0113);
                    bs2.updateBussYStatus(m01.getM0201(),m01.getM0202(),"00",m01.getM0203(),Y0000);
                    try {
                        applog.createLog("Y25", "A01", AreaBS.getUserid(), SysUtil.getCacheCurrentUser().getName(), "考录录用申报批次撤回成功", null);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    this.setMainMessage("撤回成功！","success");
                    this.createPageElement("grid", ElementType.GRID, false).reload();
                }else{
                    this.setMainMessage("当前业务流程无法撤回！");
                }
            }else{
                //删除Y01流程数据
                bs2.updateFlowRecall(Y0000,"GWYGL_KSLYGL_004_0001撤回",m0213);
                bs2.updateY01FlowRecallStatus(Y0000,m0213);
                //更新业务主表y82数据
                M01 m01= bs2.getM01ByM0113(m0113);
                bs2.updateBussYStatus(m01.getM0201(),m01.getM0202(),"00",m01.getM0203(),Y0000);
                try {
                    applog.createLog("Y25", "A01", AreaBS.getUserid(), SysUtil.getCacheCurrentUser().getName(), "考录录用申报批次撤回成功！", null);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                this.getExecuteSG().addExecuteCode("window.fn.hMessage({ type:\"success\",txt:\"撤回成功！\"})");
                this.setNextEventName("grid.dogridquery");
                this.setNextEventName("numberOfProcesses");
            }
        }
        return EventRtnType.NORMAL_SUCCESS;
    }

}
