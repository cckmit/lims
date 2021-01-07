package com.adc.da.pc_announcement.VO;

import com.adc.da.pc_announcement.entity.AnnPlanEO;

import java.util.ArrayList;
import java.util.List;

public class AnnPlanSaveVO {
    private AnnPlanEO annPlanEO;
    private List<AnnPlanProjectVO> annPlanProjectVOList = new ArrayList<>();

    public AnnPlanEO getAnnPlanEO() {
        return annPlanEO;
    }

    public void setAnnPlanEO(AnnPlanEO annPlanEO) {
        this.annPlanEO = annPlanEO;
    }

    public List<AnnPlanProjectVO> getAnnPlanProjectVOList() {
        return annPlanProjectVOList;
    }

    public void setAnnPlanProjectVOList(List<AnnPlanProjectVO> annPlanProjectVOList) {
        this.annPlanProjectVOList = annPlanProjectVOList;
    }
}
