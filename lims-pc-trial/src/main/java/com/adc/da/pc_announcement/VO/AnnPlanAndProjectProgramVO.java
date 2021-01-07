package com.adc.da.pc_announcement.VO;

import com.adc.da.pc_announcement.entity.AnnPlanEO;
import com.adc.da.pc_announcement.entity.AnnPlanProgramEO;
import com.adc.da.pc_announcement.entity.AnnPlanProjectEO;

import java.util.ArrayList;
import java.util.List;

public class AnnPlanAndProjectProgramVO {
    private AnnPlanEO annPlanEO;
    private List<AnnPlanProjectEO> annPlanProjectEOList = new ArrayList<>();
    private List<AnnPlanProgramEO> annPlanProgramEOList = new ArrayList<>();
    public AnnPlanEO getAnnPlanEO() {
        return annPlanEO;
    }

    public void setAnnPlanEO(AnnPlanEO annPlanEO) {
        this.annPlanEO = annPlanEO;
    }

    public List<AnnPlanProjectEO> getAnnPlanProjectEOList() {
        return annPlanProjectEOList;
    }

    public void setAnnPlanProjectEOList(List<AnnPlanProjectEO> annPlanProjectEOList) {
        this.annPlanProjectEOList = annPlanProjectEOList;
    }

    public List<AnnPlanProgramEO> getAnnPlanProgramEOList() {
        return annPlanProgramEOList;
    }

    public void setAnnPlanProgramEOList(List<AnnPlanProgramEO> annPlanProgramEOList) {
        this.annPlanProgramEOList = annPlanProgramEOList;
    }
}
