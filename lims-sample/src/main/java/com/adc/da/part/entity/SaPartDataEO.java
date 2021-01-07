package com.adc.da.part.entity;

import com.adc.da.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

/**
 * <b>功能：</b>SA_PART_DATA SaPartDataEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2020-01-15 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 *     零部件主信息表
 */
public class SaPartDataEO extends BaseEntity {
    @ApiModelProperty("试验任务书编号")
    private String trialTaskBookNO;
    @ApiModelProperty("报废单号")
    private String scrapNumber;
    @ApiModelProperty("报废日期")
    private String scrapDate;
    @ApiModelProperty("结算日期")
    private String settlementDate;
    @ApiModelProperty("结算单号")
    private String settlementNumber;
    @ApiModelProperty("归还日期")
    private String returnDate;
    @ApiModelProperty("领用日期")
    private String borrowDate;
    @ApiModelProperty("领用数量")
    private String borrowQuantity;
    @ApiModelProperty("领用人姓名")
    private String borrowerName;
    @ApiModelProperty("领用人id")
    private String borrowerId;
    @ApiModelProperty("领用单号")
    private String borrowNumbwe;
    @ApiModelProperty("SQE/检验员姓名")
    private String sqeName;
    @ApiModelProperty("SQE/检验员id")
    private String sqeId;
    @ApiModelProperty("检验单号")
    private String testNumber;
    @ApiModelProperty("实际到货数量")
    private String actualArrivalQuantity;
    @ApiModelProperty("实际到货时间")
    private String actualArrivalTime;
    @ApiModelProperty("生产库管员姓名")
    private String produceAdminName;
    @ApiModelProperty("生产库管员id")
    private String produceAdmin;
    @ApiModelProperty("处理意见")
    private String dealOpinion;
    @ApiModelProperty("要求到货时间")
    private String requestArrivalTime;
    @ApiModelProperty("类别")
    private String partType;
    @ApiModelProperty("样件图号")
    private String partDrawNumber;
    @ApiModelProperty("样件计划单号")
    private String partPlanNumber;
    @ApiModelProperty("项目名称")
    private String bmProjectName;
    @ApiModelProperty("创建人")
    private String createByName;
    @ApiModelProperty("零部件所在地外场管理责任人姓名")
    private String partLocationManagerName;
    @ApiModelProperty("接收人姓名")
    private String receivedUserName;
    @ApiModelProperty("送样人部门")
    private String sendPartUserOrg;
    @ApiModelProperty("送样人电话")
    private String sendPartUserPhone;
    @ApiModelProperty("送样人姓名")
    private String sendPartUserName;
    @ApiModelProperty("状态 接收:0, 领样：1, 在库:2, 退样: 3, 报废:4 , 封存：5， 拆机：6， 待收样:7")
    private Integer status;
    @ApiModelProperty("装机报告考核件清单ID(TS_ATTACHMENT表ID)")
    private String reportAssessAttachmentId;
    @ApiModelProperty("备注")
    private String remarks;
    @ApiModelProperty("在架")
    private Integer inShelf;
    @ApiModelProperty("领样人ID")
    private String getPartUserId;
    @ApiModelProperty("样品所在位置")
    private String partSpaceLocation;
    @ApiModelProperty("实验类型")
    private String trialType;
    @ApiModelProperty("是否退样")
    private String whetherReturn;
    @ApiModelProperty("接收样零部件单ID(TS_ATTACHMENT表ID)")
    private String receivePartAttachmentId;
    @ApiModelProperty("退样单ID（TS_ATTACHMENT表ID）")
    private String returnPartAttachmentId;
    @ApiModelProperty("考核件清单ID(TS_ATTACHMENT表ID)")
    private String assessAttachmentId;
    @ApiModelProperty("零部件所在地外场管理责任人ID")
    private String partLocationManagerId;
    @ApiModelProperty("样品所在地")
    private String partLocation;
    @ApiModelProperty("占用库位号")
    private Integer partSpaceNumber;
    @ApiModelProperty("零部件库房ID")
    private String partDepotId;
    @ApiModelProperty("接受日期")
    private String receivedTime;
    @ApiModelProperty("零部件状态")
    private String partStatus;
    @ApiModelProperty("生产厂家")
    private String producedFactory;
    @ApiModelProperty("接收人ID")
    private String receivedUserId;
    @ApiModelProperty("零部件阶段")
    private Integer partStage;
    @ApiModelProperty("发动机编号")
    private String partEngineNo;
    @ApiModelProperty("试验区域")
    private String trialLocation;
    @ApiModelProperty("样品数量")
    private Integer partSampleNumber;
    @ApiModelProperty("样品名称")
    private String partName;
    @ApiModelProperty("送样人ID")
    private String sendPartUserId;
    @ApiModelProperty("项目ID")
    private String bmProjectId;
    @ApiModelProperty("更新人ID")
    private String updateBy;
    @ApiModelProperty("更新时间")
    private String updateTime;
    @ApiModelProperty("创建人ID")
    private String createBy;
    @ApiModelProperty("创建时间")
    private String createTime;
    @ApiModelProperty("是否删除（0，未删除；1，已删除）")
    private Integer delFlag;
    @ApiModelProperty("")
    private String id;
    @ApiModelProperty
    private String specificationType;
    @ApiModelProperty("样品编号")
    private String sampleNO;
    @ApiModelProperty("归还人")
    private String returnUserName ;
    @ApiModelProperty("归还人id")
    private String returnUserId ;
    @ApiModelProperty("是否报废（0，否；1，是）")
    private Integer whetherScrap;


    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>scrapNumber -> scrap_number</li>
     * <li>scrapDate -> scrap_date</li>
     * <li>settlementDate -> settlement_date</li>
     * <li>settlementNumber -> settlement_number</li>
     * <li>returnDate -> return_date</li>
     * <li>borrowDate -> borrow_date</li>
     * <li>borrowQuantity -> borrow_quantity</li>
     * <li>borrowerName -> borrower_name</li>
     * <li>borrowerId -> borrower_id</li>
     * <li>borrowNumbwe -> borrow_numbwe</li>
     * <li>sqeName -> sqe_name</li>
     * <li>sqeId -> sqe_id</li>
     * <li>testNumber -> test_number</li>
     * <li>actualArrivalQuantity -> actual_arrival_quantity</li>
     * <li>actualArrivalTime -> actual_arrival_time</li>
     * <li>produceAdminName -> produce_admin_name</li>
     * <li>produceAdmin -> produce_admin</li>
     * <li>dealOpinion -> deal_opinion</li>
     * <li>requestArrivalTime -> request_arrival_time</li>
     * <li>partType -> part_type</li>
     * <li>partDrawNumber -> part_draw_number</li>
     * <li>partPlanNumber -> part_plan_number</li>
     * <li>bmProjectName -> bm_project_name</li>
     * <li>createByName -> create_by_name</li>
     * <li>partLocationManagerName -> part_location_manager_name</li>
     * <li>receivedUserName -> received_user_name</li>
     * <li>sendPartUserOrg -> send_part_user_org</li>
     * <li>sendPartUserPhone -> send_part_user_phone</li>
     * <li>sendPartUserName -> send_part_user_name</li>
     * <li>status -> status</li>
     * <li>reportAssessAttachmentId -> report_assess_attachment_id</li>
     * <li>remarks -> remarks</li>
     * <li>inShelf -> in_shelf</li>
     * <li>getPartUserId -> get_part_user_id</li>
     * <li>partSpaceLocation -> part_space_location</li>
     * <li>trialType -> trial_type</li>
     * <li>whetherReturn -> whether_return</li>
     * <li>receivePartAttachmentId -> receive_part_attachment_id</li>
     * <li>returnPartAttachmentId -> return_part_attachment_id</li>
     * <li>assessAttachmentId -> assess_attachment_id</li>
     * <li>partLocationManagerId -> part_location_manager_id</li>
     * <li>partLocation -> part_location</li>
     * <li>partSpaceNumber -> part_space_number</li>
     * <li>partDepotId -> part_depot_id</li>
     * <li>receivedTime -> received_time</li>
     * <li>partStatus -> part_status</li>
     * <li>producedFactory -> produced_factory</li>
     * <li>receivedUserId -> received_user_id</li>
     * <li>partStage -> part_stage</li>
     * <li>partEngineNo -> part_engine_no</li>
     * <li>trialLocation -> trial_location</li>
     * <li>partSampleNumber -> part_sample_number</li>
     * <li>partName -> part_name</li>
     * <li>sendPartUserId -> send_part_user_id</li>
     * <li>bmProjectId -> bm_project_id</li>
     * <li>updateBy -> update_by</li>
     * <li>updateTime -> update_time</li>
     * <li>createBy -> create_by</li>
     * <li>createTime -> create_time</li>
     * <li>delFlag -> del_flag</li>
     * <li>id -> id</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) { return null; }
        switch (fieldName) {
            case "scrapNumber": return "scrap_number";
            case "scrapDate": return "scrap_date";
            case "settlementDate": return "settlement_date";
            case "settlementNumber": return "settlement_number";
            case "returnDate": return "return_date";
            case "borrowDate": return "borrow_date";
            case "borrowQuantity": return "borrow_quantity";
            case "borrowerName": return "borrower_name";
            case "borrowerId": return "borrower_id";
            case "borrowNumbwe": return "borrow_numbwe";
            case "sqeName": return "sqe_name";
            case "sqeId": return "sqe_id";
            case "testNumber": return "test_number";
            case "actualArrivalQuantity": return "actual_arrival_quantity";
            case "actualArrivalTime": return "actual_arrival_time";
            case "produceAdminName": return "produce_admin_name";
            case "produceAdmin": return "produce_admin";
            case "dealOpinion": return "deal_opinion";
            case "requestArrivalTime": return "request_arrival_time";
            case "partType": return "part_type";
            case "partDrawNumber": return "part_draw_number";
            case "partPlanNumber": return "part_plan_number";
            case "bmProjectName": return "bm_project_name";
            case "createByName": return "create_by_name";
            case "partLocationManagerName": return "part_location_manager_name";
            case "receivedUserName": return "received_user_name";
            case "sendPartUserOrg": return "send_part_user_org";
            case "sendPartUserPhone": return "send_part_user_phone";
            case "sendPartUserName": return "send_part_user_name";
            case "status": return "status";
            case "reportAssessAttachmentId": return "report_assess_attachment_id";
            case "remarks": return "remarks";
            case "inShelf": return "in_shelf";
            case "getPartUserId": return "get_part_user_id";
            case "partSpaceLocation": return "part_space_location";
            case "trialType": return "trial_type";
            case "whetherReturn": return "whether_return";
            case "receivePartAttachmentId": return "receive_part_attachment_id";
            case "returnPartAttachmentId": return "return_part_attachment_id";
            case "assessAttachmentId": return "assess_attachment_id";
            case "partLocationManagerId": return "part_location_manager_id";
            case "partLocation": return "part_location";
            case "partSpaceNumber": return "part_space_number";
            case "partDepotId": return "part_depot_id";
            case "receivedTime": return "received_time";
            case "partStatus": return "part_status";
            case "producedFactory": return "produced_factory";
            case "receivedUserId": return "received_user_id";
            case "partStage": return "part_stage";
            case "partEngineNo": return "part_engine_no";
            case "trialLocation": return "trial_location";
            case "partSampleNumber": return "part_sample_number";
            case "partName": return "part_name";
            case "sendPartUserId": return "send_part_user_id";
            case "bmProjectId": return "bm_project_id";
            case "updateBy": return "update_by";
            case "updateTime": return "update_time";
            case "createBy": return "create_by";
            case "createTime": return "create_time";
            case "delFlag": return "del_flag";
            case "id": return "id";
            case "sampleNO": return "SAMPLE_NO";
            case "returnUserName": return "RETURN_USER_NAME";
            case "whetherScrap": return "WHETHER_SCRAP";
            default: return null;
        }
    }

    /**
     * 原始数据库列名转换为java字段名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>scrap_number -> scrapNumber</li>
     * <li>scrap_date -> scrapDate</li>
     * <li>settlement_date -> settlementDate</li>
     * <li>settlement_number -> settlementNumber</li>
     * <li>return_date -> returnDate</li>
     * <li>borrow_date -> borrowDate</li>
     * <li>borrow_quantity -> borrowQuantity</li>
     * <li>borrower_name -> borrowerName</li>
     * <li>borrower_id -> borrowerId</li>
     * <li>borrow_numbwe -> borrowNumbwe</li>
     * <li>sqe_name -> sqeName</li>
     * <li>sqe_id -> sqeId</li>
     * <li>test_number -> testNumber</li>
     * <li>actual_arrival_quantity -> actualArrivalQuantity</li>
     * <li>actual_arrival_time -> actualArrivalTime</li>
     * <li>produce_admin_name -> produceAdminName</li>
     * <li>produce_admin -> produceAdmin</li>
     * <li>deal_opinion -> dealOpinion</li>
     * <li>request_arrival_time -> requestArrivalTime</li>
     * <li>part_type -> partType</li>
     * <li>part_draw_number -> partDrawNumber</li>
     * <li>part_plan_number -> partPlanNumber</li>
     * <li>bm_project_name -> bmProjectName</li>
     * <li>create_by_name -> createByName</li>
     * <li>part_location_manager_name -> partLocationManagerName</li>
     * <li>received_user_name -> receivedUserName</li>
     * <li>send_part_user_org -> sendPartUserOrg</li>
     * <li>send_part_user_phone -> sendPartUserPhone</li>
     * <li>send_part_user_name -> sendPartUserName</li>
     * <li>status -> status</li>
     * <li>report_assess_attachment_id -> reportAssessAttachmentId</li>
     * <li>remarks -> remarks</li>
     * <li>in_shelf -> inShelf</li>
     * <li>get_part_user_id -> getPartUserId</li>
     * <li>part_space_location -> partSpaceLocation</li>
     * <li>trial_type -> trialType</li>
     * <li>whether_return -> whetherReturn</li>
     * <li>receive_part_attachment_id -> receivePartAttachmentId</li>
     * <li>return_part_attachment_id -> returnPartAttachmentId</li>
     * <li>assess_attachment_id -> assessAttachmentId</li>
     * <li>part_location_manager_id -> partLocationManagerId</li>
     * <li>part_location -> partLocation</li>
     * <li>part_space_number -> partSpaceNumber</li>
     * <li>part_depot_id -> partDepotId</li>
     * <li>received_time -> receivedTime</li>
     * <li>part_status -> partStatus</li>
     * <li>produced_factory -> producedFactory</li>
     * <li>received_user_id -> receivedUserId</li>
     * <li>part_stage -> partStage</li>
     * <li>part_engine_no -> partEngineNo</li>
     * <li>trial_location -> trialLocation</li>
     * <li>part_sample_number -> partSampleNumber</li>
     * <li>part_name -> partName</li>
     * <li>send_part_user_id -> sendPartUserId</li>
     * <li>bm_project_id -> bmProjectId</li>
     * <li>update_by -> updateBy</li>
     * <li>update_time -> updateTime</li>
     * <li>create_by -> createBy</li>
     * <li>create_time -> createTime</li>
     * <li>del_flag -> delFlag</li>
     * <li>id -> id</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) { return null; }
        switch (columnName) {
            case "scrap_number": return "scrapNumber";
            case "scrap_date": return "scrapDate";
            case "settlement_date": return "settlementDate";
            case "settlement_number": return "settlementNumber";
            case "return_date": return "returnDate";
            case "borrow_date": return "borrowDate";
            case "borrow_quantity": return "borrowQuantity";
            case "borrower_name": return "borrowerName";
            case "borrower_id": return "borrowerId";
            case "borrow_numbwe": return "borrowNumbwe";
            case "sqe_name": return "sqeName";
            case "sqe_id": return "sqeId";
            case "test_number": return "testNumber";
            case "actual_arrival_quantity": return "actualArrivalQuantity";
            case "actual_arrival_time": return "actualArrivalTime";
            case "produce_admin_name": return "produceAdminName";
            case "produce_admin": return "produceAdmin";
            case "deal_opinion": return "dealOpinion";
            case "request_arrival_time": return "requestArrivalTime";
            case "part_type": return "partType";
            case "part_draw_number": return "partDrawNumber";
            case "part_plan_number": return "partPlanNumber";
            case "bm_project_name": return "bmProjectName";
            case "create_by_name": return "createByName";
            case "part_location_manager_name": return "partLocationManagerName";
            case "received_user_name": return "receivedUserName";
            case "send_part_user_org": return "sendPartUserOrg";
            case "send_part_user_phone": return "sendPartUserPhone";
            case "send_part_user_name": return "sendPartUserName";
            case "status": return "status";
            case "report_assess_attachment_id": return "reportAssessAttachmentId";
            case "remarks": return "remarks";
            case "in_shelf": return "inShelf";
            case "get_part_user_id": return "getPartUserId";
            case "part_space_location": return "partSpaceLocation";
            case "trial_type": return "trialType";
            case "whether_return": return "whetherReturn";
            case "receive_part_attachment_id": return "receivePartAttachmentId";
            case "return_part_attachment_id": return "returnPartAttachmentId";
            case "assess_attachment_id": return "assessAttachmentId";
            case "part_location_manager_id": return "partLocationManagerId";
            case "part_location": return "partLocation";
            case "part_space_number": return "partSpaceNumber";
            case "part_depot_id": return "partDepotId";
            case "received_time": return "receivedTime";
            case "part_status": return "partStatus";
            case "produced_factory": return "producedFactory";
            case "received_user_id": return "receivedUserId";
            case "part_stage": return "partStage";
            case "part_engine_no": return "partEngineNo";
            case "trial_location": return "trialLocation";
            case "part_sample_number": return "partSampleNumber";
            case "part_name": return "partName";
            case "send_part_user_id": return "sendPartUserId";
            case "bm_project_id": return "bmProjectId";
            case "update_by": return "updateBy";
            case "update_time": return "updateTime";
            case "create_by": return "createBy";
            case "create_time": return "createTime";
            case "del_flag": return "delFlag";
            case "id": return "id";
            case "SAMPLE_NO": return "sampleNO";
            case "RETURN_USER_NAME": return "returnUserName";
            case "WHETHER_SCRAP": return "whetherScrap";
            default: return null;
        }
    }
    
    public String getScrapNumber() {
        return this.scrapNumber;
    }

    public void setScrapNumber(String scrapNumber) {
        this.scrapNumber = scrapNumber;
    }

    public String getScrapDate() {
        return this.scrapDate;
    }

    public void setScrapDate(String scrapDate) {
        this.scrapDate = scrapDate;
    }

    public String getSettlementDate() {
        return this.settlementDate;
    }

    public void setSettlementDate(String settlementDate) {
        this.settlementDate = settlementDate;
    }

    public String getSettlementNumber() {
        return this.settlementNumber;
    }

    public void setSettlementNumber(String settlementNumber) {
        this.settlementNumber = settlementNumber;
    }

    public String getReturnDate() {
        return this.returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getBorrowDate() {
        return this.borrowDate;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    public String getBorrowQuantity() {
        return this.borrowQuantity;
    }

    public void setBorrowQuantity(String borrowQuantity) {
        this.borrowQuantity = borrowQuantity;
    }

    public String getBorrowerName() {
        return this.borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    public String getBorrowerId() {
        return this.borrowerId;
    }

    public void setBorrowerId(String borrowerId) {
        this.borrowerId = borrowerId;
    }

    public String getBorrowNumbwe() {
        return this.borrowNumbwe;
    }

    public void setBorrowNumbwe(String borrowNumbwe) {
        this.borrowNumbwe = borrowNumbwe;
    }

    public String getSqeName() {
        return this.sqeName;
    }

    public void setSqeName(String sqeName) {
        this.sqeName = sqeName;
    }

    public String getSqeId() {
        return this.sqeId;
    }

    public void setSqeId(String sqeId) {
        this.sqeId = sqeId;
    }

    public String getTestNumber() {
        return this.testNumber;
    }

    public void setTestNumber(String testNumber) {
        this.testNumber = testNumber;
    }

    public String getActualArrivalQuantity() {
        return this.actualArrivalQuantity;
    }

    public void setActualArrivalQuantity(String actualArrivalQuantity) {
        this.actualArrivalQuantity = actualArrivalQuantity;
    }

    public String getActualArrivalTime() {
        return this.actualArrivalTime;
    }

    public void setActualArrivalTime(String actualArrivalTime) {
        this.actualArrivalTime = actualArrivalTime;
    }

    public String getProduceAdminName() {
        return this.produceAdminName;
    }

    public void setProduceAdminName(String produceAdminName) {
        this.produceAdminName = produceAdminName;
    }

    public String getProduceAdmin() {
        return this.produceAdmin;
    }

    public void setProduceAdmin(String produceAdmin) {
        this.produceAdmin = produceAdmin;
    }

    public String getDealOpinion() {
        return this.dealOpinion;
    }

    public void setDealOpinion(String dealOpinion) {
        this.dealOpinion = dealOpinion;
    }

    public String getRequestArrivalTime() {
        return this.requestArrivalTime;
    }

    public void setRequestArrivalTime(String requestArrivalTime) {
        this.requestArrivalTime = requestArrivalTime;
    }

    public String getPartType() {
        return this.partType;
    }

    public void setPartType(String partType) {
        this.partType = partType;
    }

    public String getPartDrawNumber() {
        return this.partDrawNumber;
    }

    public void setPartDrawNumber(String partDrawNumber) {
        this.partDrawNumber = partDrawNumber;
    }

    public String getPartPlanNumber() {
        return this.partPlanNumber;
    }

    public void setPartPlanNumber(String partPlanNumber) {
        this.partPlanNumber = partPlanNumber;
    }

    public String getBmProjectName() {
        return this.bmProjectName;
    }

    public void setBmProjectName(String bmProjectName) {
        this.bmProjectName = bmProjectName;
    }

    public String getCreateByName() {
        return this.createByName;
    }

    public void setCreateByName(String createByName) {
        this.createByName = createByName;
    }

    public String getPartLocationManagerName() {
        return this.partLocationManagerName;
    }

    public void setPartLocationManagerName(String partLocationManagerName) {
        this.partLocationManagerName = partLocationManagerName;
    }

    public String getReceivedUserName() {
        return this.receivedUserName;
    }

    public void setReceivedUserName(String receivedUserName) {
        this.receivedUserName = receivedUserName;
    }

    public String getSendPartUserOrg() {
        return this.sendPartUserOrg;
    }

    public void setSendPartUserOrg(String sendPartUserOrg) {
        this.sendPartUserOrg = sendPartUserOrg;
    }

    public String getSendPartUserPhone() {
        return this.sendPartUserPhone;
    }

    public void setSendPartUserPhone(String sendPartUserPhone) {
        this.sendPartUserPhone = sendPartUserPhone;
    }

    public String getSendPartUserName() {
        return this.sendPartUserName;
    }

    public void setSendPartUserName(String sendPartUserName) {
        this.sendPartUserName = sendPartUserName;
    }



    public String getReportAssessAttachmentId() {
        return this.reportAssessAttachmentId;
    }

    public void setReportAssessAttachmentId(String reportAssessAttachmentId) {
        this.reportAssessAttachmentId = reportAssessAttachmentId;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }



    public String getGetPartUserId() {
        return this.getPartUserId;
    }

    public void setGetPartUserId(String getPartUserId) {
        this.getPartUserId = getPartUserId;
    }

    public String getPartSpaceLocation() {
        return this.partSpaceLocation;
    }

    public void setPartSpaceLocation(String partSpaceLocation) {
        this.partSpaceLocation = partSpaceLocation;
    }

    public String getTrialType() {
        return this.trialType;
    }

    public void setTrialType(String trialType) {
        this.trialType = trialType;
    }

    public String getWhetherReturn() {
        return this.whetherReturn;
    }

    public void setWhetherReturn(String whetherReturn) {
        this.whetherReturn = whetherReturn;
    }

    public String getReceivePartAttachmentId() {
        return this.receivePartAttachmentId;
    }

    public void setReceivePartAttachmentId(String receivePartAttachmentId) {
        this.receivePartAttachmentId = receivePartAttachmentId;
    }

    public String getReturnPartAttachmentId() {
        return this.returnPartAttachmentId;
    }

    public void setReturnPartAttachmentId(String returnPartAttachmentId) {
        this.returnPartAttachmentId = returnPartAttachmentId;
    }

    public String getAssessAttachmentId() {
        return this.assessAttachmentId;
    }

    public void setAssessAttachmentId(String assessAttachmentId) {
        this.assessAttachmentId = assessAttachmentId;
    }

    public String getPartLocationManagerId() {
        return this.partLocationManagerId;
    }

    public void setPartLocationManagerId(String partLocationManagerId) {
        this.partLocationManagerId = partLocationManagerId;
    }

    public String getPartLocation() {
        return this.partLocation;
    }

    public void setPartLocation(String partLocation) {
        this.partLocation = partLocation;
    }


    public String getPartDepotId() {
        return this.partDepotId;
    }

    public void setPartDepotId(String partDepotId) {
        this.partDepotId = partDepotId;
    }

    public String getReceivedTime() {
        return this.receivedTime;
    }

    public void setReceivedTime(String receivedTime) {
        this.receivedTime = receivedTime;
    }

    public String getPartStatus() {
        return this.partStatus;
    }

    public void setPartStatus(String partStatus) {
        this.partStatus = partStatus;
    }

    public String getProducedFactory() {
        return this.producedFactory;
    }

    public void setProducedFactory(String producedFactory) {
        this.producedFactory = producedFactory;
    }

    public String getReceivedUserId() {
        return this.receivedUserId;
    }

    public void setReceivedUserId(String receivedUserId) {
        this.receivedUserId = receivedUserId;
    }

    public Integer getPartStage() {
        return partStage;
    }

    public void setPartStage(Integer partStage) {
        this.partStage = partStage;
    }

    public String getPartEngineNo() {
        return this.partEngineNo;
    }

    public void setPartEngineNo(String partEngineNo) {
        this.partEngineNo = partEngineNo;
    }

    public String getTrialLocation() {
        return this.trialLocation;
    }

    public void setTrialLocation(String trialLocation) {
        this.trialLocation = trialLocation;
    }



    public String getPartName() {
        return this.partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getSendPartUserId() {
        return this.sendPartUserId;
    }

    public void setSendPartUserId(String sendPartUserId) {
        this.sendPartUserId = sendPartUserId;
    }

    public String getBmProjectId() {
        return this.bmProjectId;
    }

    public void setBmProjectId(String bmProjectId) {
        this.bmProjectId = bmProjectId;
    }

    public String getUpdateBy() {
        return this.updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateBy() {
        return this.createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getDelFlag() {
        return this.delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getInShelf() {
        return inShelf;
    }

    public void setInShelf(Integer inShelf) {
        this.inShelf = inShelf;
    }

    public Integer getPartSpaceNumber() {
        return partSpaceNumber;
    }

    public void setPartSpaceNumber(Integer partSpaceNumber) {
        this.partSpaceNumber = partSpaceNumber;
    }

    public Integer getPartSampleNumber() {
        return partSampleNumber;
    }

    public void setPartSampleNumber(Integer partSampleNumber) {
        this.partSampleNumber = partSampleNumber;
    }

    public String getTrialTaskBookNO() {
        return trialTaskBookNO;
    }

    public String getSpecificationType() {
        return specificationType;
    }

    public void setSpecificationType(String specificationType) {
        this.specificationType = specificationType;
    }

    public void setTrialTaskBookNO(String trialTaskBookNO) {
        this.trialTaskBookNO = trialTaskBookNO;
    }

    public String getSampleNO() {
        return sampleNO;
    }

    public void setSampleNO(String sampleNO) {
        this.sampleNO = sampleNO;
    }

    public String getReturnUserName() {
        return returnUserName;
    }

    public void setReturnUserName(String returnUserName) {
        this.returnUserName = returnUserName;
    }

    public String getReturnUserId() {
        return returnUserId;
    }

    public void setReturnUserId(String returnUserId) {
        this.returnUserId = returnUserId;
    }

    public Integer getWhetherScrap() {
        return whetherScrap;
    }

    public void setWhetherScrap(Integer whetherScrap) {
        this.whetherScrap = whetherScrap;
    }
}
