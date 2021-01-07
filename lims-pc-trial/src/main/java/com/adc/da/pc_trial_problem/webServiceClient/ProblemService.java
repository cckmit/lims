
package com.adc.da.pc_trial_problem.webServiceClient;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "ProblemService", targetNamespace = "http://problem.ws.tpm.dflzm.com")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ProblemService {


    /**
     * 
     * @param paramJson
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(name = "reviewProblemByZGCheckReturn", targetNamespace = "http://problem.ws.tpm.dflzm.com")
    @RequestWrapper(localName = "reviewProblemByZGCheck", targetNamespace = "http://problem.ws.tpm.dflzm.com", className = "com.adc.da.pc_trial_problem.webServiceClient.ReviewProblemByZGCheck")
    @ResponseWrapper(localName = "reviewProblemByZGCheckResponse", targetNamespace = "http://problem.ws.tpm.dflzm.com", className = "com.adc.da.pc_trial_problem.webServiceClient.ReviewProblemByZGCheckResponse")
    public String reviewProblemByZGCheck(
        @WebParam(name = "paramJson", targetNamespace = "http://problem.ws.tpm.dflzm.com")
        String paramJson);

    /**
     * 
     * @param paramJson
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(name = "approvalCloseProblemReturn", targetNamespace = "http://problem.ws.tpm.dflzm.com")
    @RequestWrapper(localName = "approvalCloseProblem", targetNamespace = "http://problem.ws.tpm.dflzm.com", className = "com.adc.da.pc_trial_problem.webServiceClient.ApprovalCloseProblem")
    @ResponseWrapper(localName = "approvalCloseProblemResponse", targetNamespace = "http://problem.ws.tpm.dflzm.com", className = "com.adc.da.pc_trial_problem.webServiceClient.ApprovalCloseProblemResponse")
    public String approvalCloseProblem(
        @WebParam(name = "paramJson", targetNamespace = "http://problem.ws.tpm.dflzm.com")
        String paramJson);

    /**
     * 
     * @param paramJson
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(name = "queryDownDeptUsersReturn", targetNamespace = "http://problem.ws.tpm.dflzm.com")
    @RequestWrapper(localName = "queryDownDeptUsers", targetNamespace = "http://problem.ws.tpm.dflzm.com", className = "com.adc.da.pc_trial_problem.webServiceClient.QueryDownDeptUsers")
    @ResponseWrapper(localName = "queryDownDeptUsersResponse", targetNamespace = "http://problem.ws.tpm.dflzm.com", className = "com.adc.da.pc_trial_problem.webServiceClient.QueryDownDeptUsersResponse")
    public String queryDownDeptUsers(
        @WebParam(name = "paramJson", targetNamespace = "http://problem.ws.tpm.dflzm.com")
        String paramJson);

    /**
     * 
     * @param paramJson
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(name = "applyCloseProblemReturn", targetNamespace = "http://problem.ws.tpm.dflzm.com")
    @RequestWrapper(localName = "applyCloseProblem", targetNamespace = "http://problem.ws.tpm.dflzm.com", className = "com.adc.da.pc_trial_problem.webServiceClient.ApplyCloseProblem")
    @ResponseWrapper(localName = "applyCloseProblemResponse", targetNamespace = "http://problem.ws.tpm.dflzm.com", className = "com.adc.da.pc_trial_problem.webServiceClient.ApplyCloseProblemResponse")
    public String applyCloseProblem(
        @WebParam(name = "paramJson", targetNamespace = "http://problem.ws.tpm.dflzm.com")
        String paramJson);

    /**
     * 
     * @param setProOperJson
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(name = "setProblemOperationReturn", targetNamespace = "http://problem.ws.tpm.dflzm.com")
    @RequestWrapper(localName = "setProblemOperation", targetNamespace = "http://problem.ws.tpm.dflzm.com", className = "com.adc.da.pc_trial_problem.webServiceClient.SetProblemOperation")
    @ResponseWrapper(localName = "setProblemOperationResponse", targetNamespace = "http://problem.ws.tpm.dflzm.com", className = "com.adc.da.pc_trial_problem.webServiceClient.SetProblemOperationResponse")
    public String setProblemOperation(
        @WebParam(name = "setProOperJson", targetNamespace = "http://problem.ws.tpm.dflzm.com")
        String setProOperJson);

    /**
     * 
     * @param paramJson
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(name = "returnProblemReturn", targetNamespace = "http://problem.ws.tpm.dflzm.com")
    @RequestWrapper(localName = "returnProblem", targetNamespace = "http://problem.ws.tpm.dflzm.com", className = "com.adc.da.pc_trial_problem.webServiceClient.ReturnProblem")
    @ResponseWrapper(localName = "returnProblemResponse", targetNamespace = "http://problem.ws.tpm.dflzm.com", className = "com.adc.da.pc_trial_problem.webServiceClient.ReturnProblemResponse")
    public String returnProblem(
        @WebParam(name = "paramJson", targetNamespace = "http://problem.ws.tpm.dflzm.com")
        String paramJson);

    /**
     * 
     * @param paramJson
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(name = "startProblemReturn", targetNamespace = "http://problem.ws.tpm.dflzm.com")
    @RequestWrapper(localName = "startProblem", targetNamespace = "http://problem.ws.tpm.dflzm.com", className = "com.adc.da.pc_trial_problem.webServiceClient.StartProblem")
    @ResponseWrapper(localName = "startProblemResponse", targetNamespace = "http://problem.ws.tpm.dflzm.com", className = "com.adc.da.pc_trial_problem.webServiceClient.StartProblemResponse")
    public String startProblem(
        @WebParam(name = "paramJson", targetNamespace = "http://problem.ws.tpm.dflzm.com")
        String paramJson);

    /**
     * 
     * @param paramJson
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(name = "queryAllTopDeptReturn", targetNamespace = "http://problem.ws.tpm.dflzm.com")
    @RequestWrapper(localName = "queryAllTopDept", targetNamespace = "http://problem.ws.tpm.dflzm.com", className = "com.adc.da.pc_trial_problem.webServiceClient.QueryAllTopDept")
    @ResponseWrapper(localName = "queryAllTopDeptResponse", targetNamespace = "http://problem.ws.tpm.dflzm.com", className = "com.adc.da.pc_trial_problem.webServiceClient.QueryAllTopDeptResponse")
    public String queryAllTopDept(
        @WebParam(name = "paramJson", targetNamespace = "http://problem.ws.tpm.dflzm.com")
        String paramJson);

    /**
     * 
     * @param paramJson
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(name = "updateProblemReturn", targetNamespace = "http://problem.ws.tpm.dflzm.com")
    @RequestWrapper(localName = "updateProblem", targetNamespace = "http://problem.ws.tpm.dflzm.com", className = "com.adc.da.pc_trial_problem.webServiceClient.UpdateProblem")
    @ResponseWrapper(localName = "updateProblemResponse", targetNamespace = "http://problem.ws.tpm.dflzm.com", className = "com.adc.da.pc_trial_problem.webServiceClient.UpdateProblemResponse")
    public String updateProblem(
        @WebParam(name = "paramJson", targetNamespace = "http://problem.ws.tpm.dflzm.com")
        String paramJson);

    /**
     * 
     * @param problemInfoJson
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(name = "queryProblemInfoReturn", targetNamespace = "http://problem.ws.tpm.dflzm.com")
    @RequestWrapper(localName = "queryProblemInfo", targetNamespace = "http://problem.ws.tpm.dflzm.com", className = "com.adc.da.pc_trial_problem.webServiceClient.QueryProblemInfo")
    @ResponseWrapper(localName = "queryProblemInfoResponse", targetNamespace = "http://problem.ws.tpm.dflzm.com", className = "com.adc.da.pc_trial_problem.webServiceClient.QueryProblemInfoResponse")
    public String queryProblemInfo(
        @WebParam(name = "ProblemInfoJson", targetNamespace = "http://problem.ws.tpm.dflzm.com")
        String problemInfoJson);

    /**
     * 
     * @param problemTaskJson
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(name = "getProblemTaskReturn", targetNamespace = "http://problem.ws.tpm.dflzm.com")
    @RequestWrapper(localName = "getProblemTask", targetNamespace = "http://problem.ws.tpm.dflzm.com", className = "com.adc.da.pc_trial_problem.webServiceClient.GetProblemTask")
    @ResponseWrapper(localName = "getProblemTaskResponse", targetNamespace = "http://problem.ws.tpm.dflzm.com", className = "com.adc.da.pc_trial_problem.webServiceClient.GetProblemTaskResponse")
    public String getProblemTask(
        @WebParam(name = "ProblemTaskJson", targetNamespace = "http://problem.ws.tpm.dflzm.com")
        String problemTaskJson);

    /**
     * 
     * @param paramJson
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(name = "approvalProblemReturn", targetNamespace = "http://problem.ws.tpm.dflzm.com")
    @RequestWrapper(localName = "approvalProblem", targetNamespace = "http://problem.ws.tpm.dflzm.com", className = "com.adc.da.pc_trial_problem.webServiceClient.ApprovalProblem")
    @ResponseWrapper(localName = "approvalProblemResponse", targetNamespace = "http://problem.ws.tpm.dflzm.com", className = "com.adc.da.pc_trial_problem.webServiceClient.ApprovalProblemResponse")
    public String approvalProblem(
        @WebParam(name = "paramJson", targetNamespace = "http://problem.ws.tpm.dflzm.com")
        String paramJson);

    /**
     * 
     * @param paramJson
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(name = "submitSolutionReturn", targetNamespace = "http://problem.ws.tpm.dflzm.com")
    @RequestWrapper(localName = "submitSolution", targetNamespace = "http://problem.ws.tpm.dflzm.com", className = "com.adc.da.pc_trial_problem.webServiceClient.SubmitSolution")
    @ResponseWrapper(localName = "submitSolutionResponse", targetNamespace = "http://problem.ws.tpm.dflzm.com", className = "com.adc.da.pc_trial_problem.webServiceClient.SubmitSolutionResponse")
    public String submitSolution(
        @WebParam(name = "paramJson", targetNamespace = "http://problem.ws.tpm.dflzm.com")
        String paramJson);

    /**
     * 
     * @param paramJson
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(name = "getOrgByParentIdReturn", targetNamespace = "http://problem.ws.tpm.dflzm.com")
    @RequestWrapper(localName = "getOrgByParentId", targetNamespace = "http://problem.ws.tpm.dflzm.com", className = "com.adc.da.pc_trial_problem.webServiceClient.GetOrgByParentId")
    @ResponseWrapper(localName = "getOrgByParentIdResponse", targetNamespace = "http://problem.ws.tpm.dflzm.com", className = "com.adc.da.pc_trial_problem.webServiceClient.GetOrgByParentIdResponse")
    public String getOrgByParentId(
        @WebParam(name = "paramJson", targetNamespace = "http://problem.ws.tpm.dflzm.com")
        String paramJson);

}
