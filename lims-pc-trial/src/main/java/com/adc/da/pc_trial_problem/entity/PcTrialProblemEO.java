package com.adc.da.pc_trial_problem.entity;

import com.adc.da.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import java.sql.Clob;
import java.util.Date;

/**
 * <b>功能：</b>PC_TRIAL_PROBLEM PcTrialProblemEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2020-01-02 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class PcTrialProblemEO extends BaseEntity {

    @ApiModelProperty("图片名称")
    private String imageNames;
    @ApiModelProperty("图片ID")
    private String imageIds;
    @ApiModelProperty("备注")
    private String processcomments;
    @ApiModelProperty("发起人账号（域账号）")
    private String startuser;
    @ApiModelProperty("行驶里程")
    private String psqcXslc;
    @ApiModelProperty("0:待处理,1:进行中,2:关闭,-3:删除,3:挂起")
    private String status = "0";
    @ApiModelProperty("责任人")
    private String resposiblePerson;
    @ApiModelProperty("实施部门ID")
    private Long implementDeptId;
    @ApiModelProperty("对策采用时间")
    private String solutionAdoptTime;
    @ApiModelProperty("对策描述")
    private String solutionDesc;
    @ApiModelProperty("不良原因解析")
    private String proSolution;
    @ApiModelProperty("故障模式")
    private String kkxGzms;
    @ApiModelProperty("出现故障里程（km）")
    private String kkxCxgzlc;
    @ApiModelProperty("当前试验路况")
    private String kkxDqsylk;
    @ApiModelProperty("当前试验载质量")
    private String kkxDqsyzzl;
    @ApiModelProperty("试验总里程（km）")
    private String kkxSyzlc;
    @ApiModelProperty("检出日")
    private String psqcJcr;
    @ApiModelProperty("发生条件")
    private String psqcFstj;
    @ApiModelProperty("怎么了")
    private String psqcZml;
    @ApiModelProperty("何地")
    private String psqcHd;
    @ApiModelProperty("何物")
    private String psqcHw;
    @ApiModelProperty("何时")
    private String psqcHs;
    @ApiModelProperty("区分")
    private String distinguish;
    @ApiModelProperty("故障里程")
    private String faultMileage;
    @ApiModelProperty("不良现象标题")
    private String proTitle;
    @ApiModelProperty("主导人账号")
    private String mainUser;
    @ApiModelProperty("主导部门")
    private String mainDept;
    @ApiModelProperty("车型的问题等级")
    private String problemLevel;
    @ApiModelProperty("车型号")
    private String modelNo;
    @ApiModelProperty("底盘号")
    private String vinCode;
    @ApiModelProperty("零件名称")
    private String partName;
    @ApiModelProperty("零件图号")
    private String partNo;
    @ApiModelProperty("要求回答日")
    private String askTime;
    @ApiModelProperty("评价时间")
    private String evaluationDate;
    @ApiModelProperty("问题点类别")
    private String problemType;
    @ApiModelProperty("项目code")
    private String tppCode;
    @ApiModelProperty("评价人账号")
    private String evaluatePerson;
    @ApiModelProperty("问题属性2：0非专项，1专项")
    private String proPropertyTwo;
    @ApiModelProperty("问题属性:1再发，0新增")
    private String proProperty;
    @ApiModelProperty("试验委托ID")
    private String taskId;
    @ApiModelProperty("主键")
    private String id;
    @ApiModelProperty("PSQC初步原因")
    private String psqcCbyy;
    @ApiModelProperty("实施部门ID-临时")
    private Long implementDeptIdTem;
    @ApiModelProperty("对策采用时间-临时")
    private String solutionAdoptTimeTem;
    @ApiModelProperty("对策描述-临时")
    private String solutionDescTem;
    @ApiModelProperty("问题编号-返回时对应")
    private String problemCode;
    @ApiModelProperty("责任人-临时")
    private String resposiblePersonTem;
    @ApiModelProperty("倾向性判断") //500
    private String proQxxpd;  
    
    //2020.05.27 新增责任人及部门对应中文存储属性
	@ApiModelProperty("责任人名称")
    private String resposiblePersonName;
    @ApiModelProperty("实施部门名称")
    private Long implementDeptName;
	@ApiModelProperty("实施部门名称-临时")
    private Long implementDeptNameTem;
	@ApiModelProperty("责任人名字-临时")
    private String resposiblePersonNameTem;
    
	@ApiModelProperty("现场车辆故障评价人（域账号）")
    private String xcclgzPerson;
	
    

    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>imageNames -> image_names</li>
     * <li>imageIds -> image_ids</li>
     * <li>processcomments -> processcomments</li>
     * <li>startuser -> startuser</li>
     * <li>psqcXslc -> psqc_xslc</li>
     * <li>status -> status</li>
     * <li>resposiblePerson -> resposible_person</li>
     * <li>implementDeptId -> implement_dept_id</li>
     * <li>solutionAdoptTime -> solution_adopt_time</li>
     * <li>solutionDesc -> solution_desc</li>
     * <li>proSolution -> pro_solution</li>
     * <li>kkxGzms -> kkx_gzms</li>
     * <li>kkxCxgzlc -> kkx_cxgzlc</li>
     * <li>kkxDqsylk -> kkx_dqsylk</li>
     * <li>kkxDqsyzzl -> kkx_dqsyzzl</li>
     * <li>kkxSyzlc -> kkx_syzlc</li>
     * <li>psqcJcr -> psqc_jcr</li>
     * <li>psqcFstj -> psqc_fstj</li>
     * <li>psqcZml -> psqc_zml</li>
     * <li>psqcHd -> psqc_hd</li>
     * <li>psqcHw -> psqc_hw</li>
     * <li>psqcHs -> psqc_hs</li>
     * <li>distinguish -> distinguish</li>
     * <li>faultMileage -> fault_mileage</li>
     * <li>proTitle -> pro_title</li>
     * <li>mainUser -> main_user</li>
     * <li>mainDept -> main_dept</li>
     * <li>problemLevel -> problem_level</li>
     * <li>modelNo -> model_no</li>
     * <li>vinCode -> vin_code</li>
     * <li>partName -> part_name</li>
     * <li>partNo -> part_no</li>
     * <li>askTime -> ask_time</li>
     * <li>evaluationDate -> evaluation_date</li>
     * <li>problemType -> problem_type</li>
     * <li>tppCode -> tpp_code</li>
     * <li>evaluatePerson -> evaluate_person</li>
     * <li>proPropertyTwo -> pro_property_two</li>
     * <li>proProperty -> pro_property</li>
     * <li>taskId -> task_id</li>
     * <li>id -> id</li>
     * <li>psqcCbyy -> psqc_cbyy</li>
     * <li>implementDeptIdTem -> implement_dept_id_tem</li>
     * <li>solutionAdoptTimeTem -> solution_adopt_time_tem</li>
     * <li>solutionDescTem -> solution_desc_tem</li>
     * <li>problemCode -> problem_code</li>
     * <li>resposiblePersonTem -> resposible_person_tem</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) { return null; }
        switch (fieldName) {
            case "imageNames": return "image_names";
            case "imageIds": return "image_ids";
            case "processcomments": return "processcomments";
            case "startuser": return "startuser";
            case "psqcXslc": return "psqc_xslc";
            case "status": return "status";
            case "resposiblePerson": return "resposible_person";
            case "implementDeptId": return "implement_dept_id";
            case "solutionAdoptTime": return "solution_adopt_time";
            case "solutionDesc": return "solution_desc";
            case "proSolution": return "pro_solution";
            case "kkxGzms": return "kkx_gzms";
            case "kkxCxgzlc": return "kkx_cxgzlc";
            case "kkxDqsylk": return "kkx_dqsylk";
            case "kkxDqsyzzl": return "kkx_dqsyzzl";
            case "kkxSyzlc": return "kkx_syzlc";
            case "psqcJcr": return "psqc_jcr";
            case "psqcFstj": return "psqc_fstj";
            case "psqcZml": return "psqc_zml";
            case "psqcHd": return "psqc_hd";
            case "psqcHw": return "psqc_hw";
            case "psqcHs": return "psqc_hs";
            case "distinguish": return "distinguish";
            case "faultMileage": return "fault_mileage";
            case "proTitle": return "pro_title";
            case "mainUser": return "main_user";
            case "mainDept": return "main_dept";
            case "problemLevel": return "problem_level";
            case "modelNo": return "model_no";
            case "vinCode": return "vin_code";
            case "partName": return "part_name";
            case "partNo": return "part_no";
            case "askTime": return "ask_time";
            case "evaluationDate": return "evaluation_date";
            case "problemType": return "problem_type";
            case "tppCode": return "tpp_code";
            case "evaluatePerson": return "evaluate_person";
            case "proPropertyTwo": return "pro_property_two";
            case "proProperty": return "pro_property";
            case "taskId": return "task_id";
            case "id": return "id";
            case "psqcCbyy": return "psqc_cbyy";
            case "implementDeptIdTem": return "implement_dept_id_tem";
            case "solutionAdoptTimeTem": return "solution_adopt_time_tem";
            case "solutionDescTem": return "solution_desc_tem";
            case "problemCode": return "problem_code";
            case "resposiblePersonTem": return "resposible_person_tem";
            default: return null;
        }
    }

    /**
     * 原始数据库列名转换为java字段名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>image_names -> imageNames</li>
     * <li>image_ids -> imageIds</li>
     * <li>processcomments -> processcomments</li>
     * <li>startuser -> startuser</li>
     * <li>psqc_xslc -> psqcXslc</li>
     * <li>status -> status</li>
     * <li>resposible_person -> resposiblePerson</li>
     * <li>implement_dept_id -> implementDeptId</li>
     * <li>solution_adopt_time -> solutionAdoptTime</li>
     * <li>solution_desc -> solutionDesc</li>
     * <li>pro_solution -> proSolution</li>
     * <li>kkx_gzms -> kkxGzms</li>
     * <li>kkx_cxgzlc -> kkxCxgzlc</li>
     * <li>kkx_dqsylk -> kkxDqsylk</li>
     * <li>kkx_dqsyzzl -> kkxDqsyzzl</li>
     * <li>kkx_syzlc -> kkxSyzlc</li>
     * <li>psqc_jcr -> psqcJcr</li>
     * <li>psqc_fstj -> psqcFstj</li>
     * <li>psqc_zml -> psqcZml</li>
     * <li>psqc_hd -> psqcHd</li>
     * <li>psqc_hw -> psqcHw</li>
     * <li>psqc_hs -> psqcHs</li>
     * <li>distinguish -> distinguish</li>
     * <li>fault_mileage -> faultMileage</li>
     * <li>pro_title -> proTitle</li>
     * <li>main_user -> mainUser</li>
     * <li>main_dept -> mainDept</li>
     * <li>problem_level -> problemLevel</li>
     * <li>model_no -> modelNo</li>
     * <li>vin_code -> vinCode</li>
     * <li>part_name -> partName</li>
     * <li>part_no -> partNo</li>
     * <li>ask_time -> askTime</li>
     * <li>evaluation_date -> evaluationDate</li>
     * <li>problem_type -> problemType</li>
     * <li>tpp_code -> tppCode</li>
     * <li>evaluate_person -> evaluatePerson</li>
     * <li>pro_property_two -> proPropertyTwo</li>
     * <li>pro_property -> proProperty</li>
     * <li>task_id -> taskId</li>
     * <li>id -> id</li>
     * <li>psqc_cbyy -> psqcCbyy</li>
     * <li>implement_dept_id_tem -> implementDeptIdTem</li>
     * <li>solution_adopt_time_tem -> solutionAdoptTimeTem</li>
     * <li>solution_desc_tem -> solutionDescTem</li>
     * <li>problem_code -> problemCode</li>
     * <li>resposible_person_tem -> resposiblePersonTem</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) { return null; }
        switch (columnName) {
            case "image_names": return "imageNames";
            case "image_ids": return "imageIds";
            case "processcomments": return "processcomments";
            case "startuser": return "startuser";
            case "psqc_xslc": return "psqcXslc";
            case "status": return "status";
            case "resposible_person": return "resposiblePerson";
            case "implement_dept_id": return "implementDeptId";
            case "solution_adopt_time": return "solutionAdoptTime";
            case "solution_desc": return "solutionDesc";
            case "pro_solution": return "proSolution";
            case "kkx_gzms": return "kkxGzms";
            case "kkx_cxgzlc": return "kkxCxgzlc";
            case "kkx_dqsylk": return "kkxDqsylk";
            case "kkx_dqsyzzl": return "kkxDqsyzzl";
            case "kkx_syzlc": return "kkxSyzlc";
            case "psqc_jcr": return "psqcJcr";
            case "psqc_fstj": return "psqcFstj";
            case "psqc_zml": return "psqcZml";
            case "psqc_hd": return "psqcHd";
            case "psqc_hw": return "psqcHw";
            case "psqc_hs": return "psqcHs";
            case "distinguish": return "distinguish";
            case "fault_mileage": return "faultMileage";
            case "pro_title": return "proTitle";
            case "main_user": return "mainUser";
            case "main_dept": return "mainDept";
            case "problem_level": return "problemLevel";
            case "model_no": return "modelNo";
            case "vin_code": return "vinCode";
            case "part_name": return "partName";
            case "part_no": return "partNo";
            case "ask_time": return "askTime";
            case "evaluation_date": return "evaluationDate";
            case "problem_type": return "problemType";
            case "tpp_code": return "tppCode";
            case "evaluate_person": return "evaluatePerson";
            case "pro_property_two": return "proPropertyTwo";
            case "pro_property": return "proProperty";
            case "task_id": return "taskId";
            case "id": return "id";
            case "psqc_cbyy": return "psqcCbyy";
            case "implement_dept_id_tem": return "implementDeptIdTem";
            case "solution_adopt_time_tem": return "solutionAdoptTimeTem";
            case "solution_desc_tem": return "solutionDescTem";
            case "problem_code": return "problemCode";
            case "resposible_person_tem": return "resposiblePersonTem";
            default: return null;
        }
    }
    
    public String getImageNames() {
        return this.imageNames;
    }

    public void setImageNames(String imageNames) {
        this.imageNames = imageNames;
    }

    public String getImageIds() {
        return this.imageIds;
    }

    public void setImageIds(String imageIds) {
        this.imageIds = imageIds;
    }

    public String getProcesscomments() {
        return this.processcomments;
    }

    public void setProcesscomments(String processcomments) {
        this.processcomments = processcomments;
    }

    public String getStartuser() {
        return this.startuser;
    }

    public void setStartuser(String startuser) {
        this.startuser = startuser;
    }

    public String getPsqcXslc() {
        return this.psqcXslc;
    }

    public void setPsqcXslc(String psqcXslc) {
        this.psqcXslc = psqcXslc;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResposiblePerson() {
        return this.resposiblePerson;
    }

    public void setResposiblePerson(String resposiblePerson) {
        this.resposiblePerson = resposiblePerson;
    }

    public Long getImplementDeptId() {
        return this.implementDeptId;
    }

    public void setImplementDeptId(Long implementDeptId) {
        this.implementDeptId = implementDeptId;
    }

    public String getSolutionAdoptTime() {
        return this.solutionAdoptTime;
    }

    public void setSolutionAdoptTime(String solutionAdoptTime) {
        this.solutionAdoptTime = solutionAdoptTime;
    }

    public String getSolutionDesc() {
        return this.solutionDesc;
    }

    public void setSolutionDesc(String solutionDesc) {
        this.solutionDesc = solutionDesc;
    }

    public String getProSolution() {
        return this.proSolution;
    }

    public void setProSolution(String proSolution) {
        this.proSolution = proSolution;
    }

    public String getKkxGzms() {
        return this.kkxGzms;
    }

    public void setKkxGzms(String kkxGzms) {
        this.kkxGzms = kkxGzms;
    }

    public String getKkxCxgzlc() {
        return this.kkxCxgzlc;
    }

    public void setKkxCxgzlc(String kkxCxgzlc) {
        this.kkxCxgzlc = kkxCxgzlc;
    }

    public String getKkxDqsylk() {
        return this.kkxDqsylk;
    }

    public void setKkxDqsylk(String kkxDqsylk) {
        this.kkxDqsylk = kkxDqsylk;
    }

    public String getKkxDqsyzzl() {
        return this.kkxDqsyzzl;
    }

    public void setKkxDqsyzzl(String kkxDqsyzzl) {
        this.kkxDqsyzzl = kkxDqsyzzl;
    }

    public String getKkxSyzlc() {
        return this.kkxSyzlc;
    }

    public void setKkxSyzlc(String kkxSyzlc) {
        this.kkxSyzlc = kkxSyzlc;
    }

    public String getPsqcJcr() {
        return this.psqcJcr;
    }

    public void setPsqcJcr(String psqcJcr) {
        this.psqcJcr = psqcJcr;
    }

    public String getPsqcFstj() {
        return this.psqcFstj;
    }

    public void setPsqcFstj(String psqcFstj) {
        this.psqcFstj = psqcFstj;
    }

    public String getPsqcZml() {
        return this.psqcZml;
    }

    public void setPsqcZml(String psqcZml) {
        this.psqcZml = psqcZml;
    }

    public String getPsqcHd() {
        return this.psqcHd;
    }

    public void setPsqcHd(String psqcHd) {
        this.psqcHd = psqcHd;
    }

    public String getPsqcHw() {
        return this.psqcHw;
    }

    public void setPsqcHw(String psqcHw) {
        this.psqcHw = psqcHw;
    }

    public String getPsqcHs() {
        return this.psqcHs;
    }

    public void setPsqcHs(String psqcHs) {
        this.psqcHs = psqcHs;
    }

    public String getDistinguish() {
        return this.distinguish;
    }

    public void setDistinguish(String distinguish) {
        this.distinguish = distinguish;
    }

    public String getFaultMileage() {
        return this.faultMileage;
    }

    public void setFaultMileage(String faultMileage) {
        this.faultMileage = faultMileage;
    }

    public String getProTitle() {
        return this.proTitle;
    }

    public void setProTitle(String proTitle) {
        this.proTitle = proTitle;
    }

    public String getMainUser() {
        return this.mainUser;
    }

    public void setMainUser(String mainUser) {
        this.mainUser = mainUser;
    }


    public String getMainDept() {
		return mainDept;
	}

	public void setMainDept(String mainDept) {
		this.mainDept = mainDept;
	}

	public String getProblemLevel() {
        return this.problemLevel;
    }

    public void setProblemLevel(String problemLevel) {
        this.problemLevel = problemLevel;
    }

    public String getModelNo() {
        return this.modelNo;
    }

    public void setModelNo(String modelNo) {
        this.modelNo = modelNo;
    }

    public String getVinCode() {
        return this.vinCode;
    }

    public void setVinCode(String vinCode) {
        this.vinCode = vinCode;
    }

    public String getPartName() {
        return this.partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getPartNo() {
        return this.partNo;
    }

    public void setPartNo(String partNo) {
        this.partNo = partNo;
    }

    public String getAskTime() {
        return this.askTime;
    }

    public void setAskTime(String askTime) {
        this.askTime = askTime;
    }

    public String getEvaluationDate() {
        return this.evaluationDate;
    }

    public void setEvaluationDate(String evaluationDate) {
        this.evaluationDate = evaluationDate;
    }

    public String getProblemType() {
        return this.problemType;
    }

    public void setProblemType(String problemType) {
        this.problemType = problemType;
    }

    public String getTppCode() {
        return this.tppCode;
    }

    public void setTppCode(String tppCode) {
        this.tppCode = tppCode;
    }

    public String getEvaluatePerson() {
        return this.evaluatePerson;
    }

    public void setEvaluatePerson(String evaluatePerson) {
        this.evaluatePerson = evaluatePerson;
    }

    public String getProPropertyTwo() {
        return this.proPropertyTwo;
    }

    public void setProPropertyTwo(String proPropertyTwo) {
        this.proPropertyTwo = proPropertyTwo;
    }

    public String getProProperty() {
        return this.proProperty;
    }

    public void setProProperty(String proProperty) {
        this.proProperty = proProperty;
    }

    public String getTaskId() {
        return this.taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPsqcCbyy() {
        return this.psqcCbyy;
    }

    public void setPsqcCbyy(String psqcCbyy) {
        this.psqcCbyy = psqcCbyy;
    }

    public Long getImplementDeptIdTem() {
        return this.implementDeptIdTem;
    }

    public void setImplementDeptIdTem(Long implementDeptIdTem) {
        this.implementDeptIdTem = implementDeptIdTem;
    }

    public String getSolutionAdoptTimeTem() {
        return this.solutionAdoptTimeTem;
    }

    public void setSolutionAdoptTimeTem(String solutionAdoptTimeTem) {
        this.solutionAdoptTimeTem = solutionAdoptTimeTem;
    }

    public String getSolutionDescTem() {
        return this.solutionDescTem;
    }

    public void setSolutionDescTem(String solutionDescTem) {
        this.solutionDescTem = solutionDescTem;
    }

    public String getProblemCode() {
        return this.problemCode;
    }

    public void setProblemCode(String problemCode) {
        this.problemCode = problemCode;
    }

    public String getResposiblePersonTem() {
        return this.resposiblePersonTem;
    }

    public void setResposiblePersonTem(String resposiblePersonTem) {
        this.resposiblePersonTem = resposiblePersonTem;
    }

	public String getProQxxpd() {
		return proQxxpd;
	}

	public void setProQxxpd(String proQxxpd) {
		this.proQxxpd = proQxxpd;
	}

	public String getResposiblePersonName() {
		return resposiblePersonName;
	}

	public void setResposiblePersonName(String resposiblePersonName) {
		this.resposiblePersonName = resposiblePersonName;
	}

	public Long getImplementDeptName() {
		return implementDeptName;
	}

	public void setImplementDeptName(Long implementDeptName) {
		this.implementDeptName = implementDeptName;
	}

	public Long getImplementDeptNameTem() {
		return implementDeptNameTem;
	}

	public void setImplementDeptNameTem(Long implementDeptNameTem) {
		this.implementDeptNameTem = implementDeptNameTem;
	}

	public String getResposiblePersonNameTem() {
		return resposiblePersonNameTem;
	}

	public void setResposiblePersonNameTem(String resposiblePersonNameTem) {
		this.resposiblePersonNameTem = resposiblePersonNameTem;
	}

	public String getXcclgzPerson() {
		return xcclgzPerson;
	}

	public void setXcclgzPerson(String xcclgzPerson) {
		this.xcclgzPerson = xcclgzPerson;
	}

    
    
}
