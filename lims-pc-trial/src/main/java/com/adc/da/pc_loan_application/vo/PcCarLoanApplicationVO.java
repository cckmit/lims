package com.adc.da.pc_loan_application.vo;

import com.adc.da.pc_loan_application.entity.PcCarLoanApplicationEO;
import com.adc.da.pc_loan_application.entity.PcCarLoanInformationEO;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class PcCarLoanApplicationVO {
    private PcCarLoanApplicationEO pcCarLoanApplicationEO;
    private List<PcCarLoanInformationEO> list;

    public PcCarLoanApplicationEO getPcCarLoanApplicationEO() {
        return pcCarLoanApplicationEO;
    }

    public void setPcCarLoanApplicationEO(PcCarLoanApplicationEO pcCarLoanApplicationEO) {
        this.pcCarLoanApplicationEO = pcCarLoanApplicationEO;
    }

    public List<PcCarLoanInformationEO> getList() {
        return list;
    }

    public void setList(List<PcCarLoanInformationEO> list) {
        this.list = list;
    }
}
